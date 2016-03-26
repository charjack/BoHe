package com.charjack.bohe.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/14.
 */
public class WebPassageInfo implements Serializable {
    private int code = 1;
    private PagerPassageInfo data;
    private String message ="1";

    public WebPassageInfo(){}

    public WebPassageInfo(PagerPassageInfo data){
        this.data =data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public PagerPassageInfo getData() {
        return data;
    }

    public void setData(PagerPassageInfo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static class PagerPassageInfo {

        private NextPassageUrlInfo paging;
        private List<PassageInfo> items;

        public PagerPassageInfo() {
        }
        public PagerPassageInfo(List<PassageInfo> items) {
            this.items = items;
        }

        public NextPassageUrlInfo getPaging() {
            return paging;
        }

        public void setPaging(NextPassageUrlInfo paging) {
            this.paging = paging;
        }

        public List<PassageInfo> getItems() {
            return items;
        }

        public void setItems(List<PassageInfo> items) {
            this.items = items;
        }


        public static class NextPassageUrlInfo{
            private String next_url = "1";

            public String getNext_url() {
                return next_url;
            }
            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }
        }


        public static class PassageInfo{
            private String template="1";
            private String cover_image_url="1";
            private int created_at=1;
            private String editor_id="1";
            private String title="1";
            private String type="1";
            private boolean liked;
            private String url="1";
            private List<?> labels=null;
            private int likes_count=1;
            private String short_title="1";
            private int updated_at=1;
            private String share_msg="1";
            private String content_url="1";
            private int id=1;
            private int published_at=1;
            private int status=1;

            public PassageInfo(){}
            public String getTemplate() {
                return template;
            }

            public void setTemplate(String template) {
                this.template = template;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public String getEditor_id() {
                return editor_id;
            }

            public void setEditor_id(String editor_id) {
                this.editor_id = editor_id;
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

            public boolean isLiked() {
                return liked;
            }

            public void setLiked(boolean liked) {
                this.liked = liked;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<?> getLabels() {
                return labels;
            }

            public void setLabels(List<?> labels) {
                this.labels = labels;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public String getShort_title() {
                return short_title;
            }

            public void setShort_title(String short_title) {
                this.short_title = short_title;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public String getShare_msg() {
                return share_msg;
            }

            public void setShare_msg(String share_msg) {
                this.share_msg = share_msg;
            }

            public String getContent_url() {
                return content_url;
            }

            public void setContent_url(String content_url) {
                this.content_url = content_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPublished_at() {
                return published_at;
            }

            public void setPublished_at(int published_at) {
                this.published_at = published_at;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

    }
}
