package progkorny.bookpurchaseweb.controller;

import progkorny.bookpurchaseweb.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import progkorny.bookpurchaseweb.service.PurchaseService;

import java.util.List;
// Ez a vezérlő a vásárlási események (purchase) REST alapú lekérdezéséért felel.
// Az elérési út: /api/purchase
@RestController()
@RequestMapping("api/purchase")
public class PurchaseController {
    // A purchaseService osztály példányát a Spring injektálja be automatikusan.
    // Ez az osztály végzi a háttérben a vásárlási események adatainak elérését és feldolgozását.
    @Autowired
    private PurchaseService purchaseService;
    // GET /api/purchase
    // Visszaadja az összes vásárlási eseményt egy listában.
    @GetMapping()
    public List<Purchase> getAllPurchase()
    {return purchaseService.getAllPurchase(); }

}
