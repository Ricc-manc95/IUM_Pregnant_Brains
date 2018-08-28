// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            Loader

public class CompositeLoader
    implements Loader, Loader.Callback
{

    private Loader.Callback callback;
    private boolean loadedEmpty;
    public List loaders;
    private Boolean success;

    public CompositeLoader()
    {
        loaders = new ArrayList();
        loadedEmpty = false;
    }

    public Object getResult()
    {
        return null;
    }

    public final boolean isFinished()
    {
        for (Iterator iterator = loaders.iterator(); iterator.hasNext();)
        {
            if (!((Loader)iterator.next()).isFinished())
            {
                return false;
            }
        }

        return true;
    }

    public final boolean isFinishedSuccessfully()
    {
        return isFinished() && success != null && success.booleanValue();
    }

    public final boolean isRunning()
    {
        for (Iterator iterator = loaders.iterator(); iterator.hasNext();)
        {
            if (((Loader)iterator.next()).isRunning())
            {
                return true;
            }
        }

        return false;
    }

    public void load()
    {
        if (!loaders.isEmpty()) goto _L2; else goto _L1
_L1:
        if (!loadedEmpty)
        {
            loadedEmpty = true;
            onLoadingSuccess(this);
        }
_L4:
        return;
_L2:
        if (!isRunning() && !isFinished())
        {
            Iterator iterator = loaders.iterator();
            while (iterator.hasNext()) 
            {
                Loader loader = (Loader)iterator.next();
                loader.setCallback(this);
                loader.load();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onLoadingFailure(Loader loader, String s)
    {
        success = Boolean.valueOf(false);
        if (callback != null)
        {
            callback.onLoadingFailure(this, s);
        }
    }

    public void onLoadingSuccess(Loader loader)
    {
        if (success == null || success.booleanValue())
        {
            success = Boolean.valueOf(true);
            if (isFinished() && callback != null)
            {
                callback.onLoadingSuccess(this);
                return;
            }
        }
    }

    public final void setCallback(Loader.Callback callback1)
    {
        callback = callback1;
    }
}
