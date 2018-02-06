package com.ccfe.property.common.response;

import org.springframework.data.repository.query.Param;

/**
 * Created by Jo on 2017/5/12.
 */

public class Page {
    private int pageSize;
    private int pageNumber;
    private int currentNumber;
    private int totalPage;
    private int totalNumber;

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (pageNumber != 0) currentNumber = (pageNumber - 1) * pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        if (pageSize != 0) currentNumber = (pageNumber - 1) * pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Page set(int totalNumber){
        if (pageNumber == 0 || pageSize == 0) return new Page();
        setTotalNumber(totalNumber);
        setTotalPage((int) Math.ceil((double) totalNumber / (double) pageSize));
        return this;
    }
}
