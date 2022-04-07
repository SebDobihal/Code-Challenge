package codechallenge;

import org.joda.money.Money;

public class Book {
    private String title;
    private Money price;
    private BookGenres genre;
    private int pageCount;
    private String isbn;

    public Book(String title, String isbn, Money price, BookGenres genre, int pageCount) {
        this.title = title;
        this.price = price;
        this.genre = genre;
        this.pageCount = pageCount;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        //could validate isbn here, but exercise implies books with faulty isbn could exist
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public BookGenres getGenre() {
        return genre;
    }

    public void setGenre(BookGenres genre) {
        this.genre = genre;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", genre=" + genre +
                ", pageCount=" + pageCount +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        final Book other = (Book) obj;
        return this.title.equals(other.title) && this.isbn.equals(other.isbn);
    }
}
