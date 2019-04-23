package com.bw.ymy.project.my.bean;

public class FileBean {

    private String status;
    private String message;
    private String headpath;

    public FileBean(String status, String message, String headpath) {
        this.status = status;
        this.message = message;
        this.headpath = headpath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeadpath() {
        return headpath;
    }

    public void setHeadpath(String headpath) {
        this.headpath = headpath;
    }
}
