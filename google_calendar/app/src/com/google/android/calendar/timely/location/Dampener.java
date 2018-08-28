// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.location;

import android.os.Handler;
import android.os.Message;

public final class Dampener
    implements android.os.Handler.Callback
{

    private final Callback callback;
    public final int delay = 100;
    public Handler handler;

    public Dampener(int i, Callback callback1)
    {
        handler = new Handler(this);
        callback = callback1;
    }

    public final boolean handleMessage(Message message)
    {
        callback.handleDampened(message.obj);
        return true;
    }

    private class Callback
    {

        public abstract void handleDampened(Object obj);
    }

}
