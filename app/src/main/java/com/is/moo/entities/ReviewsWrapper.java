package com.is.moo.entities;

import java.util.List;

/**
 * Created by George on 2015/8/13.
 */
public class ReviewsWrapper {

    private String id;
    private String page;
    private List<Review> results;
    private Number total_pages;
    private Number total_results;

    public String getId() {

        return id;
    }

    public String getPage() {

        return page;
    }

    public List<Review> getResults() {

        return results;
    }

    public Number getTotal_pages() {

        return total_pages;
    }

    public Number getTotal_results() {

        return total_results;
    }
}
