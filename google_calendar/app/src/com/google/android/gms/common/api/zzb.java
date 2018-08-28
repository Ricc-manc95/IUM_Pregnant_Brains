// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzyn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.gms.common.api:
//            Api

public final class zzb extends Exception
{

    public final ArrayMap zzaIK;

    public zzb(ArrayMap arraymap)
    {
        zzaIK = arraymap;
    }

    public final String getMessage()
    {
        ArrayList arraylist = new ArrayList();
        Object obj = zzaIK.keySet().iterator();
        boolean flag = true;
        while (((Iterator) (obj)).hasNext()) 
        {
            Object obj2 = (zzyn)((Iterator) (obj)).next();
            Object obj1 = (ConnectionResult)zzaIK.get(obj2);
            boolean flag1;
            if (((ConnectionResult) (obj1)).zzaEP == 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                flag = false;
            }
            obj2 = String.valueOf(((zzyn) (obj2)).zzaGo.mName);
            obj1 = String.valueOf(obj1);
            arraylist.add((new StringBuilder(String.valueOf(obj2).length() + 2 + String.valueOf(obj1).length())).append(((String) (obj2))).append(": ").append(((String) (obj1))).toString());
        }
        obj = new StringBuilder();
        if (flag)
        {
            ((StringBuilder) (obj)).append("None of the queried APIs are available. ");
        } else
        {
            ((StringBuilder) (obj)).append("Some of the queried APIs are unavailable. ");
        }
        ((StringBuilder) (obj)).append(TextUtils.join("; ", arraylist));
        return ((StringBuilder) (obj)).toString();
    }
}
