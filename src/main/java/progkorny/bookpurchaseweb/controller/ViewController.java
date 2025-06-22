package progkorny.bookpurchaseweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// Ez a vezérlő felelős a HTML nézetek (weboldalak) kiszolgálásáért.
// Nem REST API, hanem klasszikus MVC Controller, amely sablonfájlokat szolgál ki.
@Controller
public class ViewController {
    // GET /
    // A főoldal megjelenítése.
    // A visszatérési érték egy sablonfájl neve (templates/index.html).
    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }
    // GET /delete
    // A hajó törléséhez tartozó oldal megjelenítése.
    // Pl. form vagy lista törléshez.
    @GetMapping("/delete")
    public String deleteBookPage() {
        return "delete-book"; // templates/delete-book.html
    }
    // GET /upload
    // Új hajó feltöltéséhez (regisztrálásához) tartozó oldal.
    @GetMapping("/upload")
    public String uploadBookPage() {
        return "upload-book"; // templates/upload-book.html
    }
    // GET /upload-renter
    // Új ügyfél (bérlő) feltöltésének oldala.
    @GetMapping("/upload-costumer")
    public String uploadCustomerPage() {
        return "upload-costumer"; // templates/upload-costumer.html
    }
}
