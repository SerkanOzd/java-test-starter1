package core.utils.testdata.typs;

import lombok.Getter;
import lombok.Setter;

/**
 * The User-Class is a representation of a physical user.
 * With name, sex, age, height and weight.
 */
@Getter
public class User {
    private @Setter String title = "";
    private @Setter Sex sex;
    private String fistName;
    private @Setter String middleName;
    private String lastName;
    private int age = 21;
    private int weight = 65;//kg
    private int height = 170;//cm

    public enum Sex { MALE, FEMALE, OTHER }

    /**
     * User only defined with fist and lastname.
     * Creating default adult with undefined sex.
     * @param firstName String
     * @param lastName String
     */
    public User(String firstName, String lastName){
        this.fistName = firstName;
        this.lastName = lastName;
    }

    /**
     * User defined by all name options and sex.
     * @param title academic title like 'doc'
     * @param sex 'female' 'male' or 'other'
     * @param firstName String
     * @param middleName String
     * @param lastName String
     */
    public User(String title, Sex sex, String firstName, String middleName, String lastName){
        this.title = title;
        this.sex = sex;
        this.fistName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public void setAge(int age) {
        if(age >= 0 && age <= 130)
            this.age = age;
        else
            throw new IllegalArgumentException("The age is not in the humanity range of life time.");
    }

    public void setWeight(int weight) {
        if(weight >= 0 && weight <= 600)
            this.weight = weight;
        throw new IllegalArgumentException("The weight is not in the humanity range of weight.");
    }

    public void setHeight(int height) {
        if(height >= 0 && height <= 260)
            this.height = height;
        throw new IllegalArgumentException("The weight is not in the humanity range of height.");
    }

}
