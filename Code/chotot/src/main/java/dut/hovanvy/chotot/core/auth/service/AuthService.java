package dut.hovanvy.chotot.core.auth.service;

import dut.hovanvy.chotot.api.v1.auth.dto.RegisterRequestDto;
import dut.hovanvy.chotot.core.user.service.UserService;
import dut.hovanvy.chotot.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private final UserService userService;

    public String register(RegisterRequestDto requestDto) {
        // sign up new user and get confirmation token
        final String token = userService.createNewUser(
                new UserEntity(
                        0L,
                        requestDto.getFirstName(),
                        requestDto.getLastName(),
                        requestDto.getEmail(),
                        requestDto.getPassword(),
                        null // enum ROLE_USER
                ));

        // send email with confirmation token
        final String linkConfirmation = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        return null;
    }
}
