package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Work;

public class WorkValidator {
    public static List<String> validate(Work w) {
        List<String> errors = new ArrayList<String>();




        String content_error = _validateContent(w.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }



    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "勤務内容を入力してください。";
            }

        return "";
    }
}