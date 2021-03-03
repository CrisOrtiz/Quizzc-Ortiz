package org.fundacionjala.app.quizz.console.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.fundacionjala.app.quizz.model.Quiz;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonReader;

public class JsonSave {
	
	    public Quiz readJsonFile() {
	        Gson gson = new Gson();
	        Quiz quiz = null;
	        try (JsonReader reader = new JsonReader(new FileReader("./myQuiz.json"))) {
	            quiz = gson.fromJson(reader, Quiz.class);
	        } catch (IOException exception) {
	            exception.printStackTrace();
	        }

	        return quiz;
	    }

	    public static void writeJsonFile(Quiz quiz) {
	        Gson gson = new Gson();
	        try (Writer writer = new FileWriter("./myQuiz.json")) {
	            gson.toJson(quiz, writer);
	        } catch (JsonIOException | IOException exception) {
	            exception.printStackTrace();
	        }
	    }

}
