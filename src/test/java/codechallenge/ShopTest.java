package codechallenge;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.util.ArrayList;

import static codechallenge.utils.testUtils.importBooks;
import static codechallenge.utils.testUtils.provideTestBooksForShop;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    final CurrencyUnit euro = CurrencyUnit.EUR;
    Shop testShop;
    Customer testCustomerRich;
    Customer testCustomerPoor;
    ArrayList<Book> testBooks;

    @BeforeEach
    void setUp() throws IOException {
        testShop = new Shop("myTestShop", euro);
        testCustomerRich = new Customer("testerRich", Money.parse(euro + " 5000"));
        testCustomerPoor = new Customer("testerPoor", Money.parse(euro + " 1"));
        testBooks = importBooks();
    }

    @AfterEach
    void tearDown() {
        testShop = null;
        testCustomerRich = null;
        testCustomerPoor = null;
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/IsbnTestValues.csv", numLinesToSkip = 1)
    void testAddBook(String isbn, boolean hasValidIsbn, String priceCSV, BookGenres genre, int pageCount) {

        Money price = Money.parse(priceCSV);
        String bookTitle = "test: " + hasValidIsbn;
        Book testBook = new Book(bookTitle, isbn, price, genre, pageCount);

        testShop.addBook(testBook);
        ArrayList<Book> availableBooks = testShop.getAvailableBooks();

        String testBookTitle = testBook.getTitle();

        for (Book book : availableBooks) {

            String addedBookTitle = book.getTitle();

            assertFalse(addedBookTitle.contains("false"), "testBookTitle was :" + testBookTitle);
        }
    }

    @Test
    void testSellBookToPoorCustomer() {
        provideTestBooksForShop(testShop, testBooks);
        assertTrue(testCustomerPoor.getBooks().isEmpty());

        Book book = testShop.findBookByTitle("expensiveBook");
        testShop.sellBook(book, testCustomerPoor);

        assertTrue(testCustomerPoor.getBooks().isEmpty());
        assertTrue(testShop.getSalesVolume().isEqual(Money.parse("EUR 0.0")));

    }

    @Test
    void testSellCheapBookToRichCustomer() {
        provideTestBooksForShop(testShop, testBooks);
        Money salesVolumeBefore = testShop.getSalesVolume();

        Book expensiveComic = testShop.findBookByTitle("expensiveComic");

        assertTrue(testCustomerRich.getBooks().isEmpty());
        assertTrue(salesVolumeBefore.isZero());
        int quantityOfBookToBeSold = testShop.getQuantity(expensiveComic);
        assertEquals(10, quantityOfBookToBeSold);

        testShop.sellBook(expensiveComic, testCustomerRich);

        assertFalse(testCustomerRich.getBooks().isEmpty());
        Book firstBookOfCustomer = testCustomerRich.getBooks().get(0);
        assertEquals(firstBookOfCustomer, expensiveComic);

        Money salesVolumeAfter = testShop.getSalesVolume();
        assertTrue(salesVolumeAfter.isEqual(Money.parse("EUR 120.00")));
        assertEquals(9, testShop.getQuantity(expensiveComic));

    }


    @Test
    void testFilterBooksByGenre() {
        provideTestBooksForShop(testShop, testBooks);
        ArrayList<Book> fantasyBooks = testShop.filterBooksByGenre(BookGenres.Fantasy);
        System.out.println("fantasyBooks " + fantasyBooks);
        assertEquals(2, fantasyBooks.size());

        ArrayList<Book> comicBooks = testShop.filterBooksByGenre(BookGenres.Comic);
        System.out.println("comics:" + comicBooks);
        assertEquals(1, comicBooks.size());
    }

}