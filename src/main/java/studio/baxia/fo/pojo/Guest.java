package studio.baxia.fo.pojo;

/**
 * Created by FirePan on 2016/10/11.
 * 访客实体信息.
 */
public class Guest {
    private Integer id;
    private String email;//通讯邮箱
    private String nickname;//昵称
    private String personalWebsite;//个人网址
    private String accessIp;//访问IP

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", personalWebsite='" + personalWebsite + '\'' +
                ", accessIp='" + accessIp + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPersonalWebsite() {
        return personalWebsite;
    }

    public void setPersonalWebsite(String personalWebsite) {
        this.personalWebsite = personalWebsite;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }
}
