package progkorny.bookpurchaseweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progkorny.bookpurchaseweb.model.Customer;
import progkorny.bookpurchaseweb.service.CustomerService;

import java.util.List;
// Ez az osztály egy REST API vezérlő (controller), amely az ügyfelek kezeléséért felel.
// Az URL-ek a "/api/customers" útvonalon keresztül érhetők el.
@RestController
@RequestMapping("api/customers")
public class CustomerRESTController {
    // A customerService példányt a Spring automatikusan injektálja (feltéve, hogy létezik bean).
    @Autowired
    private CustomerService customerService;
    // GET /api/customers
    // Visszaadja az összes ügyfelet egy listában.
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    // GET /api/customers/{id}
    // Egy adott ügyfél lekérdezése azonosító (id) alapján.
    // Ha nincs ilyen ügyfél, akkor 404 Not Found választ ad.
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        if(customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }
    // POST /api/customers
    // Új ügyfél létrehozása, vagy meglévő frissítése.
    // A kérés törzse egy JSON objektum, amit a Customer típusra konvertál a Spring.
    @PostMapping
    public Customer putCustomerIntoDb(@RequestBody Customer cust) {
        return customerService.insertOrUpdateCustomer(cust);
    }
    // DELETE /api/customers/{id}
    // Ügyfél törlése azonosító alapján.
    // Sikeres törlés esetén visszaad egy szöveges választ → 200 OK
    // Ha nem található, akkor → 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable int id) {
        if(customerService.deleteCustomerById(id)) {
            return ResponseEntity.ok("customer with id " + id + " is deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
