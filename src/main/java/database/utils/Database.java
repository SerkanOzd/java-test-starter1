package database.utils;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import lombok.NoArgsConstructor;

/**
 * This class is an extension and is used to provide database connectivity
 * as well as methods for the interaction with the db to the test class
 * @author Nils Reichstein, MaibornWolff GmbH
 */
@NoArgsConstructor
public class Database implements BeforeAllCallback, AfterAllCallback {

    private static final String TEST_PU = "test_pu";
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Override
    public void beforeAll(final ExtensionContext extensionContext) {
        entityManagerFactory = Persistence.createEntityManagerFactory(TEST_PU);
        entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public void afterAll(final ExtensionContext extensionContext) {
        entityManager.close();
        entityManagerFactory.close();
    }


    public void persistEntity(Object entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(entity);
        entityTransaction.commit();
    }


    public void persistEntities(List<Object> entities) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entities.forEach(entityManager::persist);
        entityTransaction.commit();
    }


    public <T> T findEntityById(Class<T> entityClass,Long id){
        return entityManager.find(entityClass, id);
    }
}
