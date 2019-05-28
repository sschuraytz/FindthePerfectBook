package schuraytz.GutdendexBooks;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GutendexAPIClient {

    private final GutendexAPI api;
    SearchTermClass searchClass;

    public GutendexAPIClient(SearchTermClass stc) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gutendex.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(GutendexAPI.class);
        searchClass = stc;
    }

    Observable<GutendexResponse> getBookList() { return api.getBookList(searchClass.getSearchTerm()); }
}
