package ru.ftc.android.shifttemple.features.books.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.data.BooksRepository;
import ru.ftc.android.shifttemple.features.books.domain.model.Book;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created: samokryl
 * Date: 04.07.18
 * Time: 10:22
 */

@RunWith(MockitoJUnitRunner.class)
public class BooksInteractorImplTest {

    private BooksInteractor interactor;

    private final BooksRepository repository = mock(BooksRepository.class);

    @Before
    public void setUp() throws Exception {
        interactor = new BooksInteractorImpl(repository);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void loadBooks() {
        Carry<List<Book>> carry = mock(Carry.class);

        interactor.loadBooks(carry);

        verify(repository).loadBooks(carry);

    }

    @Test
    public void loadBook() {
        final String bookId = "123";
        Carry<Book> carry = mock(Carry.class);

        interactor.loadBook(bookId, carry);

        verify(repository).loadBook(bookId, carry);
    }

    @Test
    public void createBook() {
        final Book book = mock(Book.class);
        Carry<Book> carry = mock(Carry.class);

        interactor.createBook(book, carry);

        verify(repository).createBook(book, carry);
    }

    @Test
    public void deleteBook() {
        final String bookId = "123";
        Carry<Success> carry = mock(Carry.class);

        interactor.deleteBook(bookId, carry);

        verify(repository).deleteBook(bookId, carry);
    }
}