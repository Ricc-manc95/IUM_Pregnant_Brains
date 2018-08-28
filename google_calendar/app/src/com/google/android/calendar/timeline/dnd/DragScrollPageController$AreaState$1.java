// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.dnd;

import android.os.Handler;
import android.os.SystemClock;

final class this._cls0
    implements Runnable
{

    private final llbackInterval this$0;

    public final void run()
    {
        llback.inArea(ge, rrentDepth, SystemClock.uptimeMillis() - terTime);
        NDLER.postDelayed(this, llbackInterval);
    }

    llback()
    {
        this$0 = this._cls0.this;
        super();
    }
}
