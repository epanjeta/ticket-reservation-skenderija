package ba.unsa.etf.ppis.controller;

import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.dto.*;
import ba.unsa.etf.ppis.entity.UserEntity;
import ba.unsa.etf.ppis.exception.NotValidException;
import ba.unsa.etf.ppis.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    protected UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.ALL_USERS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))})}
    )
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_WAS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = ApiResponseMessages.WRONG_EMAIL_OR_PASSWORD,
                    content = @Content)})
    @PutMapping("/authenticate")
    public ResponseEntity<LogInUserDTO> getUserByEmailAndPassword(
            @RequestBody LoginDTO login) {
        return new ResponseEntity<>(userService.getUserByEmailAndPassword(login.getEmail(), login.getPassword()), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_WAS_VERIFIED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = ApiResponseMessages.USER_NOT_FOUND_WITH_EMAIL,
                    content = @Content)})
    @PutMapping("/{email}/verify")
    public ResponseEntity<UserDTO> verifyUser(@PathVariable("email") String email, @RequestParam("code") String code) {
        return new ResponseEntity<>(userService.verifyUser(email, code), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiResponseMessages.VERIFICATION_CODE_WAS_SENT,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})})
    @PostMapping("/upload")
    public ResponseEntity<MessageDTO> createUser(@RequestBody UserDTO userDTO) {
        validateUserCreation(userDTO);
        userService.createUser(userDTO);
        return new ResponseEntity<>(new MessageDTO(ApiResponseMessages.VERIFICATION_CODE_WAS_SENT), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiResponseMessages.VERIFICATION_CODE_WAS_SENT,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})})
    @PostMapping("/changePassword/{userId}")
    public ResponseEntity<UserDTO> changePassword(@PathVariable("userId") Integer userId, @RequestBody String newPassword){
        validatePassword(newPassword);
        UserDTO user = userService.changePassword(userId, newPassword);
        if(user!=null) return new ResponseEntity<>(user, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiResponseMessages.VERIFICATION_CODE_WAS_SENT,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})})
    @PostMapping("/checkPassword/{userId}")
    public ResponseEntity<UserEntity> checkPassword(@PathVariable("userId") Integer userId,@RequestBody String currentPassword){
        UserEntity user = userService.checkPassword(userId, currentPassword);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/changeForgottenPassword")
    public ResponseEntity<MessageDTO> changeForgottenPassword(@RequestBody ForgottenPasswordDTO email){
        userService.changeForgottenPassword(email);
        return new ResponseEntity<>(new MessageDTO("Password changed"), HttpStatus.OK);
    }

    private void validateUserCreation(UserDTO userDTO) {
        if (userDTO.getUserType() == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_ACCOUNT_TYPE);
        }

        validateEmail(userDTO.getEmail());
        validatePassword(userDTO.getPassword());
        validateUser(userDTO);
    }

    private void validateUser(UserDTO userDTO) {
        if (userDTO.getUserType() == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_ACCOUNT_TYPE);
        }
        if (userDTO.getName().length() > ApiResponseMessages.MAX_NAME_LENGTH) {
            throw new NotValidException(ApiResponseMessages.FIRST_NAME_TOO_LONG);
        }
    }

    private void validateEmail(String email) {
        if (email == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_EMAIL);
        }
        if (email.length() > ApiResponseMessages.MAX_NAME_LENGTH) {
            throw new NotValidException(ApiResponseMessages.EMAIL_TOO_LONG);
        }

        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new NotValidException(ApiResponseMessages.EMAIL_HAS_WRONG_FORMAT);
        }
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_PASSWORD);
        }
        if (password.length() < 8 || password.length() > 25) {
            throw new NotValidException(ApiResponseMessages.PASSWORD_LENGTH);
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,25}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new NotValidException(ApiResponseMessages.PASSWORD_TO_WEAK);
        }
    }
}
