package com.bw.ymy.project.App;

//EventButs
public class Event {

    Object o;
    String name;

    public Event(Object o, String name) {
        this.o = o;
        this.name = name;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
