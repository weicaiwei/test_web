package com.caiwei.demo.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: SimpleBookRepository
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/24 23:31
 */
@Repository
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable(value = "books")
    public Book getBookByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
