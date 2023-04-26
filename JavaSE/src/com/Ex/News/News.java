package com.Ex.News;

public class News {
    String headline;
    String content;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News [headline=" + headline + "]";
    }

    public News(String headline) {
        this.headline = headline;
    }

    // @Override
    // public int compareTo(String s) {
    // return s.compareTo(this.headline);
    // }

}
