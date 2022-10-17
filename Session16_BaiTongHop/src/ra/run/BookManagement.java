package ra.run;

import ra.entity.Author;
import ra.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    static List<Author> listAuthor = new ArrayList<>();
    static List<Book> listBook = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //lay du lieu tu file
        Book bookGet = new Book();
        bookGet.getData(null, listBook);
        Author authorGet = new Author();
        authorGet.getData(listAuthor, null);
        do {
            System.out.println("**************QUAN LY CUA HANG SACH****************");
            System.out.println("1. Quan ly tac gia");
            System.out.println("2. Quan ly sach");
            System.out.println("3. Thoat");
            System.out.print("Su lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    BookManagement.displayMenuAuthor(sc);
                    break;
                case 2:
                    BookManagement.displayMenuBook(sc);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui long chon tu 1-3");
            }
        } while (true);
    }

    public static void displayMenuAuthor(Scanner sc) {
        boolean existAuthor = true;
        do {
            System.out.println("*******************QUAN LY TAC GIA*******************");
            System.out.println("1. Danh sach tac gia");
            System.out.println("2. Them cac tac gia");
            System.out.println("3. Cap nhat thong tin tac gia");
            System.out.println("4. Cap nhat trang thai tac gia");
            System.out.println("5. Thoat");
            int choiceAuthor = Integer.parseInt(sc.nextLine());
            switch (choiceAuthor) {
                case 1:
                    BookManagement.displayListAuthor();
                    break;
                case 2:
                    BookManagement.inputListAuthor(sc);
                    break;
                case 3:
                    BookManagement.updateAuthor(sc);
                    break;
                case 4:
                    BookManagement.updateAuthorStatus(sc);
                    break;
                case 5:
                    existAuthor = false;
                    break;
                default:
                    System.err.println("Vui long chon tu 1-5");
            }
        } while (existAuthor);
    }

    public static void displayListAuthor() {
        System.out.printf("%-10s%-50s%-15s\n", "Ma tac gia", "Ten tac gia", "Trang thai");
        for (Author author : listAuthor) {
            author.displayData();
        }
    }

    public static void inputListAuthor(Scanner sc) {
        System.out.println("Vui long nhap vao so tac gia can nhap thong tin: ");
        int cnt = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < cnt; i++) {
            Author author = new Author();
            author.inputData(sc, listAuthor, null);
            listAuthor.add(author);
            author.insertData(listAuthor);
        }
    }

    public static void updateAuthor(Scanner sc) {
        System.out.println("Nhap vao ma tac gia can cap nhat thong tin: ");
        int authorIdUpdate = Integer.parseInt(sc.nextLine());
        boolean checkExist = false;
        for (Author author : listAuthor) {
            if (author.getAuthorId() == authorIdUpdate) {
                //cap nhat thong tin
                checkExist = true;
                System.out.println("Nhap vao ten tac gia: ");
                String authorName = sc.nextLine();
                if (authorName.trim() != "" && authorName.trim().length() != 0) {
                    do {
                        if (authorName.length() >= 6 && authorName.length() <= 50) {
                            author.setAuthorName(authorName);
                            break;
                        }
                    } while (true);
                }
                System.out.println("Chon trang thai tac gia: ");
                System.out.println("1. Hoat dong");
                System.out.println("2. Khong hoat dong");
                System.out.println("3. Khong cap nhat");
                System.out.print("Su lua chon cua ban: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        author.setAuthorStatus(true);
                        break;
                    case 2:
                        author.setAuthorStatus(false);
                        break;
                }
                //Ghi de ra file
                author.insertData(listAuthor);
                break;
            }
        }
        if (!checkExist) {
            System.err.println("Khong tim thay tac gia co ma nhu vay");
        }
    }


    public static void updateAuthorStatus(Scanner sc) {
        System.out.println("Nhap vao ma tac gia can cap nhat thong tin: ");
        int authorIdUdpate = Integer.parseInt(sc.nextLine());
        for (Author author : listAuthor) {
            if (author.getAuthorId() == authorIdUdpate) {
                author.setAuthorStatus(!author.isAuthorStatus());
                author.insertData(listAuthor);
                break;
            }
        }

    }

    public static void displayMenuBook(Scanner sc) {
        boolean existBook = true;
        do {
            System.out.println("******************QUAN LY SACH*******************");
            System.out.println("1. Danh sach sach");
            System.out.println("2. Them cac sach");
            System.out.println("3. Cap nhat thong tin sach");
            System.out.println("4. Cap nhat trang thai sach");
            System.out.println("5. Tinh loi nhuan sach");
            System.out.println("6. Sap xep sach theo gia tang dan");
            System.out.println("7. Tim kiem sach theo ten sach, ten tac gia");
            System.out.println("8. Ban sach");
            System.out.println("9. Thoat");
            System.out.print("Su lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    BookManagement.displayListBook();
                    break;
                case 2:
                    BookManagement.inputListBook(sc);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    existBook = false;
                    break;
                default:
                    System.err.println("Vui long chon tu 1-9");
            }
        } while (existBook);
    }

    public static void displayListBook() {
        System.out.printf("%-10s%-50s%-10s%-10s%-30s%-30s%-15s\n", "Ma sach", "Ten sach", "Gia ban", "So luong", "Tieu de", "Nha xuat ban", "Trang thai");
        for (Book book : listBook) {
            book.displayData();
        }
    }

    public static void inputListBook(Scanner sc){
        System.out.println("Nhap vao so luong sach can nhap thong tin: ");
        int cnt = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < cnt; i++) {
            Book book = new Book();
            book.inputData(sc,listAuthor,listBook);
            listBook.add(book);
            book.insertData(listBook);
        }
    }
}
