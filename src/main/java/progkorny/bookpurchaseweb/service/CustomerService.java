package progkorny.bookpurchaseweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progkorny.bookpurchaseweb.model.Customer;
import progkorny.bookpurchaseweb.repository.CustomerRepository;

import java.util.List;

@Service  // Service komponens a Springben, az üzleti logikáért felel
public class CustomerService {

    @Autowired  // Automatikus injektálás a CustomerRepository példányába
    private CustomerRepository customerRepository;

    // Visszaadja az összes ügyfelet az adatbázisból
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Egy ügyfél lekérése azonosító alapján, ha nincs ilyen, null-lal tér vissza
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    // Új ügyfél beszúrása vagy meglévő frissítése
    public Customer insertOrUpdateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Ügyfél törlése ID alapján, visszatérési érték jelzi, sikerült-e a törlés
    public boolean deleteCustomerById(int id) {
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
