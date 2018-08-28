// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class iClient extends com.google.android.gms.people.
{

    public final Result zzb(Status status)
    {
        class _cls1
            implements com.google.android.gms.people.Images.LoadImageResult
        {

            private final Status zzavV;

            public final ParcelFileDescriptor getParcelFileDescriptor()
            {
                return null;
            }

            public final Status getStatus()
            {
                return zzavV;
            }

            public final void release()
            {
            }

            _cls1(Status status)
            {
                zzavV = status;
                super();
            }
        }

        return new _cls1(status);
    }

    iClient(GoogleApiClient googleapiclient)
    {
        super(googleapiclient);
    }
}
