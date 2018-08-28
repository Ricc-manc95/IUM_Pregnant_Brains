// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzi, DataType, Application, Device

public class DataSource extends zza
{
    public static final class Builder
    {

        public String name;
        public int type;
        public Application zzbhO;
        public String zzbhP;
        public DataType zzbhk;

        public final DataSource build()
        {
            boolean flag1 = true;
            boolean flag;
            if (zzbhk != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Must set data type"));
            }
            if (type >= 0)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Must set data source type"));
            } else
            {
                return new DataSource(this);
            }
        }

        public Builder()
        {
            type = -1;
            zzbhP = "";
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new zzi();
    private static final int zzbhM[] = new int[0];
    public final String name;
    public final int type;
    public final int versionCode;
    public final Device zzbhN;
    public final Application zzbhO;
    public final String zzbhP;
    public final int zzbhQ[];
    private final String zzbhR;
    public final DataType zzbhk;

    DataSource(int i, DataType datatype, String s, int j, Device device, Application application, String s1, 
            int ai[])
    {
        versionCode = i;
        zzbhk = datatype;
        type = j;
        name = s;
        zzbhN = device;
        zzbhO = application;
        zzbhP = s1;
        zzbhR = zzEz();
        if (ai == null)
        {
            ai = zzbhM;
        }
        zzbhQ = ai;
    }

    DataSource(Builder builder)
    {
        versionCode = 3;
        zzbhk = builder.zzbhk;
        type = builder.type;
        name = builder.name;
        zzbhN = null;
        zzbhO = builder.zzbhO;
        zzbhP = builder.zzbhP;
        zzbhR = zzEz();
        zzbhQ = null;
    }

    private final String getTypeString()
    {
        switch (type)
        {
        default:
            return "derived";

        case 0: // '\0'
            return "raw";

        case 1: // '\001'
            return "derived";

        case 2: // '\002'
            return "cleaned";

        case 3: // '\003'
            return "converted";
        }
    }

    private final String zzEz()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getTypeString());
        stringbuilder.append(":").append(zzbhk.name);
        if (zzbhO != null)
        {
            stringbuilder.append(":").append(zzbhO.packageName);
        }
        if (zzbhN != null)
        {
            StringBuilder stringbuilder1 = stringbuilder.append(":");
            Device device = zzbhN;
            stringbuilder1.append(String.format("%s:%s:%s", new Object[] {
                device.manufacturer, device.model, device.zzbia
            }));
        }
        if (zzbhP != null)
        {
            stringbuilder.append(":").append(zzbhP);
        }
        return stringbuilder.toString();
    }

    public static String zzjU(int i)
    {
        switch (i)
        {
        default:
            return "unknown";

        case 1: // '\001'
            return "blood_pressure_esh2002";

        case 2: // '\002'
            return "blood_pressure_esh2010";

        case 3: // '\003'
            return "blood_pressure_aami";

        case 4: // '\004'
            return "blood_pressure_bhs_a_a";

        case 5: // '\005'
            return "blood_pressure_bhs_a_b";

        case 6: // '\006'
            return "blood_pressure_bhs_b_a";

        case 7: // '\007'
            return "blood_pressure_bhs_b_b";

        case 8: // '\b'
            return "blood_glucose_iso151972003";

        case 9: // '\t'
            return "blood_glucose_iso151972013";
        }
    }

    public boolean equals(Object obj)
    {
label0:
        {
            if (this != obj)
            {
                if (!(obj instanceof DataSource))
                {
                    break label0;
                }
                obj = (DataSource)obj;
                if (!zzbhR.equals(((DataSource) (obj)).zzbhR))
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode()
    {
        return zzbhR.hashCode();
    }

    public final String toDebugString()
    {
        type;
        JVM INSTR tableswitch 0 3: default 36
    //                   0 273
    //                   1 279
    //                   2 285
    //                   3 291;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_291;
_L1:
        Object obj = "?";
_L6:
        String s3 = String.valueOf(obj);
        obj = zzbhk;
        String s1;
        String s2;
        String s4;
        if (((DataType) (obj)).name.startsWith("com.google."))
        {
            obj = ((DataType) (obj)).name.substring(11);
        } else
        {
            obj = ((DataType) (obj)).name;
        }
        s4 = String.valueOf(obj);
        if (zzbhO == null)
        {
            obj = "";
        } else
        if (zzbhO.equals(Application.GOOGLE_PLAY_SERVICES))
        {
            obj = ":gms";
        } else
        {
            obj = String.valueOf(zzbhO.packageName);
            if (((String) (obj)).length() != 0)
            {
                obj = ":".concat(((String) (obj)));
            } else
            {
                obj = new String(":");
            }
        }
        if (zzbhN != null)
        {
            String s = String.valueOf(zzbhN.model);
            s2 = String.valueOf(zzbhN.zzbia);
            s2 = (new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(s2).length())).append(":").append(s).append(":").append(s2).toString();
        } else
        {
            s2 = "";
        }
        if (zzbhP != null)
        {
            s1 = String.valueOf(zzbhP);
            if (s1.length() != 0)
            {
                s1 = ":".concat(s1);
            } else
            {
                s1 = new String(":");
            }
        } else
        {
            s1 = "";
        }
        return (new StringBuilder(String.valueOf(s3).length() + 1 + String.valueOf(s4).length() + String.valueOf(obj).length() + String.valueOf(s2).length() + String.valueOf(s1).length())).append(s3).append(":").append(s4).append(((String) (obj))).append(s2).append(s1).toString();
_L2:
        obj = "r";
          goto _L6
_L3:
        obj = "d";
          goto _L6
_L4:
        obj = "c";
          goto _L6
        obj = "v";
          goto _L6
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("DataSource{");
        stringbuilder.append(getTypeString());
        if (name != null)
        {
            stringbuilder.append(":").append(name);
        }
        if (zzbhO != null)
        {
            stringbuilder.append(":").append(zzbhO);
        }
        if (zzbhN != null)
        {
            stringbuilder.append(":").append(zzbhN);
        }
        if (zzbhP != null)
        {
            stringbuilder.append(":").append(zzbhP);
        }
        stringbuilder.append(":").append(zzbhk);
        return stringbuilder.append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbhk, i, false);
        zzc.zza(parcel, 2, name, false);
        int k = type;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 4, zzbhN, i, false);
        zzc.zza(parcel, 5, zzbhO, i, false);
        zzc.zza(parcel, 6, zzbhP, false);
        i = versionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zza(parcel, 8, zzbhQ, false);
        zzc.zzJ(parcel, j);
    }

}
