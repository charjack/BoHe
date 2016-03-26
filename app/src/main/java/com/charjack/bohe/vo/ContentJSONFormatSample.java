package com.charjack.bohe.vo;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 * jsonformat
 */
public class ContentJSONFormatSample {
    private int code;
    private DataEntity data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public class DataEntity {
        private PagingEntity paging;
        private List<ItemsEntity> items;

        public void setPaging(PagingEntity paging) {
            this.paging = paging;
        }

        public void setItems(List<ItemsEntity> items) {
            this.items = items;
        }

        public PagingEntity getPaging() {
            return paging;
        }

        public List<ItemsEntity> getItems() {
            return items;
        }

        public class PagingEntity {
            private String next_url;

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }

            public String getNext_url() {
                return next_url;
            }
        }

        public class ItemsEntity {
            private String template;
            private String cover_image_url;
            private int created_at;
            private String editor_id;
            private String title;
            private String type;
            private boolean liked;
            private String url;
            private List<?> labels;
            private int likes_count;
            private String short_title;
            private int updated_at;
            private String share_msg;
            private String content_url;
            private int id;
            private int published_at;
            private int status;

            public void setTemplate(String template) {
                this.template = template;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public void setEditor_id(String editor_id) {
                this.editor_id = editor_id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setLiked(boolean liked) {
                this.liked = liked;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setLabels(List<?> labels) {
                this.labels = labels;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public void setShort_title(String short_title) {
                this.short_title = short_title;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public void setShare_msg(String share_msg) {
                this.share_msg = share_msg;
            }

            public void setContent_url(String content_url) {
                this.content_url = content_url;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setPublished_at(int published_at) {
                this.published_at = published_at;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTemplate() {
                return template;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public String getEditor_id() {
                return editor_id;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public boolean isLiked() {
                return liked;
            }

            public String getUrl() {
                return url;
            }

            public List<?> getLabels() {
                return labels;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public String getShort_title() {
                return short_title;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public String getShare_msg() {
                return share_msg;
            }

            public String getContent_url() {
                return content_url;
            }

            public int getId() {
                return id;
            }

            public int getPublished_at() {
                return published_at;
            }

            public int getStatus() {
                return status;
            }
        }
    }
}
