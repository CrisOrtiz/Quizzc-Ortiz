package org.fundacionjala.app.quizz.model.validator;

import java.util.List;

public class UpperCaseValidator implements Validator {

    private static final String ERROR_MESSAGE = "Only uppercase admitted.";

    @Override
    public void validate(String value, String conditionValue, List<String> errors) { 	
    	if (!value.equals(value.toUpperCase())) {
            errors.add(ERROR_MESSAGE);
        }
    }
}
