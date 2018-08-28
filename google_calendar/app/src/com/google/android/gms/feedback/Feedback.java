// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

// Referenced classes of package com.google.android.gms.feedback:
//            FeedbackOptions

public final class Feedback
{

    public static final Api API;
    private static final com.google.android.gms.common.api.Api.zzf zzahs;
    private static final com.google.android.gms.common.api.Api.zza zzaht;

    public static PendingResult silentSendFeedback(GoogleApiClient googleapiclient, FeedbackOptions feedbackoptions)
    {
        return googleapiclient.zza(new _cls8(googleapiclient, feedbackoptions));
    }

    public static PendingResult startFeedback(GoogleApiClient googleapiclient, FeedbackOptions feedbackoptions)
    {
        return googleapiclient.zza(new _cls6(googleapiclient, feedbackoptions));
    }

    static 
    {
        zzahs = new com.google.android.gms.common.api.Api.zzf();
        zzaht = new _cls1();
        API = new Api("Feedback.API", zzaht, zzahs);
    }

    private class _cls8 extends zza
    {
        private class zza extends com.google.android.gms.internal.zzyq.zza
        {

            public final Result zzb(Status status)
            {
                return status;
            }

            public zza(GoogleApiClient googleapiclient)
            {
                super(Feedback.API, googleapiclient);
            }
        }


        private final FeedbackOptions zzbha;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb)
            throws RemoteException
        {
            zzb = (zzaoc)zzb;
            FeedbackOptions feedbackoptions = zzbha;
            ((zzaod)zzb.zzyP()).zzb(zzaoc.zza(feedbackoptions, ((zzaoc) (zzb)).mContext.getCacheDir()));
            zzb(Status.zzaJt);
        }

        _cls8(GoogleApiClient googleapiclient, FeedbackOptions feedbackoptions)
        {
            zzbha = feedbackoptions;
            super(googleapiclient);
        }
    }


    private class _cls6 extends zza
    {

        private final FeedbackOptions zzbha;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb)
            throws RemoteException
        {
            zzb = (zzaoc)zzb;
            FeedbackOptions feedbackoptions = zzbha;
            ((zzaod)zzb.zzyP()).zza(zzaoc.zza(feedbackoptions, ((zzaoc) (zzb)).mContext.getCacheDir()));
            zzb(Status.zzaJt);
        }

        _cls6(GoogleApiClient googleapiclient, FeedbackOptions feedbackoptions)
        {
            zzbha = feedbackoptions;
            super(googleapiclient);
        }
    }


    private class _cls1 extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, zzg zzg, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return new zzaoc(context, looper, connectioncallbacks, onconnectionfailedlistener, zzg);
        }

        _cls1()
        {
        }
    }

}
