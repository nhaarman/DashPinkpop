package com.nhaarman.dashclock.pinkpop;

import android.content.res.Resources;

public interface ExtensionStrategy {

    String getStatus(Resources resources);

    String getExpandedTitle(Resources resources);

    String getExpandedBody(Resources resources);

}
