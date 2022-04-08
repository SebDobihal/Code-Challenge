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

import static codechallenge.utils.HeadersCsv.*;

public class testUtils {
    //part of the possible validation structure
    private static ArrayList<String> brokenRecords;
    
    @NotNull
    public static ArrayList<Book> importBooks() throws IOException {
        brokenRecords = new ArrayList<>();
        Reader in = new FileReader("src/main/resources/bookList.csv");
        Iterable<CSVRecord> records = getCsvRecords(in);

        return getValidBooks(records);
    }

    private static ArrayList<Book> getValidBooks(Iterable<CSVRecord> records) {
        ArrayList<Book> books = new ArrayList<>();
        for (CSVRecord record : records) {
            if (validateRecord(record))
                books.add(createBook(record));
        }
        return books;
    }

    private static boolean validateRecord(CSVRecord record) {
        for (HeadersCsv header : values()) {
            if (!header.validate(record.get(header))) {
                brokenRecords.add("record: " + record + " is broken, because of: " + header);
                return false;
            }
        }
        return true;
    }

    private static Book createBook(CSVRecord record) {
        //builder pattern would maybe be cleaner, scope?
        String title = record.get(TITLE);
        String isbn = record.get(ISBN);
        String priceAsString = record.get(String.valueOf(PRICE));
        Money price = Money.parse(priceAsString);
        BookGenres genre = BookGenres.valueOf(record.get(GENRE));
        int pageCount = Integer.parseInt(record.get(PAGECOUNT));

        return new Book(title, isbn, price, genre, pageCount);
    }

    private static Iterable<CSVRecord> getCsvRecords(Reader in) throws IOException {
        CSVFormat delimiter = CSVFormat.Builder.create().setDelimiter(',')
                .setSkipHeaderRecord(true)
                .setHeader(HeadersCsv.class)
                .build();
        return delimiter.parse(in);
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
