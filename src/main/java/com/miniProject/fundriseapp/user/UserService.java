package com.miniProject.fundriseapp.user;

import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {

   public User register(User user) throws UserException;
   public Integer signIn(SignInRequest signInRequest,HttpSession httpSession) throws UserException;
   public String signOut(HttpSession httpSession);

   User getProfile(HttpSession httpSession) throws UserException;

   List<User> getProfiles();

   List<User> getProfilecharity();

   List<User> getProfileInvestors();

   List<User> getProfileUsers();
   String createConversation(ChatDTO chatDTO) throws UserException;
   PersonalMessage getpersonalMessage(Integer userid1,Integer userid2);

   List<PersonalMessage> getallpersonalMessage();
   public Message editMessage(MessageDTO message) throws UserException;
}
