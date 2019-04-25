package com.caiwei.demo.cache;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Book
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/24 22:56
 */
@Data
public class Book implements Serializable {

    private String isbn;
    private String title;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }


}
