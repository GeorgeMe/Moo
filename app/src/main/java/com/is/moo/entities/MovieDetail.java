package com.is.moo.entities;

import java.util.List;

/**
 * Created by George on 2015/8/13.
 */
@SuppressWarnings("UnusedDeclaration")

public class MovieDetail {

    private int count;
    private int fcount;
    private int galleryclass;
    private int id;
    private String img;
    private int rcount;
    private int size;
    private boolean status;
    private long time;
    private String title;
    private String url;

    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int gallery;
        private int id;
        private String src;

        public int getGallery() {
            return gallery;
        }

        public void setGallery(int gallery) {
            this.gallery = gallery;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
}
