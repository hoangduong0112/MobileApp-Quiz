package com.hd.quiz;

public class Question {
    private String QuestionName;
    private Boolean Answer;

    public Boolean getAnswer() {
        return Answer;
    }

    public String getQuestionName() {
        return QuestionName;
    }

    public void setAnswer(Boolean answer) {
        Answer = answer;
    }

    public void setQuestionName(String questionName) {
        QuestionName = questionName;
    }


    public Question(String name, Boolean answer){
        QuestionName = name;
        Answer = answer;
    }
}
