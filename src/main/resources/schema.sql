CREATE TABLE CUSTOMER_ACCOUNT( ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                CUSTOMER_ID INTEGER NOT NULL,
                                FIRST_NAME VARCHAR(250) NOT NULL,
                                SURNAME VARCHAR(250), BALANCE DECIMAL,
                                LAST_MODIFIED_DATE DATE NOT NULL DEFAULT CURRENT_TIMESTAMP);

CREATE TABLE ACCOUNT(ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      INITIAL_BALANCE DECIMAL,
                      ACCOUNT_TYPE VARCHAR(100) NOT NULL DEFAULT 'DEFAULT_ACCOUNT',
                      CREATION_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                     )
