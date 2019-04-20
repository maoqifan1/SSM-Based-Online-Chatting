package com.mao.model;

import javax.persistence.*;

@Entity
@Table(name = "message_type", schema = "Project")
public class MessageTypeEntity {
    private int messageTypeId;
    private String messageType;

    @Id
    @Column(name = "messageTypeId")
    public int getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(int messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Basic
    @Column(name = "messageType")
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageTypeEntity that = (MessageTypeEntity) o;

        if (messageTypeId != that.messageTypeId) return false;
        if (messageType != null ? !messageType.equals(that.messageType) : that.messageType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageTypeId;
        result = 31 * result + (messageType != null ? messageType.hashCode() : 0);
        return result;
    }
}
