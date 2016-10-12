package studio.baxia.fo.pojo;

import java.util.Date;

/**
 * Created by FirePan on 2016/10/11.
 * 作者实体信息.
 */
public class Authors {
    private Integer id;
    private String account;//账户名
    private String password;//账户密码
    private Integer userStatus;//用户状态:0禁用，1启用
    private String penName;//笔名
    private String email;//常用邮箱
    private String profile;//个人介绍
    private String otherInfo;//其他信息
    private String verificationCode;//验证码
    private Date verifCodeDeadline;//验证码截止时间
    private String securityQuestionId;//密保问题id


    public Authors() {
    }

    public Authors(String account, String password, Integer userStatus) {
        this.account = account;
        this.password = password;
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", userStatus=" + userStatus +
                ", penName='" + penName + '\'' +
                ", email='" + email + '\'' +
                ", profile='" + profile + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", verifCodeDeadline=" + verifCodeDeadline +
                ", securityQuestionId='" + securityQuestionId + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Date getVerifCodeDeadline() {
        return verifCodeDeadline;
    }

    public void setVerifCodeDeadline(Date verifCodeDeadline) {
        this.verifCodeDeadline = verifCodeDeadline;
    }

    public String getSecurityQuestionId() {
        return securityQuestionId;
    }

    public void setSecurityQuestionId(String securityQuestionId) {
        this.securityQuestionId = securityQuestionId;
    }
}