package com.example.forum.service;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.repository.CommentRepository;
import com.example.forum.repository.entity.Comment;
import com.example.forum.repository.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    /*
     * DBから取得したデータをFormに設定
     */
    private List<CommentForm> setCommentForm(List<Comment> results) {
        List<CommentForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            CommentForm comment = new CommentForm();
            Comment result = results.get(i);
            comment.setId(result.getId());
            comment.setContent(result.getContent());
            reports.add(comment);
        }
        return reports;
    }

    /*
     * 返信追加
     */
    public void saveComment(CommentForm form) {
        Comment comment = new Comment();
        comment.setContent(form.getContent());

        Report post = new Report();
        post.setId(form.getPostId());
        comment.setPost(post);

        commentRepository.save(comment);
    }

    /*
     * 紐づいている返信を全件取得
     */
    public List<CommentForm> findByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return setCommentForm(comments);
    }

    /*
     * 返信削除
     */
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}
