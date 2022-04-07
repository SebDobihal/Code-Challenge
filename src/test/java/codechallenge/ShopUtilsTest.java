package codechallenge;

import org.joda.money.CurrencyUnit;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static codechallenge.utils.ShopUtils.compareShops;
import static codechallenge.utils.testUtils.getTestBooksFromCSV;
import static codechallenge.utils.testUtils.provideTestBooksForShop;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShopUtilsTest {

    @Test
    void testCompareShops() throws IOException {
        Shop testShopA = new Shop("shopA", CurrencyUnit.EUR);
        Shop testShopB = new Shop("shopB", CurrencyUnit.EUR);
        ArrayList<Book> testBooks = getTestBooksFromCSV();
        ArrayList<Book> testBooks2 = getTestBooksFromCSV();
        provideTestBooksForShop(testShopA, testBooks);
        provideTestBooksForShop(testShopB, testBooks2);

        assertTrue(compareShops(testShopA, testShopB));

    }
}