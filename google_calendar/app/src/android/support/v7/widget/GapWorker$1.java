// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import java.util.Comparator;

final class sk
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        int i;
        boolean flag1;
        flag1 = true;
        obj = (sk)obj;
        obj1 = (sk)obj1;
        boolean flag;
        if (((sk) (obj)).view == null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (((sk) (obj1)).view == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (i == flag) goto _L2; else goto _L1
_L1:
        if (((sk) (obj)).view != null) goto _L4; else goto _L3
_L3:
        i = ((flag1) ? 1 : 0);
_L6:
        return i;
_L4:
        return -1;
_L2:
        if (((sk) (obj)).immediate == ((sk) (obj1)).immediate)
        {
            break; /* Loop/switch isn't completed */
        }
        i = ((flag1) ? 1 : 0);
        if (((sk) (obj)).immediate)
        {
            return -1;
        }
        if (true) goto _L6; else goto _L5
_L5:
        int j = ((sk) (obj1)).viewVelocity - ((sk) (obj)).viewVelocity;
        i = j;
        if (j == 0)
        {
            int k = ((sk) (obj)).distanceToItem - ((sk) (obj1)).distanceToItem;
            i = k;
            if (k == 0)
            {
                return 0;
            }
        }
        if (true) goto _L6; else goto _L7
_L7:
    }

    sk()
    {
    }
}
