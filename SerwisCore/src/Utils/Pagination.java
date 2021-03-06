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
    private List<T> f_collection;

    public Pagination(int rowsPerPage, List<T> collection) {
        this.page = 1;
        this.collection = f_collection!=null ? f_collection : collection;
        this.rowsPerPage = rowsPerPage;
    }

    public void refresh(int rowsPerPage) {
        this.page = 1;
        this.collection = f_collection!=null ? f_collection : collection;
        this.rowsPerPage = rowsPerPage;
    }
    
    public List<T> getCollection() {
        return collection;
    }

    public void setCollection(List<T> collection) {
        this.collection = collection;
    }

    public List<T> getF_collection() {
        return f_collection;
    }

    public void setF_collection(List<T> f_collection) {
        this.f_collection = f_collection;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page > getPagesCount() ? getPagesCount() : page;
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
            tmp += ">,";
        }
        if (page < getPagesCount() - 3) {
            tmp += ">>";
        }
        return tmp.split(",");
    }

    public List<String> generateControlList() {
        List<String> tmp = new ArrayList<>();
        if (page > 4) {
            tmp.add("<<");
        }
        if (page > 1) {
            tmp.add("<");
        }
        int p;
        for (p = page - 3; p < page; p++) {
            if (p > 0) {
                tmp.add("" + p);
            }
        }
        for (p = page; p < page + 4 && p <= getPagesCount(); p++) {
            tmp.add("" + p);
        }
        if (page != getPagesCount()) {
            tmp.add(">");
        }
        if (page < getPagesCount() - 3) {
            tmp.add(">>");
        }
        return tmp;
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

    public List<T> firstPage() {
        page = 1;
        return generateDataArray();
    }

    public List<T> lastPage() {
        page = getPagesCount();
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
        if (collection.isEmpty()) return out;
        for (int s = rowsPerPage * (page - 1); s < rowsPerPage * (page) && s < collection.size(); s++) {
            out.add(collection.get(s));
        }
        return out;
    }

    public String getControlArrayItem(int pos) {
        String[] cAL = generateControlArray();
        if (pos >= 0 && pos < cAL.length) {
            return cAL[pos];
        }
        return "";
    }
}
