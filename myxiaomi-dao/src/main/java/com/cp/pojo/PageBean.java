package com.cp.pojo;

import java.util.List;

/**
 * cp 2019-09-11  14:00
 */
public class PageBean<T> {
    private int pageNum;
    private int pageSize;
    private Long totalSize;
    private int pageCount;
    private List<T> data;
    private int startPage;
    private int endPage;

    public PageBean(int pageNum, int pageSize, Long totalSize, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.data = data;
        this.pageCount= (int) (totalSize%pageSize==0?totalSize/pageSize:totalSize/pageSize+1);

        //正常情况
        this.startPage=pageNum-4;
        this.endPage=pageNum+5;
        //当前页小于等于5
        if(pageNum<=5){
            this.startPage=1;
            this.endPage=10;
        }
        //当前页大于总页数-5
        if(pageNum>pageCount-5){
            this.startPage=pageCount-9;
            this.endPage=pageCount;
        }
        //总页数小于10
        if(pageCount<=10){
            this.startPage=1;
            this.endPage=pageCount;
        }
    }

    public PageBean() {
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPagSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
}
