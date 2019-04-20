package com.mao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UsersGroupEntityPK implements Serializable {
    private int cuserId;
    private int friendId;

    @Column(name = "cuserId")
    @Id
    public int getCuserId() {
        return cuserId;
    }

    public void setCuserId(int cuserId) {
        this.cuserId = cuserId;
    }

    @Column(name = "friendId")
    @Id
    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersGroupEntityPK that = (UsersGroupEntityPK) o;

        if (cuserId != that.cuserId) return false;
        if (friendId != that.friendId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cuserId;
        result = 31 * result + friendId;
        return result;
    }
}
