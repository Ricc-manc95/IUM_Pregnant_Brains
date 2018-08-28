// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzaau
{

    public static zza zzaNH = null;
    public static int zzaNI = 0;
    public static final Object zzun = new Object();
    public final String zzAZ;
    public final Object zzBa;
    private Object zzadW;

    protected zzaau(String s, Object obj)
    {
        zzadW = null;
        zzAZ = s;
        zzBa = obj;
    }

    public static zzaau zzH(String s, String s1)
    {
        return new _cls5(s, s1);
    }

    public static zzaau zza(String s, Integer integer)
    {
        return new _cls3(s, integer);
    }

    public static zzaau zza(String s, Long long1)
    {
        return new _cls2(s, long1);
    }

    public static zzaau zzm(String s, boolean flag)
    {
        return new _cls1(s, Boolean.valueOf(false));
    }

    public final Object get()
    {
        Object obj = zzcH(zzAZ);
        return obj;
        Object obj1;
        obj1;
        long l = Binder.clearCallingIdentity();
        obj1 = zzcH(zzAZ);
        Binder.restoreCallingIdentity(l);
        return obj1;
        obj1;
        Binder.restoreCallingIdentity(l);
        throw obj1;
    }

    protected abstract Object zzcH(String s);


    private class _cls5 extends zzaau
    {

        protected final Object zzcH(String s)
        {
            return zzaau.zzaNH.getString(zzAZ, (String)zzBa);
        }

        _cls5(String s, String s1)
        {
            super(s, s1);
        }

        private class zza
        {

            public abstract Long getLong(String s, Long long1);

            public abstract String getString(String s, String s1);

            public abstract Boolean zza(String s, Boolean boolean1);

            public abstract Integer zzb(String s, Integer integer);
        }

    }


    private class _cls3 extends zzaau
    {

        protected final Object zzcH(String s)
        {
            return zzaau.zzaNH.zzb(zzAZ, (Integer)zzBa);
        }

        _cls3(String s, Integer integer)
        {
            super(s, integer);
        }
    }


    private class _cls2 extends zzaau
    {

        protected final Object zzcH(String s)
        {
            return zzaau.zzaNH.getLong(zzAZ, (Long)zzBa);
        }

        _cls2(String s, Long long1)
        {
            super(s, long1);
        }
    }


    private class _cls1 extends zzaau
    {

        protected final Object zzcH(String s)
        {
            return zzaau.zzaNH.zza(zzAZ, (Boolean)zzBa);
        }

        _cls1(String s, Boolean boolean1)
        {
            super(s, boolean1);
        }
    }

}
