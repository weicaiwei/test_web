package com.caiwei.demo.cache;

import com.caiwei.demo.model.Book;

/**
 * @ClassName: BookRepository
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/24 23:28
 */
public interface BookRepository {

    Book getBookByIsbn(String isbn);
}
