/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darek Xperia
 */
public class Pagination<T> implements Serializable {

    private int page;
    private int rowsPerPage;
    private List<T> collection;

    public Pagination(int rowsPerPage, List<T> collection) {
        this.page = 1;
        this.collection = collection;
        this.rowsPerPage = rowsPerPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public void setCollection(ArrayList<T> collection) {
        this.collection = collection;
    }

    public int getPagesCount() { // ceil
        return collection.size() / rowsPerPage + (collection.size() % rowsPerPage == 0 ? 0 : 1);
    }

    public String[] generateControlArray() {
        String tmp = "";
        if (page > 4) {
            tmp += "<<,";
        }
        if (page > 1) {
            tmp += "<,";
        }
        int p;
        for (p = page - 3; p < page; p++) {
            if (p > 0) {
                tmp += p + ",";
            }
        }
        for (p = page; p < page + 4 && p <= getPagesCount(); p++) {
            tmp += p + ",";
        }
        if (page != getPagesCount()) {
            tmp += ",>";
        }
        if (page < getPagesCount() - 3) {
            tmp += ",>>";
        }
        return tmp.split(",");
    }

    public List<T> nextPage() {
        if (page + 1 > getPagesCount()) {
            return null;
        }
        page++;
        return generateDataArray();
    }

    public List<T> previousPage() {
        if (page - 1 <= 0) {
            return null;
        }
        page--;
        return generateDataArray();
    }

    public List<T> getPageNo(int pageNo) {
        if (pageNo <= 0 || pageNo > getPagesCount()) {
            return null;
        }
        page = pageNo;
        return generateDataArray();
    }

    public List<T> generateDataArray() {
        ArrayList<T> out = new ArrayList<>();
        for (int s = rowsPerPage * (page - 1); s < rowsPerPage * (page) && s < collection.size(); s++) {
            out.add(collection.get(s));
        }
        return out;
    }
}