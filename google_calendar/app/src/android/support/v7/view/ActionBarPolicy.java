// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public final class ActionBarPolicy
{

    public Context mContext;

    public ActionBarPolicy(Context context)
    {
        mContext = context;
    }

    public final int getMaxActionButtons()
    {
        Configuration configuration = mContext.getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int j = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i > 600 || i > 960 && j > 720 || i > 720 && j > 960)
        {
            return 5;
        }
        if (i >= 500 || i > 640 && j > 480 || i > 480 && j > 640)
        {
            return 4;
        }
        return i < 360 ? 2 : 3;
    }
}
