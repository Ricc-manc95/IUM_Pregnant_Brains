// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;

public abstract class zzk extends zza
{

    private volatile transient boolean zzaXA;

    public zzk()
    {
        zzaXA = false;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag;
        if (!zzaXA)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            zzaXA = true;
            zzK(parcel, i);
            return;
        }
    }

    public abstract void zzK(Parcel parcel, int i);
}
