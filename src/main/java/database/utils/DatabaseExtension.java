package database.utils;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import io.qameta.allure.Step;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

/**
 * This class is an extension and is used to provide database connectivity
 * as well as methods for the interaction with the db to the test class
 *
 * @author Nils Reichstein, MaibornWolff GmbH
 */
@NoArgsConstructor
@Log
public class DatabaseExtension implements BeforeAllCallback, AfterAllCallback {

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


    @Step("Create {0} in database")
    public void persistEntity(Object entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(entity);
        entityTransaction.commit();
    }


    @Step("Create {0} in database")
    public void persistEntities(List<Object> entities) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entities.forEach(entityManager::persist);
        entityTransaction.commit();
    }


    @Step("Find {0} with id {1} in database ")
    public <T> T findEntityById(Class<T> entityClass, Long id) {
        T entity = entityManager.find(entityClass, id);
        logResult(entity.toString());
        return entity;
    }


    @Step("Result")
    private void logResult(String result) {
        log.log(Level.INFO, result);
    }


}
