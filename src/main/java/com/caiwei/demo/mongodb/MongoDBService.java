package com.caiwei.demo.mongodb;

import com.caiwei.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName: MongoDBService
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/30 00:50
 */
@Service
public class MongoDBService {

    @Autowired
    MongoTemplate mongoTemplate;

    public void saveTest(Book book) {

        mongoTemplate.insert(book);
    }
}
