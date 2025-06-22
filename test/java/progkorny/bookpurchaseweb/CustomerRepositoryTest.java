package progkorny.bookpurchaseweb.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import progkorny.bookpurchaseweb.model.Customer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void saveAndFindCustomer() {
        Customer customer = new Customer("Lenke", "Nagy", "lenke.nagy@example.com", "+3612345678");
        customer.setFirstName("András");
        customer.setLastName("Varga");
        customer.setEmail("andras.vrg@example.com");
        customer.setPhoneNumber("+3612121212");

        customerRepository.save(customer);

        Integer id = customer.getId();  // ID-t most kapja meg az adatbázisból

        Optional<Customer> found = customerRepository.findById(id);

        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("András");
        assertThat(found.get().getEmail()).isEqualTo("andras.vrg@example.com");
    }

    @Test
    void deleteCustomer_shouldRemoveFromDb() {
        Customer customer = new Customer("Lenke", "Nagy", "virag.nagy@example.com", "+3612345678");
        customer.setFirstName("Károly");
        customer.setLastName("Kis");
        customer.setEmail("karoly.kis@example.com");
        customer.setPhoneNumber("+3630123456");

        customerRepository.save(customer);

        Integer id = customer.getId();

        customerRepository.deleteById(id);

        Optional<Customer> deleted = customerRepository.findById(id);

        assertThat(deleted).isEmpty();
    }
}
