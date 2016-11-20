package studio.baxia.fo.vo;

import org.hibernate.validator.constraints.NotEmpty;
import studio.baxia.fo.pojo.Guest;
import studio.baxia.fo.pojo.Message;

import javax.validation.constraints.NotNull;

/**
 * Created by Pan on 2016/11/20.
 */
public class ArticleMessageFormVo {
    @NotEmpty(message = "邮箱不能为空")
    private String email;//通讯邮箱
    @NotEmpty(message = "昵称不能为空")
    private String nickname;//昵称
    private String personalWebsite;//个人网址
    @NotEmpty(message = "留言内容不能为空")
    private String messageContent;//留言内容
    @NotNull(message = "对应文章不能为空")
    private Integer articleId;

    public Guest getGuest(){
        Guest guest = new Guest();
        guest.setEmail(this.email);
        guest.setNickname(this.nickname);
        guest.setPersonalWebsite(this.personalWebsite);
        return guest;
    }

    public Message getMessage(){
        Message message = new Message();
        message.setArticleId(this.articleId);
        message.setContent(this.messageContent);
        return message;
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

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
