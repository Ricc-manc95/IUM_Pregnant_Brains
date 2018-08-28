// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.Address;
import com.google.android.gms.reminders.model.FeatureIdProto;
import com.google.android.gms.reminders.model.Location;
import com.google.android.gms.reminders.model.zzr;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zza, zzg

public class zzi extends zzo
    implements Location
{

    private boolean zzcgR;
    private zzg zzcgS;
    private boolean zzcgT;
    private zza zzcgU;

    public zzi(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzcgR = false;
        zzcgT = false;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Location))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzr.zza(this, (Location)obj);
        }
    }

    public final Object freeze()
    {
        return new zzr(this);
    }

    public final Address getAddress()
    {
        if (!zzcgT)
        {
            zzcgT = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("address_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zza.zza(dataholder, i, k, s))
            {
                zzcgU = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("address_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzcgU = new zza(dataholder1, j, s1);
            }
        }
        return zzcgU;
    }

    public final String getDisplayAddress()
    {
        return getString(zziU("display_address"));
    }

    public final FeatureIdProto getGeoFeatureId()
    {
        if (!zzcgR)
        {
            zzcgR = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("location_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zzg.zza(dataholder, i, k, s))
            {
                zzcgS = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("location_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzcgS = new zzg(dataholder1, j, s1);
            }
        }
        return zzcgS;
    }

    public final Double getLat()
    {
        String s = zziU("lat");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Double.valueOf(getDouble(s));
        }
    }

    public final Double getLng()
    {
        String s = zziU("lng");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Double.valueOf(getDouble(s));
        }
    }

    public final String getLocationAliasId()
    {
        return getString(zziU("location_alias_id"));
    }

    public final Integer getLocationType()
    {
        String s = zziU("location_type");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final String getName()
    {
        return getString(zziU("name"));
    }

    public final Integer getRadiusMeters()
    {
        String s = zziU("radius_meters");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public int hashCode()
    {
        return zzr.zzb(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzr(this)).writeToParcel(parcel, i);
    }
}
