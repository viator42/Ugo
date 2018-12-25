package com.viator42.ugo.module.dev;

import javax.inject.Inject;

public class CPU {
    @Inject
    public CPU() {
    }

    public String display() {
        return "This is CPU";
    }

}
