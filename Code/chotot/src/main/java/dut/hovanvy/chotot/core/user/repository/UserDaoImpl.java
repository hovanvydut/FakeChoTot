package dut.hovanvy.chotot.core.user.repository;

import com.google.common.collect.Lists;
import dut.hovanvy.chotot.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        TypedQuery<UserEntity> query =
                this.entityManager.createQuery(
                        "SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class);

        query.setParameter("email", email);

        List<UserEntity> listResult = query.getResultList();

        if (listResult.isEmpty()) {
            return Optional.ofNullable(null);
        } else {
            return Optional.ofNullable(listResult.get(0));
        }
    }

    @Override
    public Optional<UserEntity> findById(int id) {
        UserEntity user = this.entityManager.find(UserEntity.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void save(UserEntity userEntity) {
        if (userEntity != null) {
            UserEntity newUser = this.entityManager.merge(userEntity);
            userEntity.setId(newUser.getId());
        }
    }

}
