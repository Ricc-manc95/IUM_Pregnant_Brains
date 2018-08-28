// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.util.Arrays;

final class zzaIg
{

    public final String zzaIe;
    public final long zzaIf;
    public final long zzaIg;

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof zzaIg))
        {
            return false;
        }
        obj = (zzaIg)obj;
        Object obj1 = zzaIe;
        Object obj2 = ((zzaIe) (obj)).zzaIe;
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
        obj1 = Long.valueOf(zzaIf);
        obj2 = Long.valueOf(((zzaIf) (obj)).zzaIf);
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
        obj1 = Long.valueOf(zzaIg);
        obj = Long.valueOf(((zzaIg) (obj)).zzaIg);
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

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            zzaIe, Long.valueOf(zzaIf), Long.valueOf(zzaIg)
        });
    }

    public (String s, long l, long l1)
    {
        zzaIe = s;
        zzaIf = l;
        zzaIg = l1;
    }
}
