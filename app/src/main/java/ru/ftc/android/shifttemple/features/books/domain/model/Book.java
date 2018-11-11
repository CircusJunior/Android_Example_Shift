package ru.ftc.android.shifttemple.features.books.domain.model;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:40
 */

public final class Book {

    private String id;
    private String name;
    private String author;
    private String[] genre;
    private String pages;
    private boolean isAvailable;

    public Book(String name, String author, String pages) {
        this.name = name;
        this.author = author;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String[] getGenre() {
        return genre;
    }

    public String getPages() {
        return pages;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
