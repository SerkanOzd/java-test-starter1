package core;

public class TestDataGenerator {

    public TestDataGenerator(){
        //empty constructor
    }

    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {
            builder.append("a");
        }

        return builder.toString();
    }
}
