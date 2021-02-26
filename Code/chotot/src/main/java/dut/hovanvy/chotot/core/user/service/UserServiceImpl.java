package dut.hovanvy.chotot.core.user.service;

import dut.hovanvy.chotot.config.PasswordEncoder;
import dut.hovanvy.chotot.core.user.repository.UserDao;
import dut.hovanvy.chotot.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public void createNewUser(UserEntity userEntity) {

        Optional<UserEntity> userOptional = this.userDao.findByEmail(userEntity.getEmail());

        if (userOptional.isPresent()) {
            throw new IllegalStateException("Email has already taken.");
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = this.passwordEncoder.bCryptPasswordEncoder();
            String hashedPassword = bCryptPasswordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(hashedPassword);
            this.userDao.save(userEntity);
        }

    }

    public Optional<UserEntity> findUserByEmail(String email) {
        // re-validate email
        return this.userDao.findByEmail(email);
    }

    @Transactional
    public void enableUser(UserEntity userEntity) {
        if (!userEntity.isEnable()) {
            userEntity.setEnable(true);
            this.userDao.save(userEntity);
        }
    }
}
