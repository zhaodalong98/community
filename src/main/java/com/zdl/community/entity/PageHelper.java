package com.zdl.community.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageHelper {
    //当前页码
    private int currentPage = 1;
    //显示上限
    private int limit = 10;
    //数据的行数，用于计算总页数
    private int rows;
    //查询路径，用于复用分页链接
    private String path;



    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if(currentPage >= 1){
            this.currentPage = currentPage;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100){
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

//     获取当前页的起始行
    public int getOffset(){
        return (currentPage - 1) * limit;
    }

//    获取总页数
    public int getTotal(){
        if(rows % limit == 0){
            return rows / limit;
        }else {
            return rows / limit + 1;
        }
    }

//    获取起始页码
    public int getFrom(){
        int from = currentPage - 2;
        return from < 1 ? 1 : from;
    }
//    获取结束页码
    public  int getTo(){
        int to = currentPage + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
