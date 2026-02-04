package com.example.forum.controller;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.controller.form.ReportForm;
import com.example.forum.service.ReportService;
import com.example.forum.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    ReportService reportService;
    @Autowired
    CommentService commentService;

    /*
     * 返信画面表示
     */
    @GetMapping("/comments/{postId}")
    public ModelAndView commentContent(@PathVariable Integer postId){
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得
        ReportForm postData = reportService.findById(postId);
        List<CommentForm> commentData = commentService.findByPostId(postId);
        // 画面遷移先を指定
        mav.setViewName("/comment");
        // 返信する投稿をセット
        CommentForm form = new CommentForm();
        form.setPostId(postId);
        mav.addObject("formModel", form);
        // 投稿データオブジェクトを保管
        mav.addObject("post", postData);
        mav.addObject("comments", commentData);
        return mav;
    }

    /*
     * 新規返信処理
     */
    @PostMapping("/comments/add")
    public ModelAndView addContent(@ModelAttribute("formModel") CommentForm commentForm){
        // 返信をテーブルに格納
        commentService.saveComment(commentForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 返信削除処理
     */
    @DeleteMapping("/comments/delete/{id}")
    public ModelAndView deleteContent(@PathVariable Integer id){
        // 返信をテーブルに格納
        commentService.deleteComment(id);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
}
