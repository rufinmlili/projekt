package progkorny.bookpurchaseweb.repository;

import progkorny.bookpurchaseweb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progkorny.bookpurchaseweb.model.Purchase;


// A @Repository annotációval jelezzük, hogy ez az osztály egy Spring Data Repository,
// amely az adatbázis műveletekért felelős a Book entitás számára.
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // A JpaRepository biztosítja az alapvető CRUD műveleteket (Create, Read, Update, Delete)
    // és további hasznos metódusokat.
    // A paraméterek:
    // - Book: az entitás típusa, amellyel dolgozunk
    // - Long: az entitás azonosítójának típusa (Book.id típusa)
}
