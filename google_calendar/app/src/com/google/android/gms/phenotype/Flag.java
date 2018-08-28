// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

// Referenced classes of package com.google.android.gms.phenotype:
//            zze

public class Flag extends zza
    implements Comparable
{
    public static final class NonValueComparator
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (Flag)obj;
            obj1 = (Flag)obj1;
            if (((Flag) (obj)).flagStorageType == ((Flag) (obj1)).flagStorageType)
            {
                return ((Flag) (obj)).name.compareTo(((Flag) (obj1)).name);
            } else
            {
                return ((Flag) (obj)).flagStorageType - ((Flag) (obj1)).flagStorageType;
            }
        }

        public NonValueComparator()
        {
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new zze();
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public final boolean booleanValue;
    public final double doubleValue;
    public final int flagStorageType;
    public final int flagValueType;
    public final int mVersionCode;
    public final String name;
    public final String stringValue;
    public final long zzcaC;
    public final byte zzcaD[];

    Flag(int i, String s, long l, boolean flag, double d, 
            String s1, byte abyte0[], int j, int k)
    {
        mVersionCode = i;
        name = s;
        zzcaC = l;
        booleanValue = flag;
        doubleValue = d;
        stringValue = s1;
        zzcaD = abyte0;
        flagValueType = j;
        flagStorageType = k;
    }

    public int compareTo(Object obj)
    {
        Object obj1;
        int i;
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = false;
        obj1 = (Flag)obj;
        i = name.compareTo(((Flag) (obj1)).name);
        if (i == 0) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        i = flagValueType;
        int j = ((Flag) (obj1)).flagValueType;
        if (i < j)
        {
            i = -1;
        } else
        if (i == j)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        if (i != 0)
        {
            return i;
        }
        flagValueType;
        JVM INSTR tableswitch 1 5: default 108
    //                   1 142
    //                   2 183
    //                   3 213
    //                   4 225
    //                   5 267;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        i = flagValueType;
        throw new AssertionError((new StringBuilder(31)).append("Invalid enum value: ").append(i).toString());
_L4:
        long l1;
        long l2;
        l1 = zzcaC;
        l2 = ((Flag) (obj1)).zzcaC;
        if (l1 >= l2) goto _L10; else goto _L9
_L9:
        i = -1;
_L12:
        return i;
_L10:
        i = ((flag) ? 1 : 0);
        if (l1 != l2) goto _L1; else goto _L11
_L11:
        i = ((flag1) ? 1 : 0);
          goto _L12
_L5:
        boolean flag2 = booleanValue;
        i = ((flag1) ? 1 : 0);
        if (flag2 != ((Flag) (obj1)).booleanValue)
        {
            if (flag2)
            {
                i = 1;
            } else
            {
                return -1;
            }
        }
          goto _L12
_L6:
        return Double.compare(doubleValue, ((Flag) (obj1)).doubleValue);
_L7:
        obj = stringValue;
        obj1 = ((Flag) (obj1)).stringValue;
        i = ((flag1) ? 1 : 0);
        if (obj != obj1)
        {
            if (obj == null)
            {
                i = -1;
            } else
            if (obj1 == null)
            {
                i = 1;
            } else
            {
                return ((String) (obj)).compareTo(((String) (obj1)));
            }
        }
          goto _L12
_L8:
        if (zzcaD == ((Flag) (obj1)).zzcaD)
        {
            return 0;
        }
        if (zzcaD == null)
        {
            return -1;
        }
        i = ((flag) ? 1 : 0);
        if (((Flag) (obj1)).zzcaD == null) goto _L1; else goto _L13
_L13:
        int l;
        int i1;
label0:
        {
            for (i = 0; i < Math.min(zzcaD.length, ((Flag) (obj1)).zzcaD.length); i++)
            {
                int k = zzcaD[i] - ((Flag) (obj1)).zzcaD[i];
                if (k != 0)
                {
                    return k;
                }
            }

            l = zzcaD.length;
            i1 = ((Flag) (obj1)).zzcaD.length;
            if (l >= i1)
            {
                break label0;
            }
            i = -1;
        }
          goto _L12
        i = ((flag) ? 1 : 0);
        if (l != i1) goto _L1; else goto _L14
_L14:
        i = ((flag1) ? 1 : 0);
          goto _L12
    }

    public boolean equals(Object obj)
    {
        boolean flag2 = true;
        if (obj == null || !(obj instanceof Flag)) goto _L2; else goto _L1
_L1:
        obj = (Flag)obj;
        if (mVersionCode != ((Flag) (obj)).mVersionCode) goto _L4; else goto _L3
_L3:
        boolean flag1;
        String s = name;
        String s2 = ((Flag) (obj)).name;
        boolean flag;
        if (s == s2 || s != null && s.equals(s2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && flagValueType == ((Flag) (obj)).flagValueType && flagStorageType == ((Flag) (obj)).flagStorageType) goto _L5; else goto _L4
_L4:
        flag1 = false;
_L12:
        return flag1;
_L5:
        flagValueType;
        JVM INSTR tableswitch 1 5: default 136
    //                   1 172
    //                   2 190
    //                   3 207
    //                   4 225
    //                   5 262;
           goto _L6 _L7 _L8 _L9 _L10 _L11
_L6:
        int i = flagValueType;
        throw new AssertionError((new StringBuilder(31)).append("Invalid enum value: ").append(i).toString());
_L7:
        flag1 = flag2;
        if (zzcaC != ((Flag) (obj)).zzcaC)
        {
            return false;
        }
          goto _L12
_L8:
        flag1 = flag2;
        if (booleanValue != ((Flag) (obj)).booleanValue)
        {
            return false;
        }
          goto _L12
_L9:
        flag1 = flag2;
        if (doubleValue != ((Flag) (obj)).doubleValue)
        {
            return false;
        }
          goto _L12
_L10:
        String s1;
        s1 = stringValue;
        obj = ((Flag) (obj)).stringValue;
        flag1 = flag2;
        if (s1 == obj) goto _L12; else goto _L13
_L13:
        if (s1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = flag2;
        if (s1.equals(obj)) goto _L12; else goto _L14
_L14:
        return false;
_L11:
        return Arrays.equals(zzcaD, ((Flag) (obj)).zzcaD);
_L2:
        return false;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        toString(stringbuilder);
        return stringbuilder.toString();
    }

    public final String toString(StringBuilder stringbuilder)
    {
        stringbuilder.append("Flag(");
        stringbuilder.append(mVersionCode);
        stringbuilder.append(", ");
        stringbuilder.append(name);
        stringbuilder.append(", ");
        flagValueType;
        JVM INSTR tableswitch 1 5: default 76
    //                   1 132
    //                   2 185
    //                   3 197
    //                   4 209
    //                   5 235;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        stringbuilder = name;
        int i = flagValueType;
        throw new AssertionError((new StringBuilder(String.valueOf(stringbuilder).length() + 27)).append("Invalid type: ").append(stringbuilder).append(", ").append(i).toString());
_L2:
        stringbuilder.append(zzcaC);
_L8:
        stringbuilder.append(", ");
        stringbuilder.append(flagValueType);
        stringbuilder.append(", ");
        stringbuilder.append(flagStorageType);
        stringbuilder.append(")");
        return stringbuilder.toString();
_L3:
        stringbuilder.append(booleanValue);
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append(doubleValue);
        continue; /* Loop/switch isn't completed */
_L5:
        stringbuilder.append("'");
        stringbuilder.append(stringValue);
        stringbuilder.append("'");
        continue; /* Loop/switch isn't completed */
_L6:
        if (zzcaD == null)
        {
            stringbuilder.append("null");
        } else
        {
            stringbuilder.append("'");
            stringbuilder.append(new String(zzcaD, UTF_8));
            stringbuilder.append("'");
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, name, false);
        long l = zzcaC;
        zzc.zzb(parcel, 3, 8);
        parcel.writeLong(l);
        boolean flag = booleanValue;
        zzc.zzb(parcel, 4, 4);
        double d;
        if (!flag)
        {
            i = 0;
        }
        parcel.writeInt(i);
        d = doubleValue;
        zzc.zzb(parcel, 5, 8);
        parcel.writeDouble(d);
        zzc.zza(parcel, 6, stringValue, false);
        zzc.zza(parcel, 7, zzcaD, false);
        i = flagValueType;
        zzc.zzb(parcel, 8, 4);
        parcel.writeInt(i);
        i = flagStorageType;
        zzc.zzb(parcel, 9, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

    static 
    {
        new NonValueComparator();
    }
}
