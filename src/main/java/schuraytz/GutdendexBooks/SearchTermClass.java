package schuraytz.GutdendexBooks;

public class SearchTermClass {

    public String searchTerm;

    public SearchTermClass() {
        new SearchTermClass("all");
    }

    public SearchTermClass(String topic) {
        searchTerm = topic;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

}
