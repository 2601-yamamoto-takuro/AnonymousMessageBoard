package com.example.forum.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public LocalDateTime getCreateDate() {return createDate;}

    public void setCreateDate(LocalDateTime createDate) {this.createDate = createDate;}

    public LocalDateTime getUpdateDate() {return updateDate;}

    public void setUpdateDate(LocalDateTime updateDate) {this.updateDate = updateDate;}
}
