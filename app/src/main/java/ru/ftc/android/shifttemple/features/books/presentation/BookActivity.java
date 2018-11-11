package ru.ftc.android.shifttemple.features.books.presentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.books.domain.model.Book;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class BookActivity extends BaseActivity implements BookListView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button createBookButton;
    private BookAdapter adapter;

    private BookListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.books_progress);
        recyclerView = findViewById(R.id.books_recycle_view);
        createBookButton = findViewById(R.id.create_button);

        createBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateBookClicked();
            }
        });

        adapter = new BookAdapter(this, new BookAdapter.SelectBookListener() {
            @Override
            public void onBookSelect(Book book) {
                presenter.onBookSelected(book);
            }

            @Override
            public void onBookLongClick(Book book) {
                presenter.onBookLongClicked(book);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBookList(List<Book> list) {
        adapter.setBooks(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected MvpPresenter<BookListView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}
