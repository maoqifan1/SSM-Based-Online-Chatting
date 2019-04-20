package com.mao.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_conversion", schema = "Project")
@IdClass(UserConversionEntityPK.class)
public class UserConversionEntity {
    private int fuserId;
    private Object fuserState;
    private int tuserId;
    private Object tuserState;
    private Timestamp createTime;
    private Object messageFlag;

    @Id
    @Column(name = "fuserId")
    public int getFuserId() {
        return fuserId;
    }

    public void setFuserId(int fuserId) {
        this.fuserId = fuserId;
    }

    @Basic
    @Column(name = "fuserState")
    public Object getFuserState() {
        return fuserState;
    }

    public void setFuserState(Object fuserState) {
        this.fuserState = fuserState;
    }

    @Id
    @Column(name = "tuserId")
    public int getTuserId() {
        return tuserId;
    }

    public void setTuserId(int tuserId) {
        this.tuserId = tuserId;
    }

    @Basic
    @Column(name = "tuserState")
    public Object getTuserState() {
        return tuserState;
    }

    public void setTuserState(Object tuserState) {
        this.tuserState = tuserState;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "messageFlag")
    public Object getMessageFlag() {
        return messageFlag;
    }

    public void setMessageFlag(Object messageFlag) {
        this.messageFlag = messageFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserConversionEntity that = (UserConversionEntity) o;

        if (fuserId != that.fuserId) return false;
        if (tuserId != that.tuserId) return false;
        if (fuserState != null ? !fuserState.equals(that.fuserState) : that.fuserState != null) return false;
        if (tuserState != null ? !tuserState.equals(that.tuserState) : that.tuserState != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (messageFlag != null ? !messageFlag.equals(that.messageFlag) : that.messageFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fuserId;
        result = 31 * result + (fuserState != null ? fuserState.hashCode() : 0);
        result = 31 * result + tuserId;
        result = 31 * result + (tuserState != null ? tuserState.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (messageFlag != null ? messageFlag.hashCode() : 0);
        return result;
    }
}
