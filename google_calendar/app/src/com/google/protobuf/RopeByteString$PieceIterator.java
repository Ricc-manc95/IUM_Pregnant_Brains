// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

// Referenced classes of package com.google.protobuf:
//            RopeByteString, ByteString

final class getLeafByLeft
    implements Iterator
{

    private final Stack breadCrumbs = new Stack();
    private breadCrumbs next;

    private final getLeafByLeft getLeafByLeft(ByteString bytestring)
    {
        for (; bytestring instanceof RopeByteString; bytestring = ((RopeByteString) (bytestring)).left)
        {
            bytestring = (RopeByteString)bytestring;
            breadCrumbs.push(bytestring);
        }

        return (breadCrumbs)bytestring;
    }

    public final boolean hasNext()
    {
        return next != null;
    }

    public final Object next()
    {
        next next2;
        if (next == null)
        {
            throw new NoSuchElementException();
        }
        next2 = next;
_L4:
        if (!breadCrumbs.isEmpty()) goto _L2; else goto _L1
_L1:
        next next1 = null;
_L3:
        next = next1;
        return next2;
_L2:
        next1 = getLeafByLeft(((RopeByteString)breadCrumbs.pop()).right);
        boolean flag;
        if (next1.size() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L4; else goto _L3
    }

    public final void remove()
    {
        throw new UnsupportedOperationException();
    }

    (ByteString bytestring)
    {
        next = getLeafByLeft(bytestring);
    }
}
