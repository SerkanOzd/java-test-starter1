package core.utils.testdata.typs;

import lombok.Getter;

/**
 * The BankAccount-Class is a representation of a banking account.
 * With a physical user that own a giro-card and credit-card.
 */
@Getter
public class BankAccount {

    private User accountHolder;
    private String creditCardNumber;
    private String creditCardExpiry;
    private String creditCardType;
    private String iban;
    private String bic;


    /**
     * Create account instance for banking with credit-card.
     * @param creditCardNumber String
     * @param creditCardExpiry String
     * @param creditCardType String
     */
    public BankAccount(User accountHolder, String creditCardNumber, String creditCardExpiry, String creditCardType) {
        this.accountHolder = accountHolder;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardType = creditCardType;
    }

    /**
     * Create account instance for banking with giro-card.
     * @param iban String
     * @param bic String
     */
    public BankAccount(User accountHolder, String iban, String bic){
        this.accountHolder = accountHolder;
        this.iban = iban;
        this.bic = bic;
    }
}
