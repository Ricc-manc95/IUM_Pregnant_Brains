// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            Location, zzq, zzp, zzb, 
//            FeatureIdProto, Address

public class zzr extends zza
    implements Location
{

    public static final android.os.Parcelable.Creator CREATOR = new zzq();
    public final String mName;
    public final int mVersionCode;
    public final Double zzcih;
    public final Double zzcii;
    public final Integer zzcij;
    public final Integer zzcik;
    public final String zzcim;
    public final String zzcio;
    public final zzp zzcip;
    public final zzb zzciq;

    zzr(int i, Double double1, Double double2, String s, Integer integer, Integer integer1, zzp zzp1, 
            String s1, zzb zzb1, String s2)
    {
        zzcih = double1;
        zzcii = double2;
        mName = s;
        zzcij = integer;
        zzcik = integer1;
        zzcip = zzp1;
        zzcim = s1;
        zzciq = zzb1;
        zzcio = s2;
        mVersionCode = i;
    }

    public zzr(Location location)
    {
        this(location.getLat(), location.getLng(), location.getName(), location.getRadiusMeters(), location.getLocationType(), location.getGeoFeatureId(), location.getDisplayAddress(), location.getAddress(), location.getLocationAliasId(), false);
    }

    private zzr(Double double1, Double double2, String s, Integer integer, Integer integer1, FeatureIdProto featureidproto, String s1, 
            Address address, String s2, boolean flag)
    {
        Object obj = null;
        super();
        mVersionCode = 2;
        zzcih = double1;
        zzcii = double2;
        mName = s;
        zzcij = integer;
        zzcik = integer1;
        zzcim = s1;
        zzcio = s2;
        if (featureidproto == null)
        {
            double1 = null;
        } else
        {
            double1 = new zzp(featureidproto);
        }
        zzcip = double1;
        if (address == null)
        {
            double1 = obj;
        } else
        {
            double1 = new zzb(address);
        }
        zzciq = double1;
    }

    public static boolean zza(Location location, Location location1)
    {
        Double double1 = location.getLat();
        Double double3 = location1.getLat();
        boolean flag;
        if (double1 == double3 || double1 != null && double1.equals(double3))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Double double2 = location.getLng();
            Double double4 = location1.getLng();
            if (double2 == double4 || double2 != null && double2.equals(double4))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                String s = location.getName();
                String s2 = location1.getName();
                if (s == s2 || s != null && s.equals(s2))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    Integer integer = location.getRadiusMeters();
                    Integer integer2 = location1.getRadiusMeters();
                    if (integer == integer2 || integer != null && integer.equals(integer2))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        Integer integer1 = location.getLocationType();
                        Integer integer3 = location1.getLocationType();
                        if (integer1 == integer3 || integer1 != null && integer1.equals(integer3))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            FeatureIdProto featureidproto = location.getGeoFeatureId();
                            FeatureIdProto featureidproto1 = location1.getGeoFeatureId();
                            if (featureidproto == featureidproto1 || featureidproto != null && featureidproto.equals(featureidproto1))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                String s1 = location.getDisplayAddress();
                                String s3 = location1.getDisplayAddress();
                                if (s1 == s3 || s1 != null && s1.equals(s3))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag)
                                {
                                    Address address = location.getAddress();
                                    Address address1 = location1.getAddress();
                                    if (address == address1 || address != null && address.equals(address1))
                                    {
                                        flag = true;
                                    } else
                                    {
                                        flag = false;
                                    }
                                    if (flag)
                                    {
                                        location = location.getLocationAliasId();
                                        location1 = location1.getLocationAliasId();
                                        if (location == location1 || location != null && location.equals(location1))
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
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int zzb(Location location)
    {
        return Arrays.hashCode(new Object[] {
            location.getLat(), location.getLng(), location.getName(), location.getRadiusMeters(), location.getLocationType(), location.getGeoFeatureId(), location.getDisplayAddress(), location.getAddress(), location.getLocationAliasId()
        });
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
            return zza(this, (Location)obj);
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

    public final Address getAddress()
    {
        return zzciq;
    }

    public final String getDisplayAddress()
    {
        return zzcim;
    }

    public final FeatureIdProto getGeoFeatureId()
    {
        return zzcip;
    }

    public final Double getLat()
    {
        return zzcih;
    }

    public final Double getLng()
    {
        return zzcii;
    }

    public final String getLocationAliasId()
    {
        return zzcio;
    }

    public final Integer getLocationType()
    {
        return zzcik;
    }

    public final String getName()
    {
        return mName;
    }

    public final Integer getRadiusMeters()
    {
        return zzcij;
    }

    public int hashCode()
    {
        return zzb(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        Object obj = zzcih;
        if (obj != null)
        {
            zzc.zzb(parcel, 2, 8);
            parcel.writeDouble(((Double) (obj)).doubleValue());
        }
        obj = zzcii;
        if (obj != null)
        {
            zzc.zzb(parcel, 3, 8);
            parcel.writeDouble(((Double) (obj)).doubleValue());
        }
        zzc.zza(parcel, 4, mName, false);
        obj = zzcij;
        if (obj != null)
        {
            zzc.zzb(parcel, 5, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        obj = zzcik;
        if (obj != null)
        {
            zzc.zzb(parcel, 6, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        zzc.zza(parcel, 7, zzcip, i, false);
        zzc.zza(parcel, 8, zzcim, false);
        zzc.zza(parcel, 9, zzcio, false);
        zzc.zza(parcel, 10, zzciq, i, false);
        zzc.zzJ(parcel, j);
    }

}
