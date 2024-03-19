package com;

import java.util.LinkedList;
import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository<T> {
    void rAdd(T object);
    void rRemove(T object);
    void rUpdate(T object,int num,String column); // Think it as replace for set

    List<T> rList();
}
