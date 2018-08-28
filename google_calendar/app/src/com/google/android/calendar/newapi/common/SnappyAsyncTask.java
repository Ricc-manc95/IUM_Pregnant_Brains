// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

public abstract class SnappyAsyncTask extends AsyncTask
{

    public SnappyAsyncTask()
    {
    }

    protected transient Object doInBackground(final Object result[])
    {
        result = ((Object []) (runInBackground(result)));
        (new Handler(Looper.getMainLooper())).postAtFrontOfQueue(new _cls1());
        return ((Object) (result));
    }

    protected abstract void onCompleted(Object obj);

    protected final void onPostExecute(Object obj)
    {
    }

    public transient abstract Object runInBackground(Object aobj[]);

    private class _cls1
        implements Runnable
    {

        private final SnappyAsyncTask this$0;
        private final Object val$result;

        public final void run()
        {
            onCompleted(result);
        }

        _cls1()
        {
            this$0 = SnappyAsyncTask.this;
            result = obj;
            super();
        }
    }

}
