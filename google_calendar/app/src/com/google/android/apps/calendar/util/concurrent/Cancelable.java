// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;


public interface Cancelable
{

    public static final Cancelable EMPTY = .Lambda._cls0..instance;

    public abstract void cancel(boolean flag);


    class .Lambda._cls0
        implements Cancelable
    {

        public static final Cancelable $instance = new .Lambda._cls0();

        public final void cancel(boolean flag)
        {
        }


            private .Lambda._cls0()
            {
            }
    }

}
