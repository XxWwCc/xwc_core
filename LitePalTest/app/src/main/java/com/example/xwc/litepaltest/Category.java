package com.example.xwc.litepaltest;

import org.litepal.crud.LitePalSupport;

public class Category extends LitePalSupport {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    private int id;
    private String categoryName;
    private int categoryCode;

}
