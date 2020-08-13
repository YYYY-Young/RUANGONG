package com.diamond.controller;

import com.diamond.result.Result;
import com.diamond.result.ResultFactory;
import com.diamond.service.UserDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserDocController
 * @Description TODO
 * @Date 2020/8/12 13:23
 * @Created by lrf
 */
@RestController
public class UserDocController {
    @Autowired
    UserDocService userDocService;
    @GetMapping("api/admin/content/doc/list/{uid}")
    public Result getDocsByuid(@PathVariable("uid") int uid){
        return ResultFactory.buildSuccessResult(userDocService.userDocs(uid));
    }
    @GetMapping("api/admin/content/doc/list/read/{uid}")
    public Result getDocsReadByuid(@PathVariable("uid") int uid){
        return ResultFactory.buildSuccessResult(userDocService.findByRead(uid));
    }
    @GetMapping("api/admin/content/doc/list/edit/{uid}")
    public Result getDocsEditByuid(@PathVariable("uid") int uid){
        return ResultFactory.buildSuccessResult(userDocService.findByEdit(uid));
    }
    @GetMapping("api/admin/content/doc/list/comment/{uid}")
    public Result getDocsCommentByuid(@PathVariable("uid") int uid){
        return ResultFactory.buildSuccessResult(userDocService.findByComment(uid));
    }
    @GetMapping("api/admin/content/doc/list/share/{uid}")
    public Result getDocsShareByuid(@PathVariable("uid") int uid){
        return ResultFactory.buildSuccessResult(userDocService.findByShare(uid));
    }
    @GetMapping("api/admin/content/doc/likes/{docid}")
    public  Result getDocLikes(@PathVariable("docid") int docid){
        return ResultFactory.buildSuccessResult(userDocService.finddoclikes(docid));
    }
    @GetMapping("api/admin/content/doc/list/likes/{uid}")
    public  Result getUserDocLikes(@PathVariable("uid") int uid){
        return ResultFactory.buildSuccessResult(userDocService.finduserlikes(uid));
    }


}
