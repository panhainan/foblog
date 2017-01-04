package studio.baxia.fo.pojo;

import java.util.Date;

/**
 * Created by Pan on 2016/12/27.
 */
public class Project {
    private int id;
    private String name;
    private String introduction;
    private Date pubTime;
    private int hits;
    private String downUrl;
    private String articleUrl;
    private boolean status;

    public Project() {
    }
    public Project(boolean status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", pubTime=" + pubTime +
                ", hits=" + hits +
                ", downUrl='" + downUrl + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", status=" + status +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
