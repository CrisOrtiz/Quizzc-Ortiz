package org.fundacionjala.app.quizz.model;

import java.util.Arrays;
import java.util.Set;

public class Answer {
    private final Question question;
    private final Set<String> answers;

    public Answer(Question question, Set<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    @Override
    public String toString() {
    	String answer = Arrays.toString(answers.toArray());
    	answer = answer.substring(1, answer.length() - 1);
    	
        return question.getTitle() + " \n" + "Answer: " + answer + "\n";
    }
}
