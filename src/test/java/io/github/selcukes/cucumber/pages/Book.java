package io.github.selcukes.cucumber.pages;

public class Book {
    private final String bookName;
    public Book(String bookName) {
        this.bookName=bookName;
    }
    public String getBookName()
    {
        return bookName;
    }
}
