package com.project.workgroup.partyplay.model.entities;

/**
 * Created by TALLER on 13/11/2015.
 */
public class Preference  {

    String id ;
    int imagen;
    String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String text) {
        this.title = text;
    }
}
