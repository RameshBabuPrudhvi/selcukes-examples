package io.github.selcukes.cucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DocStringType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;
import io.github.selcukes.cucumber.pages.Book;

public class BookStoreSteps {
    Logger logger = LoggerFactory.getLogger(BookStoreSteps.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @DocStringType
    public JsonNode json(String docString) throws JsonProcessingException {
        return objectMapper.readValue(docString, JsonNode.class);
    }

    @ParameterType(".*")
    public Book book(String bookName) {
        return new Book(bookName);
    }

    @Given("{book} is my favorite book")
    public void this_is_my_favorite_book(Book book) {
        logger.info(book::getBookName);
    }

    @Given("Books are defined by json")
    public void books_are_defined_by_json(JsonNode books) {
        logger.info(() -> String.valueOf(books.get("Name")));
        logger.info(() -> String.valueOf(books.get("Company")));
        logger.info(() -> String.valueOf(books.get("Location")));
        throw new RuntimeException("BookStoreException");
    }
}
