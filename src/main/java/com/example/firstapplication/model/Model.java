package com.example.firstapplication.model;

import java.util.List;

public class Model {

    public Model(){
        super();
    }

    public Model(Boolean status){
        this.status = status;
    }

    public Model(Boolean status, Object list){
        this.status = status;
        this.list = list;
    }
    private Boolean status;
    private Object list;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
