package com.veve.flowreader.dao;

import com.veve.flowreader.model.Book;

import java.util.List;

/**
 * Designed to implement persistance
 * Created by ddreval on 4/3/2018.
 */

public interface BookStorage {

    public List<BookRecord> getBooksList();

    public int addBook(BookRecord bookRecord);

    public void deleteBook(int bookId);

    public void updateBook(BookRecord bookRecord);
}
