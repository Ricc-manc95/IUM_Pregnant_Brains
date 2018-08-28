// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            CompositeLoader, Loader

public abstract class SerialLoader extends CompositeLoader
{

    private int currentLoaderIndex;
    private final Object result;

    public SerialLoader(Object obj)
    {
        currentLoaderIndex = -1;
        result = obj;
    }

    public final Object getResult()
    {
        return result;
    }

    public final void load()
    {
        while (isRunning() || isFinished() || Collections.unmodifiableList(super.loaders).isEmpty()) 
        {
            return;
        }
        currentLoaderIndex = 0;
        Loader loader = (Loader)Collections.unmodifiableList(super.loaders).get(currentLoaderIndex);
        loader.setCallback(this);
        loader.load();
    }

    public abstract void onLoaderFinished(int i, Object obj);

    public final void onLoadingSuccess(Loader loader)
    {
        onLoaderFinished(currentLoaderIndex, result);
        if (currentLoaderIndex + 1 < Collections.unmodifiableList(super.loaders).size())
        {
            loader = Collections.unmodifiableList(super.loaders);
            int i = currentLoaderIndex + 1;
            currentLoaderIndex = i;
            loader = (Loader)loader.get(i);
            loader.setCallback(this);
            loader.load();
            return;
        } else
        {
            super.onLoadingSuccess(loader);
            return;
        }
    }
}
