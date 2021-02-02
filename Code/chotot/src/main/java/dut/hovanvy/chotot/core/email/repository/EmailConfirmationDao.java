package dut.hovanvy.chotot.core.email.repository;

import dut.hovanvy.chotot.entity.EmailConfirmationEntity;

public interface EmailConfirmationDao {

    void saveNewToken(EmailConfirmationEntity emailConfirmationEntity);

}
