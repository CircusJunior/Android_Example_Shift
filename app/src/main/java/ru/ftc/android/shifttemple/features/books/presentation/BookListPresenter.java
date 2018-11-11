package ru.ftc.android.shifttemple.features.books.presentation;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.books.domain.BooksInteractor;
import ru.ftc.android.shifttemple.features.books.domain.model.Book;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:43
 */

final class BookListPresenter extends MvpPresenter<BookListView> {

    private final BooksInteractor interactor;

    BookListPresenter(BooksInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadBooks();
    }

    private void loadBooks() {
        view.showProgress();
        interactor.loadBooks(new Carry<List<Book>>() {

            @Override
            public void onSuccess(List<Book> result) {
                view.showBookList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });
    }

    void onBookSelected(Book book) {
        view.showProgress();
        interactor.loadBook(book.getId(), new Carry<Book>() {

            @Override
            public void onSuccess(Book result) {
                view.hideProgress();
                // do something
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });
    }

    void onBookLongClicked(Book book) {
        view.showProgress();
        interactor.deleteBook(book.getId(), new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                loadBooks();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    private final AtomicInteger atomicInteger = new AtomicInteger();

    public void onCreateBookClicked() {
        int id = atomicInteger.incrementAndGet();
        String name = "Name_" + id;
        String author = "Author_" + id;
        int pages = 7 * id;

        Book book = new Book(name, author, String.valueOf(pages));
        interactor.createBook(book, new Carry<Book>() {
            @Override
            public void onSuccess(Book result) {
                loadBooks();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

}