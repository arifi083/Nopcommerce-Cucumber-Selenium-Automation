package utils;

public class Utils {
    public static int generateRandomNumber(int min, int max) {

        return (int) Math.round(Math.random() * (max - min) + min);
    }
}
