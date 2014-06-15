CREATE SCHEMA CAFFE;
SET SCHEMA CAFFE;
CREATE TABLE credit_card_type
(
    ID int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    TYPE varchar(20) NOT NULL
);

CREATE TABLE credit_card
(
    ID int PRIMARY KEY AUTO_INCREMENT NOT NULL ,
    TYPE_ID int NOT NULL REFERENCES credit_card_type ( ID ),
    NUM_HASH text NOT NULL,
    EXP_DATE timestamp NOT NULL,
    SALT varchar(45) NOT NULL
);
CREATE INDEX FK_CREDIR_CARD_TYPE_idx ON credit_card ( TYPE_ID );
CREATE UNIQUE INDEX TYPE_UNIQUE ON credit_card_type ( TYPE );
CREATE TABLE customers
(
    ID int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NAME varchar(45) NOT NULL,
    LAST_NAME varchar(45) NOT NULL,
    EMAIL varchar(45) NOT NULL,
    PWD_HASH varchar(256) NOT NULL,
    CREDIT_CARD_ID int NOT NULL REFERENCES credit_card ( ID ),
    SALT varchar(45) NOT NULL
);
CREATE UNIQUE INDEX EMAIL_UNIQUE ON customers ( EMAIL );
CREATE UNIQUE INDEX CREDIR_CARD_ID_UNIQUE ON customers ( CREDIT_CARD_ID );
CREATE TABLE meal_type
(
    ID int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    TYPR varchar(45) NOT NULL
);
CREATE TABLE meals
(
    ID int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    MEAL_TYPE_ID int NOT NULL REFERENCES meal_type ( ID ),
    PRICE decimal(10,2) NOT NULL,
    NAME varchar(45) NOT NULL,
    PICTURE longblob
);
CREATE UNIQUE INDEX NAME_UNIQUE ON meals ( NAME );
CREATE INDEX FK_MEAL_TYPE_ID_idx ON meals ( MEAL_TYPE_ID );
CREATE TABLE orders
(
    ID int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    CUSTOMER_ID int NOT NULL REFERENCES customers ( ID ),
    SUMM decimal(10,2) NOT NULL
);
CREATE INDEX FK_CUSTOMER_ID_idx ON orders ( CUSTOMER_ID );
CREATE TABLE order_meals
(
    ID int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    ORDER_ID int NOT NULL REFERENCES orders ( ID ),
    MEALS_ID int NOT NULL REFERENCES meals ( ID )
);
CREATE INDEX FK_ORDER_ID_idx ON order_meals ( ORDER_ID );
CREATE INDEX FK_MEALS_ID_idx ON order_meals ( MEALS_ID );

