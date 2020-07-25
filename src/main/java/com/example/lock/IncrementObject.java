package com.example.lock;

/**
 * @author mk
 * @title: IncrementObject
 * @description: 自增演示对象
 * @date 2020/6/29
 */
public class IncrementObject {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "IncrementObject{" +
                "count=" + count +
                '}';
    }
}
