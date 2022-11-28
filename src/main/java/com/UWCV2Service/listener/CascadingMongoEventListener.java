package com.UWCV2Service.listener;

import com.UWCV2Service.anotation.CascadeSave;
import java.lang.reflect.Field;
import java.util.List;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mapping.MappingException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;

public class CascadingMongoEventListener
    extends AbstractMongoEventListener<Object> {

  @Autowired private MongoOperations mongoOperations;

  @Override
  public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
    final Object source = event.getSource();
    ReflectionUtils.doWithFields(source.getClass(),
                                 new FieldCallbackImplementation(source));
  }

  private void checkNSave(final Object fieldValue) {
    final DbRefFieldCallback callback = new DbRefFieldCallback();
    ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
    if (!callback.isIdFound()) {
      throw new MappingException(
          "Oops, something went wrong. Child doesn't have @Id?");
    }
    mongoOperations.save(fieldValue);
  }

  private final class FieldCallbackImplementation
      implements ReflectionUtils.FieldCallback {
    private final Object source;

    private FieldCallbackImplementation(Object source) { this.source = source; }

    @Override
    public void doWith(final Field field)
        throws IllegalArgumentException, IllegalAccessException {
      ReflectionUtils.makeAccessible(field);

      if (field.isAnnotationPresent(DocumentReference.class) &&
          field.isAnnotationPresent(CascadeSave.class)) {
        final Object fieldValue = field.get(source);

        if (fieldValue instanceof List<?>) {
          for (final Object item : (List<?>)fieldValue) {
            checkNSave(item);
          }
        } else {
          checkNSave(fieldValue);
        }
      }
    }
  }

  private static class DbRefFieldCallback
      implements ReflectionUtils.FieldCallback {
    private boolean idFound;

    @Override
    public void doWith(final Field field)
        throws IllegalArgumentException, IllegalAccessException {
      ReflectionUtils.makeAccessible(field);
      if (field.isAnnotationPresent(Id.class)) {
        idFound = true;
      }
    }
    public boolean isIdFound() { return idFound; }
  }
}
