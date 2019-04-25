package com.caiwei.demo.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AppRunner
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/24 23:36
 */
@Component
@Slf4j
public class AppRunner implements CommandLineRunner {

    private final BookRepository bookRepository;

    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(".... Fetching books");
        log.info("isbn-1234 -->" + bookRepository.getBookByIsbn("isbn-1234"));
        log.info("isbn-4567 -->" + bookRepository.getBookByIsbn("isbn-4567"));
        log.info("isbn-1234 -->" + bookRepository.getBookByIsbn("isbn-1234"));
        log.info("isbn-4567 -->" + bookRepository.getBookByIsbn("isbn-4567"));
        log.info("isbn-1234 -->" + bookRepository.getBookByIsbn("isbn-1234"));
        log.info("isbn-1234 -->" + bookRepository.getBookByIsbn("isbn-1234"));

    }
}
