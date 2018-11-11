package ru.ftc.android.shifttemple.features.books.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Book;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created: samokryl
 * Date: 04.07.18
 * Time: 10:15
 */

@RunWith(MockitoJUnitRunner.class)
public class BooksRepositoryImplTest {

    private BooksRepository repository;

    private final BooksDataSource dataSource = mock(BooksDataSource.class);

    @Before
    public void setUp() throws Exception {
        repository = new BooksRepositoryImpl(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(dataSource);
    }

    @Test
    public void loadBooks() {
        Carry<List<Book>> carry = mock(Carry.class);

        repository.loadBooks(carry);

        verify(dataSource).getBooks(carry);
    }

    @Test
    public void loadBook() {
        final String bookId = "123";
        Carry<Book> carry = mock(Carry.class);

        repository.loadBook(bookId, carry);

        verify(dataSource).getBook(bookId, carry);
    }

    @Test
    public void createBook() {
        final Book book = mock(Book.class);
        Carry<Book> carry = mock(Carry.class);

        repository.createBook(book, carry);

        verify(dataSource).createBook(book, carry);
    }

    @Test
    public void deleteBook() {
        final String bookId = "123";
        Carry<Success> carry = mock(Carry.class);

        repository.deleteBook(bookId, carry);

        verify(dataSource).deleteBook(bookId, carry);
    }
}