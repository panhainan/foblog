package studio.baxia.fo.pojo;

/**
 * Created by FirePan on 2016/10/11.
 * 安全问题实体信息.
 */
public class SecurityQuestion {
    private Integer id;
    private Integer userType;//用户类别（管理员，作者）
    private String questoin01;
    private String answer01;
    private String question02;
    private String answer02;
    private String question03;
    private String answer03;

    @Override
    public String toString() {
        return "SecurityQuestion{" +
                "id=" + id +
                ", userType=" + userType +
                ", questoin01='" + questoin01 + '\'' +
                ", answer01='" + answer01 + '\'' +
                ", question02='" + question02 + '\'' +
                ", answer02='" + answer02 + '\'' +
                ", question03='" + question03 + '\'' +
                ", answer03='" + answer03 + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getQuestoin01() {
        return questoin01;
    }

    public void setQuestoin01(String questoin01) {
        this.questoin01 = questoin01;
    }

    public String getAnswer01() {
        return answer01;
    }

    public void setAnswer01(String answer01) {
        this.answer01 = answer01;
    }

    public String getQuestion02() {
        return question02;
    }

    public void setQuestion02(String question02) {
        this.question02 = question02;
    }

    public String getAnswer02() {
        return answer02;
    }

    public void setAnswer02(String answer02) {
        this.answer02 = answer02;
    }

    public String getQuestion03() {
        return question03;
    }

    public void setQuestion03(String question03) {
        this.question03 = question03;
    }

    public String getAnswer03() {
        return answer03;
    }

    public void setAnswer03(String answer03) {
        this.answer03 = answer03;
    }
}
