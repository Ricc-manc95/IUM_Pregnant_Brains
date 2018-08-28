// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


// Referenced classes of package io.grpc.internal:
//            WithLogId

public final class 
{

    public String description;
    public  severity;
    public WithLogId subchannelRef;
    public Long timestampNanos;

    public final  build()
    {
        if (description == null)
        {
            throw new NullPointerException(String.valueOf("description"));
        }
        if (severity == null)
        {
            throw new NullPointerException(String.valueOf("severity"));
        }
        if (timestampNanos == null)
        {
            throw new NullPointerException(String.valueOf("timestampNanos"));
        } else
        {
            return new timestampNanos(description, severity, timestampNanos.longValue(), null, subchannelRef);
        }
    }

    public ()
    {
    }
}
