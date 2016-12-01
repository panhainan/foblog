package studio.baxia.fo.pojo;

import java.util.Date;

/**
 * Created by Pan on 2016/12/1.
 */
public class Friendlink {

    private int id;
    private String name;//网站名称
    private String website;//网站地址
    private String description;//网站介绍
    private int hits;//点击量
    private int priority;//优先级
    private String webAuthorEmail;//网站作者联系邮箱
    private String webAuthorName;//网站作者名称
    private Date addTime;

    @Override
    public String toString() {
        return "Friendlink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", description='" + description + '\'' +
                ", hits=" + hits +
                ", priority=" + priority +
                ", webAuthorEmail='" + webAuthorEmail + '\'' +
                ", webAuthorName='" + webAuthorName + '\'' +
                ", addTime=" + addTime +
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getWebAuthorEmail() {
        return webAuthorEmail;
    }

    public void setWebAuthorEmail(String webAuthorEmail) {
        this.webAuthorEmail = webAuthorEmail;
    }

    public String getWebAuthorName() {
        return webAuthorName;
    }

    public void setWebAuthorName(String webAuthorName) {
        this.webAuthorName = webAuthorName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
