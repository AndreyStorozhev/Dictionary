package example.validator;

import example.entity.KeyDictionary;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return KeyDictionary.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "key", "dictionary.char.null");
        KeyDictionary key = (KeyDictionary) target;
        if (key.getFlag() == 0) {
            if (key.getKey().length() != 4)
                errors.rejectValue("key", "dictionary.char.length");
            else if (!key.getKey().matches("[A-z]+"))
                errors.rejectValue("key", "dictionary.char.math");
        }else if (key.getFlag() == 1) {
            if (key.getKey().length() != 5)
                errors.rejectValue("key", "dictionary.number.length");
            else if (!key.getKey().matches("[0-9]+"))
                errors.rejectValue("key", "dictionary.number.math");
        }
    }
}
