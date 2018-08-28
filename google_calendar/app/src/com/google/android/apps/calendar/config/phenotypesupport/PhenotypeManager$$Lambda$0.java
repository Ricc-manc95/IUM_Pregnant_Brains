// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.phenotypesupport;

import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.apps.calendar.config.phenotypesupport:
//            PhenotypeManager

final class arg._cls1
    implements ResultCallback
{

    private final GoogleApiClient arg$1;

    public final void onResult(Result result)
    {
        GoogleApiClient googleapiclient = arg$1;
        result = (Status)result;
        LogUtils.d(PhenotypeManager.TAG, "Registered: %s", new Object[] {
            result
        });
        googleapiclient.disconnect();
    }

    Y(GoogleApiClient googleapiclient)
    {
        arg$1 = googleapiclient;
    }
}
