package org.abhishek.behavioural;

/*
Memento pattern is used to restore state of an object to a previous state. It is also known as snapshot pattern.
The intent of memento pattern is to capture the internal state of an object without violating encapsulation and
thus providing a mean for restoring the object into initial state when needed.

Design participants

The memento pattern has three participants.

Originator – is the object that knows how to create and save it’s state for future.
It provides methods createMemento() and restore(memento).

Caretaker – performs an operation on the Originator while having the possibility to rollback.
It keeps track of multiple mementos. Caretaker class refers to the Originator class for saving (createMemento())
and restoring (restore(memento)) originator’s internal state.

Memento – the lock box that is written and read by the Originator, and shepherded by the Caretaker.
In principle, a memento must be in immutable object so that no one can change it’s state once created.


Example:
1) Ctrl +Z
2) Database transaction

Usage:
1) rollback or undo feature
2) IDE working state
 */

//Originator
class Article {
    private long id;
    private String title;
    private String content;

    public Article(long id, String title) {
        super();
        this.id = id;
        this.title = title;
    }



    public ArticleMemento createMemento() {
        ArticleMemento m = new ArticleMemento(id, title, content);
        return m;
    }

    public void restore(ArticleMemento m) {
        this.id = m.getId();
        this.title = m.getTitle();
        this.content = m.getContent();
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

//Caretaker
final class ArticleMemento {
    private final long id;
    private final String title;
    private final String content;

    public ArticleMemento(long id, String title, String content) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

public class Memento {
    public static void main(String[] args) {
        Article article = new Article(1, "My Article");
        article.setContent("ABC");    //original content
        System.out.println(article);

        ArticleMemento memento = article.createMemento(); //created immutable memento

        article.setContent("123");    //changed content
        System.out.println(article);

        article.restore(memento);   //UNDO change
        System.out.println(article);  //original content
    }
}
