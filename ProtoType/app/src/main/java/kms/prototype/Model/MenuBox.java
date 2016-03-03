package kms.prototype.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by KMS on 2016-02-14. 2016
 */
public class MenuBox {

    private String restaurant;
    private String menuName;
    private Integer price;
    private Integer mid;
    private String pictureUrl;

    public MenuBox(JSONObject jsonObject) {
        try {
            this.mid = jsonObject.getInt("mid");
            this.pictureUrl = jsonObject.getString("pictureUrl");
            this.restaurant = jsonObject.getString("restaurant");
            this.menuName = jsonObject.getString("menuName");
            this.price = jsonObject.getInt("price");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public MenuBox(String pictureUrl, String restaurant, String menuName, Integer price, Integer mid) {
        this.pictureUrl = pictureUrl;
        this.restaurant = restaurant;
        this.menuName = menuName;
        this.price = price;
        this.mid = mid;
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

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

}
