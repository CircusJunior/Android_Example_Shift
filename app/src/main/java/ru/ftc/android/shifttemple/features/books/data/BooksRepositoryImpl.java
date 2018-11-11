package ru.ftc.android.shifttemple.features.books.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Book;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class BooksRepositoryImpl implements BooksRepository {

    private final BooksDataSource dataSource;

    public BooksRepositoryImpl(BooksDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadBooks(Carry<List<Book>> carry) {
        dataSource.getBooks(carry);
    }

    @Override
    public void loadBook(String id, Carry<Book> carry) {
        dataSource.getBook(id, carry);
    }

    @Override
    public void createBook(Book book, Carry<Book> carry) {
        dataSource.createBook(book, carry);
    }

    @Override
    public void deleteBook(String id, Carry<Success> carry) {
        dataSource.deleteBook(id, carry);
    }
}