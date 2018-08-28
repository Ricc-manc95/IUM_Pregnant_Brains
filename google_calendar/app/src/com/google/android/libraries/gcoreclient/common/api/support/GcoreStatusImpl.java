// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.support;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.gcoreclient.common.api.GcoreStatus;

// Referenced classes of package com.google.android.libraries.gcoreclient.common.api.support:
//            ResultWrapper

public final class GcoreStatusImpl
    implements GcoreStatus
{

    public static final ResultWrapper STATUS_RESULT_WRAPPER = new _cls1();
    private final Status status;

    public GcoreStatusImpl(Status status1)
    {
        status = status1;
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof GcoreStatusImpl))
        {
            return false;
        } else
        {
            return status.equals(((GcoreStatusImpl)obj).status);
        }
    }

    public final GcoreStatus getStatus()
    {
        return this;
    }

    public final String getStatusMessage()
    {
        return status.zzaIk;
    }

    public final int hashCode()
    {
        return status.hashCode();
    }

    public final boolean isInterrupted()
    {
        return status.zzaEP == 14;
    }

    public final boolean isSuccess()
    {
        return status.zzaEP <= 0;
    }

    public final String toString()
    {
        return status.toString();
    }


    private class _cls1
        implements ResultWrapper
    {

        public final GcoreResult wrap(Result result)
        {
            return new GcoreStatusImpl((Status)result);
        }

        _cls1()
        {
        }
    }

}
