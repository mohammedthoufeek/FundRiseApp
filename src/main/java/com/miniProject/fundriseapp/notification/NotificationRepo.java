package com.miniProject.fundriseapp.notification;

import com.miniProject.fundriseapp.user.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo  extends JpaRepository<Notification,Integer>{
    List<Notification> findByUserId(Integer userId);
}
