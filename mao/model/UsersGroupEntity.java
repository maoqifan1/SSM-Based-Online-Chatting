package com.mao.model;

import javax.persistence.*;

@Entity
@Table(name = "users_group", schema = "Project")
@IdClass(UsersGroupEntityPK.class)
public class UsersGroupEntity {
    private int cuserId;
    private int friendId;
    private String relationShip;
    private String userGroup;
    private String friendGroup;
    private String userRemark;
    private String friendRemark;

    @Id
    @Column(name = "cuserId")
    public int getCuserId() {
        return cuserId;
    }

    public void setCuserId(int cuserId) {
        this.cuserId = cuserId;
    }

    @Id
    @Column(name = "friendId")
    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    @Basic
    @Column(name = "relationShip")
    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    @Basic
    @Column(name = "userGroup")
    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    @Basic
    @Column(name = "friendGroup")
    public String getFriendGroup() {
        return friendGroup;
    }

    public void setFriendGroup(String friendGroup) {
        this.friendGroup = friendGroup;
    }

    @Basic
    @Column(name = "userRemark")
    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    @Basic
    @Column(name = "friendRemark")
    public String getFriendRemark() {
        return friendRemark;
    }

    public void setFriendRemark(String friendRemark) {
        this.friendRemark = friendRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersGroupEntity that = (UsersGroupEntity) o;

        if (cuserId != that.cuserId) return false;
        if (friendId != that.friendId) return false;
        if (relationShip != null ? !relationShip.equals(that.relationShip) : that.relationShip != null) return false;
        if (userGroup != null ? !userGroup.equals(that.userGroup) : that.userGroup != null) return false;
        if (friendGroup != null ? !friendGroup.equals(that.friendGroup) : that.friendGroup != null) return false;
        if (userRemark != null ? !userRemark.equals(that.userRemark) : that.userRemark != null) return false;
        if (friendRemark != null ? !friendRemark.equals(that.friendRemark) : that.friendRemark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cuserId;
        result = 31 * result + friendId;
        result = 31 * result + (relationShip != null ? relationShip.hashCode() : 0);
        result = 31 * result + (userGroup != null ? userGroup.hashCode() : 0);
        result = 31 * result + (friendGroup != null ? friendGroup.hashCode() : 0);
        result = 31 * result + (userRemark != null ? userRemark.hashCode() : 0);
        result = 31 * result + (friendRemark != null ? friendRemark.hashCode() : 0);
        return result;
    }
}
