package ru.ftc.android.shifttemple.features.books.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Book;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;
import ru.ftc.android.shifttemple.network.DefaultCallback;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:57
 */

public final class BooksDataSourceImpl implements BooksDataSource {

    private final BooksApi booksApi;

    public BooksDataSourceImpl(BooksApi booksApi) {
        this.booksApi = booksApi;
    }

    @Override
    public void getBooks(final Carry<List<Book>> carry) {
        booksApi.getBookList().enqueue(new DefaultCallback(carry));
       // carry.onSuccess();
    }

    @Override
    public void getBook(String id, Carry<Book> carry) {
        booksApi.getBook(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createBook(Book book, Carry<Book> carry) {
        booksApi.createBook(book).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteBook(String id, Carry<Success> carry) {
        booksApi.deleteBook(id).enqueue(new DefaultCallback(carry));
    }
}