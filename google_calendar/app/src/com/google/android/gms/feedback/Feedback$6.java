// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzaoc;
import com.google.android.gms.internal.zzaod;
import com.google.android.gms.internal.zzys;

// Referenced classes of package com.google.android.gms.feedback:
//            FeedbackOptions

final class Client extends a
{

    private final FeedbackOptions zzbha;

    protected final void zza(com.google.android.gms.common.api.ient ient)
        throws RemoteException
    {
        ient = (zzaoc)ient;
        FeedbackOptions feedbackoptions = zzbha;
        ((zzaod)ient.zzyP()).zza(zzaoc.zza(feedbackoptions, ((zzaoc) (ient)).mContext.getCacheDir()));
        zzb(Status.zzaJt);
    }

    Client(GoogleApiClient googleapiclient, FeedbackOptions feedbackoptions)
    {
        zzbha = feedbackoptions;
        super(googleapiclient);
    }
}
