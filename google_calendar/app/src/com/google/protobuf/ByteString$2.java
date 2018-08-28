// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Comparator;

// Referenced classes of package com.google.protobuf:
//            ByteString

final class teIterator
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (ByteString)obj;
        obj1 = (ByteString)obj1;
        teIterator teiterator = (teIterator)((ByteString) (obj)).iterator();
        for (teIterator teiterator1 = (teIterator)((ByteString) (obj1)).iterator(); teiterator.hasNext() && teiterator1.hasNext();)
        {
            int i = Integer.compare(ByteString.toInt(teiterator.nextByte()), ByteString.toInt(teiterator1.nextByte()));
            if (i != 0)
            {
                return i;
            }
        }

        return Integer.compare(((ByteString) (obj)).size(), ((ByteString) (obj1)).size());
    }

    teIterator()
    {
    }
}
