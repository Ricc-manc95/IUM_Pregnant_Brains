// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.util.Log;

public abstract class zzi
    implements android.content.DialogInterface.OnClickListener
{

    public zzi()
    {
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        zzzg();
        dialoginterface.dismiss();
        return;
        Object obj;
        obj;
        Log.e("DialogRedirect", "Failed to start resolution intent", ((Throwable) (obj)));
        dialoginterface.dismiss();
        return;
        obj;
        dialoginterface.dismiss();
        throw obj;
    }

    protected abstract void zzzg();
}
