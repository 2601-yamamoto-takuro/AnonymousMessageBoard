package com.example.forum.controller.form;

import java.time.LocalDateTime;
import java.util.List;

public class ReportForm {

    private int id;
    private String content;
    private List<CommentForm> comments;
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

    public List<CommentForm> getComments() {
        return comments;
    }

    public void setComments(List<CommentForm> comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreateDate() { return createDate; }

    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }

    public LocalDateTime getUpdateDate() { return updateDate; }

    public void setUpdateDate(LocalDateTime updateDate) { this.updateDate = updateDate; }
}

