package codechallenge;

import org.joda.money.CurrencyUnit;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static codechallenge.utils.ShopUtils.compareShops;
import static codechallenge.utils.UtilsForTests.*;
import static org.junit.jupiter.api.Assertions.*;

class ShopUtilsTest {

    @Test
    void testCompareShopsWithIdenticalOffers() throws IOException {
        Shop testShopA = new Shop("shopA", CurrencyUnit.EUR);
        Shop testShopB = new Shop("shopB", CurrencyUnit.EUR);
        ArrayList<Book> testBooks = importBooks();
        ArrayList<Book> testBooks2 = importBooks();

        provideTestBooksForShop(testShopA, testBooks);
        provideTestBooksForShop(testShopB, testBooks2);

        assertTrue(compareShops(testShopA, testShopB));
    }

    @Test
    void testCompareShopsIfShopsHaveDifferentOffers() throws IOException {
        Shop testShopA = new Shop("shopA", CurrencyUnit.EUR);
        Shop testShopB = new Shop("shopB", CurrencyUnit.EUR);
        ArrayList<Book> testBooks = importBooks();
        ArrayList<Book> testBooks2 = importBooks();

        provideTestBooksForShop(testShopA, testBooks);
        provideTestBooksForShop(testShopB, testBooks2);

        Book cleanCodeBook = provideCleanCodeBook();
        testShopA.addBook(cleanCodeBook);

        assertFalse(compareShops(testShopA, testShopB));
        assertTrue(testShopA.getAvailableBooks().contains(cleanCodeBook));
        assertFalse(testShopB.getAvailableBooks().contains(cleanCodeBook));
    }

    @Test
    void testCompareShopsOnShopsWithoutAnyOffers() {
        Shop testShopA = new Shop("shopA", CurrencyUnit.EUR);
        Shop testShopB = new Shop("shopB", CurrencyUnit.EUR);

        assertTrue(compareShops(testShopA, testShopB));
    }


}