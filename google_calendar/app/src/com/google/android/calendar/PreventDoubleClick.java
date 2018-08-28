// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.os.SystemClock;
import android.view.View;

public abstract class PreventDoubleClick
    implements android.view.View.OnClickListener
{

    private final long mBetweenClickTime;
    private long mFirstClickTime;

    public PreventDoubleClick()
    {
        this(600L);
    }

    private PreventDoubleClick(long l)
    {
        mFirstClickTime = 0L;
        mBetweenClickTime = 600L;
    }

    public final void onClick(View view)
    {
        long l = SystemClock.elapsedRealtime();
        if (l - mFirstClickTime < mBetweenClickTime)
        {
            return;
        } else
        {
            mFirstClickTime = l;
            onFirstClick$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0();
            return;
        }
    }

    public abstract void onFirstClick$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0();
}
