package com.example.pepperluchapplication.DTO;

public class IMAGE_NEWS {
    String ID_IMAGE, ID_NEWS, PATH_IMAGE;

    public IMAGE_NEWS() {
    }

    public IMAGE_NEWS(String ID_IMAGE, String ID_NEWS, String PATH_IMAGE) {
        this.ID_IMAGE = ID_IMAGE;
        this.ID_NEWS = ID_NEWS;
        this.PATH_IMAGE = PATH_IMAGE;
    }

    public String getID_IMAGE() {
        return ID_IMAGE;
    }

    public void setID_IMAGE(String ID_IMAGE) {
        this.ID_IMAGE = ID_IMAGE;
    }

    public String getID_NEWS() {
        return ID_NEWS;
    }

    public void setID_NEWS(String ID_NEWS) {
        this.ID_NEWS = ID_NEWS;
    }

    public String getPATH_IMAGE() {
        return PATH_IMAGE;
    }

    public void setPATH_IMAGE(String PATH_IMAGE) {
        this.PATH_IMAGE = PATH_IMAGE;
    }
}
