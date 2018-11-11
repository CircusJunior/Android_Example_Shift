package ru.ftc.android.shifttemple.features.books.presentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.BooksInteractor;
import ru.ftc.android.shifttemple.features.books.domain.model.Book;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created: samokryl
 * Date: 04.07.18
 * Time: 10:27
 */

@RunWith(MockitoJUnitRunner.class)
public class BookListPresenterTest {

    private BookListPresenter presenter;

    private final BookListView view = mock(BookListView.class);
    private final BooksInteractor interactor = mock(BooksInteractor.class);

    private final List<Book> list = mock(List.class);

    @Before
    public void setUp() throws Exception {
        presenter = new BookListPresenter(interactor);

        doAnswer(createAnswer(list)).when(interactor).loadBooks(any(Carry.class));

        presenter.attachView(view);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(view, interactor);
    }

    @Test
    public void onViewReadySuccess() {
        checkLoadBooks();
    }

    @Test
    public void onViewReadyError() {
        reset(view);
        reset(interactor);

        final BookListPresenter errorPresenter = new BookListPresenter(interactor);

        final String message = "message";
        doAnswer(createAnswer(new Exception(message))).when(interactor).loadBooks(any(Carry.class));

        errorPresenter.attachView(view);

        verify(view).showProgress();
        verify(interactor).loadBooks(any(Carry.class));

        verify(view).hideProgress();
        verify(view).showError(message);
    }

    @Test
    public void onBookSelected() {
        reset(view);
        reset(interactor);

    }

    @Test
    public void onBookLongClicked() {
        reset(view);
        reset(interactor);

        final String bookId = "123";
        final Book book = mock(Book.class);

        when(book.getId()).thenReturn(bookId);
        doAnswer(createAnswer(new Success(), 1)).when(interactor).deleteBook(any(String.class), any(Carry.class));
        doAnswer(createAnswer(list)).when(interactor).loadBooks(any(Carry.class));

        presenter.onBookLongClicked(book);

        verify(interactor).deleteBook(any(String.class), any(Carry.class));
        verify(interactor).loadBooks(any(Carry.class));

        verify(view, times(2)).showProgress();
        verify(view).hideProgress();
        verify(view).showBookList(list);

    }

    @Test
    public void onBookLongClickedFail() {
        reset(view);
        reset(interactor);

        final String bookId = "123";
        final Book book = mock(Book.class);
        final String message = "message";

        when(book.getId()).thenReturn(bookId);
        doAnswer(createAnswer(new Exception(message), 1)).when(interactor).deleteBook(any(String.class), any(Carry.class));

        presenter.onBookLongClicked(book);

        verify(interactor).deleteBook(any(String.class), any(Carry.class));
        verify(view).showProgress();

        verify(view).hideProgress();
        verify(view).showError(message);

    }

    @Test
    public void onCreateBookClicked() {
        reset(view);
        reset(interactor);

        final String bookId = "123";
        final Book book = mock(Book.class);

        when(book.getId()).thenReturn(bookId);
        doAnswer(createAnswer(book, 1)).when(interactor).loadBook(any(String.class), any(Carry.class));

        presenter.onBookSelected(book);

        verify(interactor).loadBook(any(String.class), any(Carry.class));
        verify(view).showProgress();
        verify(view).hideProgress();

    }

    private void checkLoadBooks() {
        verify(view).showProgress();
        verify(interactor).loadBooks(any(Carry.class));

        verify(view).hideProgress();
        verify(view).showBookList(list);
    }

    private Answer<Object> createAnswer(final Object result) {
        return createAnswer(result, 0);
    }

    private Answer<Object> createAnswer(final Object result, final int carryPosition) {
        return new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final Object[] arguments = invocation.getArguments();
                final Carry carry = (Carry) arguments[carryPosition];

                if (result instanceof Throwable) {
                    carry.onFailure((Throwable) result);
                } else {
                    carry.onSuccess(result);
                }

                return invocation;
            }
        };
    }
}