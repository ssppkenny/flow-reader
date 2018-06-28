package com.veve.flowreader.dao;

/**
 * Created by ddreval on 4/3/2018.
 */

public class BookRecord {

    public BookRecord() {

    }

    public BookRecord(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public BookRecord(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public String toString() {
        return String.format(
                "id:%d name:%s url:%s pages:%d", getId(), getName(), getUrl(), getPagesCount());
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    Integer currentPage;

    Integer pagesCount;

    String name;

    String url;

    Integer id;

}
