package core.utils.testdata;

import java.util.regex.Pattern;
import static org.junit.Assert.*;

/**
 * Validate standard test data values of common pattern.
 *
 * @author StellaB
 * @version 0.0
 */
public class TestDataValidator {

    /**
     * Validate Name with fist uppercase and 1 - 80 chars
     *
     * @param name String to validate
     */
    public void validateName(String name) {
        try {
            validateRegex("[A-Z][a-z]{1,80}$", name);
        } catch (Exception e) {
            fail("Name wasn't valid.");
        }
    }


    /**
     * Validate address with street, zip-code, city, country
     *
     * @param address String to validate
     */
    public void validateAddress(String address) {

        try {
            validateRegex("/^([a-zäöüß\\s\\d.,-]+?)\\s*([\\d\\s]+(?:\\s?[-|+/]\\s?\\d+)?\\s*[a-z]?)?\\s*(\\d{5})\\s*(.+)?$/i", address);
        } catch (Exception e) {
            fail("Address wasn't valid.");
        }
    }


    /**
     * Validate iban and bic
     *
     * @param iban String to validate
     * @param bic  String to validate
     */
    public void validateGiroCardComponents(String iban, String bic) {

        try {
            validateRegex("^DE\\d{2}\\s?([0-9a-zA-Z]{4}\\s?){4}[0-9a-zA-Z]{2}$", iban);
        } catch (Exception e) {
            fail("IBAN wasn't valid.");
        }

        try {
            validateRegex("[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}", bic);
        } catch (Exception e) {
            fail("BIC wasn't valid.");
        }
    }


    /**
     * Validate email with [user_specific]@[domain].[end]
     *
     * @param email String to validate
     */
    public void validateEmail(String email) {
        //see https://digitalfortress.tech/js/top-15-commonly-used-regex/ or more detailed http://emailregex.com/
        validateRegex("/^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$/", email);
    }


    /**
     * Validate if the given String is a phone number.
     * <p>
     * Out of digital and > 6
     *
     * @param phoneNumber String to validate
     */
    public void validatePhoneNumber(String phoneNumber) {
        validateRegex("(\\(?([\\d \\-\\)\\–\\+\\/\\(]+){6,}\\)?([ .\\-–\\/]?)([\\d]+))", phoneNumber);
    }


    /**
     * Validate if the String value is equivalent to the given regex pattern.
     *
     * @param reg   regular expressing String
     * @param value String to be checked
     */
    public void validateRegex(String reg, String value) {
        var match = Pattern.compile(reg).matcher(value);

        assertTrue(String.format("The String '%s' doesn't matched by the regex: '%s'", reg, value), match.find());
    }
}
