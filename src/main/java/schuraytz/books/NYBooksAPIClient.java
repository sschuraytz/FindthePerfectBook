package schuraytz.books;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NYBooksAPIClient {

    private final NYBooksAPI api;

    public NYBooksAPIClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(NYBooksAPI.class);
    }


    //Observable<Object> getBookList() { return api.items(); }
    //Observable<NYTimesResponse> getBookList() { return api.items(); }
    Observable<NYTimesResponse> getBookList() { return api.getBookList(); }
}
