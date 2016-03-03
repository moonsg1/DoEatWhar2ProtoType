package kms.prototype.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by KMS on 2016-02-09. 2016
 */
public class DataBox {

    private String pictureUrl;
    private String restaurant;
    private String menuName;
    private Integer price;

    private String userName;
    private Integer userLv;
    private String userTitle;

    private String content;
    private String date;
    private Integer likeCount;
    private Integer replyCount;

    public DataBox(JSONObject jsonObject) {
        try {
            this.pictureUrl = jsonObject.getString("pictureUrl");
            this.restaurant = jsonObject.getString("restaurant");
            this.menuName = jsonObject.getString("menuName");
            this.price = jsonObject.getInt("price");

            this.userName = jsonObject.getString("userName");
            this.userLv = jsonObject.getInt("userLv");
            this.userTitle = jsonObject.getString("userTitle");

            this.content = jsonObject.getString("content");
            this.date = jsonObject.getString("date");
            this.likeCount = jsonObject.getInt("likeCount");
            this.replyCount = jsonObject.getInt("replyCount");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public DataBox(String pictureUrl, String restaurant, String menuName,
                   Integer price, String userName, Integer userLv,
                   String userTitle, String content, String date,
                   Integer likeCount, Integer replyCount)
    {
        this.pictureUrl = pictureUrl;
        this.restaurant = restaurant;
        this.menuName = menuName;
        this.price = price;

        this.userName = userName;
        this.userLv = userLv;
        this.userTitle = userTitle;

        this.content = content;
        this.date = date;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
