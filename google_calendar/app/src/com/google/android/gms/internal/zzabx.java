// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.AppOpsManager;
import android.content.Context;

public final class zzabx
{

    public final Context mContext;

    public zzabx(Context context)
    {
        mContext = context;
    }

    public final boolean zzg(int i, String s)
    {
        try
        {
            ((AppOpsManager)mContext.getSystemService("appops")).checkPackage(i, s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return false;
        }
        return true;
    }
}
