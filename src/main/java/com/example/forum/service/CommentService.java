package com.example.forum.service;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.repository.ReportRepository;
import com.example.forum.repository.CommentRepository;
import com.example.forum.repository.entity.Comment;
import com.example.forum.repository.entity.Report;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReportRepository reportRepository;

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
    public void saveComment(CommentForm reqComment) {
        Comment comment = new Comment();
        comment.setContent(reqComment.getContent());

        Report report = reportRepository.findById(reqComment.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        ;
        report.setId(reqComment.getPostId());
        comment.setPost(report);

        report.setUpdateDate(LocalDateTime.now());
        commentRepository.save(comment);
    }

    /*
     * 紐づいている返信を全件取得
     */
    public List<CommentForm> findByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findByPostIdOrderByCreateDateDesc(postId);
        return setCommentForm(comments);
    }

    /*
     * 返信削除
     */
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    /*
     * 編集画面用投稿1件取得
     */
    public CommentForm editReport(Integer id) {
        List<Comment> results = new ArrayList<>();
        results.add(commentRepository.findById(id).orElse(null));
        List<CommentForm> reports = setCommentForm(results);
        return reports.get(0);
    }

    @Transactional
    public void updateComment(Integer id, String content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(content);
    }

}
