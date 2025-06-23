package progkorny.bookpurchaseweb.service;

import progkorny.bookpurchaseweb.exception.NosuchEntityException;
import progkorny.bookpurchaseweb.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  // Service réteg komponens, a Spring automatikusan kezeli az életciklust
public class BookServiceWithArrayList {

    // Könyvek listája, inicializálva 3 előre definiált könyvvel
    private List<Book> books = new ArrayList<>(List.of(
            createBook(1L, "Micimacskó"),
            createBook(2L, "Vuk"),
            createBook(3L, "Német szótár")
    ));

    // Segédfüggvény könyv létrehozására adott id és cím alapján
    private Book createBook(Long id, String title) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        // További alapértelmezett értékek beállítása
        book.setAuthor("Ismeretlen");
        book.setPublisher("Ismeretlen");
        book.setYearOfPublication(2000);
        book.setAvailable(true);
        book.setPrice(1000);
        book.setCategory("Ismeretlen");
        return book;
    }

    // Visszaadja az összes könyvet listában
    public List<Book> getAllBooks() {
        return books;
    }

    // Könyv keresése ID alapján, ha nincs ilyen könyv, kivételt dob
    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))  // Szűrés azonosító alapján
                .findFirst()                              // Az első találat
                .orElseThrow(() ->                        // Ha nincs találat, kivétel
                        new NosuchEntityException("There is no Book with id: " + id));
    }

    // Beszúr egy új könyvet vagy frissíti a meglévőt azonosító alapján
    public int insertOrUpdateBook(Book book) {
        // Először törli, ha már van ilyen ID-jű könyv a listában
        books.removeIf(b -> b.getId().equals(book.getId()));
        // Majd hozzáadja az új vagy frissített könyvet
        books.add(book);
        // Visszatér a listában lévő könyvek számával
        return books.size();
    }

    // Könyv törlése ID alapján
    public boolean deleteBookById(Long id) {
        Optional<Book> bookToDelete = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();

        if (bookToDelete.isPresent()) {
            books.remove(bookToDelete.get());  // Ha megtalálta, törli
            return true;
        } else {
            // Ha nem található a könyv, kivételt dob
            throw new NosuchEntityException("There is no Book to delete with id: " + id);
        }
    }
}
