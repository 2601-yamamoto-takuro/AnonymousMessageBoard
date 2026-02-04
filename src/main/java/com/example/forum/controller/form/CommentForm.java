package com.example.forum.controller.form;

import java.time.LocalDateTime;

public class CommentForm {

    private int id;
    private String content;
    private int postId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDateTime getCreateDate() { return createDate; }

    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }

    public LocalDateTime getUpdateDate() { return updateDate; }

    public void setUpdateDate(LocalDateTime updateDate) { this.updateDate = updateDate; }
}
