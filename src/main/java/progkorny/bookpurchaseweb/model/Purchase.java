package progkorny.bookpurchaseweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

// Az @Entity annotációval megjelöljük, hogy ez az osztály egy adatbázis-entitás (JPA entitás)
@Entity
public class Purchase {

    // Az esemény egyedi azonosítója (manuálisan kell beállítani, mivel nincs @GeneratedValue)
    @Id
    private long id;

    // Több RentalEvent is hivatkozhat egy hajóra (ManyToOne kapcsolat)
    @ManyToOne
    @JoinColumn(name = "book_purchase_id") // Az adatbázisban ez a mező tárolja a hivatkozott hajó ID-ját
    private Book bookPurchase;

    // Több RentalEvent is tartozhat egy ügyfélhez
    @ManyToOne
    @JoinColumn(name = "purchase_customer_id") // Külső kulcs a customer(id)-ra
    private Customer purchaseCustomer;

    // A kölcsönzés kezdő dátuma
    private LocalDate purchaseDate;

    // A bérlés teljes költsége
    private double totalCost;

    // Meg van-e zárva a bérlés (pl. visszahozták-e a hajót)
    private boolean isClosed;

    // --- Getterek és Setterek ---

    public Customer getPurchaseCustomer() {
        return purchaseCustomer;
    }

    public void setPurchaseCustomer(Customer purchaseCustomer) {
        this.purchaseCustomer = purchaseCustomer;
    }

    public Book getBookPurchase() {
        return bookPurchase;
    }

    public void setBookPurchase(Book bookPurchase) {
        this.bookPurchase = bookPurchase;
    }

    // --- Konstruktor Builderrel ---

    // Privát konstruktor, amelyet a Builder használ
    private Purchase(Builder builder) {
        this.id = builder.id;
        this.bookPurchase = builder.bookPurchase;
        this.purchaseCustomer = builder.purchaseCustomer;
        this.purchaseDate = builder.purchaseDate;
        this.totalCost = builder.totalCost;
        this.isClosed = builder.isClosed;
    }

    // Üres konstruktor a JPA működéséhez szükséges
    public Purchase() { }

    // Statikus metódus, amivel a Builder-t indíthatjuk
    public static Builder builder() {
        return new Builder();
    }

    // --- Belső Builder osztály ---
    public static class Builder {
        private long id;
        private Book bookPurchase;
        private Customer purchaseCustomer;
        private LocalDate purchaseDate;
        private double totalCost;
        private boolean isClosed;

        public Builder id(long id) { this.id = id; return this; }
        public Builder book(Book book) { this.bookPurchase = book; return this; }
        public Builder customer(Customer customer) { this.purchaseCustomer = customer; return this; }
        public Builder purchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; return this; }
        public Builder totalCost(double totalCost) { this.totalCost = totalCost; return this; }
        public Builder isClosed(boolean isClosed) { this.isClosed = isClosed; return this; }

        public Purchase build() {
            return new Purchase(this);
        }
    }

    // --- Getterek és setterek ---

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return bookPurchase;
    }

    public void setBook(Book book) {
        this.bookPurchase = book;
    }

    public Customer getCustomer() {
        return purchaseCustomer;
    }

    public void setCustomerId(Customer customer) {
        this.purchaseCustomer = customer;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    // --- toString metódus: naplózáshoz, hibakereséshez hasznos ---
    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", book=" + bookPurchase +
                ", customer=" + purchaseCustomer +
                ", purchaseDate=" + purchaseDate +
                ", totalCost=" + totalCost +
                ", isClosed=" + isClosed +
                '}';
    }
}
