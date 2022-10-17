package ra.inter;

import ra.entity.Author;
import ra.entity.Book;

import java.util.List;
import java.util.Scanner;

public interface IBook{
    String PATH_AUTHOR = "author.txt";
    String PATH_BOOK = "book.txt";
    void inputData(Scanner sc, List<Author> listAuthor, List<Book> listBook);
    void displayData();
    void insertData(Object object);
    void getData(List<Author> listAuthor, List<Book> listBook);
}
