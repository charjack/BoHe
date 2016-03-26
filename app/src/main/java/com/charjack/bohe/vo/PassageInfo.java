package com.charjack.bohe.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/13.
 */
/*
* {"data":[{"id":"61f9a2a636a243cab4c32f99542a8d8e","description":"不求安慰，只希望有人能懂。","img":"http://www.himimarket.com/uploadimg/177820160131123304.jpg","type":"情感","addtime":"2016-01-31 12:33:04.0","zannum":"58729","dianjinum":"0","fengxiangnum":"0","shoucangnum":"3","tid":"1761","realzannum":"0","realshoucangnum":"3","publish":"1","publishdate":null,"haszan":"0","hasliked":"0"}
* */
public class PassageInfo implements Serializable{
    private String content_url = "1";
    private String cover_image_url ="1";  //need to use
    private long created_at = 1;
    private long editor_id= 1;
    private long id= 1;
    private String labels;
    private boolean liked = false;  //need to use
    private long likes_count=1;  //need to use
    private long published_at= 1;
    private String share_msg= "1";
    private String short_title= "1";
    private long status= 1;
    private String template= "1";
    private String title= "1";
    private String type= "1";
    private long updated_at = 1;  //need to use
    private String url = "1";

    public String getCover_image_url() {
        return cover_image_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getEditor_id() {
        return editor_id;
    }

    public void setEditor_id(long editor_id) {
        this.editor_id = editor_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public long getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(long likes_count) {
        this.likes_count = likes_count;
    }

    public long getPublished_at() {
        return published_at;
    }

    public void setPublished_at(long published_at) {
        this.published_at = published_at;
    }

    public String getShare_msg() {
        return share_msg;
    }

    public void setShare_msg(String share_msg) {
        this.share_msg = share_msg;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
