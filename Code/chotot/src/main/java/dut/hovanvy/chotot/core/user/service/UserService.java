package dut.hovanvy.chotot.core.user.service;

import dut.hovanvy.chotot.core.user.repository.UserDao;
import dut.hovanvy.chotot.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserDao userDao;

    public void createNewUser(UserEntity userEntity) {

        Optional<UserEntity> userOptional = this.userDao.findByEmail(userEntity.getEmail());

        if (userOptional.isPresent()) {
            throw new IllegalStateException("Email has already taken.");
        } else {
            this.userDao.save(userEntity);
        }

    }

    @Transactional
    public void enableUser(UserEntity userEntity) {
        if (!userEntity.isEnable()) {
            userEntity.setEnable(true);
            this.userDao.save(userEntity);
        }
    }
}
