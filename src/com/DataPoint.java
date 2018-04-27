package com;


public class DataPoint {
    private double data;
    private long time;
    public DataPoint(double data,long time){
        this.data = data;
        this.time = time;
    }
    public DataPoint(){}

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return "["+data+","+time+"]";
    }
}
