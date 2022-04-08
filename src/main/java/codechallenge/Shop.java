package codechallenge;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import static codechallenge.utils.IsbnUtils.isValidIsbn;
import static java.util.Comparator.comparing;

public class Shop {
    private final HashMap<Book, Integer> inventoryOfBooks = new HashMap<>();
    private String name;
    //Umsatz
    private Money salesVolume;

    public Shop(String name, CurrencyUnit currencyUnit) {
        this.name = name;
        this.salesVolume = Money.of(currencyUnit, new BigDecimal("0.0"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Book, Integer> getInventoryOfBooks() {
        return this.inventoryOfBooks;
    }

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> listOfAvailableBooks = new ArrayList<>();
        HashMap<Book, Integer> inventoryOfBooks = this.inventoryOfBooks;

        for (Book book : inventoryOfBooks.keySet()) {
            if (isAvailable(book)) {
                listOfAvailableBooks.add(book);
            }
        }
        listOfAvailableBooks.sort(comparing(Book::getTitle));
        return listOfAvailableBooks;
    }

    public Money getSalesVolume() {
        return salesVolume;
    }

    public void addBook(Book book) {
        if (isValidIsbn(book.getIsbn())) {
            updateQuantity(book, 1);
        }
    }

    public int getQuantity(Book book) {
        return this.inventoryOfBooks.getOrDefault(book, 0);
    }

    private boolean isAvailable(Book book) {
        return getQuantity(book) > 0;
    }

    public void sellBook(Book book, Customer customer) {
        Money bookPrice = book.getPrice();
        boolean hasEnoughMoney = customer.hasMoreMoneyThanOrTheSame(bookPrice);

        if (isAvailable(book) && hasEnoughMoney) {
            updateQuantity(book, -1);
            this.salesVolume = this.salesVolume.plus(bookPrice);

            customer.payMoney(bookPrice);
            customer.addBook(book);
        }
    }

    public Book findBookByTitle(String title) {
        for (Book book : this.getAvailableBooks()) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }


    private void updateQuantity(Book book, int amount) {
        Integer quantityOfThisBook = getQuantity(book) + amount;
        this.inventoryOfBooks.put(book, quantityOfThisBook);
    }

    public ArrayList<Book> filterBooksByGenre(BookGenres requestedGenre) {
        ArrayList<Book> booksOfRequestedGenre = new ArrayList<>();

        for (Book book : this.inventoryOfBooks.keySet()) {
            if (requestedGenre.equals(book.getGenre())) {
                booksOfRequestedGenre.add(book);
            }
        }

        return booksOfRequestedGenre;
    }


}
