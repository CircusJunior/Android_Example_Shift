package ru.ftc.android.shifttemple.features.books.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Book;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:57
 */

public interface BooksDataSource {

    void getBooks(Carry<List<Book>> carry);

    void getBook(String id, Carry<Book> carry);

    void createBook(Book book, Carry<Book> carry);

    void deleteBook(String id, Carry<Success> carry);
}
