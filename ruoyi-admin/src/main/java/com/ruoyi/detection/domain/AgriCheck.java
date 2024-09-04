package com.ruoyi.detection.domain;


import java.io.Serializable;

/**
 * ClassName: AgriCheck
 * Package: com.ruoyi.detection.domain
 * Description:
 *
 * @Author tanzx
 * @Create 2024/9/4 14:50
 * @Version 1.0
 */

public class AgriCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String userName;

    private String content;

    private Integer flag;

    private Long citySampleTestDetailsId;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Long getCitySampleTestDetailsId() {
        return citySampleTestDetailsId;
    }

    public void setCitySampleTestDetailsId(Long citySampleTestDetailsId) {
        this.citySampleTestDetailsId = citySampleTestDetailsId;
    }

    @Override
    public String toString() {
        return "AgriCheck{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", flag=" + flag +
                ", citySampleTestDetailsId=" + citySampleTestDetailsId +
                '}';
    }
}
