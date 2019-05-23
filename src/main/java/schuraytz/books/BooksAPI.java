package schuraytz.books;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface BooksAPI {

    //@GET("/books/v1/volumes?q=summer")
    @GET("/books/v1/volumes?q=mystery")
    //( @Query String searchTerm)
    //Observable<Object> items();
    Observable<GoogleBooksResponse> items();
    //ArrayList<Object> items = new ArrayList<Object>();

}