package com.viator42.ugo.module.dev;

public class Computer {
    public String cpu;
    public String ram;
    public String storage;

    public Computer(String cpu, String ram, String storage) {
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
    }

    public String display() {
        return "CPU:" + this.cpu + " Ram:" + this.ram + " Storage:" + this.storage;
    }

}
