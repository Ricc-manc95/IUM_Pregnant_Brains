// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import java.lang.ref.WeakReference;

final class duration
{

    public final WeakReference callback;
    public int duration;
    public boolean paused;

    (int i,  )
    {
        callback = new WeakReference();
        duration = i;
    }
}
