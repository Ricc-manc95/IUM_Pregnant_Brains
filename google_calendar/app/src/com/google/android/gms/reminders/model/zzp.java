// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            FeatureIdProto, zzo

public class zzp extends zza
    implements FeatureIdProto
{

    public static final android.os.Parcelable.Creator CREATOR = new zzo();
    public final int mVersionCode;
    public final Long zzcif;
    public final Long zzcig;

    zzp(int i, Long long1, Long long2)
    {
        zzcif = long1;
        zzcig = long2;
        mVersionCode = i;
    }

    public zzp(FeatureIdProto featureidproto)
    {
        this(featureidproto.getCellId(), featureidproto.getFprint());
    }

    private zzp(Long long1, Long long2)
    {
        this(1, long1, long2);
    }

    public static int zza(FeatureIdProto featureidproto)
    {
        return Arrays.hashCode(new Object[] {
            featureidproto.getCellId(), featureidproto.getFprint()
        });
    }

    public static boolean zza(FeatureIdProto featureidproto, FeatureIdProto featureidproto1)
    {
        Long long1 = featureidproto.getCellId();
        Long long2 = featureidproto1.getCellId();
        boolean flag;
        if (long1 == long2 || long1 != null && long1.equals(long2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            featureidproto = featureidproto.getFprint();
            featureidproto1 = featureidproto1.getFprint();
            if (featureidproto == featureidproto1 || featureidproto != null && featureidproto.equals(featureidproto1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof FeatureIdProto))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (FeatureIdProto)obj);
        }
    }

    public final Object freeze()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final Long getCellId()
    {
        return zzcif;
    }

    public final Long getFprint()
    {
        return zzcig;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            getCellId(), getFprint()
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        Long long1 = zzcif;
        if (long1 != null)
        {
            zzc.zzb(parcel, 2, 8);
            parcel.writeLong(long1.longValue());
        }
        long1 = zzcig;
        if (long1 != null)
        {
            zzc.zzb(parcel, 3, 8);
            parcel.writeLong(long1.longValue());
        }
        zzc.zzJ(parcel, i);
    }

}
