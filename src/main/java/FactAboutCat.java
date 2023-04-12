import com.fasterxml.jackson.annotation.JsonProperty;

public class FactAboutCat { //это pojo объект
    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final int upvotes;

    public FactAboutCat( //создаем конструктор, который заполнит нам поля по json файлу, полученному по URL
                         @JsonProperty("id") String id,
                         @JsonProperty("text") String text,
                         @JsonProperty("type") String type,
                         @JsonProperty("user") String user,
                         @JsonProperty("upvotes") int upvotes
    ) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public int getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "FactAboutCat{" +
                "\n\t id=" + id +
                "\n\t, text='" + text + '\'' +
                "\n\t, type='" + type + '\'' +
                "\n\t, user='" + user + '\'' +
                "\n\t, upvotes=" + upvotes +
                "\n}";
    }
}
