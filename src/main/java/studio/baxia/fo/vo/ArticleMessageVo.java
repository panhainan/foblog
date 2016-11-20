package studio.baxia.fo.vo;

import java.util.Date;

/**
 * Created by Pan on 2016/11/20.
 */
public class ArticleMessageVo {
    private String guestNickname;//昵称
    private String guestWebsite;//个人网址
    private String messageId;
    private String messageContent;//留言内容
    private Date messagePubTime;
    private int messageSupportCount;

    public String getGuestNickname() {
        return guestNickname;
    }

    public void setGuestNickname(String guestNickname) {
        this.guestNickname = guestNickname;
    }

    public String getGuestWebsite() {
        return guestWebsite;
    }

    public void setGuestWebsite(String guestWebsite) {
        this.guestWebsite = guestWebsite;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessagePubTime() {
        return messagePubTime;
    }

    public void setMessagePubTime(Date messagePubTime) {
        this.messagePubTime = messagePubTime;
    }

    public int getMessageSupportCount() {
        return messageSupportCount;
    }

    public void setMessageSupportCount(int messageSupportCount) {
        this.messageSupportCount = messageSupportCount;
    }
}
