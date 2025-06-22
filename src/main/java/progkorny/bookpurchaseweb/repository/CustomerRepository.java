package progkorny.bookpurchaseweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progkorny.bookpurchaseweb.model.Customer;

// Ez az interfész a Customer entitás adatbázis műveleteiért felelős.
// Az JpaRepository alap CRUD és lekérdezési metódusokat biztosít.
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Paraméterek:
    // Customer: az entitás típusa, amelyet kezelünk
    // Integer: a Customer entitás azonosítójának típusa (id mező típusa)
}
