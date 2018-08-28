// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.Address;
import com.google.android.gms.reminders.model.zzb;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo

public class zza extends zzo
    implements Address
{

    public zza(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = zzaw(s, "country");
        dataholder.zzj(s1, i);
        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            String s2 = zzaw(s, "locality");
            dataholder.zzj(s2, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s2)))
            {
                String s3 = zzaw(s, "region");
                dataholder.zzj(s3, i);
                if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s3)))
                {
                    String s4 = zzaw(s, "street_address");
                    dataholder.zzj(s4, i);
                    if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s4)))
                    {
                        String s5 = zzaw(s, "street_number");
                        dataholder.zzj(s5, i);
                        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s5)))
                        {
                            String s6 = zzaw(s, "street_name");
                            dataholder.zzj(s6, i);
                            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s6)))
                            {
                                String s7 = zzaw(s, "postal_code");
                                dataholder.zzj(s7, i);
                                if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s7)))
                                {
                                    s = zzaw(s, "name");
                                    dataholder.zzj(s, i);
                                    if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Address))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzb.zza(this, (Address)obj);
        }
    }

    public final Object freeze()
    {
        return new zzb(this);
    }

    public final String getCountry()
    {
        return getString(zziU("country"));
    }

    public final String getLocality()
    {
        return getString(zziU("locality"));
    }

    public final String getName()
    {
        return getString(zziU("name"));
    }

    public final String getPostalCode()
    {
        return getString(zziU("postal_code"));
    }

    public final String getRegion()
    {
        return getString(zziU("region"));
    }

    public final String getStreetAddress()
    {
        return getString(zziU("street_address"));
    }

    public final String getStreetName()
    {
        return getString(zziU("street_name"));
    }

    public final String getStreetNumber()
    {
        return getString(zziU("street_number"));
    }

    public int hashCode()
    {
        return zzb.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzb(this)).writeToParcel(parcel, i);
    }
}
