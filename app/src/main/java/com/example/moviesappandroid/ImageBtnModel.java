package com.example.moviesappandroid;

public class ImageBtnModel {

   private int btnid;
   private String btntitle;
   private int genryid;


    public ImageBtnModel(int btnid, String btntitle, int genryid) {
        this.btnid = btnid;
        this.btntitle = btntitle;
        this.genryid = genryid;
    }

    public ImageBtnModel(int btnid, String btntitle) {
        this.btnid = btnid;
        this.btntitle = btntitle;
    }

    public int getBtnid() {
        return btnid;
    }

    public void setBtnid(int btnid) {
        this.btnid = btnid;
    }

    public String getBtntitle() {
        return btntitle;
    }

    public void setBtntitle(String btntitle) {
        this.btntitle = btntitle;
    }


    public int getGenryid() {
        return genryid;
    }

    public void setGenryid(int genryid) {
        this.genryid = genryid;
    }
}
