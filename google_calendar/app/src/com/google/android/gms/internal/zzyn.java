// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import java.util.Arrays;

public final class zzyn
{

    public final Api zzaGo;
    private final com.google.android.gms.common.api.Api.ApiOptions zzaIS;
    private final boolean zzaJG;
    private final int zzaJH;

    public zzyn(Api api)
    {
        zzaJG = true;
        zzaGo = api;
        zzaIS = null;
        zzaJH = System.identityHashCode(this);
    }

    public zzyn(Api api, com.google.android.gms.common.api.Api.ApiOptions apioptions)
    {
        zzaJG = false;
        zzaGo = api;
        zzaIS = apioptions;
        zzaJH = Arrays.hashCode(new Object[] {
            zzaGo, zzaIS
        });
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof zzyn))
        {
            return false;
        }
        obj = (zzyn)obj;
        if (zzaJG || ((zzyn) (obj)).zzaJG)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = zzaGo;
        Api api = ((zzyn) (obj)).zzaGo;
        boolean flag;
        if (obj1 == api || obj1 != null && obj1.equals(api))
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
        obj1 = zzaIS;
        obj = ((zzyn) (obj)).zzaIS;
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
        return zzaJH;
    }
}
