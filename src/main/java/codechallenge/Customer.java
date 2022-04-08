package codechallenge;

import org.joda.money.Money;

import java.util.ArrayList;

public class Customer {
    private String name;
    private final ArrayList<Book> books = new ArrayList<>();
    private Money amountOfMoney;


    Customer(String name, Money amountOfMoney) {
        this.name = name;
        this.amountOfMoney = amountOfMoney;
    }

    public boolean hasMoreMoneyThanOrTheSame(Money payment) {
        boolean hasMoreThan = this.amountOfMoney.isGreaterThan(payment);
        boolean hasEqualTo = this.amountOfMoney.isEqual((payment));
        return hasMoreThan|| hasEqualTo;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void payMoney(Money payment) {
        this.amountOfMoney = this.amountOfMoney.minus(payment);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    public void receiveMoney(Money amountOfMoney) {
        this.amountOfMoney = this.amountOfMoney.plus(amountOfMoney);
    }
}
