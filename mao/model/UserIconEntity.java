package com.mao.model;

import javax.persistence.*;

@Entity
@Table(name = "user_icon", schema = "Project")
public class UserIconEntity {
    private int userid;
    private String imagesource;

    @Id
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "imagesource")
    public String getImagesource() {
        return imagesource;
    }

    public void setImagesource(String imagesource) {
        this.imagesource = imagesource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserIconEntity that = (UserIconEntity) o;

        if (userid != that.userid) return false;
        if (imagesource != null ? !imagesource.equals(that.imagesource) : that.imagesource != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (imagesource != null ? imagesource.hashCode() : 0);
        return result;
    }
}
