SELECT books.id, books.author, books.isbn, books.title, pages.content, pages.chapter
	FROM public.books
    LEFT OUTER JOIN pages ON (books.id = pages.book_id);