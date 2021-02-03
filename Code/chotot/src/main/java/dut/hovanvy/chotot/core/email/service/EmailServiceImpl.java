package dut.hovanvy.chotot.core.email.service;

import dut.hovanvy.chotot.core.email.repository.EmailConfirmationDao;
import dut.hovanvy.chotot.core.user.service.UserService;
import dut.hovanvy.chotot.entity.EmailConfirmationEntity;
import dut.hovanvy.chotot.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    private final EmailConfirmationDao emailConfirmationDao;
    private final UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmailConfirmationRegister(String to, String email) {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@hovanvy.dut");
            helper.setTo(to);
            helper.setSubject("Confirm your account!");
            helper.setText(email, true);

            this.javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Failed to send email");
            throw new IllegalStateException("fail to send email");
        }

    }

    @Override
    public void saveEmailConfirmationRegisterToken(String token, UserEntity userEntity) {
        this.emailConfirmationDao.saveNewToken(new EmailConfirmationEntity(
                null,
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                null,
                userEntity
        ));
    }

    @Override
    public void confirmEmailConfirmationRegisterToken(String token) {
        Optional<EmailConfirmationEntity> emailConfirmationOpt = this.emailConfirmationDao.getToken(token);

        if (emailConfirmationOpt.isPresent()) {
            EmailConfirmationEntity emailConfirmation = emailConfirmationOpt.get();

            if (emailConfirmation.isExpired()) {
                System.out.println("TOken is expired");
                return;
            }

            if (emailConfirmation.isConfirmed()) {
                System.out.println("Token is confirmed");
                return;
            }

            this.emailConfirmationDao.confirmToken(emailConfirmation);
            UserEntity user = emailConfirmation.getUser();
            this.userService.enableUser(user);
        } else {
            System.out.println("Token is not exist");
            return;
        }
    }

}
