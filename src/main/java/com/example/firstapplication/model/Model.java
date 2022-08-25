package com.example.firstapplication.model;
public class Model {
    public Model(Boolean status){
        this.status = status;
    }

    public Model(Boolean status, Object list, String error){
        this.status = status;
        this.list = list;
        this.error = error;
    }
    private Boolean status;
    private Object list;

    private String error;
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }
}
