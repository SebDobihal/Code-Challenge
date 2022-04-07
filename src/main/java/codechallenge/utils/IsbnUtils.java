package codechallenge.utils;

public class IsbnUtils {

    public static boolean isValidIsbn(String number) {
        number = cleanupInputNumber(number);

        return hasRightLength(number) &&
                hasValidPrefix(number) &&
                isNumber(number) &&
                checkSumIsValid(number);
    }

    private static boolean checkSumIsValid(String number) {
        char checkChar = number.charAt(number.length() - 1);
        int checkDigit = Character.getNumericValue(checkChar);
        int checkSum = 0;
        for (int i = 1; i <= 12; i++) {
            int expo = (i + 1) % 2;
            int ch = number.charAt(i - 1);
            int numericValue = Character.getNumericValue(ch);
            checkSum = (int) (checkSum + (numericValue * (Math.pow(3, expo))));
        }
        checkSum = (10 - (checkSum % 10)) % 10;

        return checkSum == checkDigit;
    }

    private static boolean isNumber(String number) {
        try {
            Long.parseLong(number);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    private static boolean hasValidPrefix(String number) {
        return (number.startsWith("978")) || (number.startsWith("979"));
    }

    private static boolean hasRightLength(String number) {
        return number.length() == 13;
    }

    private static String cleanupInputNumber(String number) {
        number = number.replaceAll("\\s", "");
        number = number.replaceAll("-", "");
        return number;
    }

}
