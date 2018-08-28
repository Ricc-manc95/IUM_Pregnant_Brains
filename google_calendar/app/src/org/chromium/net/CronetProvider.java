// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.chromium.net;

import android.content.Context;

public abstract class CronetProvider
{

    public final Context mContext;

    public CronetProvider(Context context)
    {
        if (context == null)
        {
            throw new IllegalArgumentException("Context must not be null");
        } else
        {
            mContext = context;
            return;
        }
    }

    public abstract String getName();

    public abstract String getVersion();

    public abstract boolean isEnabled();

    public String toString()
    {
        return (new StringBuilder("[class=")).append(getClass().getName()).append(", name=").append(getName()).append(", version=").append(getVersion()).append(", enabled=").append(isEnabled()).append("]").toString();
    }

    static 
    {
        org/chromium/net/CronetProvider.getSimpleName();
    }
}
