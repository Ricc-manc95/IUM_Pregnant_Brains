// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;


// Referenced classes of package com.google.android.calendar.timely.interaction:
//            InteractionTracker

public static final class counter
    implements Runnable
{

    private final Object controller;
    private int counter;
    private final Object target;

    public final void run()
    {
        int i = counter - 1;
        counter = i;
        if (i == 0)
        {
            InteractionTracker.getInstance().trackInteractionEnd(controller, target);
        }
    }

    public (Object obj, Object obj1, int i)
    {
        controller = obj;
        target = obj1;
        counter = i;
    }
}
