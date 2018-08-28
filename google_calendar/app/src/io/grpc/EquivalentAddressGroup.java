// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package io.grpc:
//            Attributes

public final class EquivalentAddressGroup
{

    public final List addrs;
    public final Attributes attrs;
    private final int hashCode;

    public EquivalentAddressGroup(SocketAddress socketaddress)
    {
        this(socketaddress, Attributes.EMPTY);
    }

    private EquivalentAddressGroup(SocketAddress socketaddress, Attributes attributes)
    {
        this(Collections.singletonList(socketaddress), attributes);
    }

    private EquivalentAddressGroup(List list, Attributes attributes)
    {
        boolean flag;
        if (!list.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("addrs is empty"));
        }
        addrs = Collections.unmodifiableList(new ArrayList(list));
        if (attributes == null)
        {
            throw new NullPointerException(String.valueOf("attrs"));
        } else
        {
            attrs = (Attributes)attributes;
            hashCode = addrs.hashCode();
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof EquivalentAddressGroup) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if (addrs.size() != ((EquivalentAddressGroup) (obj = (EquivalentAddressGroup)obj)).addrs.size()) goto _L1; else goto _L3
_L3:
        int i = 0;
_L5:
        if (i >= addrs.size())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!((SocketAddress)addrs.get(i)).equals(((EquivalentAddressGroup) (obj)).addrs.get(i))) goto _L1; else goto _L4
_L4:
        i++;
          goto _L5
        if (!attrs.equals(((EquivalentAddressGroup) (obj)).attrs)) goto _L1; else goto _L6
_L6:
        return true;
    }

    public final int hashCode()
    {
        return hashCode;
    }

    public final String toString()
    {
        String s = String.valueOf(addrs);
        String s1 = String.valueOf(attrs);
        return (new StringBuilder(String.valueOf(s).length() + 16 + String.valueOf(s1).length())).append("[addrs=").append(s).append(", attrs=").append(s1).append("]").toString();
    }
}
