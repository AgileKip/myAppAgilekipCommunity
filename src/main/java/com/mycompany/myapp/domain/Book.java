package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Book.
 */
@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "pub_year")
    private String pubYear;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "rel_book__authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "authors_id")
    )
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "order", "book" }, allowSetters = true)
    private Set<OrderBook> orderBooks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book id(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public Book title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubYear() {
        return this.pubYear;
    }

    public Book pubYear(String pubYear) {
        this.pubYear = pubYear;
        return this;
    }

    public void setPubYear(String pubYear) {
        this.pubYear = pubYear;
    }

    public Double getPrice() {
        return this.price;
    }

    public Book price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public Book publisher(Publisher publisher) {
        this.setPublisher(publisher);
        return this;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public Book authors(Set<Author> authors) {
        this.setAuthors(authors);
        return this;
    }

    public Book addAuthors(Author author) {
        this.authors.add(author);
        return this;
    }

    public Book removeAuthors(Author author) {
        this.authors.remove(author);
        return this;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<OrderBook> getOrderBooks() {
        return this.orderBooks;
    }

    public Book orderBooks(Set<OrderBook> orderBooks) {
        this.setOrderBooks(orderBooks);
        return this;
    }

    public Book addOrderBook(OrderBook orderBook) {
        this.orderBooks.add(orderBook);
        orderBook.setBook(this);
        return this;
    }

    public Book removeOrderBook(OrderBook orderBook) {
        this.orderBooks.remove(orderBook);
        orderBook.setBook(null);
        return this;
    }

    public void setOrderBooks(Set<OrderBook> orderBooks) {
        if (this.orderBooks != null) {
            this.orderBooks.forEach(i -> i.setBook(null));
        }
        if (orderBooks != null) {
            orderBooks.forEach(i -> i.setBook(this));
        }
        this.orderBooks = orderBooks;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        return id != null && id.equals(((Book) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Book{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", pubYear='" + getPubYear() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
