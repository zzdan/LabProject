package com;



public class SuperInfo {
    private double cpu;
    private double memory;
    private double disk;
    private double innet;
    private double outnet;

    public SuperInfo(){

    }

    public SuperInfo(double cpu,double memory,double disk,double innet,double outnet){
        this.cpu = cpu ;
        this.memory = memory;
        this.disk = disk;
        this.innet = innet;
        this.outnet = outnet;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public double getDisk() {
        return disk;
    }

    public void setDisk(double disk) {
        this.disk = disk;
    }

    public double getInnet() {
        return innet;
    }

    public void setInnet(double innet) {
        this.innet = innet;
    }

    public double getOutnet() {
        return outnet;
    }

    public void setOutnet(double outnet) {
        this.outnet = outnet;
    }

}
