package kms.prototype.Model;

/**
 * Created by KMS on 2016-02-09. 2016
 */
public class KMS_MenuComment {
    private String name;
    private Integer lv;
    private String userTitle;
    private String content;
    private Integer likeCount;
    private Integer replyCount;

    public KMS_MenuComment(String name, int lv, String userTitle, String content, int likeCount, int replyCount){
        this.name = name;
        this.lv = lv;
        this.userTitle = userTitle;
        this.content = content;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
    }

    public String getName(){
        return name;
    }

    public Integer getLv() {
        return lv;
    }

    public String getContent() {
        return content;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }
}
