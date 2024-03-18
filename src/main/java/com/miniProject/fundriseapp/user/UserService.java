package com.miniProject.fundriseapp.user;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

public interface UserService {

   public User register(  User user) throws UserException;
   public User signIn(SignInRequest signInRequest, HttpSession httpSession) throws UserException;
   public Map<String, String> signOut(HttpSession httpSession);



    User getProfile(Integer userId) throws UserException;

    List<User> getProfiles() throws UserException;

   List<User> getProfilecharity();

   List<User> getProfileInvestors();

   List<User> getProfileUsers();
   Map<String, String> createConversation(ChatDTO chatDTO) throws UserException;
   PersonalMessage getpersonalMessage(Integer userid1,Integer userid2);

   List<PersonalMessage> getallpersonalMessage();
   public Message editMessage(MessageDTO message) throws UserException;

   List<User> messagedusers(Integer id1);
}