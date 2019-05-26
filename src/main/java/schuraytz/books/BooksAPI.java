package schuraytz.books;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface BooksAPI {

    //@GET("/books/v1/volumes?q=summer")
    @GET("/books/v1/volumes?")
    Observable<GoogleBooksResponse> items(@Query("q") String searchTerm);

  //  Observable<GoogleBooksResponse> items();
}