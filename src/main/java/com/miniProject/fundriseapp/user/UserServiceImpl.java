package com.miniProject.fundriseapp.user;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User register(User user) throws UserException {
        User email = userRepo.findByEmail(user.getEmail());
        User password=userRepo.findByPassword(user.getPassword());
        if(email!=null)throw  new UserException("Email already exists");
        if(password!=null)throw  new UserException("Password is weak");
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return userRepo.save(user);
    }
    @Override
    public Integer signIn(SignInRequest signInRequest, HttpSession httpSession) throws UserException {
        User user = userRepo.findByEmail(signInRequest.getEmail());
        if (user != null) {
            if (BCrypt.checkpw(signInRequest.getPassword(), user.getPassword())){
            httpSession.setAttribute("userId", user.getId());
            return user.getId();}else{
                throw  new UserException("Password not matches");
            }
        } else {
            throw  new UserException("Email does not  exists");

        }

    }
    @Override
    public String signOut(HttpSession httpSession) {
        httpSession.invalidate();
        return "Signed out successfully";
    }

    @Override
    public User getProfile(HttpSession httpSession) throws UserException {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId != null) {
            throw new UserException("Session does not exist");
        }
        return this.userRepo.findById(userId).get();
    }

    @Override
    public List<User> getProfiles() {

        List<User> users= userRepo.findAll();
        return users;
    }

    @Override
    public List<User> getProfilecharity() {
        User.Usertype userTypeEnum = User.Usertype.valueOf("CHARITY");
        return userRepo.findByUsertype(userTypeEnum);

    }
    @Override
    public List<User> getProfileInvestors() {
        User.Usertype userTypeEnum = User.Usertype.valueOf("INVESTOR");
        return userRepo.findByUsertype(userTypeEnum);

    }
    @Override
    public List<User> getProfileUsers() {
        User.Usertype userTypeEnum = User.Usertype.valueOf("USER");
        return userRepo.findByUsertype(userTypeEnum);

    }




}
