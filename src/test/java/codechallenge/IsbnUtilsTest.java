package codechallenge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static codechallenge.utils.IsbnUtils.isValidIsbn;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IsbnUtilsTest {

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/IsbnTestValues.csv", numLinesToSkip = 1)
    void testIsValidIsbn(String number, boolean expectedResult) {
        assertEquals(expectedResult, isValidIsbn(number));
    }
}