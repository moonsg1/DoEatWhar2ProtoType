package kms.prototype.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by KMS on 2016-02-14. 2016
 */
public class CommentBox {
    private String userName;
    private Integer userLv;
    private String userTitle;
    private String userProfileUrl;

    private Integer reid;
    private String date;
    private String content;
    private Integer likeCount;
    private Integer replyCount;

    public CommentBox(JSONObject jsonObject) {
        try {
            this.userName = jsonObject.getString("userName");
            this.userLv = jsonObject.getInt("userLv");
            this.userTitle = jsonObject.getString("userTitle");

            this.reid = jsonObject.getInt("reid");
            this.content = jsonObject.getString("content");
            this.date = jsonObject.getString("date");
            this.likeCount = jsonObject.getInt("likeCount");
            this.replyCount = jsonObject.getInt("replyCount");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserLv() {
        return userLv;
    }

    public void setUserLv(Integer userLv) {
        this.userLv = userLv;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public Integer getReid() {
        return reid;
    }

    public void setReid(Integer reid) {
        this.reid = reid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }
}
