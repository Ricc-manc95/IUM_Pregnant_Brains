// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            Address, zza

public class zzb extends zza
    implements Address
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.reminders.model.zza();
    public final String mName;
    public final int mVersionCode;
    public final String zzbCG;
    public final String zzbWl;
    public final String zzbWm;
    public final String zzbWn;
    public final String zzchF;
    public final String zzchG;
    public final String zzchH;

    zzb(int i, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7)
    {
        zzbCG = s;
        zzchF = s1;
        zzbWm = s2;
        zzbWn = s3;
        zzchG = s4;
        zzchH = s5;
        zzbWl = s6;
        mName = s7;
        mVersionCode = i;
    }

    public zzb(Address address)
    {
        this(address.getCountry(), address.getLocality(), address.getRegion(), address.getStreetAddress(), address.getStreetNumber(), address.getStreetName(), address.getPostalCode(), address.getName());
    }

    private zzb(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7)
    {
        this(1, s, s1, s2, s3, s4, s5, s6, s7);
    }

    public static int zza(Address address)
    {
        return Arrays.hashCode(new Object[] {
            address.getCountry(), address.getLocality(), address.getRegion(), address.getStreetAddress(), address.getStreetNumber(), address.getStreetName(), address.getPostalCode(), address.getName()
        });
    }

    public static boolean zza(Address address, Address address1)
    {
        String s = address.getCountry();
        String s7 = address1.getCountry();
        boolean flag;
        if (s == s7 || s != null && s.equals(s7))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            String s1 = address.getLocality();
            String s8 = address1.getLocality();
            if (s1 == s8 || s1 != null && s1.equals(s8))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                String s2 = address.getRegion();
                String s9 = address1.getRegion();
                if (s2 == s9 || s2 != null && s2.equals(s9))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    String s3 = address.getStreetAddress();
                    String s10 = address1.getStreetAddress();
                    if (s3 == s10 || s3 != null && s3.equals(s10))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        String s4 = address.getStreetNumber();
                        String s11 = address1.getStreetNumber();
                        if (s4 == s11 || s4 != null && s4.equals(s11))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            String s5 = address.getStreetName();
                            String s12 = address1.getStreetName();
                            if (s5 == s12 || s5 != null && s5.equals(s12))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                String s6 = address.getPostalCode();
                                String s13 = address1.getPostalCode();
                                if (s6 == s13 || s6 != null && s6.equals(s13))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag)
                                {
                                    address = address.getName();
                                    address1 = address1.getName();
                                    if (address == address1 || address != null && address.equals(address1))
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
        return false;
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
            return zza(this, (Address)obj);
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

    public final String getCountry()
    {
        return zzbCG;
    }

    public final String getLocality()
    {
        return zzchF;
    }

    public final String getName()
    {
        return mName;
    }

    public final String getPostalCode()
    {
        return zzbWl;
    }

    public final String getRegion()
    {
        return zzbWm;
    }

    public final String getStreetAddress()
    {
        return zzbWn;
    }

    public final String getStreetName()
    {
        return zzchH;
    }

    public final String getStreetNumber()
    {
        return zzchG;
    }

    public int hashCode()
    {
        return zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzbCG, false);
        zzc.zza(parcel, 3, zzchF, false);
        zzc.zza(parcel, 4, zzbWm, false);
        zzc.zza(parcel, 5, zzbWn, false);
        zzc.zza(parcel, 6, zzbWl, false);
        zzc.zza(parcel, 7, zzchG, false);
        zzc.zza(parcel, 8, zzchH, false);
        zzc.zza(parcel, 9, mName, false);
        zzc.zzJ(parcel, i);
    }

}
