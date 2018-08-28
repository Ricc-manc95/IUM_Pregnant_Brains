// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class zzc
{

    public static void zzJ(Parcel parcel, int i)
    {
        int j = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(j - i);
        parcel.setDataPosition(j);
    }

    public static void zza(Parcel parcel, int i, Bundle bundle, boolean flag)
    {
        if (bundle == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeBundle(bundle);
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }

    public static void zza(Parcel parcel, int i, IBinder ibinder, boolean flag)
    {
        if (ibinder == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeStrongBinder(ibinder);
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }

    public static void zza(Parcel parcel, int i, Parcelable parcelable, int j, boolean flag)
    {
        if (parcelable == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcelable.writeToParcel(parcel, j);
            j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }

    public static void zza(Parcel parcel, int i, String s, boolean flag)
    {
        if (s == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeString(s);
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }

    public static void zza(Parcel parcel, int i, List list, boolean flag)
    {
        if (list == null)
        {
            return;
        }
        parcel.writeInt(0xffff0000 | i);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = list.size();
        parcel.writeInt(k);
        for (i = 0; i < k; i++)
        {
            parcel.writeInt(((Integer)list.get(i)).intValue());
        }

        i = parcel.dataPosition();
        parcel.setDataPosition(j - 4);
        parcel.writeInt(i - j);
        parcel.setDataPosition(i);
    }

    public static void zza(Parcel parcel, int i, byte abyte0[], boolean flag)
    {
        if (abyte0 == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeByteArray(abyte0);
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }

    public static void zza(Parcel parcel, int i, int ai[], boolean flag)
    {
        if (ai == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeIntArray(ai);
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }

    public static void zza(Parcel parcel, int i, Parcelable aparcelable[], int j, boolean flag)
    {
        if (aparcelable == null)
        {
            return;
        }
        parcel.writeInt(0xffff0000 | i);
        parcel.writeInt(0);
        int k = parcel.dataPosition();
        int l = aparcelable.length;
        parcel.writeInt(l);
        i = 0;
        while (i < l) 
        {
            Parcelable parcelable = aparcelable[i];
            if (parcelable == null)
            {
                parcel.writeInt(0);
            } else
            {
                zza(parcel, parcelable, j);
            }
            i++;
        }
        i = parcel.dataPosition();
        parcel.setDataPosition(k - 4);
        parcel.writeInt(i - k);
        parcel.setDataPosition(i);
    }

    public static void zza(Parcel parcel, int i, String as[], boolean flag)
    {
        if (as == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeStringArray(as);
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }

    public static void zza(Parcel parcel, int i, boolean aflag[], boolean flag)
    {
        if (aflag == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeBooleanArray(aflag);
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }

    public static void zza(Parcel parcel, int i, byte abyte0[][], boolean flag)
    {
        boolean flag1 = false;
        if (abyte0 == null)
        {
            return;
        }
        parcel.writeInt(0xffff0000 | i);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = abyte0.length;
        parcel.writeInt(k);
        for (i = ((flag1) ? 1 : 0); i < k; i++)
        {
            parcel.writeByteArray(abyte0[i]);
        }

        i = parcel.dataPosition();
        parcel.setDataPosition(j - 4);
        parcel.writeInt(i - j);
        parcel.setDataPosition(i);
    }

    private static void zza(Parcel parcel, Parcelable parcelable, int i)
    {
        int j = parcel.dataPosition();
        parcel.writeInt(1);
        int k = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i);
        i = parcel.dataPosition();
        parcel.setDataPosition(j);
        parcel.writeInt(i - k);
        parcel.setDataPosition(i);
    }

    public static void zzb(Parcel parcel, int i, int j)
    {
        if (j >= 65535)
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(j);
            return;
        } else
        {
            parcel.writeInt(j << 16 | i);
            return;
        }
    }

    public static void zzb(Parcel parcel, int i, List list, boolean flag)
    {
        if (list == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeStringList(list);
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }

    public static void zzc(Parcel parcel, int i, List list, boolean flag)
    {
        if (list == null)
        {
            return;
        }
        parcel.writeInt(0xffff0000 | i);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = list.size();
        parcel.writeInt(k);
        i = 0;
        while (i < k) 
        {
            Parcelable parcelable = (Parcelable)list.get(i);
            if (parcelable == null)
            {
                parcel.writeInt(0);
            } else
            {
                zza(parcel, parcelable, 0);
            }
            i++;
        }
        i = parcel.dataPosition();
        parcel.setDataPosition(j - 4);
        parcel.writeInt(i - j);
        parcel.setDataPosition(i);
    }

    public static void zze(Parcel parcel, int i, List list, boolean flag)
    {
        if (list == null)
        {
            return;
        } else
        {
            parcel.writeInt(0xffff0000 | i);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeList(list);
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
            return;
        }
    }
}
