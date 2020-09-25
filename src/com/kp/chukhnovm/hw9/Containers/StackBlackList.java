package com.kp.chukhnovm.hw9.Containers;

public class StackBlackList {

    private Class[] list = new Class[0];

    public StackBlackList() {}

    public StackBlackList(Class...list) {
        this.setList(list);
    }

    public Class[] getList() {
        return this.list == null ? new Class[0] : this.list;
    }

    public void setList(Class[] list) {
        this.list = list;
    }

    public boolean isInBlackList(Class classItem) {
        for (int i = 0; i < getList().length; i++) {
            if(getList()[i].equals(classItem)) return true;
        }

        return false;
    }
}
