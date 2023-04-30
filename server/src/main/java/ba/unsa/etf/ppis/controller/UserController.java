package ba.unsa.etf.ppis.controller;

import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.dto.UserDTO;
import ba.unsa.etf.ppis.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

}
