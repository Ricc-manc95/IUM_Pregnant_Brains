// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zzh
{

    public static final ConcurrentHashMap zzcaE = new ConcurrentHashMap();
    public final Uri mUri;
    public final ContentResolver zzcaF;
    public final ContentObserver zzcaG = new _cls1(null);
    private final Object zzcaH = new Object();
    private volatile Map zzcaI;

    zzh(ContentResolver contentresolver, Uri uri)
    {
        zzcaF = contentresolver;
        mUri = uri;
    }

    static Object zza(zzh zzh1)
    {
        return zzh1.zzcaH;
    }

    static Map zza(zzh zzh1, Map map)
    {
        zzh1.zzcaI = null;
        return null;
    }

    public final Integer getInteger(String s, Integer integer)
    {
        integer = null;
        String s1 = getString(s, null);
        s = integer;
        if (s1 != null)
        {
            int i;
            try
            {
                i = Integer.parseInt(s1);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                s = String.valueOf(s);
                Log.e("PhenotypeCfgPkg", (new StringBuilder(String.valueOf(s).length() + 17)).append("Invalid integer: ").append(s).toString());
                return null;
            }
            s = Integer.valueOf(i);
        }
        return s;
    }

    public final Long getLong(String s, Long long1)
    {
        long1 = null;
        String s1 = getString(s, null);
        s = long1;
        if (s1 != null)
        {
            long l;
            try
            {
                l = Long.parseLong(s1);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                s = String.valueOf(s);
                Log.e("PhenotypeCfgPkg", (new StringBuilder(String.valueOf(s).length() + 14)).append("Invalid long: ").append(s).toString());
                return null;
            }
            s = Long.valueOf(l);
        }
        return s;
    }

    public final String getString(String s, String s1)
    {
        Object obj;
        obj = zzcaI;
        s1 = ((String) (obj));
        if (obj != null) goto _L2; else goto _L1
_L1:
        Object obj1 = zzcaH;
        obj1;
        JVM INSTR monitorenter ;
        obj = zzcaI;
        s1 = ((String) (obj));
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        s1 = new HashMap();
        obj = zzcaF.query(mUri, null, null, null, null);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        for (; ((Cursor) (obj)).moveToNext(); s1.put(((Cursor) (obj)).getString(0), ((Cursor) (obj)).getString(1))) { }
        break MISSING_BLOCK_LABEL_107;
        s;
        ((Cursor) (obj)).close();
        throw s;
        s;
        obj1;
        JVM INSTR monitorexit ;
        throw s;
        ((Cursor) (obj)).close();
        zzcaI = s1;
        obj1;
        JVM INSTR monitorexit ;
_L2:
        s1 = (String)s1.get(s);
        s = s1;
        if (s1 == null)
        {
            s = null;
        }
        return s;
    }

    public final Double zza(String s, Double double1)
    {
        double1 = null;
        String s1 = getString(s, null);
        s = double1;
        if (s1 != null)
        {
            double d;
            try
            {
                d = Double.parseDouble(s1);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                s = String.valueOf(s);
                Log.e("PhenotypeCfgPkg", (new StringBuilder(String.valueOf(s).length() + 16)).append("Invalid double: ").append(s).toString());
                return null;
            }
            s = Double.valueOf(d);
        }
        return s;
    }


    private class _cls1 extends ContentObserver
    {

        private final zzh zzcaJ;

        public final void onChange(boolean flag)
        {
            synchronized (zzh.zza(zzcaJ))
            {
                zzh.zza(zzcaJ, null);
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls1(Handler handler)
        {
            zzcaJ = zzh.this;
            super(null);
        }
    }

}
