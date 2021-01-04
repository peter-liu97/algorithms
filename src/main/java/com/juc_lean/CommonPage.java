package com.juc_lean;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommonPage<T> {
    private Integer pageNum; //第几页
    private Integer pageSize; // 一页有几条
    private Integer totalPage; //一共多少页
    private Long total; // 一共多少条
    private List<T> list; //


    public static <T> CommonPage<T> restPage(List<T> pageInfo, PageInfo info, long tatil) {
        List<T> p = pageInfo;
        int page = info.getPage(); //第几页
        int size = info.getSize(); //页面大小
        if (page == 0 || size == 0) {
            throw new IllegalArgumentException("page or size not of zero");
        }
        long t = tatil;
        t = Math.max(p.size(), t); //一共用多少行

        int s = (int) ((t / size)) + (t % size == 0 ? 0 : 1);  //一共几页
        page = Math.min(page, s);// 第几页
        List<T> list = new ArrayList<T>();
        for (int i = (page - 1) * size; i < Math.min(t, size * page) && i > -1; i++) {
            list.add(p.get(i));
        }
        CommonPage<T> commonPage = new CommonPage<T>();
        commonPage.setList(list);
        commonPage.setPageSize(Math.min(size, list.size()));
        commonPage.setTotalPage(s);
        commonPage.setTotal(t);
        commonPage.setPageNum(page);
        return commonPage;
    }

    @Test
    public void test() {
        List<Integer> p = new ArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            p.add(i);
        }
        CommonPage<Integer> integerCommonPage = restPage(p, new PageInfo(23, 8), 0);
        System.out.println(integerCommonPage);

    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
