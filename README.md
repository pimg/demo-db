# Data-JPA
This repository uses Spring data JPA with a reactive controller to fetch records from two tables 
using a one-to-many relationship. The blocking JPA call is done on a separate thread using a reactor scheduler.

## database initialization 
In the branch data-JPA the database is initialized, the tables created and the record inserted.
This can provide the base for the other branches. If however you want to create the database manually below are the scripts.
```roomsql
-- Database: dbtest

-- DROP DATABASE dbtest;

CREATE DATABASE dbtest
    WITH 
    OWNER = admin
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
-- Table: public.books

-- DROP TABLE public.books;

CREATE TABLE public.books
(
    id bigint NOT NULL DEFAULT nextval('books_id_seq'::regclass),
    author character varying(255) COLLATE pg_catalog."default",
    isbn character varying(255) COLLATE pg_catalog."default",
    title character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT books_pkey PRIMARY KEY (id),
    CONSTRAINT uk_kibbepcitr0a3cpk3rfr7nihn UNIQUE (isbn)
)

TABLESPACE pg_default;

ALTER TABLE public.books
    OWNER to admin;
    
-- Table: public.pages

-- DROP TABLE public.pages;

CREATE TABLE public.pages
(
    id bigint NOT NULL DEFAULT nextval('pages_id_seq'::regclass),
    chapter character varying(255) COLLATE pg_catalog."default",
    content character varying(255) COLLATE pg_catalog."default",
    "number" integer NOT NULL,
    book_id bigint NOT NULL,
    CONSTRAINT pages_pkey PRIMARY KEY (id),
    CONSTRAINT fk5lpjg9gvqj3g9ukhyecxdf6cq FOREIGN KEY (book_id)
        REFERENCES public.books (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.pages
    OWNER to admin;
```

## Database record insert scripts
```roomsql
INSERT INTO books (id, author, isbn, title) values (1, 'John Doe', '123456', 'Java 101');

INSERT INTO pages (id, book_id, chapter, content, number) values (1, 1, 'Introduction', 'Introduction contents', 1);
INSERT INTO pages (id, book_id, chapter, content, number) values (2, 1, 'Java 8', 'Java 8 contents');
INSERT INTO pages (id, book_id, chapter, content, number) values (2, 1, 'Concurrencly', 'Concurrency contents', 1);
```