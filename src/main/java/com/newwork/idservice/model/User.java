package com.newwork.idservice.model;

public class User {
    private long internalUserId;
    private String toolAUserId;
    private String toolBUserId;

    public User(long internalUserId, String toolAUserId, String toolBUserId){
        this.internalUserId = internalUserId;
        this.toolAUserId = toolAUserId;
        this.toolBUserId = toolBUserId;
    }

    public long getInternalUserId() {
        return internalUserId;
    }

    public String getToolAUserId() {
        return toolAUserId;
    }

    public String getToolBUserId() {
        return toolBUserId;
    }
}
