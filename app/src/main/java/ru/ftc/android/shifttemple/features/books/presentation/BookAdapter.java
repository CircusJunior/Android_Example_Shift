package ru.ftc.android.shifttemple.features.books.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.books.domain.model.Book;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:24
 */

final class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    private final List<Book> books = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectBookListener selectBookListener;

    BookAdapter(Context context, SelectBookListener selectBookListener) {
        inflater = LayoutInflater.from(context);
        this.selectBookListener = selectBookListener;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.book_item, parent, false);
        return new BookHolder(itemView, selectBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.bind(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> bookList) {
        books.clear();
        books.addAll(bookList);
        notifyDataSetChanged();
    }

    class BookHolder extends RecyclerView.ViewHolder {

        private final TextView bookNameView;
        private final TextView bookAuthorView;
        private final SelectBookListener selectBookListener;

        BookHolder(View view, SelectBookListener selectBookListener) {
            super(view);
            this.selectBookListener = selectBookListener;
            bookNameView = view.findViewById(R.id.book_item_name);
            bookAuthorView = view.findViewById(R.id.book_item_author);
        }

        void bind(final Book book) {
            bookNameView.setText(book.getName());
            bookAuthorView.setText(book.getAuthor());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectBookListener.onBookSelect(book);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectBookListener.onBookLongClick(book);
                    return true;
                }
            });

        }

    }

    interface SelectBookListener {

        void onBookSelect(Book book);

        void onBookLongClick(Book book);

    }

}
