package schuraytz.books;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYBooksAPI {

    //@GET("/books/v1/volumes?q=summer")
    @GET("/svc/books/v3/lists/names.json?api-key=XTpGtopGJeQYcYpcGeZybQzvHBeriXwm")
    //Observable<GoogleBooksResponse> items(@Query("q") String searchTerm);
    //Observable<NYTimesResponse> items();
    Observable<NYTimesResponse> getBookList();
}
