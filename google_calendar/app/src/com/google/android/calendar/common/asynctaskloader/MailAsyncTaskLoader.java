// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.asynctaskloader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

public abstract class MailAsyncTaskLoader extends AsyncTaskLoader
{

    private Object result;

    public MailAsyncTaskLoader(Context context)
    {
        super(context);
    }

    public final void deliverResult(Object obj)
    {
        if (super.mReset)
        {
            if (obj != null)
            {
                onDiscardResult(obj);
            }
        } else
        {
            Object obj1 = result;
            result = obj;
            if (super.mStarted)
            {
                super.deliverResult(obj);
            }
            if (obj1 != null && obj1 != result)
            {
                onDiscardResult(obj1);
                return;
            }
        }
    }

    public final void onCanceled(Object obj)
    {
        super.onCanceled(obj);
        if (obj != null)
        {
            onDiscardResult(obj);
        }
    }

    public abstract void onDiscardResult(Object obj);

    protected final void onReset()
    {
        super.onReset();
        onStopLoading();
        if (result != null)
        {
            onDiscardResult(result);
        }
        result = null;
    }

    protected final void onStartLoading()
    {
        if (result != null)
        {
            deliverResult(result);
        }
        boolean flag = super.mContentChanged;
        super.mContentChanged = false;
        super.mProcessingChange = super.mProcessingChange | flag;
        if (flag || result == null)
        {
            onForceLoad();
        }
    }

    protected final void onStopLoading()
    {
        onCancelLoad();
    }
}
