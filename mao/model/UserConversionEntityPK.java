package com.mao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserConversionEntityPK implements Serializable {
    private int fuserId;
    private int tuserId;

    @Column(name = "fuserId")
    @Id
    public int getFuserId() {
        return fuserId;
    }

    public void setFuserId(int fuserId) {
        this.fuserId = fuserId;
    }

    @Column(name = "tuserId")
    @Id
    public int getTuserId() {
        return tuserId;
    }

    public void setTuserId(int tuserId) {
        this.tuserId = tuserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserConversionEntityPK that = (UserConversionEntityPK) o;

        if (fuserId != that.fuserId) return false;
        if (tuserId != that.tuserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fuserId;
        result = 31 * result + tuserId;
        return result;
    }
}
