package parsers;

public class MovieInfo {

    private String title;
    private String url;
    private String imagePath;
    private String source;
    private String message;

    public MovieInfo(String title, String url, String source) {
        this(title, url, null, source);
    }

    public MovieInfo(String title, String url, String imagePath, String source) {
        this.title = title;
        this.url = url;
        this.imagePath = imagePath;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "title: " + title + "\n" +
                "hittades på " + source + "\n" +
                "url: " + url;
    }
}
