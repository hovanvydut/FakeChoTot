package dut.hovanvy.chotot.core.user.service;

import dut.hovanvy.chotot.core.email.service.EmailService;
import dut.hovanvy.chotot.core.user.repository.UserDao;
import dut.hovanvy.chotot.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {

    private final UserDao userDao;
    private final EmailService emailService;

    @Transactional
    public String createNewUser(UserEntity userEntity) {

        Optional<UserEntity> userOptional = this.userDao.findByEmail(userEntity.getEmail());

        if (userOptional.isPresent()) {
            throw new IllegalStateException("Email has already taken.");
        } else {
            this.userDao.save(userEntity);
        }

        // save new confirmation token into DB
        final String confirmationToken = UUID.randomUUID().toString();
        this.emailService.saveEmailConfirmationToken(confirmationToken);

        // return confirmation token
        return confirmationToken;
    }
}
