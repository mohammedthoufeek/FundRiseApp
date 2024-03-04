package com.miniProject.fundriseapp;

import com.miniProject.fundriseapp.user.*;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceTest {

    private UserServiceImpl userService;
    private UserRepo userRepo;
    private PersonalMessageRepo personalMessageRepo;
    private MessageRepo messageRepo;

    @BeforeEach
    public void setup() {
        userRepo = mock(UserRepo.class);
        personalMessageRepo = mock(PersonalMessageRepo.class);
        messageRepo = mock(MessageRepo.class);

        userService = new UserServiceImpl(userRepo, personalMessageRepo, messageRepo);
    }

    @Test
    public void testRegister_Success() throws UserException {
        User newUser = new User(1, "Test User", LocalDate.now(), "Test Address", "1234567890", 30, User.Usertype.USER, "test@example.com", "password");

        when(userRepo.findByEmail(newUser.getEmail())).thenReturn(null);
        when(userRepo.findByPassword(newUser.getPassword())).thenReturn(null);
        when(userRepo.save(any(User.class))).thenReturn(newUser);

        User result = userService.register(newUser);

        assertEquals(newUser, result);
        verify(userRepo, times(1)).findByEmail(newUser.getEmail());
        verify(userRepo, times(1)).findByPassword(newUser.getPassword());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    public void testSignIn_Success() throws UserException {
        SignInRequest signInRequest = new SignInRequest("test@example.com", "password");
        User existingUser = new User(1, "Test User", LocalDate.now(), "Test Address", "1234567890", 30, User.Usertype.USER, "test@example.com", BCrypt.hashpw("password", BCrypt.gensalt()));

        when(userRepo.findByEmail(signInRequest.getEmail())).thenReturn(existingUser);

        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute("userId")).thenReturn(null);

        Integer result = userService.signIn(signInRequest, httpSession);

        assertEquals(existingUser.getId(), result);
        verify(userRepo, times(1)).findByEmail(signInRequest.getEmail());
        verify(httpSession, times(1)).setAttribute("userId", existingUser.getId());
    }

    @Test
    public void testSignOut_Success() {
        HttpSession httpSession = mock(HttpSession.class);

        String result = userService.signOut(httpSession);

        assertEquals("Signed out successfully", result);
        verify(httpSession, times(1)).invalidate();
    }

    @Test
    public void testGetProfile_Success() throws UserException {
        HttpSession httpSession = mock(HttpSession.class);
        User user = new User(1, "Test User", LocalDate.now(), "Test Address", "1234567890", 30, User.Usertype.USER, "test@example.com", "password");

        when(httpSession.getAttribute("userId")).thenReturn(user.getId());
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));

        User result = userService.getProfile(httpSession);

        assertEquals(user, result);
        verify(httpSession, times(1)).getAttribute("userId");
        verify(userRepo, times(1)).findById(user.getId());
    }

    @Test
    public void testGetProfiles_Success() {
        List<User> allUsers = Arrays.asList(new User(), new User()); // Sample list of users

        when(userRepo.findAll()).thenReturn(allUsers);

        List<User> result = userService.getProfiles();

        assertEquals(allUsers, result);
        verify(userRepo, times(1)).findAll();
    }

    @Test
    public void testGetProfilecharity_Success() {
        User.Usertype userTypeEnum = User.Usertype.CHARITY;
        List<User> charityUsers = Arrays.asList(new User(), new User()); // Sample list of charity users

        when(userRepo.findByUsertype(userTypeEnum)).thenReturn(charityUsers);

        List<User> result = userService.getProfilecharity();

        assertEquals(charityUsers, result);
        verify(userRepo, times(1)).findByUsertype(userTypeEnum);
    }

    @Test
    public void testGetProfileInvestors_Success() {
        User.Usertype userTypeEnum = User.Usertype.INVESTOR;
        List<User> investorUsers = Arrays.asList(new User(), new User()); // Sample list of investor users

        when(userRepo.findByUsertype(userTypeEnum)).thenReturn(investorUsers);

        List<User> result = userService.getProfileInvestors();

        assertEquals(investorUsers, result);
        verify(userRepo, times(1)).findByUsertype(userTypeEnum);
    }

    @Test
    public void testGetProfileUsers_Success() {
        User.Usertype userTypeEnum = User.Usertype.USER;
        List<User> userUsers = Arrays.asList(new User(), new User()); // Sample list of user users

        when(userRepo.findByUsertype(userTypeEnum)).thenReturn(userUsers);

        List<User> result = userService.getProfileUsers();

        assertEquals(userUsers, result);
        verify(userRepo, times(1)).findByUsertype(userTypeEnum);
    }
    @Test
    public void testCreateConversation_Success() throws UserException {
        ChatDTO chatDTO = new ChatDTO(1, 2, "Test Message");
        User user1 = new User(1, "User 1", LocalDate.now(), "Address 1", "1234567890", 30, User.Usertype.USER, "user1@example.com", "password");
        User user2 = new User(2, "User 2", LocalDate.now(), "Address 2", "9876543210", 25, User.Usertype.USER, "user2@example.com", "password");
        PersonalMessage personalMessage = new PersonalMessage(user1, user2);

        when(userRepo.findById(1)).thenReturn(Optional.of(user1));
        when(userRepo.findById(2)).thenReturn(Optional.of(user2));
        when(personalMessageRepo.findByUser1AndUser2(user1, user2)).thenReturn(null);
        when(personalMessageRepo.save(any(PersonalMessage.class))).thenReturn(personalMessage);
        when(messageRepo.save(any(Message.class))).thenReturn(new Message());

        String result = userService.createConversation(chatDTO);

        assertEquals("successfull", result);
        verify(userRepo, times(1)).findById(1);
        verify(userRepo, times(1)).findById(2);
        verify(personalMessageRepo, times(1)).findByUser1AndUser2(user1, user2);
        verify(personalMessageRepo, times(1)).save(any(PersonalMessage.class));
        verify(messageRepo, times(1)).save(any(Message.class));
    }

    @Test
    public void testGetpersonalMessage_Success() {
        User user1 = new User(1, "User 1", LocalDate.now(), "Address 1", "1234567890", 30, User.Usertype.USER, "user1@example.com", "password");
        User user2 = new User(2, "User 2", LocalDate.now(), "Address 2", "9876543210", 25, User.Usertype.USER, "user2@example.com", "password");
        PersonalMessage personalMessage = new PersonalMessage(user1, user2);

        when(userRepo.findById(1)).thenReturn(Optional.of(user1));
        when(userRepo.findById(2)).thenReturn(Optional.of(user2));
        when(personalMessageRepo.findByUser1AndUser2(user1, user2)).thenReturn(personalMessage);

        PersonalMessage result = userService.getpersonalMessage(1, 2);

        assertEquals(personalMessage, result);
        verify(userRepo, times(1)).findById(1);
        verify(userRepo, times(1)).findById(2);
        verify(personalMessageRepo, times(1)).findByUser1AndUser2(user1, user2);
    }
    @Test
    public void testGetallpersonalMessage_Success() {
        List<PersonalMessage> allPersonalMessages = Arrays.asList(new PersonalMessage(), new PersonalMessage()); // Sample list of personal messages

        when(personalMessageRepo.findAll()).thenReturn(allPersonalMessages);

        List<PersonalMessage> result = userService.getallpersonalMessage();

        assertEquals(allPersonalMessages, result);
        verify(personalMessageRepo, times(1)).findAll();
    }
}