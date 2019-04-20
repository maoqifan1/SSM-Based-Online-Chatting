package com.mao.pojo;


public class VUsersMessagesPojo {

  private String messageContent;
  private java.sql.Timestamp messageDate;
  private long messageFuserId;
  private long messageTuserId;
  private String messageStatus;
  private String messageFuserIcon;
  private String messageTuserIcon;


  public String getMessageContent() {
    return messageContent;
  }

  public void setMessageContent(String messageContent) {
    this.messageContent = messageContent;
  }


  public java.sql.Timestamp getMessageDate() {
    return messageDate;
  }

  public void setMessageDate(java.sql.Timestamp messageDate) {
    this.messageDate = messageDate;
  }


  public long getMessageFuserId() {
    return messageFuserId;
  }

  public void setMessageFuserId(long messageFuserId) {
    this.messageFuserId = messageFuserId;
  }


  public long getMessageTuserId() {
    return messageTuserId;
  }

  public void setMessageTuserId(long messageTuserId) {
    this.messageTuserId = messageTuserId;
  }


  public String getMessageStatus() {
    return messageStatus;
  }

  public void setMessageStatus(String messageStatus) {
    this.messageStatus = messageStatus;
  }


  public String getMessageFuserIcon() {
    return messageFuserIcon;
  }

  public void setMessageFuserIcon(String messageFuserIcon) {
    this.messageFuserIcon = messageFuserIcon;
  }


  public String getMessageTuserIcon() {
    return messageTuserIcon;
  }

  public void setMessageTuserIcon(String messageTuserIcon) {
    this.messageTuserIcon = messageTuserIcon;
  }

}
