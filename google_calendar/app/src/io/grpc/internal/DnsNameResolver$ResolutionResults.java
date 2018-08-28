// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.Collections;
import java.util.List;

// Referenced classes of package io.grpc.internal:
//            DnsNameResolver

static final class balancerAddresses
{

    public final List addresses;
    public final List balancerAddresses;
    public final List txtRecords;

    (List list, List list1, List list2)
    {
        if (list == null)
        {
            throw new NullPointerException(String.valueOf("addresses"));
        }
        addresses = Collections.unmodifiableList((List)list);
        if (list1 == null)
        {
            throw new NullPointerException(String.valueOf("txtRecords"));
        }
        txtRecords = Collections.unmodifiableList((List)list1);
        if (list2 == null)
        {
            throw new NullPointerException(String.valueOf("balancerAddresses"));
        } else
        {
            balancerAddresses = Collections.unmodifiableList((List)list2);
            return;
        }
    }
}
