package com.diamond.dao;

import com.diamond.entity.Team;
import com.diamond.entity.User;
import com.diamond.entity.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Classname UserTeamDAO
 * @Description TODO
 * @Date 2020/8/11 13:56
 * @Created by lrf
 */
public interface UserTeamDAO extends JpaRepository<UserTeam,Integer> {
    List<UserTeam> findAllByUid(int uid);
    List<UserTeam> findAllByTid(int tid);
//    @Modifying
//    @Query("delete from Customer where id = ?1")
    void deleteByUidAndTid(int uid,int tid);
    void deleteAllByTid(int tid);
    UserTeam findByUidAndTid(int uid,int tid);
    void deleteById(int id);

}
