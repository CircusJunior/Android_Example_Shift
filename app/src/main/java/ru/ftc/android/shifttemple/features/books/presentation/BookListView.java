package ru.ftc.android.shifttemple.features.books.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.books.domain.model.Book;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:22
 */

interface BookListView extends MvpView {

    void showProgress();

    void hideProgress();

    void showBookList(List<Book> list);

    void showError(String message);

}
