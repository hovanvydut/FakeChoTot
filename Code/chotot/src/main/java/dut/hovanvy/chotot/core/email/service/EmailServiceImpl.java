package dut.hovanvy.chotot.core.email.service;

import dut.hovanvy.chotot.core.email.repository.EmailConfirmationDao;
import dut.hovanvy.chotot.entity.EmailConfirmationEntity;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    private final EmailConfirmationDao emailConfirmationDao;
    private final static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmailConfirmation(String to, String email) {
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
    public void saveEmailConfirmationToken(String token) {
        this.emailConfirmationDao.saveNewToken(new EmailConfirmationEntity(
                null,
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                null
        ));
    }

}
