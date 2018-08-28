// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import com.google.android.gms.internal.zzbdn;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzg
{

    public final Set zzaIZ;
    public final String zzaJd;
    public final Set zzaPM;
    public final Map zzaPN;
    public final zzbdn zzaPO;
    public Integer zzaPP;
    public final Account zzafe;
    public final String zzahF;

    public zzg(Account account, Set set, Map map, int i, View view, String s, String s1, 
            zzbdn zzbdn)
    {
        zzafe = account;
        if (set == null)
        {
            account = Collections.EMPTY_SET;
        } else
        {
            account = Collections.unmodifiableSet(set);
        }
        zzaIZ = account;
        account = map;
        if (map == null)
        {
            account = Collections.EMPTY_MAP;
        }
        zzaPN = account;
        zzahF = s;
        zzaJd = s1;
        zzaPO = zzbdn;
        account = new HashSet(zzaIZ);
        for (set = zzaPN.values().iterator(); set.hasNext(); account.addAll(((zza)set.next()).zzalx)) { }
        zzaPM = Collections.unmodifiableSet(account);
    }

    private class zza
    {

        public final boolean zzaPQ;
        public final Set zzalx;
    }

}
