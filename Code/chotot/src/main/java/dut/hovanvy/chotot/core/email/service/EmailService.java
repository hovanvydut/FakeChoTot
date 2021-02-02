package dut.hovanvy.chotot.core.email.service;

public interface EmailService {

    void sendEmailConfirmation(String to, String email);
    void saveEmailConfirmationToken(String token);

}
