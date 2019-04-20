package com.mao.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_message", schema = "Project")
public class UserMessageEntity {
    private int messageId;
    private String messageContent;
    private Object messageStatus;
    private Timestamp messageDate;
    private int messageTypeId;
    private int messageFuserId;
    private int messageTuserId;

    @Id
    @Column(name = "messageId")
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "messageContent")
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Basic
    @Column(name = "messageStatus")
    public Object getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Object messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Basic
    @Column(name = "messageDate")
    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }

    @Basic
    @Column(name = "messageTypeId")
    public int getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(int messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Basic
    @Column(name = "messageFuserId")
    public int getMessageFuserId() {
        return messageFuserId;
    }

    public void setMessageFuserId(int messageFuserId) {
        this.messageFuserId = messageFuserId;
    }

    @Basic
    @Column(name = "messageTuserId")
    public int getMessageTuserId() {
        return messageTuserId;
    }

    public void setMessageTuserId(int messageTuserId) {
        this.messageTuserId = messageTuserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMessageEntity that = (UserMessageEntity) o;

        if (messageId != that.messageId) return false;
        if (messageTypeId != that.messageTypeId) return false;
        if (messageFuserId != that.messageFuserId) return false;
        if (messageTuserId != that.messageTuserId) return false;
        if (messageContent != null ? !messageContent.equals(that.messageContent) : that.messageContent != null)
            return false;
        if (messageStatus != null ? !messageStatus.equals(that.messageStatus) : that.messageStatus != null)
            return false;
        if (messageDate != null ? !messageDate.equals(that.messageDate) : that.messageDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        result = 31 * result + (messageStatus != null ? messageStatus.hashCode() : 0);
        result = 31 * result + (messageDate != null ? messageDate.hashCode() : 0);
        result = 31 * result + messageTypeId;
        result = 31 * result + messageFuserId;
        result = 31 * result + messageTuserId;
        return result;
    }
}
