package dut.hovanvy.chotot.core.user.repository;

import dut.hovanvy.chotot.entity.UserEntity;

import java.util.Optional;

public interface UserDao {

    Optional<UserEntity> findByEmail(String email);
    public void save(UserEntity userEntity);

}
