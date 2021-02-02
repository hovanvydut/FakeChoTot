package dut.hovanvy.chotot.core.email.repository;

import dut.hovanvy.chotot.entity.EmailConfirmationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

}
