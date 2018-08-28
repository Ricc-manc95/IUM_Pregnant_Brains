// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.feedback:
//            Feedback

abstract class ient extends com.google.android.gms.internal.ient
{

    public final Result zzb(Status status)
    {
        return status;
    }

    public ient(GoogleApiClient googleapiclient)
    {
        super(Feedback.API, googleapiclient);
    }
}
