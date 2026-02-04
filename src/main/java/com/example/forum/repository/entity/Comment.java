package com.example.forum.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Report post;

    @Column
    private String content;

    // 作成日時
    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    // 更新日時
    @UpdateTimestamp
    @Column(name = "update_date")
    private  LocalDateTime updateDate;

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

    public Report getPost() { return post; }

    public void setPost(Report post) { this.post = post; }

    public LocalDateTime getCreateDate() {return createDate;}

    public void setCreateDate(LocalDateTime createDate) {this.createDate = createDate;}

    public LocalDateTime getUpdateDate() {return updateDate;}

    public void setUpdateDate(LocalDateTime updateDate) {this.updateDate = updateDate;}
}

