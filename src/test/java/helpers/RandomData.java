package helpers;

import org.apache.commons.lang3.RandomStringUtils;

public  class RandomData {
    public static String RandomDataInput() {
        String randomSummary = RandomStringUtils.random(10, true, true);
        return randomSummary;
    }
}
