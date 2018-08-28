// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import java.util.ArrayList;
import java.util.List;

public class zzo extends zzc
{

    public int zzaNR;
    public final String zzchp;
    private final boolean zzchq;

    public zzo(DataHolder dataholder, int i)
    {
        this(dataholder, i, "");
    }

    public zzo(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i);
        if (s == null)
        {
            dataholder = "";
        } else
        {
            dataholder = s;
        }
        zzchp = dataholder;
        zzchq = TextUtils.isEmpty(s);
    }

    protected static String zzaw(String s, String s1)
    {
        if (TextUtils.isEmpty(s))
        {
            return s1;
        }
        s = String.valueOf(s);
        s1 = String.valueOf(s1);
        if (s1.length() != 0)
        {
            return s.concat(s1);
        } else
        {
            return new String(s);
        }
    }

    protected final void zzfM(int i)
    {
        super.zzfM(i);
        zzaNR = zzaKT.zzfO(zzaNQ);
    }

    protected final String zziU(String s)
    {
        if (zzchq)
        {
            return s;
        }
        String s1 = String.valueOf(zzchp);
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            return s1.concat(s);
        } else
        {
            return new String(s1);
        }
    }

    protected final List zziV(String s)
    {
        int i = 0;
        if (!zzcO(s)) goto _L2; else goto _L1
_L1:
        s = new ArrayList(0);
_L4:
        return s;
_L2:
        String s1 = getString(s);
        ArrayList arraylist = new ArrayList();
        s = arraylist;
        if (TextUtils.isEmpty(s1))
        {
            continue;
        }
        String as[] = TextUtils.split(s1, ",");
        int j = as.length;
        do
        {
            s = arraylist;
            if (i >= j)
            {
                continue;
            }
            arraylist.add(Integer.valueOf(as[i]));
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }
}
