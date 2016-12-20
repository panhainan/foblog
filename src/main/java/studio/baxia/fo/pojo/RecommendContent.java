package studio.baxia.fo.pojo;

/**
 * Created by Pan on 2016/12/20.
 */
public class RecommendContent {
    private int id;
    private String content;

    public RecommendContent() {
    }

    public RecommendContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
