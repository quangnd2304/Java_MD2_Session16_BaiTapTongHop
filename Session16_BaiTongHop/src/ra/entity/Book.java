package ra.entity;

import ra.inter.IBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book implements IBook, Serializable {
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private List<Author> listAuthor = new ArrayList<>();
    private String title;
    private String content;
    private String publisher;
    private boolean bookStatus;
    private static final Float RATE = 1.2F;

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, float profit, int quantity, List<Author> listAuthor, String title, String content, String publisher, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.listAuthor = listAuthor;
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.bookStatus = bookStatus;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Author> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(List<Author> listAuthor) {
        this.listAuthor = listAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public void inputData(Scanner sc, List<Author> listAuthorBook, List<Book> listBook) {
        System.out.println("Nhap vao ma sach: ");
        do {
            this.bookId = sc.nextLine();
            if (this.bookId.length() == 5) {
                if (this.bookId.startsWith("B")) {
                    boolean checkAuthorExist = false;
                    for (Book book : listBook) {
                        if (book.getBookId().equals(this.bookId)) {
                            checkAuthorExist = true;
                            break;
                        }
                    }
                    if (!checkAuthorExist) {
                        break;
                    } else {
                        System.err.println("Ma sach da ton tai, vui long nhap lai");
                    }
                } else {
                    System.err.println("Vui long nhap ma sach bat dau la ky tu B");
                }
            } else {
                System.err.println("Vui long nhap ma sach gom 5 ky tu");
            }
        } while (true);
        System.out.println("Nhap vao ten sach: ");
        do {
            this.bookName = sc.nextLine();
            if (this.bookName.length() >= 10 && this.bookName.length() <= 100) {
                break;
            } else {
                System.err.println("Vui long nhap ten sach gom 10-100 ky tu");
            }
        } while (true);
        System.out.println("Nhap vao gia nhap cua sach: ");
        do {
            try {
                this.importPrice = Float.parseFloat(sc.nextLine());
                if (this.importPrice > 0) {
                    break;
                } else {
                    System.err.println("Vui long nhap gia nhap sach co gia tri lon hon 0");
                }
            } catch (Exception ex) {
                System.err.println("Vui long nhap gia nhap cua sach la so thuc");
            }
        } while (true);
        System.out.println("Nhap vao gia ban cua sach: ");
        do {
            try {
                this.exportPrice = Float.parseFloat(sc.nextLine());
                if (this.exportPrice > this.importPrice * RATE) {
                    break;
                } else {
                    System.err.println("Vui long nhap gia ban sach co gia tri lon hon 20% so voi gia nhap");
                }
            } catch (Exception ex) {
                System.err.println("Vui long nhap gia ban sach la so thuc");
            }
        } while (true);
        System.out.println("Nhap vao so luong sach: ");
        do {
            try {
                this.quantity = Integer.parseInt(sc.nextLine());
                if (this.quantity >= 0) {
                    break;
                } else {
                    System.err.println("Vui long nhap so luong sach lon hon hoac bang 0");
                }
            } catch (Exception ex) {
                System.err.println("Vui long nhap so luong sach la mot so nguyen");
            }
        } while (true);
        System.out.println("Chon cac tac gia cua sach: ");
        do {
            int cnt = 1;
            for (Author author : listAuthorBook) {
                System.out.printf("%d.%s\n", cnt, author.getAuthorName());
                cnt++;
            }
            System.out.print("Chon tac gia: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= listAuthorBook.size()) {
                    //add tac gia chon vao danh sach tac gia cua sach
                    boolean checkAuthorExist = false;
                    for (Author authorExist : this.listAuthor) {
                        if (authorExist.getAuthorId() == listAuthorBook.get(choice - 1).getAuthorId()) {
                            checkAuthorExist = true;
                        }
                    }
                    if (!checkAuthorExist) {
                        this.listAuthor.add(listAuthorBook.get(choice - 1));
                    } else {
                        System.err.println("Tac gia da ton tai trong danh sach tac gia cua sach");
                    }
                    System.out.println("Ban co muon chon them tac gia khong: ");
                    System.out.println("1. Co");
                    System.out.println("2. Khong");
                    System.out.print("Lua chon cua ban: ");
                    int choiceExit = Integer.parseInt(sc.nextLine());
                    if (choiceExit != 1) {
                        break;
                    }
                } else {
                    System.err.println("Vui long chon tac gia trong danh sach");
                }
            } catch (Exception ex) {
                System.err.println("Vui long chon 1 so nguyen");
            }
        } while (true);
        System.out.println("Nhap vao tieu de sach: ");
        do {
            this.title = sc.nextLine();
            if (this.title.length() >= 30 && this.title.length() <= 100) {
                break;
            } else {
                System.err.println("Vui long nhap tieu de tu 30-100 ky tu");
            }
        } while (true);
        System.out.println("Nhap vao noi dung sach: ");
        this.content = sc.nextLine();
        System.out.println("Nhap nha san xuat sach: ");
        this.publisher = sc.nextLine();
        System.out.println("Chon trang thai sach: ");
        System.out.println("1. Con sach");
        System.out.println("2. Het sach");
        System.out.print("Lua chon cua ban: ");
        int choiceBookStatus = Integer.parseInt(sc.nextLine());
        if (choiceBookStatus != 1) {
            this.bookStatus = false;
        } else {
            this.bookStatus = true;
        }
    }

    @Override
    public void displayData() {
        String status;
        if (this.bookStatus) {
            status = "Con sach";
        } else {
            status = "Het sach";
        }
        System.out.printf("%-10s%-50s%-10.0f%-10d%-30s%-30s%-15s\n", this.bookId, this.bookName, this.exportPrice,
                this.quantity, this.title, this.publisher, status);
    }

    @Override
    public void insertData(Object object) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            file = new File(PATH_BOOK);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject((List<Book>) object);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }

    @Override
    public void getData(List<Author> listAuthor, List<Book> listBook) {
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(PATH_BOOK);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listBook.addAll((List<Book>) ois.readObject());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }

    public void calProfit() {
        this.profit = this.exportPrice - this.importPrice;
    }

    public void buyBook(int numberBook) {
        if (numberBook > this.quantity) {
            System.err.println("So luong sach khong du de ban");
        } else {
            this.quantity -= numberBook;
        }
    }

}
