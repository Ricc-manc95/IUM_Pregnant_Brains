// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.view.View;

final class val.finalHeight
    implements Runnable
{

    private final int val$finalHeight;
    private final View val$view;

    public final void run()
    {
        android.view.  = val$view.getLayoutParams();
        .height = val$finalHeight;
        val$view.setLayoutParams();
    }

    ()
    {
        val$view = view1;
        val$finalHeight = i;
        super();
    }
}
