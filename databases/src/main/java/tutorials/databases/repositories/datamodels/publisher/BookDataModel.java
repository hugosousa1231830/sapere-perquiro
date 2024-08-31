package tutorials.databases.repositories.datamodels.publisher;

public interface BookDataModel {
    String getId();
    void setId(String id);

    String getTitle();
    void setTitle(String title);

    String getGenre();
    void setGenre(String genre);

    String getAuthorId();
    void setAuthorId(String authorId);

    String getPublisherId();
    void setPublisherId(String publisherId);
}
