// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            ConnectivityState, Status

public final class ConnectivityStateInfo
{

    public final ConnectivityState state;
    public final Status status;

    public ConnectivityStateInfo(ConnectivityState connectivitystate, Status status1)
    {
        if (connectivitystate == null)
        {
            throw new NullPointerException(String.valueOf("state is null"));
        }
        state = (ConnectivityState)connectivitystate;
        if (status1 == null)
        {
            throw new NullPointerException(String.valueOf("status is null"));
        } else
        {
            status = (Status)status1;
            return;
        }
    }

    public static ConnectivityStateInfo forNonError(ConnectivityState connectivitystate)
    {
        boolean flag;
        if (connectivitystate != ConnectivityState.TRANSIENT_FAILURE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("state is TRANSIENT_ERROR. Use forError() instead"));
        } else
        {
            return new ConnectivityStateInfo(connectivitystate, Status.OK);
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof ConnectivityStateInfo)
        {
            if (state.equals(((ConnectivityStateInfo) (obj = (ConnectivityStateInfo)obj)).state) && status.equals(((ConnectivityStateInfo) (obj)).status))
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return state.hashCode() ^ status.hashCode();
    }

    public final String toString()
    {
        Status status1 = status;
        boolean flag;
        if (Status.Code.OK == status1.code)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return state.toString();
        } else
        {
            String s = String.valueOf(state);
            String s1 = String.valueOf(status);
            return (new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(s1).length())).append(s).append("(").append(s1).append(")").toString();
        }
    }
}
