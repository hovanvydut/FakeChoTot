package dut.hovanvy.chotot.core.email.service;

import dut.hovanvy.chotot.entity.UserEntity;

public interface EmailService {

    void sendEmailConfirmationRegister(String to, String email);
    void saveEmailConfirmationRegisterToken(String token, UserEntity userEntity);
    void confirmEmailConfirmationRegisterToken(String token);

}
