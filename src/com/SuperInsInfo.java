package com;

public class SuperInsInfo {
    private double cpu_total;
    private double cpu_use;
    private double memory_total;
    private double memory_use;
    private double disk_total;
    private double disk_use;
    private double innet;
    private double outnet;
    private double mem_use;
    private double mem_share;
    private double mem_cache;
    private double mem_buffer;
    private double mem_swap;

    public SuperInsInfo(){

    }

    public SuperInsInfo(double cpu_total,double cpu_use,double memory_total,
                        double memory_use,double disk_total,double disk_use,
                        double innet,double outnet, double mem_use,double mem_share,double mem_cache,
                        double mem_buffer, double mem_swap){
        this.cpu_total = cpu_total;
        this.cpu_use = cpu_use;
        this.memory_total = memory_total;
        this.memory_use = memory_use;
        this.disk_total = disk_total;
        this.disk_use = disk_use;
        this.innet = innet;
        this.outnet = outnet;
        this.mem_use=mem_use;
        this.mem_share=mem_share;
        this.mem_cache=mem_cache;
        this.mem_buffer=mem_buffer;
        this.mem_swap=mem_swap;
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

    public double getCpu_total() {
        return cpu_total;
    }

    public void setCpu_total(double cpu_total) {
        this.cpu_total = cpu_total;
    }

    public double getCpu_use() {
        return cpu_use;
    }

    public void setCpu_use(double cpu_use) {
        this.cpu_use = cpu_use;
    }

    public double getMemory_total() {
        return memory_total;
    }

    public void setMemory_total(double memory_total) {
        this.memory_total = memory_total;
    }

    public double getMemory_use() {
        return memory_use;
    }

    public void setMemory_use(double memory_use) {
        this.memory_use = memory_use;
    }

    public double getDisk_total() {
        return disk_total;
    }

    public void setDisk_total(double disk_total) {
        this.disk_total = disk_total;
    }

    public double getDisk_use() {
        return disk_use;
    }

    public void setDisk_use(double disk_use) {
        this.disk_use = disk_use;
    }

	public double getMem_use() {
		return mem_use;
	}

	public void setMem_use(double mem_use) {
		this.mem_use = mem_use;
	}

	public double getMem_share() {
		return mem_share;
	}

	public void setMem_share(double mem_share) {
		this.mem_share = mem_share;
	}

	public double getMem_cache() {
		return mem_cache;
	}

	public void setMem_cache(double mem_cache) {
		this.mem_cache = mem_cache;
	}

	public double getMem_buffer() {
		return mem_buffer;
	}

	public void setMem_buffer(double mem_buffer) {
		this.mem_buffer = mem_buffer;
	}

	public double getMem_swap() {
		return mem_swap;
	}

	public void setMem_swap(double mem_swap) {
		this.mem_swap = mem_swap;
	}
    
}
