package studio.baxia.fo.pojo;

/**
 * Created by FirePan on 2016/10/11.
 * 管理员实体信息.
 */
public class Admin {
    private Integer id;
    private String adminAccount;//管理员账户
    private String adminPassword;//管理员密码
    private String email;//邮箱
    private Integer securityQuestionId;//密保问题id

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminAccount='" + adminAccount + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", email='" + email + '\'' +
                ", securityQuestionId=" + securityQuestionId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSecurityQuestionId() {
        return securityQuestionId;
    }

    public void setSecurityQuestionId(Integer securityQuestionId) {
        this.securityQuestionId = securityQuestionId;
    }
}
