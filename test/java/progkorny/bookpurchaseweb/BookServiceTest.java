package progkorny.bookpurchaseweb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import progkorny.bookpurchaseweb.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import progkorny.bookpurchaseweb.repository.BookRepository;

class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	private Book book;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		book = new Book();
		book.setId(1L);
		book.setTitle("Prédák háza");
		book.setAuthor("Diana Hunt");
		book.setPublisher("Magánkiadás");
		book.setYearOfPublication(2024);
		book.setAvailable(true);
		book.setPrice(5310);
		book.setCategory("Krimi");
	}

	@Test
	void testGetAllBooks() {
		List<Book> boats = List.of(book);
		when(bookRepository.findAll()).thenReturn(List.of(book));

		List<Book> result = bookService.getAllBooks();

		assertEquals(1, result.size());
		verify(bookRepository).findAll();
	}

	@Test
	void testGetBookById_Found() {
		when(bookRepository.findAll()).thenReturn(List.of(book));

		Optional<Book> result = bookService.getBookById(1L);

		assertTrue(result.isPresent());
		assertEquals("Prédák háza", result.get().getTitle());
	}

	@Test
	void testGetBookById_NotFound() {
		when(bookRepository.findById(99L)).thenReturn(Optional.empty());

		Optional<Book> result = bookService.getBookById(99L);

		assertTrue(result.isEmpty());
	}

	@Test
	void testCreateBook() {
		when(bookRepository.save(any(Book.class))).thenReturn(book);

		Book result = bookService.createBook(book);

		assertNotNull(result);
		assertEquals("Prédák háza", result.getTitle());
		verify(bookRepository).save(book);
	}

	@Test
	void testUpdateBook_Success() {
		Book updatedBook = new Book();
		updatedBook.setTitle("Updated Title");
		updatedBook.setAuthor("Updated Author");
		updatedBook.setPublisher("Updated Publisher");
		updatedBook.setYearOfPublication(2024);
		updatedBook.setAvailable(false);
		updatedBook.setPrice(3000);
		updatedBook.setCategory("Update Category");

		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Book result = bookService.updateBook(1L, updatedBook);

		assertEquals("Updated Title", result.getTitle());
		assertEquals("Updated Author", result.getAuthor());
		assertEquals("Updated Publisher", result.getPublisher());
		assertEquals(2024, result.getYearOfPublication());
		assertFalse(result.isAvailable());
		assertEquals(3000, result.getPrice());
		assertEquals("Update Category", result.getCategory());

		verify(bookRepository).findById(1L);
		verify(bookRepository).save(result);
	}

	@Test
	void testUpdateBook_NotFound() {
		when(bookRepository.findById(99L)).thenReturn(Optional.empty());

		Book updatedBook = new Book();

		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> bookService.updateBook(99L, updatedBook));

		assertEquals("Boat not found", ex.getMessage());
	}

	@Test
	void testDeleteBook_Exists() {
		when(bookRepository.existsById(1L)).thenReturn(true);
		doNothing().when(bookRepository).deleteById(1L);

		boolean result = bookService.deleteBook(1L);

		assertTrue(result);
		verify(bookRepository).existsById(1L);
		verify(bookRepository).deleteById(1L);
	}

	@Test
	void testDeleteBook_NotExists() {
		when(bookRepository.existsById(99L)).thenReturn(false);

		boolean result = bookService.deleteBook(99L);

		assertFalse(result);
		verify(bookRepository).existsById(99L);
		verify(bookRepository, never()).deleteById(anyLong());
	}
}
