package progkorny.bookpurchaseweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progkorny.bookpurchaseweb.model.Book;
import progkorny.bookpurchaseweb.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service  // Service réteg komponensként jelöli az osztályt, a Spring kezeli az életciklust
public class BookService {

    @Autowired  // Automatikusan beszúrja a BoatRepository példányt (dependency injection)
    private BookRepository bookRepository;

    // Összes könyv lekérdezése az adatbázisból
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Egy könyv lekérdezése ID alapján, Optional típusban visszatérve (lehet üres)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Új könyv létrehozása és mentése az adatbázisba
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional  // Tranzakciókezelés: az egész metódus vagy végrehajtódik, vagy nem
    public Book updateBook(Long id, Book updatedBook) {
        // Megkeressük az adott ID-jű könyvet, ha nincs, RuntimeException dobódik
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Optimistic Locking: a @Version mező használatával automatikus versenykezelés a frissítések között

        // A létező könyv adatainak frissítése az új adatokkal
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setPublisher(updatedBook.getPublisher());
        existing.setYearOfPublication(updatedBook.getYearOfPublication());
        existing.setAvailable(updatedBook.isAvailable());
        existing.setPrice(updatedBook.getPrice());
        existing.setCategory(updatedBook.getCategory()); // Verzió mező frissítése, hogy az optimista zárolás működjön

        // Mentés az adatbázisba, frissített entitás visszaadása
        return bookRepository.save(existing);
    }

    // Könyv törlése ID alapján, visszatérési érték jelzi, sikerült-e a törlés
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {  // Ellenőrizzük, hogy létezik-e a könyv
            bookRepository.deleteById(id);     // Törlés adatbázisból
            return true;                       // Törlés sikeres
        } else {
            return false;                      // Nem találtuk a könyvet, törlés nem történt
        }
    }
}
