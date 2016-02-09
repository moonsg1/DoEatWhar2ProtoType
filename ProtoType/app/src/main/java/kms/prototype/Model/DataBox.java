package kms.prototype.Model;

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
    private Integer likeCount;
    private Integer replyCount;

    public DataBox(){};

    public DataBox(String pictureUrl, String restaurant, String menuName,
                   Integer price, String userName, Integer userLv,
                   String userTitle, String content,
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
