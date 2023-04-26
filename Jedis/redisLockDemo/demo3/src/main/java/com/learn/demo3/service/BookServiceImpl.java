package com.learn.demo3.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.learn.demo3.constant.CacheType;
import com.learn.demo3.mapper.BookMapper;
import com.learn.demo3.model.Book;
import com.learn.demo3.util.RedisLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private RedisTemplate<String, Book> redisTemplate;

    @Resource(name = "redissonClient")
    private RedissonClient redissonClient;

    private RBloomFilter<String> bloomFilter;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RedisLock redisLock;

    @PostConstruct
    public void init() {
        bloomFilter = redissonClient.getBloomFilter("bloom-filter");
    }

    @Override
    public Book getBookById(String id, CacheType cacheType) {
        if (CacheType.SIDE.equals(cacheType)) {
            return getBookById(id);
        }
        return null;
    }

    @Override
    public boolean updateBook(Book book) {
        return false;
    }

    /**
     * 策略1: 旁路缓存
     * @param id
     * @return
     */
    private Book getBookById(String id) {
        bloomFilter.tryInit(1000, 0.003);
        for (int i = 0; i < 1000; i++) {
            bloomFilter.add(String.valueOf(i));
        }

        if (!bloomFilter.contains(id)) {
            log.info("被布隆过滤器阻挡");
            return null;
        }

        Book book = redisTemplate.opsForValue().get(id);

        if (!ObjectUtils.isEmpty(book)) {
            log.info("缓存命中");
            return book;
        } else {
            log.info("缓存未命中, 将上锁重试");
        }

        try {
            Boolean lock = redisLock.tryLock(id);
            if (!lock) {
                log.info("error");
                return null;
            }
            book = redisTemplate.opsForValue().get(id);
            if (!ObjectUtils.isEmpty(book)) {
                log.info("加锁阶段有其他线程更新缓存, 查缓存成功");
                return book;
            }

            book = bookMapper.getBookById(Integer.valueOf(id));

            if (!ObjectUtils.isEmpty(book)) {
                redisTemplate.opsForValue().set(id, book, 10, TimeUnit.HOURS);
                log.info("数据库命中");
                return book;
            } else {
                log.info("数据库也没有");
                return book;
            }
        } finally {
            redisLock.unLock(id);
        }
    }
}