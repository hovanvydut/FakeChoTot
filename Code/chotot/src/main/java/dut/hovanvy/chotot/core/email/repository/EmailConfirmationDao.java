package dut.hovanvy.chotot.core.email.repository;

import dut.hovanvy.chotot.entity.EmailConfirmationEntity;
import java.util.Optional;

public interface EmailConfirmationDao {

    void saveNewToken(EmailConfirmationEntity emailConfirmationEntity);
    Optional<EmailConfirmationEntity> getToken(final String token);
    void confirmToken(EmailConfirmationEntity emailConfirmationEntity);

}
