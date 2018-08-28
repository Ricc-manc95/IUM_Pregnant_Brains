// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import android.app.Activity;
import com.google.android.calendar.utils.ActivitySingletonCache;

// Referenced classes of package com.google.android.calendar.timely.interaction:
//            SwipeInteractionController

final class Q extends ActivitySingletonCache
{

    protected final Object createInstance(Activity activity)
    {
        return new SwipeInteractionController();
    }

    Q()
    {
    }
}
