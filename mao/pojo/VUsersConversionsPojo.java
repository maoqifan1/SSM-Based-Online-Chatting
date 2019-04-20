package com.mao.pojo;


public class VUsersConversionsPojo {

  private long ownId;
  private String ownState;
  private long friendId;
  private String friendRemark;
  private String friendIcon;
  private String friendState;
  private java.sql.Timestamp createTime;


  public long getOwnId() {
    return ownId;
  }

  public void setOwnId(long ownId) {
    this.ownId = ownId;
  }


  public String getOwnState() {
    return ownState;
  }

  public void setOwnState(String ownState) {
    this.ownState = ownState;
  }


  public long getFriendId() {
    return friendId;
  }

  public void setFriendId(long friendId) {
    this.friendId = friendId;
  }


  public String getFriendRemark() {
    return friendRemark;
  }

  public void setFriendRemark(String friendRemark) {
    this.friendRemark = friendRemark;
  }


  public String getFriendIcon() {
    return friendIcon;
  }

  public void setFriendIcon(String friendIcon) {
    this.friendIcon = friendIcon;
  }


  public String getFriendState() {
    return friendState;
  }

  public void setFriendState(String friendState) {
    this.friendState = friendState;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
