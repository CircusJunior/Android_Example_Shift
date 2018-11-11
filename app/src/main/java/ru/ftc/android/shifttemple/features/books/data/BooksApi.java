package ru.ftc.android.shifttemple.features.books.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.ftc.android.shifttemple.features.books.domain.model.Book;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.books.domain.model.Wrapper;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface BooksApi {

    @GET("books")
    Call<Wrapper<List<Book>>> getBookList();

    @GET("books/{id}")
    Call<Wrapper<Book>> getBook(@Path("id") String id);

    @POST("books")
    Call<Wrapper<Book>> createBook(@Body Book book);

    @DELETE("books/{id}")
    Call<Wrapper<Success>> deleteBook(@Path("id") String id);

}
