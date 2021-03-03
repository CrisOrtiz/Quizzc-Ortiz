package org.fundacionjala.app.quizz.console;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fundacionjala.app.quizz.model.Question;
import org.fundacionjala.app.quizz.model.validator.DateValidator;
import org.fundacionjala.app.quizz.model.validator.MaxLengthValidator;
import org.fundacionjala.app.quizz.model.validator.MaxValidator;
import org.fundacionjala.app.quizz.model.validator.MinLengthValidator;
import org.fundacionjala.app.quizz.model.validator.MinValidator;
import org.fundacionjala.app.quizz.model.validator.RequiredValidator;
import org.fundacionjala.app.quizz.model.validator.UpperCaseValidator;
import org.fundacionjala.app.quizz.console.util.InputReader;

public class QuestionInputHandler {

	public Set<String> askQuestionValue(Question question) {
		System.out.println("Question: " + question.getTitle());
		return getAnswer(question);
	}

	private Set<String> getAnswer(Question question) {
		Set<String> answers = new HashSet<>();
		if (question.getType().getConfiguration().hasAdditionalData()) {
			answers.add(collectAnswerFromOptions(question));
		} else {
			System.out.println(question.getType().getName() + " value:");
			String value = InputReader.readLine();
			answers.add(value);
			
			List<String> errors = new ArrayList<>();
            MinLengthValidator minLengthValidator = new MinLengthValidator();
            MaxLengthValidator maxLengthValidator = new MaxLengthValidator();
            RequiredValidator rv = new RequiredValidator();
            DateValidator dateValidator = new DateValidator();
            MinValidator minValidator = new MinValidator();
            MaxValidator maxValidator = new MaxValidator();
            UpperCaseValidator upperCaseValidator = new UpperCaseValidator();
            
            for (int i = 0; i < question.getValidations().size(); i++) {
                switch (question.getValidations().get(i)) {
                    case REQUIRED:
                        rv.validate(value, "", errors);
                        System.out.println(errors);
                        break;
                    case DATE:
                        dateValidator.validate(value, null, errors);
                        System.out.println(errors);
                        break;
                    case MIN:
                        minValidator.validate(value, "10", errors);
                        System.out.println(errors);
                        break;
                    case MAX:
                        maxValidator.validate(value, "10", errors);
                        System.out.println(errors);
                        break;
                    case MIN_LENGTH:
                        minLengthValidator.validate(value, "2", errors);
                        System.out.println(errors);
                        break;
                    case MAX_LENGTH:
                        maxLengthValidator.validate(value, "2", errors);
                        System.out.println(errors);
                        break;
                    case UPPERCASE:
                        upperCaseValidator.validate(value, "2", errors);
                        System.out.println(errors);
                        break;
                }
            }	
		}

		return answers;
	}

	private String collectAnswerFromOptions(Question question) {
		String answer = null;

		while (true) {
			showOptions(question);
			char option = InputReader.readOption();
			if (option == '0') {
				break;
			}
		}

		return answer;
	}

	private void showOptions(Question question) {
		System.out.println("Select an option: ");
		for (int index = 0; index < question.getAdditionalData().size(); index++) {
			System.out.printf("%d. %s" + System.lineSeparator(), index + 1, question.getAdditionalData().get(index));
		}
		System.out.println("0. To Finish");
	}
}
