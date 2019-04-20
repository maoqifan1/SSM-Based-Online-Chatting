package com.mao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class GroupEntityPK implements Serializable {
    private int userid;
    private String groupname;

    @Column(name = "userid")
    @Id
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Column(name = "groupname")
    @Id
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupEntityPK that = (GroupEntityPK) o;

        if (userid != that.userid) return false;
        if (groupname != null ? !groupname.equals(that.groupname) : that.groupname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
        return result;
    }
}
