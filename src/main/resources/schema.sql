DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS customer;

CREATE TABLE book (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255),
  publisher VARCHAR(255),
  yearOfPublication INT NOT NULL,
  available BOOLEAN NOT NULL,
  price INT NOT NULL,
  category VARCHAR(255) NOT NULL,
  CONSTRAINT pk_book PRIMARY KEY (id)
);


CREATE TABLE customer
(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name            VARCHAR(255),
    last_name             VARCHAR(255),
    email                 VARCHAR(255),
    phone_number          VARCHAR(255),
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE purchase
(
    id                 INT              NOT NULL,
    book_purchase_id      BIGINT,
    purchase_customer_id  INT,
    purchase_date         DATE,
    total_cost          DOUBLE PRECISION NOT NULL,
    is_closed           BOOLEAN          NOT NULL,
    CONSTRAINT pk_purchase PRIMARY KEY (id),
    CONSTRAINT fk_book FOREIGN KEY (book_purchase_id) REFERENCES book(id),
    CONSTRAINT fk_customer FOREIGN KEY (purchase_customer_id) REFERENCES customer(id)
);
