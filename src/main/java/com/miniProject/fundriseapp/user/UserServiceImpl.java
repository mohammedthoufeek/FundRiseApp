package com.miniProject.fundriseapp.user;



import jakarta.servlet.http.HttpSession;

import com.miniProject.fundriseapp.comment.CommentRepo;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Validated
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PersonalMessageRepo personalMessageRepo;

    @Autowired
    private MessageRepo messageRepo;


    @Autowired
    private CommentRepo commentRepo;









    @Override
    public User register(  User user) throws UserException {

        User email = userRepo.findByEmail(user.getEmail());
        User password=userRepo.findByPassword(user.getPassword());
        if(email!=null)throw  new UserException("Email already exists");
        if(password!=null)throw  new UserException("Password is weak");
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return this.userRepo.save(user);
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
    public List<User> getProfiles()throws UserException {
        List<User> users= userRepo.findAll();
        if(users.isEmpty()) throw new UserException("Need to create new user");
        return users;
    }






    @Override
    public List<User> getProfileUsers() {
        User.Usertype userTypeEnum = User.Usertype.valueOf("USER");
        return userRepo.findByUsertype(userTypeEnum);

    }

    @Override
    public String createConversation(ChatDTO chatDTO) throws UserException {
        User user1 = this.userRepo.findById(chatDTO.getUserid1()).get();

        User user2 = this.userRepo.findById(chatDTO.getUserid2()).get();

        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        if (user1 == null || user2 == null) throw new UserException("User does not exist");
        PersonalMessage personalMessage1=this.personalMessageRepo.findByUser1AndUser2(user1,user2);
        PersonalMessage personalMessage2=this.personalMessageRepo.findByUser1AndUser2(user2,user1);

        Message message=new Message();
        message.setDate(LocalDate.now());
        message.setTime(LocalTime.now());
        message.setMessage(chatDTO.getMessage());
        message.setUser(user1);
        if(personalMessage1==null && personalMessage2 ==null){
            PersonalMessage personalMessage=new PersonalMessage();
            personalMessage.setUser1(user1);
            personalMessage.setUser2(user2);
            PersonalMessage personalMessage3=this.personalMessageRepo.save(personalMessage);
            message.setPersonalMessage(personalMessage3);
            Message message1=this.messageRepo.save(message);
            personalMessage3.getMessageList().add(message1);
            this.personalMessageRepo.save(personalMessage3);
            return "successfull";
        }else{
            if(personalMessage1==null){
                message.setPersonalMessage(personalMessage2);
                Message message1=this.messageRepo.save(message);
                personalMessage2.getMessageList().add(message1);
                this.personalMessageRepo.save(personalMessage2);
            }
            if(personalMessage2==null){
                message.setPersonalMessage(personalMessage1);
                Message message1=this.messageRepo.save(message);
                personalMessage1.getMessageList().add(message1);
                this.personalMessageRepo.save(personalMessage1);
            }
            return "successfull";
        }
    }

    @Override
    public PersonalMessage getpersonalMessage(Integer userid1, Integer userid2) {
        User user1 = this.userRepo.findById(userid1).get();
        User user2 = this.userRepo.findById(userid2).get();
        System.out.println(user1+" "+user2);
        PersonalMessage personalMessage1=this.personalMessageRepo.findByUser1AndUser2(user1,user2);
        PersonalMessage personalMessage2=this.personalMessageRepo.findByUser1AndUser2(user2,user1);
        if(personalMessage1==null) return personalMessage2;
        if(personalMessage2==null) return personalMessage1;
        return null;
    }

    @Override
    public List<PersonalMessage> getallpersonalMessage() {
        return this.personalMessageRepo.findAll();
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
    public Message editMessage(MessageDTO messageDTO) throws UserException {
        User userObj = this.userRepo.findById(messageDTO.getUserId()).get();
        Message messageObj = this.messageRepo.findById(messageDTO.getMessageId()).get();
        if (userObj.getId() != messageObj.getUser().getId()) throw new UserException("User can't edit");
        messageObj.setMessage(messageDTO.getMessage());
        return this.messageRepo.save(messageObj);
    }
}




