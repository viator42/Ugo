package com.viator42.ugo.module.dev;

import dagger.Module;
import dagger.Provides;

@Module
public class ComputerModule {
    @Provides Computer provideComputer() {
        Computer computer = new Computer("i5", "8G", "256G");
        return computer;
    }

}
