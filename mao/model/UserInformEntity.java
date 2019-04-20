package com.mao.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_inform", schema = "Project")
public class UserInformEntity {
    private int no;
    private int fuserId;
    private int tuserId;
    private String info;
    private Timestamp date;
    private Object active;

    @Id
    @Column(name = "no")
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Basic
    @Column(name = "fuserId")
    public int getFuserId() {
        return fuserId;
    }

    public void setFuserId(int fuserId) {
        this.fuserId = fuserId;
    }

    @Basic
    @Column(name = "tuserId")
    public int getTuserId() {
        return tuserId;
    }

    public void setTuserId(int tuserId) {
        this.tuserId = tuserId;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "active")
    public Object getActive() {
        return active;
    }

    public void setActive(Object active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInformEntity that = (UserInformEntity) o;

        if (no != that.no) return false;
        if (fuserId != that.fuserId) return false;
        if (tuserId != that.tuserId) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = no;
        result = 31 * result + fuserId;
        result = 31 * result + tuserId;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }
}
