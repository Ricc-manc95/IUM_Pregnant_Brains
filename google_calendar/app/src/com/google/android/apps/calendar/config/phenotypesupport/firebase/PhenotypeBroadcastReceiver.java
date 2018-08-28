// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.phenotypesupport.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.google.android.apps.calendar.config.phenotypesupport.firebase:
//            PhenotypeCommitFirebaseService

public class PhenotypeBroadcastReceiver extends BroadcastReceiver
{

    public PhenotypeBroadcastReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        PhenotypeCommitFirebaseService.scheduleJob(context);
    }
}
