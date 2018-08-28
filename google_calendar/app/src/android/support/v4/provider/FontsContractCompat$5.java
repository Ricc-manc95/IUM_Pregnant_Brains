// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import java.util.Comparator;

final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        boolean flag;
        flag = false;
        obj = (byte[])obj;
        obj1 = (byte[])obj1;
        if (obj.length == obj1.length) goto _L2; else goto _L1
_L1:
        int i = obj.length - obj1.length;
_L4:
        return i;
_L2:
        int j = 0;
        do
        {
            i = ((flag) ? 1 : 0);
            if (j >= obj.length)
            {
                continue;
            }
            if (obj[j] != obj1[j])
            {
                return obj[j] - obj1[j];
            }
            j++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    ()
    {
    }
}
