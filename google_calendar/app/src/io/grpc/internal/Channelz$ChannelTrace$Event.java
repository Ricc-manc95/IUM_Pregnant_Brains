// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.Arrays;

// Referenced classes of package io.grpc.internal:
//            WithLogId

public final class subchannelRef
{

    private final WithLogId channelRef;
    private final String description;
    private final Severity severity;
    private final WithLogId subchannelRef;
    private final long timestampNanos;

    public final boolean equals(Object obj)
    {
        if (obj instanceof Severity)
        {
            obj = (Severity)obj;
            String s = description;
            String s1 = ((description) (obj)).description;
            boolean flag;
            if (s == s1 || s != null && s.equals(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Severity severity1 = severity;
                Severity severity2 = ((severity) (obj)).severity;
                if (severity1 == severity2 || severity1 != null && severity1.equals(severity2))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && timestampNanos == ((timestampNanos) (obj)).timestampNanos)
                {
                    WithLogId withlogid = channelRef;
                    WithLogId withlogid2 = ((channelRef) (obj)).channelRef;
                    if (withlogid == withlogid2 || withlogid != null && withlogid.equals(withlogid2))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        WithLogId withlogid1 = subchannelRef;
                        obj = ((subchannelRef) (obj)).subchannelRef;
                        if (withlogid1 == obj || withlogid1 != null && withlogid1.equals(obj))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            description, severity, Long.valueOf(timestampNanos), channelRef, subchannelRef
        });
    }

    public final String toString()
    {
        return (new com.google.common.base.init>(getClass().getSimpleName())).dd("description", description).dd("severity", severity).dd("timestampNanos", timestampNanos).dd("channelRef", channelRef).dd("subchannelRef", subchannelRef).oString();
    }

    Severity(String s, Severity severity1, long l, WithLogId withlogid, WithLogId withlogid1)
    {
        description = s;
        if (severity1 == null)
        {
            throw new NullPointerException(String.valueOf("severity"));
        } else
        {
            class Severity extends Enum
            {

                private static final Severity $VALUES[];
                private static final Severity CT_ERROR;
                public static final Severity CT_INFO;
                private static final Severity CT_UNKNOWN;
                public static final Severity CT_WARNING;

                public static Severity[] values()
                {
                    return (Severity[])$VALUES.clone();
                }

                static 
                {
                    CT_UNKNOWN = new Severity("CT_UNKNOWN", 0);
                    CT_INFO = new Severity("CT_INFO", 1);
                    CT_WARNING = new Severity("CT_WARNING", 2);
                    CT_ERROR = new Severity("CT_ERROR", 3);
                    $VALUES = (new Severity[] {
                        CT_UNKNOWN, CT_INFO, CT_WARNING, CT_ERROR
                    });
                }

            private Severity(String s, int i)
            {
                super(s, i);
            }
            }

            severity = (Severity)severity1;
            timestampNanos = l;
            channelRef = withlogid;
            subchannelRef = withlogid1;
            return;
        }
    }
}
