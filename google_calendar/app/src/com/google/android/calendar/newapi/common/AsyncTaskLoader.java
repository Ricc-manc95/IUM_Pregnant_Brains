// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.os.AsyncTask;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            SnappyAsyncTask, Loader

public abstract class AsyncTaskLoader extends SnappyAsyncTask
    implements Loader
{

    public Loader.Callback callback;
    public String failureMessage;
    public Boolean finished;
    private Object result;
    public boolean success;

    public AsyncTaskLoader()
    {
        success = true;
        finished = Boolean.valueOf(false);
    }

    public Object getResult()
    {
        return result;
    }

    public final boolean isFinished()
    {
        return finished.booleanValue();
    }

    public final boolean isFinishedSuccessfully()
    {
        return finished.booleanValue();
    }

    public final boolean isRunning()
    {
        return getStatus() == android.os.AsyncTask.Status.RUNNING;
    }

    public final void load()
    {
label0:
        {
            if (!finished.booleanValue())
            {
                boolean flag;
                if (getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            return;
        }
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected final void onCompleted(Object obj)
    {
label0:
        {
            result = obj;
            finished = Boolean.valueOf(true);
            if (callback != null)
            {
                if (!success)
                {
                    break label0;
                }
                callback.onLoadingSuccess(this);
            }
            return;
        }
        callback.onLoadingFailure(this, failureMessage);
    }

    public final void setCallback(Loader.Callback callback1)
    {
        callback = callback1;
    }
}
