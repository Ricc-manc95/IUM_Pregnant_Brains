// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.Arrays;

// Referenced classes of package io.grpc:
//            Status

public final class drop
{

    public static final ry NO_RESULT;
    public final boolean drop;
    public final Status status;
    public final ry streamTracerFactory;
    public final ry subchannel;

    public static drop withDrop(Status status1)
    {
        boolean flag1 = false;
        boolean flag;
        if (ry == status1.code)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag1 = true;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("drop status shouldn't be OK"));
        } else
        {
            return new <init>(null, null, status1, true);
        }
    }

    public static ry withError(Status status1)
    {
        boolean flag1 = true;
        boolean flag;
        if (ry == status1.code)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("error status shouldn't be OK"));
        } else
        {
            return new <init>(null, null, status1, false);
        }
    }

    public static ry withSubchannel(ry ry)
    {
        if (ry == null)
        {
            throw new NullPointerException(String.valueOf("subchannel"));
        } else
        {
            return new <init>((<init>)ry, null, Status.OK, false);
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof ry)
        {
            obj = (ry)obj;
            ry ry = subchannel;
            ry ry2 = ((subchannel) (obj)).subchannel;
            boolean flag;
            if (ry == ry2 || ry != null && ry.equals(ry2))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Status status1 = status;
                Status status2 = ((status) (obj)).status;
                if (status1 == status2 || status1 != null && status1.equals(status2))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    ry ry1 = streamTracerFactory;
                    ry ry3 = ((streamTracerFactory) (obj)).streamTracerFactory;
                    if (ry1 == ry3 || ry1 != null && ry1.equals(ry3))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag && drop == ((drop) (obj)).drop)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            subchannel, status, streamTracerFactory, Boolean.valueOf(drop)
        });
    }

    public final String toString()
    {
        return (new com.google.common.base.er(getClass().getSimpleName())).add("subchannel", subchannel).add("streamTracerFactory", streamTracerFactory).add("status", status).add("drop", drop).toString();
    }

    static 
    {
        NO_RESULT = new <init>(null, null, Status.OK, false);
    }

    private ry(ry ry, ry ry1, Status status1, boolean flag)
    {
        subchannel = ry;
        streamTracerFactory = ry1;
        if (status1 == null)
        {
            throw new NullPointerException(String.valueOf("status"));
        } else
        {
            status = (Status)status1;
            drop = flag;
            return;
        }
    }
}
