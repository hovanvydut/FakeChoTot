package dut.hovanvy.chotot.core.user.service;

import dut.hovanvy.chotot.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    public void createNewUser(UserEntity userEntity);
    public Optional<UserEntity> getUserByEmail(String email);
    public void enableUser(UserEntity userEntity);
}
