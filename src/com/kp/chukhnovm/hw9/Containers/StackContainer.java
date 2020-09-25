package com.kp.chukhnovm.hw9.Containers;

import com.kp.chukhnovm.hw9.Exceptions.ClassInBlackListException;

import java.util.Arrays;

public class StackContainer {

    private Object[] list = new Object[0];

    private int size = 0;

    private StackBlackList blackList = new StackBlackList();

    public StackContainer() {}

    public StackBlackList getBlackList() {
        return blackList;
    }

    public void setBlackList(StackBlackList blackList) {
        this.blackList = blackList;
    }

    public Object[] getList() {
        return this.list == null ? new Object[0] : this.list;
    }

    public Object[] setList(Object[] newList) throws ClassInBlackListException {

        for (int i = 0; i < newList.length; i++) {
            Class className = newList[i].getClass();
            if(getBlackList().isInBlackList(className)) {
                throw new ClassInBlackListException(String.format("%s in black list.", className.toString()));
            }
        }

        return this.list = newList;
    }

    public Object get() {

        if(size > 0) {
            return this.getList()[0];
        }

        return null;
    }

    public Object retrieve() throws ClassInBlackListException {

        Object o = null;

        if(size > 0) {
            o = this.getList()[0];
            this.setList(Arrays.copyOfRange(this.getList(), 1, this.getList().length));
        }

        return o;
    }

    public boolean add(Object object) throws ClassInBlackListException {

        if(getBlackList().isInBlackList(object.getClass())) {
            throw new ClassInBlackListException(String.format("%s in black list.", object.getClass().toString()));
        }

        int length = 1;
        for (int i = 0; i < size ; i++) {
            if(this.getList()[i] != null) length++;
        }

        Object[] newList = new Object[length];
        newList[0] = object;

        int j = 0;
        for (int i = 0; i < length; i++) {
            if(newList[i] != null) continue;
            newList[i] = this.getList()[j++];
        }

        this.setList(newList);

        return true;
    }


}
