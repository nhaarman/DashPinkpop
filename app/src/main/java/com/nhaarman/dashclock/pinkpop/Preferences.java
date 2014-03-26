package com.nhaarman.dashclock.pinkpop;

public class Preferences {

    public static ExtensionStrategy getExtensionStrategy() {
        return new CountDownStrategy();
    }

}
