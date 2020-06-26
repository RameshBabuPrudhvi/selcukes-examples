package io.github.selcukes.cucumber.pages;

public class TestData {

    private String question;
    private String answer;

    public TestData(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestions() {
        return question;
    }

    public String getAnswers() {
        return answer;
    }

    public void setQuestions(String question) {
        this.question = question;
    }

    public void setAnswers(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format("Username = %s Password = %s", this.question, this.answer);
    }
}
