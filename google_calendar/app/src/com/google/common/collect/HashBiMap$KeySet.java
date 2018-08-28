// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            HashBiMap

final class nit> extends nit>
{

    private final HashBiMap this$0;

    public final boolean contains(Object obj)
    {
        return containsKey(obj);
    }

    final Object forEntry(int i)
    {
        return keys[i];
    }

    public final boolean remove(Object obj)
    {
        boolean flag = false;
        HashBiMap hashbimap;
        int i;
        int j;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        i = (int)((long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15) * 0x1b873593L);
        hashbimap = HashBiMap.this;
        j = hashbimap.findEntry(obj, i, hashbimap.hashTableKToV, hashbimap.nextInBucketKToV, hashbimap.keys);
        if (j != -1)
        {
            removeEntryKeyHashKnown(j, i);
            flag = true;
        }
        return flag;
    }

    ()
    {
        this$0 = HashBiMap.this;
        super(HashBiMap.this);
    }
}
