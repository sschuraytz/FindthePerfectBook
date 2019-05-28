package schuraytz.GutdendexBooks;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GutendexAPI {

    @GET("/books")
    Observable<GutendexResponse> getBookList();
}
