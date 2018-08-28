// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.util.zzm;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzab, MapValue

public final class Value extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzab();
    public final int format;
    public String stringValue;
    public float value;
    public final int versionCode;
    public boolean zzbiH;
    public Map zzbiI;
    public int zzbiJ[];
    public float zzbiK[];
    public byte zzbiL[];

    Value(int i, int j, boolean flag, float f, String s, Bundle bundle, int ai[], 
            float af[], byte abyte0[])
    {
        versionCode = i;
        format = j;
        zzbiH = flag;
        value = f;
        stringValue = s;
        if (bundle == null)
        {
            s = null;
        } else
        {
            bundle.setClassLoader(com/google/android/gms/fitness/data/MapValue.getClassLoader());
            s = new ArrayMap(bundle.size());
            Iterator iterator = bundle.keySet().iterator();
            while (iterator.hasNext()) 
            {
                String s1 = (String)iterator.next();
                s.put(s1, (MapValue)bundle.getParcelable(s1));
            }
        }
        zzbiI = s;
        zzbiJ = ai;
        zzbiK = af;
        zzbiL = abyte0;
    }

    private final int asInt()
    {
        boolean flag = true;
        if (format != 1)
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Value is not in int format"));
        } else
        {
            return Float.floatToRawIntBits(value);
        }
    }

    public final boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof Value)) goto _L4; else goto _L3
_L3:
        obj = (Value)obj;
        if (format != ((Value) (obj)).format || zzbiH != ((Value) (obj)).zzbiH) goto _L6; else goto _L5
_L5:
        format;
        JVM INSTR tableswitch 1 7: default 92
    //                   1 120
    //                   2 141
    //                   3 163
    //                   4 200
    //                   5 237
    //                   6 252
    //                   7 267;
           goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L7:
        boolean flag;
        if (value == ((Value) (obj)).value)
        {
            flag = true;
        } else
        {
            flag = false;
        }
_L16:
        flag1 = flag2;
        if (!flag) goto _L4; else goto _L2
_L2:
        flag1 = true;
_L4:
        return flag1;
_L8:
        if (asInt() == ((Value) (obj)).asInt())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L9:
        if (value == ((Value) (obj)).value)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L10:
        String s = stringValue;
        obj = ((Value) (obj)).stringValue;
        if (s == obj || s != null && s.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L11:
        Map map = zzbiI;
        obj = ((Value) (obj)).zzbiI;
        if (map == obj || map != null && map.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L12:
        flag = Arrays.equals(zzbiJ, ((Value) (obj)).zzbiJ);
        continue; /* Loop/switch isn't completed */
_L13:
        flag = Arrays.equals(zzbiK, ((Value) (obj)).zzbiK);
        continue; /* Loop/switch isn't completed */
_L14:
        flag = Arrays.equals(zzbiL, ((Value) (obj)).zzbiL);
        continue; /* Loop/switch isn't completed */
_L6:
        flag = false;
        if (true) goto _L16; else goto _L15
_L15:
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Float.valueOf(value), stringValue, zzbiI, zzbiJ, zzbiK, zzbiL
        });
    }

    public final String toString()
    {
        if (!zzbiH)
        {
            return "unset";
        }
        switch (format)
        {
        default:
            return "unknown";

        case 1: // '\001'
            return Integer.toString(asInt());

        case 2: // '\002'
            return Float.toString(value);

        case 3: // '\003'
            return stringValue;

        case 4: // '\004'
            return (new TreeMap(zzbiI)).toString();

        case 5: // '\005'
            return Arrays.toString(zzbiJ);

        case 6: // '\006'
            return Arrays.toString(zzbiK);

        case 7: // '\007'
            return zzm.zza(zzbiL, 0, zzbiL.length, false);
        }
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = format;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        boolean flag = zzbiH;
        zzc.zzb(parcel, 2, 4);
        float f;
        Object obj;
        if (!flag)
        {
            i = 0;
        }
        parcel.writeInt(i);
        f = value;
        zzc.zzb(parcel, 3, 4);
        parcel.writeFloat(f);
        zzc.zza(parcel, 4, stringValue, false);
        if (zzbiI == null)
        {
            obj = null;
        } else
        {
            obj = new Bundle(zzbiI.size());
            Iterator iterator = zzbiI.entrySet().iterator();
            while (iterator.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                ((Bundle) (obj)).putParcelable((String)entry.getKey(), (Parcelable)entry.getValue());
            }
        }
        zzc.zza(parcel, 5, ((Bundle) (obj)), false);
        zzc.zza(parcel, 6, zzbiJ, false);
        obj = zzbiK;
        if (obj != null)
        {
            parcel.writeInt(-65529);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeFloatArray(((float []) (obj)));
            int l = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(l - i);
            parcel.setDataPosition(l);
        }
        i = versionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zza(parcel, 8, zzbiL, false);
        zzc.zzJ(parcel, j);
    }

}
