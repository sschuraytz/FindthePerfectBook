package schuraytz.GutdendexBooks;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GutendexAPI {

    @GET("/books")
    Observable<GutendexResponse> getBookList(@Query("topic") String searchTerm);
}
