// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.chromium.net.impl;

import android.content.Context;
import java.util.Arrays;
import org.chromium.net.CronetProvider;

public class JavaCronetProvider extends CronetProvider
{

    public JavaCronetProvider(Context context)
    {
        super(context);
    }

    public boolean equals(Object obj)
    {
        return obj == this || (obj instanceof JavaCronetProvider) && mContext.equals(((JavaCronetProvider)obj).mContext);
    }

    public final String getName()
    {
        return "Fallback-Cronet-Provider";
    }

    public final String getVersion()
    {
        return "69.0.3475.0";
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            org/chromium/net/impl/JavaCronetProvider, mContext
        });
    }

    public final boolean isEnabled()
    {
        return true;
    }
}
