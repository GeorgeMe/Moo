package com.is.moo.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by George on 2015/8/13.
 */
public class MoviesWrapper implements Serializable {


    private boolean status;
    private int total;

    private List<Movie> tngou;

    public MoviesWrapper(List<Movie> tngou) {
        this.tngou = tngou;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Movie> getTngou() {
        return tngou;
    }

    public void setTngou(List<Movie> tngou) {
        this.tngou = tngou;
    }

}
