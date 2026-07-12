CREATE TABLE transactions (
     id UUID NOT NULL PRIMARY KEY,
     account_id UUID NOT NULL,
     amount DECIMAL(19,2) NOT NULL,
     description VARCHAR(100) NOT NULL,
     balance DECIMAL(19,2) NOT NULL,
     status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
     type VARCHAR(20) NOT NULL,
     created_at TIMESTAMP NOT NULL
);