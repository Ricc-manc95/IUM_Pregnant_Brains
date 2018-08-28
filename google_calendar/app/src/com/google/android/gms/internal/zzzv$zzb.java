// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


public final class 
{

    private final Object mListener;
    private final String zzaMM;

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof ))
            {
                return false;
            }
            obj = ()obj;
            if (mListener != ((mListener) (obj)).mListener || !zzaMM.equals(((zzaMM) (obj)).zzaMM))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return System.identityHashCode(mListener) * 31 + zzaMM.hashCode();
    }
}
