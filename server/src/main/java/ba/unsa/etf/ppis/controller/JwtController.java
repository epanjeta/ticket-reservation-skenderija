package ba.unsa.etf.ppis.controller;

import ba.unsa.etf.ppis.dto.MessageDTO;
import ba.unsa.etf.ppis.dto.TaskDTO;
import ba.unsa.etf.ppis.service.AuthService;
import ba.unsa.etf.ppis.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jwt")
@CrossOrigin(origins = "*")
public class JwtController {

    @Autowired
    AuthService authService;

    @GetMapping("/validate-token")
    public ResponseEntity<MessageDTO> validateToken(@RequestHeader(name = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(new MessageDTO("Invalid or missing Authorization header"), HttpStatus.OK);
        }

        if(authService.isValidToken(authorizationHeader)){
            return new ResponseEntity<>(new MessageDTO("Token is valid"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new MessageDTO("Invalid token"), HttpStatus.OK);
        }
    }

    @PutMapping("/remove-token")
    public ResponseEntity<MessageDTO> removeToken(@RequestHeader(name = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(new MessageDTO("Invalid or missing Authorization header"), HttpStatus.OK);
        }

        if(authService.isValidToken(authorizationHeader)){
            authService.setTokenNotValid(authorizationHeader);
            return new ResponseEntity<>(new MessageDTO("Token set to not valid"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new MessageDTO("Invalid token"), HttpStatus.OK);
        }
    }
}
