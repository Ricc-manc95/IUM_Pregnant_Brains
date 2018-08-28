// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzi

public final class nit> extends zzi
{

    private final Activity val$activity;
    private final Intent val$intent;
    private final int val$requestCode;

    public final void zzzg()
    {
        if (val$intent != null)
        {
            val$activity.startActivityForResult(val$intent, val$requestCode);
        }
    }

    public ()
    {
        val$intent = intent1;
        val$activity = activity1;
        val$requestCode = i;
        super();
    }
}
