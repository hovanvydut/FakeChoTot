package dut.hovanvy.chotot.core.user.service;

import dut.hovanvy.chotot.core.user.repository.UserDao;
import dut.hovanvy.chotot.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserDao userDao;

    public String createNewUser(UserEntity userEntity) {


        // return confirmation token
        return null;
    }
}
