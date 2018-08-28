// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.view.View;
import android.view.ViewTreeObserver;

final class val.runnable
    implements android.view.youtListener
{

    private final Runnable val$runnable;
    private final View val$view;

    public final void onGlobalLayout()
    {
        val$view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        val$runnable.run();
    }

    ()
    {
        val$view = view1;
        val$runnable = runnable1;
        super();
    }
}
