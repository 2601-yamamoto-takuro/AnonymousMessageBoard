package com.example.forum.controller;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.controller.form.ReportForm;
import com.example.forum.service.ReportService;
import com.example.forum.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        // 元の投稿を取得
        ReportForm postData = reportService.findById(postId);
        mav.addObject("post", postData);
        // 紐づいているコメントを取得
        List<CommentForm> commentData = commentService.findByPostId(postId);
        mav.addObject("comments", commentData);
        // 画面遷移先を指定
        mav.setViewName("/comment");
        // 返信する投稿をセット
        CommentForm form = new CommentForm();
        form.setPostId(postId);
        mav.addObject("formModel", form);
        return mav;
    }

    /*
     * 新規返信処理
     */
    @PostMapping("/comments/add")
    public ModelAndView addContent(@Validated @ModelAttribute("formModel") CommentForm commentForm,
                                    BindingResult result){
        if (result.hasErrors()) {
            // GET の処理を呼んで Model を作り直す
            ModelAndView mav = commentContent(commentForm.getPostId());

            mav.addObject("formModel", commentForm);

            return mav;
        }
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

    /*
     * コメント編集画面表示処理
     */
    @GetMapping("/comments/edit/{id}")
    public ModelAndView editContent(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        // 編集する投稿を取得
        CommentForm comment = commentService.editReport(id);
        // 編集する投稿をセット
        mav.addObject("formModel", comment);
        // 画面遷移先を指定
        mav.setViewName("/commentedit");
        return mav;
    }

    /*
     * 編集処理
     */
    @PutMapping("/comments/update/{id}")
    public ModelAndView updateContent (@PathVariable Integer id,
                                       @Validated @ModelAttribute("formModel") CommentForm comment,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/commentedit");
        }
        comment.setId(id);
        commentService.updateComment(id, comment.getContent());
        return new ModelAndView("redirect:/");
    }
}
