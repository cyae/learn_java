package com.learn.demo3.constant;

public enum CacheType {
    SIDE("SIDE", "旁路缓存"),
    BOOK_BLOOM_FILTER("book:bloom-filter", "图书布隆过滤器");

    private String type;

    CacheType(String type, String desc) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
