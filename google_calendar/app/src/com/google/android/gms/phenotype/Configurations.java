// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

// Referenced classes of package com.google.android.gms.phenotype:
//            zzb, Configuration

public class Configurations extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzb();
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Map configurationMap = new TreeMap();
    public final Configuration configurations[];
    public final byte experimentToken[];
    public final boolean isDelta;
    public final int mVersionCode;
    public final String serverToken;
    public final String snapshotToken;

    Configurations(int i, String s, String s1, Configuration aconfiguration[], boolean flag, byte abyte0[])
    {
        mVersionCode = i;
        snapshotToken = s;
        serverToken = s1;
        configurations = aconfiguration;
        isDelta = flag;
        experimentToken = abyte0;
        int j = aconfiguration.length;
        for (i = 0; i < j; i++)
        {
            s = aconfiguration[i];
            configurationMap.put(Integer.valueOf(((Configuration) (s)).flagType), s);
        }

    }

    public boolean equals(Object obj)
    {
        if (obj != null && (obj instanceof Configurations))
        {
            obj = (Configurations)obj;
            if (mVersionCode == ((Configurations) (obj)).mVersionCode)
            {
                String s = snapshotToken;
                String s2 = ((Configurations) (obj)).snapshotToken;
                boolean flag;
                if (s == s2 || s != null && s.equals(s2))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    String s1 = serverToken;
                    String s3 = ((Configurations) (obj)).serverToken;
                    if (s1 == s3 || s1 != null && s1.equals(s3))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        Map map = configurationMap;
                        Map map1 = ((Configurations) (obj)).configurationMap;
                        if (map == map1 || map != null && map.equals(map1))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag && isDelta == ((Configurations) (obj)).isDelta && Arrays.equals(experimentToken, ((Configurations) (obj)).experimentToken))
                        {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            snapshotToken, serverToken, configurationMap, Boolean.valueOf(isDelta), experimentToken
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("Configurations(");
        stringbuilder.append(mVersionCode);
        stringbuilder.append(", ");
        stringbuilder.append('\'');
        stringbuilder.append(snapshotToken);
        stringbuilder.append('\'');
        stringbuilder.append(", ");
        stringbuilder.append('\'');
        stringbuilder.append(serverToken);
        stringbuilder.append('\'');
        stringbuilder.append(", ");
        stringbuilder.append('(');
        for (Iterator iterator = configurationMap.values().iterator(); iterator.hasNext(); stringbuilder.append(", "))
        {
            stringbuilder.append((Configuration)iterator.next());
        }

        stringbuilder.append(')');
        stringbuilder.append(", ");
        stringbuilder.append(isDelta);
        stringbuilder.append(", ");
        String s;
        if (experimentToken == null)
        {
            s = "null";
        } else
        {
            s = new String(experimentToken, UTF_8);
        }
        stringbuilder.append(s);
        stringbuilder.append(')');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, snapshotToken, false);
        zzc.zza(parcel, 3, serverToken, false);
        zzc.zza(parcel, 4, configurations, i, false);
        boolean flag1 = isDelta;
        zzc.zzb(parcel, 5, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zza(parcel, 6, experimentToken, false);
        zzc.zzJ(parcel, j);
    }

}
