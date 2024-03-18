package com.miniProject.fundriseapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonalMessageRepo extends JpaRepository<PersonalMessage,Integer> {
    PersonalMessage findByUser1AndUser2(User userId1, User userId2);
    @Query("SELECT pm FROM PersonalMessage pm WHERE pm.user1.id = :userId OR pm.user2.id = :userId")
    List<PersonalMessage> findByUser1OrUser2(Integer userId);
}
