// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import java.util.Arrays;

final class PickSubchannelArgsImpl extends io.grpc.LoadBalancer.PickSubchannelArgs
{

    private final CallOptions callOptions;
    private final Metadata headers;
    private final MethodDescriptor method;

    PickSubchannelArgsImpl(MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions)
    {
        if (methoddescriptor == null)
        {
            throw new NullPointerException(String.valueOf("method"));
        }
        method = (MethodDescriptor)methoddescriptor;
        if (metadata == null)
        {
            throw new NullPointerException(String.valueOf("headers"));
        }
        headers = (Metadata)metadata;
        if (calloptions == null)
        {
            throw new NullPointerException(String.valueOf("callOptions"));
        } else
        {
            callOptions = (CallOptions)calloptions;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        obj = (PickSubchannelArgsImpl)obj;
        Object obj1 = callOptions;
        Object obj2 = ((PickSubchannelArgsImpl) (obj)).callOptions;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = headers;
        obj2 = ((PickSubchannelArgsImpl) (obj)).headers;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = method;
        obj = ((PickSubchannelArgsImpl) (obj)).method;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final CallOptions getCallOptions()
    {
        return callOptions;
    }

    public final Metadata getHeaders()
    {
        return headers;
    }

    public final MethodDescriptor getMethodDescriptor()
    {
        return method;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            callOptions, headers, method
        });
    }

    public final String toString()
    {
        String s = String.valueOf(method);
        String s1 = String.valueOf(headers);
        String s2 = String.valueOf(callOptions);
        return (new StringBuilder(String.valueOf(s).length() + 31 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("[method=").append(s).append(" headers=").append(s1).append(" callOptions=").append(s2).append("]").toString();
    }
}
