package codechallenge.utils;

import codechallenge.Book;
import codechallenge.BookGenres;
import codechallenge.Shop;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import org.joda.money.Money;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class testUtils {

    @NotNull
    public static ArrayList<Book> getTestBooksFromCSV() throws IOException {
        ArrayList<Book> testBooks = new ArrayList<>();
        String[] HEADERS = {"title", "ISBN", "price", "genre", "pageCount"};
        Reader in = new FileReader("src/main/resources/bookList.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);


        for (CSVRecord record : records) {
            String bookTitle = record.get("title");
            String bookIsbn = record.get("isbn");
            Money bookPrice = Money.parse(record.get("price"));
            BookGenres genre = BookGenres.valueOf(record.get("genre"));
            int pageCount = Integer.parseInt(record.get("pageCount"));

            testBooks.add(new Book(bookTitle, bookIsbn, bookPrice, genre, pageCount));

        }
        return testBooks;
    }


    public static void provideTestBooksForShop(Shop shop, List<Book> testBooks) {
               //add every book 10 times
        for (Book book : testBooks) {
            for (int i = 0; i < 10; i++) {
                shop.addBook(book);
            }
        }
    }
}
