package com.mao.model;

import javax.persistence.*;

@Entity
@Table(name = "group", schema = "Project")
@IdClass(GroupEntityPK.class)
public class GroupEntity {
    private int userid;
    private String groupname;

    @Id
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Id
    @Column(name = "groupname")
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

        GroupEntity that = (GroupEntity) o;

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
