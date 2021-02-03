package dut.hovanvy.chotot.core.email.repository;

import dut.hovanvy.chotot.entity.EmailConfirmationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class EmailConfirmationDaoImpl implements EmailConfirmationDao{

    private final EntityManager entityManager;

    @Override
    public void saveNewToken(EmailConfirmationEntity emailConfirmationEntity) {
        if (emailConfirmationEntity != null) {
            this.entityManager.persist(emailConfirmationEntity);
        }
    }

    @Override
    public Optional<EmailConfirmationEntity> getToken(String token) {
        if (token == null) {
            return Optional.empty();
        } else {
            TypedQuery<EmailConfirmationEntity> query =
                    this.entityManager.createQuery("SELECT e FROM EmailConfirmationEntity e WHERE e.token = :token", EmailConfirmationEntity.class);
            query.setParameter("token", token);
            List<EmailConfirmationEntity> listResult = query.getResultList();

            if (listResult.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.ofNullable(listResult.get(0));
            }
        }
    }

    @Override
    public void confirmToken(EmailConfirmationEntity emailConfirmationEntity) {
        if (emailConfirmationEntity != null) {
            emailConfirmationEntity.setConfirmedAt(LocalDateTime.now());
            this.entityManager.merge(emailConfirmationEntity);
        }
    }

}
