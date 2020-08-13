package com.diamond.service;

import com.diamond.dao.DocDAO;
import com.diamond.dao.UserDocDAO;
import com.diamond.entity.Doc;
import com.diamond.entity.UserDoc;
import com.diamond.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Classname DocService
 * @Description TODO
 * @Date 2020/8/11 16:20
 * @Created by lrf
 */
@Service
public class DocService {
    @Autowired
    DocDAO docDAO;
    @Autowired
    UserTeamService userTeamService;
    @Autowired
    UserDocDAO userDocDAO;
    @Autowired
    UserDocService userDocService;
    public Doc findById(int uid,int id) {
        Doc doc;
        doc = docDAO.findById(id);
        if(doc!=null){
            int tid=doc.getDoc_team();
            System.out.println(doc.isDoc_recycle());
            if (doc.isDoc_recycle())return null;
            if(doc.getDoc_founder()!=uid&&(!doc.isDoc_read()||(doc.isDoc_only_team()&&!userTeamService.isTeammember(uid,tid)))){
                return null;
            }
        }
        UserDoc userDoc=new UserDoc();
        userDoc.setUid(uid);
        userDoc.setDocid(id);
        userDoc.setDoc_read(true);
        java.sql.Timestamp ctime = new java.sql.Timestamp(new java.util.Date().getTime());
        userDoc.setDoc_open_time(ctime);
        userDocDAO.save(userDoc);
        return doc;
    }
    public int addOrUpdate(Doc doc){

        int uid=doc.getDoc_last_edit_uid();
        int tid=doc.getDoc_team();
        if(doc.getDoc_founder()!=uid&&(!doc.isDoc_edit()||(doc.isDoc_only_team()&&!userTeamService.isTeammember(uid,tid)))){
            return 0;
        }
        doc =docDAO.save(doc);
        int docid=doc.getId();
        UserDoc userDoc=new UserDoc();
        userDoc.setUid(uid);
        userDoc.setDocid(docid);
        userDoc.setDoc_read(true);
        java.sql.Timestamp ctime = new java.sql.Timestamp(new java.util.Date().getTime());
        userDoc.setDoc_open_time(ctime);
        userDocDAO.save(userDoc);
        return 1;
    }
    @Transactional
    public int delete(int uid,int id){
        Doc doc = docDAO.findById(id);
        int tid=doc.getDoc_team();
        if(doc.getDoc_founder()!=uid&&(!doc.isDoc_delete()||(doc.isDoc_only_team()&&!userTeamService.isTeammember(uid,tid)))){
            return 0;
        }
        if(!doc.isDoc_recycle()){
           docDAO.put_in_recycle_bin(id);
            return 1;
        }else {
            docDAO.deleteById(id);
            userDocDAO.deleteAllByDocid(id);
        }
        return 2;
    }

    public void resume(int id){
        docDAO.take_out_recycle_bin(id);
    }
    public int share(int fromuid,int touid,int docid,String msg){
        Doc doc = docDAO.findById(docid);
        int tid=doc.getDoc_team();
        if(doc.getDoc_founder()!=fromuid&&(!doc.isDoc_share()||(doc.isDoc_only_team()&&!userTeamService.isTeammember(fromuid,tid)))){
            return 0;
        }
        UserDoc userDoc=new UserDoc();
        userDoc.setUid(touid);
        userDoc.setDocid(docid);
        userDoc.setDoc_share(true);
        java.sql.Timestamp ctime = new java.sql.Timestamp(new java.util.Date().getTime());
        userDoc.setDoc_open_time(ctime);
        userDoc.setDoc_open_des(msg);
        userDocDAO.save(userDoc);
        return 1;
    }
    public List<Doc>findbyuidread(int uid){
        List<Doc> docs=new ArrayList<>();
        List<UserDoc>userDocs=userDocService.findByRead(uid);
       for( UserDoc tempuserdoc  : userDocs){
           Doc doctemp=docDAO.findnotdeletedocs(tempuserdoc.getDocid());
           if(doctemp!=null)
           docs.add(doctemp);}
           Set<Doc> userSet = new HashSet<>(docs);
           List<Doc> list = new ArrayList<>(userSet);
           return list;
       }

    public MyPage list(int page, int size) {
        MyPage<Doc> docs;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Page<Doc> articlesInDb = docDAO.findAll(PageRequest.of(page, size, sort));
        docs = new MyPage<>(articlesInDb);
        return docs;
    }

}
