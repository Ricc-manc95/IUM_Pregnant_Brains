// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            zzy

public class zzx extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzy();
    public final String mTag;
    public final int mVersionCode;

    zzx(int i, String s)
    {
        mVersionCode = i;
        mTag = s;
    }

    private zzx(String s)
    {
        this(1, s);
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        obj = (zzx)obj;
        if (mTag != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (((zzx) (obj)).mTag == null) goto _L1; else goto _L3
_L3:
        return false;
        if (mTag.equals(((zzx) (obj)).mTag)) goto _L1; else goto _L4
_L4:
        return false;
    }

    public int hashCode()
    {
        int i;
        if (mTag == null)
        {
            i = 0;
        } else
        {
            i = mTag.hashCode();
        }
        return i + 31;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        zzc.zza(parcel, 1, mTag, false);
        int j = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

    static 
    {
        new zzx("=");
        new zzx("<");
        new zzx("<=");
        new zzx(">");
        new zzx(">=");
        new zzx("and");
        new zzx("or");
        new zzx("not");
        new zzx("contains");
    }
}
