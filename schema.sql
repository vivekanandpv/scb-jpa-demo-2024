CREATE TABLE customer(
                         customer_id SERIAL PRIMARY KEY,
                         first_name VARCHAR(200) NOT NULL,
                         last_name VARCHAR(200),
                         email VARCHAR(200) NOT NULL,
                         phone VARCHAR(15) NOT NULL,
                         active bool NOT NULL,
                         password VARCHAR(200) NOT NULL,
                         role VARCHAR(50) NOT NULL
);

CREATE TABLE account(
                        id SERIAL PRIMARY KEY,
                        account_type INTEGER NOT NULL,
                        account_number BIGINT NOT NULL,
                        account_branch VARCHAR(100),
                        account_balance NUMERIC(15,2),
                        customer_id INTEGER NOT NULL,
                        CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);