package com.miniProject.fundriseapp.user;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("user")
    public User createuser(@Valid @RequestBody User user) throws UserException {
        return this.userService.register(user);
    }

    @PostMapping("signin")
    public User signIn(@Valid @RequestBody SignInRequest signInRequest, HttpSession httpSession) throws UserException {
        return this.userService.signIn(signInRequest,httpSession);
    }
    @GetMapping("signout")
    public Map<String, String> signOut(HttpSession httpSession) {
        return this.userService.signOut(httpSession);
    }
    @GetMapping("userid")
    public Integer getUserId(HttpSession httpSession) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId != null) {
            return userId;
        } else {
            return null; // or throw exception or handle as required
        }
    }

    @GetMapping("profile/{userId}")
    public User getProfileById(@PathVariable  Integer userId) throws UserException {
        return this.userService.getProfileById(userId);
    }
        @GetMapping("profiles")
        public List<User> getProfile () throws UserException {
            return this.userService.getProfiles();
        }
        @GetMapping("profiles/charity")
        public List<User> getProfilecharity () throws UserException {
            return this.userService.getProfilecharity();
        }
        @GetMapping("profiles/investors")
        public List<User> getProfilinvestors () throws UserException {
            return this.userService.getProfileInvestors();
        }
        @GetMapping("profiles/users")
        public List<User> getProfileusers () throws UserException {
            return this.userService.getProfileUsers();
        }

        @PostMapping("chat")
        public Map<String, String> CreateConversation (@Valid @RequestBody ChatDTO chatDTO) throws UserException {
            System.out.println(chatDTO.getUserid1() + " " + chatDTO.getUserid2() + " ");
            return this.userService.createConversation(chatDTO);
        }
        @GetMapping("chat/get/{id1}/{id2}")
        public PersonalMessage getConversation (@PathVariable Integer id1, @PathVariable Integer id2) throws
        UserException {
            return this.userService.getpersonalMessage(id1, id2);
        }
        @GetMapping("messagedusers/get/{id1}")
        public List<User> getmessagedUsers (@PathVariable Integer id1) throws UserException {
            return this.userService.messagedusers(id1);
        }
        @GetMapping("getchat")
        public List<PersonalMessage> getallconverstaion () {
            return this.userService.getallpersonalMessage();
        }
        @PatchMapping("EditMessage")
        public Message editMessage (@Valid @RequestBody MessageDTO messageDTO) throws UserException {
            return this.userService.editMessage(messageDTO);
        }



}
