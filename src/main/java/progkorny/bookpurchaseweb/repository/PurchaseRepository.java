package progkorny.bookpurchaseweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progkorny.bookpurchaseweb.model.Purchase;

// Ez az interfész a Purchase entitás adatbázis műveleteit kezeli.
// A JpaRepository által biztosított CRUD műveleteket használja,
// így nincs szükség manuális implementációra.
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    // Paraméterek:
    // Purchase: az entitás típusa, amelyhez a repository tartozik
    // Integer: a Purchase entitás azonosítójának típusa (id mező típusa)
}
