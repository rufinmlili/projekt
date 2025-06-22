package progkorny.bookpurchaseweb.model;

import jakarta.persistence.*;

// Ez az osztály a "book" nevű adatbázistáblát reprezentálja.
// Az @Entity jelzi, hogy ez egy JPA entitás.
@Entity
@Table(name = "book") // Megadja, hogy az osztály a "book" táblához tartozik.
public class Book {

    // Az elsődleges kulcs (id), amelyet az adatbázis generál automatikusan (AUTO_INCREMENT).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A könyv neve nem lehet null, kötelező mező.
    @Column(nullable = false)
    private String title;

    // A könyv szerzője (nem kötelező mező).
    private String author;

    // A könyv kiadója, kötelező mező.
    @Column(nullable = false)
    private String publisher;

    // Kiadás éve, az adatbázisban külön megnevezett oszlopnévvel (yearOfPublication).
    @Column(name = "yearOfPublication", nullable = false)
    private int yearOfPublication;

    // Elérhetőség jelzése (pl. bérelhető-e jelenleg).
    @Column(nullable = false)
    private boolean available;

    // A könyv ára, kötelező mező.
    @Column(name = "price", nullable = false)
    private int price;

    // A könyv kategóriája (nem verzió mező).
    private String category;

    // Optimista zároláshoz használt verziómező.
    // Ha más is módosítja az adatot, akkor kivétel dobódik mentéskor.
    @Version
    private int version;

    // Üres konstruktor a JPA számára.
    public Book() {
    }

    // Teljes konstruktor, amely minden kötelező mezőt inicializál.
    public Book(String title, String author, String publisher, int yearOfPublication,
                boolean available, int price, String category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
        this.available = available;
        this.price = price;
        this.category = category;
    }

    // Getter metódusok – az osztály mezőinek lekérdezésére szolgálnak.
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getVersion() {
        return version;
    }

    // Setter metódusok – az osztály mezőinek beállítására szolgálnak.
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
