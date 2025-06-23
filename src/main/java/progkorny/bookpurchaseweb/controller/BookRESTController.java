package progkorny.bookpurchaseweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.OptimisticLockingFailureException;
import progkorny.bookpurchaseweb.model.Book;
import progkorny.bookpurchaseweb.service.BookService;

import java.util.List;
// Ez az osztály egy REST API vezérlő (controller), amely a könyvek kezeléséért felel.
// Az URL-ek a "/api/book" útvonalról érhetők el.
@RestController
@RequestMapping("/api/book")
public class BookRESTController {
    // A könyvekkel kapcsolatos üzleti logikát egy külön szolgáltatás (service) kezeli.
    // Az @Autowired automatikusan betölti a BookService példányt a Spring konténerből.
    @Autowired
    private BookService bookService;
    // GET /api/books
    // Minden könyv lekérdezése.
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    // GET /api/books/{id}
    // Egy adott könyv lekérdezése azonosító alapján.
    // Ha nem található, 404 NOT FOUND válasszal tér vissza.
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // POST /api/books
    // Új könyv létrehozása. A kérés törzsében (body) JSON formátumban küldjük a könyv adatait.
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
    // PUT /api/books/{id}
    // Egy meglévő könyv frissítése azonosító alapján.
    // Optimista zárolás esetén 409 Conflict hibaüzenettel tér vissza.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book) {
        try {
            Book updated = bookService.updateBook(id, book);
            return ResponseEntity.ok(updated);
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.status(409).body("Hiba: A könyvet időközben más is módosította.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // DELETE /api/books/{id}
    // Könyv törlése azonosító alapján.
    // Ha sikeres → 204 No Content, ha nem található → 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
