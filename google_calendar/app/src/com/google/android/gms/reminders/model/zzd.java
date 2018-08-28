// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders.model:
//            CategoryInfo, zzc

public class zzd extends zza
    implements CategoryInfo
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.reminders.model.zzc();
    public final int mVersionCode;
    public final String zzahI;
    public final List zzbCC;
    public final String zzchI;

    zzd(int i, String s, List list, String s1)
    {
        zzbCC = new ArrayList();
        zzchI = s;
        zzahI = s1;
        mVersionCode = i;
        if (list != null)
        {
            zzbCC.addAll(list);
        }
    }

    public zzd(CategoryInfo categoryinfo)
    {
        this(categoryinfo.getCategoryId(), categoryinfo.getPlaceTypes(), categoryinfo.getDisplayName());
    }

    private zzd(String s, List list, String s1)
    {
        this(2, s, list, s1);
    }

    public static int zza(CategoryInfo categoryinfo)
    {
        return Arrays.hashCode(new Object[] {
            categoryinfo.getCategoryId(), categoryinfo.getPlaceTypes(), categoryinfo.getDisplayName()
        });
    }

    public static boolean zza(CategoryInfo categoryinfo, CategoryInfo categoryinfo1)
    {
        String s = categoryinfo.getCategoryId();
        String s1 = categoryinfo1.getCategoryId();
        boolean flag;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            List list = categoryinfo.getPlaceTypes();
            List list1 = categoryinfo1.getPlaceTypes();
            if (list == list1 || list != null && list.equals(list1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                categoryinfo = categoryinfo.getDisplayName();
                categoryinfo1 = categoryinfo1.getDisplayName();
                if (categoryinfo == categoryinfo1 || categoryinfo != null && categoryinfo.equals(categoryinfo1))
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
        return false;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof CategoryInfo))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (CategoryInfo)obj);
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

    public final String getCategoryId()
    {
        return zzchI;
    }

    public final String getDisplayName()
    {
        return zzahI;
    }

    public final List getPlaceTypes()
    {
        return zzbCC;
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
        zzc.zza(parcel, 2, zzchI, false);
        zzc.zza(parcel, 3, zzahI, false);
        zzc.zzb(parcel, 4, zzbCC, false);
        zzc.zzJ(parcel, i);
    }

}
