package ru.ftc.android.shifttemple.features.books.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.data.BooksRepository;
import ru.ftc.android.shifttemple.features.books.domain.model.Book;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class BooksInteractorImpl implements BooksInteractor {

    private final BooksRepository repository;

    public BooksInteractorImpl(BooksRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadBooks(Carry<List<Book>> carry) {
        repository.loadBooks(carry);
    }

    @Override
    public void loadBook(String id, Carry<Book> carry) {
        repository.loadBook(id, carry);
    }

    @Override
    public void createBook(Book book, Carry<Book> carry) {
        repository.createBook(book, carry);
    }

    @Override
    public void deleteBook(String id, Carry<Success> carry) {
        repository.deleteBook(id, carry);
    }
}