// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;


final class counter
    implements com.google.android.calendar.timely.istener._cls1
{

    private int counter;
    private final com.google.android.calendar.timely.teFinished val$updateFinishedListener;

    public final void notifyUpdateFinished()
    {
        counter = counter - 1;
        if (counter == 0)
        {
            val$updateFinishedListener.teFinished();
        }
    }

    ()
    {
        val$updateFinishedListener = _pcls1;
        super();
        counter = 2;
    }
}
