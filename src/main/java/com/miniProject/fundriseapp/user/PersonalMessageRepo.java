package com.miniProject.fundriseapp.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalMessageRepo extends JpaRepository<PersonalMessage,Integer> {
    PersonalMessage findByUser1AndUser2(User userId1, User userId2);
}
