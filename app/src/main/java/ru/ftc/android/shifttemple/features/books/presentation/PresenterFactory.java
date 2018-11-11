package ru.ftc.android.shifttemple.features.books.presentation;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.books.data.BooksApi;
import ru.ftc.android.shifttemple.features.books.data.BooksDataSource;
import ru.ftc.android.shifttemple.features.books.data.BooksDataSourceImpl;
import ru.ftc.android.shifttemple.features.books.data.BooksRepository;
import ru.ftc.android.shifttemple.features.books.data.BooksRepositoryImpl;
import ru.ftc.android.shifttemple.features.books.domain.BooksInteractor;
import ru.ftc.android.shifttemple.features.books.domain.BooksInteractorImpl;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 1:03
 */

final class PresenterFactory {

    static BookListPresenter createPresenter(Context context) {
        final BooksApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(BooksApi.class);

        final BooksDataSource dataSource = new BooksDataSourceImpl(api);
        final BooksRepository repository = new BooksRepositoryImpl(dataSource);
        final BooksInteractor interactor = new BooksInteractorImpl(repository);

        return new BookListPresenter(interactor);
    }

}