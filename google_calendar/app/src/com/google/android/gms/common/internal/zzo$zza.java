// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Arrays;

final class zzaql
{

    private final String mAction;
    private final String zzaQt;
    private final ComponentName zzaql;

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof zzaql))
        {
            return false;
        }
        obj = (zzaql)obj;
        Object obj1 = mAction;
        String s = ((mAction) (obj)).mAction;
        boolean flag;
        if (obj1 == s || obj1 != null && obj1.equals(s))
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
        obj1 = zzaql;
        obj = ((zzaql) (obj)).zzaql;
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
            mAction, zzaql
        });
    }

    public final String toString()
    {
        if (mAction == null)
        {
            return zzaql.flattenToString();
        } else
        {
            return mAction;
        }
    }

    public final Intent zzzm()
    {
        if (mAction != null)
        {
            return (new Intent(mAction)).setPackage(zzaQt);
        } else
        {
            return (new Intent()).setComponent(zzaql);
        }
    }

    public (ComponentName componentname)
    {
        mAction = null;
        zzaQt = null;
        if (componentname == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            zzaql = (ComponentName)componentname;
            return;
        }
    }

    public zzaql(String s, String s1)
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        mAction = s;
        if (TextUtils.isEmpty(s1))
        {
            throw new IllegalArgumentException("Given String is empty or null");
        } else
        {
            zzaQt = s1;
            zzaql = null;
            return;
        }
    }
}
