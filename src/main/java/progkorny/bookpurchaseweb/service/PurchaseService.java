package progkorny.bookpurchaseweb.service;

import progkorny.bookpurchaseweb.model.Book;
import progkorny.bookpurchaseweb.model.Customer;
import progkorny.bookpurchaseweb.model.Purchase;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service  // Ez az annotáció jelzi, hogy ez egy Spring service komponens
public class PurchaseService {


    // Purchase lista, egyetlen elem példányosítva builder patternnel
    private List<Purchase> purchase = new ArrayList<Purchase>(List.of(
            Purchase.builder()
                    .id(1)                            // Az esemény azonosítója
                    .book(new Book())                 // Új könyv objektum (üres példány)
                    .customer(new Customer("Virág", "Nagy", "virag.nagy@example.com", "+3612345678"))         // Új ügyfél objektum (üres példány)
                    .purchaseDate(LocalDate.of(2025, 3, 18))  // Vásárlás kezdete
                    .isClosed(true)                          // Vásárlás lezárva
                    .totalCost(8000)                        // Fizetett összeg
                    .build()));

    // Visszaadja az összes tárolt Purchase-et
    public List<Purchase> getAllPurchase() {
        return purchase;
    }
}
