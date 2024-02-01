package org.example.week1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Book {
   private String title;
   private String author;
   private int year;
   private int pages;
   private double rating;

    public Book(String title, String author, int year, int pages, double rating) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", rating=" + rating +
                '}';
    }

    public static class StreamProcessing {
        public static void main(String[] args) {
            // Create a list of books
            List<Book> bookList = new ArrayList<>();
            bookList.add(new Book("The Fellowship of the Ring", "J.R.R. Tolkien", 1954, 423, 4.35));
            bookList.add(new Book("The Two Towers", "J.R.R. Tolkien", 1954, 352, 4.43));
            bookList.add(new Book("The Return of the King", "J.R.R. Tolkien", 1955, 416, 4.52));
            bookList.add(new Book("1984", "George Orwell", 1949, 328, 4.17));
            bookList.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960, 384, 4.27));
            bookList.add(new Book("Pride and Prejudice", "Jane Austen", 1813, 279, 4.25));
            // Calculate the average rating of all books

            double averageRating = bookList.stream().collect(Collectors.averagingDouble(num -> num.getRating()));
            System.out.println(averageRating);

            // Filter and display books published after a specific year
            bookList.stream()
                    .filter(f -> f.getYear() > 1954)
                    .forEach(System.out::println);

            // Sort books by rating in descending order
            bookList.stream()
                    .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                    .forEach(System.out::println);
            // Find and display the title of the highest-rated book
                bookList.stream()
                        .max(Comparator.comparingDouble(Book::getRating))
                        .ifPresent(book -> System.out.println(book.getTitle()));

            // Group books by author and calculate average rating for each author
                bookList.stream()
                        .collect(Collectors.groupingBy(Book::getAuthor, Collectors.averagingDouble(Book::getRating)))
                        .forEach((author, rating) -> System.out.println(author + " " + rating));

            // Calculate the total number of pages for all books
                System.out.println(bookList.stream()
                        .collect(Collectors.summarizingDouble(Book::getPages))
                        .getSum());

        }
    }
}
