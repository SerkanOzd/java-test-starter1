package core.utils.testdata;

import java.util.Locale;
import com.github.javafaker.Faker;
import core.utils.testdata.typs.BankAccount;
import core.utils.testdata.typs.User;

/**
 *  Test data generator for user data.
 *  Based on the JavaFaker library
 *  <p>
 *  The TestDataGenerator creates Testdata-objects for testing. For simple Testdata you can get a faker-instance to generate.
 * @see com.github.javafaker.Faker
 * @author StellaB
 * @version 0.0
 */
public class TestDataGenerator {
    private Faker faker;

    /**
     * Create an instance for test data generator with a local-language setup.
     * @param local language-String like 'de' for german
     */
    public TestDataGenerator(String local){
         this.faker = new Faker(new Locale(local));
    }


    /**
     * Generator for simple user. Default set with generated fist and lastname.
     *
     * @return User instance
     */
    public User generateSimpleUser() {
        return new User(faker.name().firstName(), faker.name().lastName());
    }

    /**
     *
     * @param bankingOption 'credit-card' or 'giro-card'
     * @return BankAccount instance
     */
    public BankAccount genUserBankData(String bankingOption) {
        switch (bankingOption){
            case "credit-card":
                return new BankAccount(new User(faker.name().firstName(),faker.name().lastName()), faker.business().creditCardNumber(), faker.business().creditCardExpiry(), faker.business().creditCardType());
            case "giro-card":
            default:
                return new BankAccount(new User(faker.name().firstName(),faker.name().lastName()), faker.finance().iban(), faker.finance().bic());
        }
    }

    /**
     * Getter of faker instance to generate single values, like firstname
     * @return Faker, generator for test data
     */
    public Faker getFaker(){
        return faker;
    }

    /**
     * Setter for local-language. If mixed values are needed.
     * Like german-name but english address
     * @param local language-String like 'en' for english
     */
    public void setLocal(String local){
        this.faker = new Faker(new Locale(local));
    }


}
