package progkorny.bookpurchaseweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progkorny.bookpurchaseweb.model.Purchase;

// Ez az interfész a RentalEvent entitás adatbázis műveleteit kezeli.
// A JpaRepository által biztosított CRUD műveleteket használja,
// így nincs szükség manuális implementációra.
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    // Paraméterek:
    // RentalEvent: az entitás típusa, amelyhez a repository tartozik
    // Integer: a RentalEvent entitás azonosítójának típusa (id mező típusa)
}
