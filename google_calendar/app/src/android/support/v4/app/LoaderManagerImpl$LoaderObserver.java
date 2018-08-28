// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.arch.lifecycle.Observer;
import android.support.v4.content.Loader;

final class mCallback
    implements Observer
{

    public final mCallback mCallback;
    public boolean mDeliveredData;
    private final Loader mLoader;

    public final void onChanged(Object obj)
    {
        mCallback.oadFinished(mLoader, obj);
        mDeliveredData = true;
    }

    public final String toString()
    {
        return mCallback.toString();
    }

    (Loader loader,  )
    {
        mDeliveredData = false;
        mLoader = loader;
        mCallback = ;
    }
}
