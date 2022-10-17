package ra.entity;

import ra.inter.IBook;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Author implements IBook, Serializable {
    private int authorId;
    private String authorName;
    private boolean authorStatus;

    public Author() {
    }

    public Author(int authorId, String authorName, boolean authorStatus) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorStatus = authorStatus;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isAuthorStatus() {
        return authorStatus;
    }

    public void setAuthorStatus(boolean authorStatus) {
        this.authorStatus = authorStatus;
    }


    @Override
    public void inputData(Scanner sc, List<Author> listAuthor, List<Book> listBook) {
        if (listAuthor.size() == 0) {
            this.authorId = 1;
        } else {
            //Lay ma phan tu co chi so cao nhat
            int max = 0;
            for (Author author : listAuthor) {
                if (max < author.getAuthorId()) {
                    max = author.getAuthorId();
                }
            }
            this.authorId = max + 1;
        }
        System.out.println("Nhap ten tac gia: ");
        do {
            this.authorName = sc.nextLine();
            if (this.authorName.length() >= 6 && this.authorName.length() <= 50) {
                break;
            } else {
                System.err.println("Vui long nhap ten tac gia gom 5-50 ky tu");
            }
        } while (true);
        System.out.println("Chon trang thai tac gia: ");
        System.out.println("1. Hoat dong");
        System.out.println("2. Khong hoat dong");
        System.out.print("Lua chon cua ban: ");
        try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                this.authorStatus = true;
            } else {
                this.authorStatus = false;
            }
        } catch (NumberFormatException ex) {
            this.authorStatus = false;
        }
    }

    @Override
    public void displayData() {
        String status = "Khong Hoat Dong";
        if (this.authorStatus) {
            status = "Hoat Dong";
        }
        System.out.printf("%-10d%-50s%-15s\n", this.authorId, this.authorName, status);
    }

    @Override
    public void insertData(Object object) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            file = new File(PATH_AUTHOR);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject((List<Author>) object);
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
            file = new File(PATH_AUTHOR);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                List<Author> listRead = (List<Author>) ois.readObject();
                //THam chieu listAuthor den listRead, listRead bien cuc bo --> null khi phuong thuc ket thuc
//                listAuthor = listRead;
                listAuthor.addAll(listRead);
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
}
