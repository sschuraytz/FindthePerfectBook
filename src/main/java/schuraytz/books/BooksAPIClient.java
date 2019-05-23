package schuraytz.books;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksAPIClient {

    private final BooksAPI api;

    public BooksAPIClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(BooksAPI.class);
    }

    //Observable<Object> getBookList() { return api.items(); }
    Observable<GoogleBooksResponse> getBookList() { return api.items(); }
}
