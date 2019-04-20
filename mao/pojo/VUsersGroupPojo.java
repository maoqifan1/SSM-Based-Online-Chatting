package com.mao.pojo;


public class VUsersGroupPojo {

  private long ownId;
  private long friendId;
  private String friendIcon;
  private String friendRemark;
  private String myGroup;


  public long getOwnId() {
    return ownId;
  }

  public void setOwnId(long ownId) {
    this.ownId = ownId;
  }


  public long getFriendId() {
    return friendId;
  }

  public void setFriendId(long friendId) {
    this.friendId = friendId;
  }


  public String getFriendIcon() {
    return friendIcon;
  }

  public void setFriendIcon(String friendIcon) {
    this.friendIcon = friendIcon;
  }


  public String getFriendRemark() {
    return friendRemark;
  }

  public void setFriendRemark(String friendRemark) {
    this.friendRemark = friendRemark;
  }


  public String getMyGroup() {
    return myGroup;
  }

  public void setMyGroup(String myGroup) {
    this.myGroup = myGroup;
  }

}
