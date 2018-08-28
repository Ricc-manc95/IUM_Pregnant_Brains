// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.common.api:
//            zzg

public final class Scope extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzg();
    public final int mVersionCode;
    public final String zzaJs;

    Scope(int i, String s)
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException(String.valueOf("scopeUri must not be null or empty"));
        } else
        {
            mVersionCode = i;
            zzaJs = s;
            return;
        }
    }

    public Scope(String s)
    {
        this(1, s);
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Scope))
        {
            return false;
        } else
        {
            return zzaJs.equals(((Scope)obj).zzaJs);
        }
    }

    public final int hashCode()
    {
        return zzaJs.hashCode();
    }

    public final String toString()
    {
        return zzaJs;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        String s = zzaJs;
        if (s != null)
        {
            parcel.writeInt(-65534);
            parcel.writeInt(0);
            j = parcel.dataPosition();
            parcel.writeString(s);
            int k = parcel.dataPosition();
            parcel.setDataPosition(j - 4);
            parcel.writeInt(k - j);
            parcel.setDataPosition(k);
        }
        j = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(j - i);
        parcel.setDataPosition(j);
    }

}
