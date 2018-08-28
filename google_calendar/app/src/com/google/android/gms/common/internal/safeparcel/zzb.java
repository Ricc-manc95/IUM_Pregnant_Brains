// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public final class zzb
{

    public static String[] zzC(Parcel parcel, int i)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            String as[] = parcel.createStringArray();
            parcel.setDataPosition(i + j);
            return as;
        }
    }

    public static ArrayList zzD(Parcel parcel, int i)
    {
        int k;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        k = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        }
        ArrayList arraylist = new ArrayList();
        int l = parcel.readInt();
        for (int j = 0; j < l; j++)
        {
            arraylist.add(Integer.valueOf(parcel.readInt()));
        }

        parcel.setDataPosition(i + k);
        return arraylist;
    }

    public static ArrayList zzE(Parcel parcel, int i)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            ArrayList arraylist = parcel.createStringArrayList();
            parcel.setDataPosition(i + j);
            return arraylist;
        }
    }

    public static Parcelable zza(Parcel parcel, int i, android.os.Parcelable.Creator creator)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            creator = (Parcelable)creator.createFromParcel(parcel);
            parcel.setDataPosition(i + j);
            return creator;
        }
    }

    public static void zza(Parcel parcel, int i, int j)
    {
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        if (i != j)
        {
            String s = String.valueOf(Integer.toHexString(i));
            throw new zza((new StringBuilder(String.valueOf(s).length() + 46)).append("Expected size ").append(j).append(" got ").append(i).append(" (0x").append(s).append(")").toString(), parcel);
        } else
        {
            return;
        }
    }

    public static void zza(Parcel parcel, int i, List list, ClassLoader classloader)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return;
        } else
        {
            parcel.readList(list, classloader);
            parcel.setDataPosition(i + j);
            return;
        }
    }

    public static void zza$51662RJ4E9NMIP1FDTPIUK31E9HMAR1R954KIAAM0(Parcel parcel, int i, int j)
    {
        if (i != j)
        {
            String s = String.valueOf(Integer.toHexString(i));
            throw new zza((new StringBuilder(String.valueOf(s).length() + 46)).append("Expected size ").append(j).append(" got ").append(i).append(" (0x").append(s).append(")").toString(), parcel);
        } else
        {
            return;
        }
    }

    public static void zzb(Parcel parcel, int i)
    {
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        parcel.setDataPosition(i + parcel.dataPosition());
    }

    public static Object[] zzb(Parcel parcel, int i, android.os.Parcelable.Creator creator)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            creator = ((android.os.Parcelable.Creator) (parcel.createTypedArray(creator)));
            parcel.setDataPosition(i + j);
            return creator;
        }
    }

    public static ArrayList zzc(Parcel parcel, int i, android.os.Parcelable.Creator creator)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            creator = parcel.createTypedArrayList(creator);
            parcel.setDataPosition(i + j);
            return creator;
        }
    }

    public static Boolean zzd(Parcel parcel, int i)
    {
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        if (i == 0)
        {
            return null;
        }
        zza$51662RJ4E9NMIP1FDTPIUK31E9HMAR1R954KIAAM0(parcel, i, 4);
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return Boolean.valueOf(flag);
    }

    public static int zzdE(Parcel parcel)
    {
        int j = parcel.readInt();
        int i;
        int k;
        if ((j & 0xffff0000) != 0xffff0000)
        {
            i = j >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        k = parcel.dataPosition();
        if ((0xffff & j) != 20293)
        {
            String s = String.valueOf(Integer.toHexString(j));
            if (s.length() != 0)
            {
                s = "Expected object header. Got 0x".concat(s);
            } else
            {
                s = new String("Expected object header. Got 0x");
            }
            throw new zza(s, parcel);
        }
        i += k;
        if (i < k || i > parcel.dataSize())
        {
            throw new zza((new StringBuilder(54)).append("Size read is invalid start=").append(k).append(" end=").append(i).toString(), parcel);
        } else
        {
            return i;
        }
    }

    public static Integer zzh(Parcel parcel, int i)
    {
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        if (i == 0)
        {
            return null;
        } else
        {
            zza$51662RJ4E9NMIP1FDTPIUK31E9HMAR1R954KIAAM0(parcel, i, 4);
            return Integer.valueOf(parcel.readInt());
        }
    }

    public static Long zzj(Parcel parcel, int i)
    {
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        if (i == 0)
        {
            return null;
        } else
        {
            zza$51662RJ4E9NMIP1FDTPIUK31E9HMAR1R954KIAAM0(parcel, i, 8);
            return Long.valueOf(parcel.readLong());
        }
    }

    public static Double zzo(Parcel parcel, int i)
    {
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        if (i == 0)
        {
            return null;
        } else
        {
            zza$51662RJ4E9NMIP1FDTPIUK31E9HMAR1R954KIAAM0(parcel, i, 8);
            return Double.valueOf(parcel.readDouble());
        }
    }

    public static String zzq(Parcel parcel, int i)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            String s = parcel.readString();
            parcel.setDataPosition(i + j);
            return s;
        }
    }

    public static IBinder zzr(Parcel parcel, int i)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            IBinder ibinder = parcel.readStrongBinder();
            parcel.setDataPosition(i + j);
            return ibinder;
        }
    }

    public static Bundle zzs(Parcel parcel, int i)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            Bundle bundle = parcel.readBundle();
            parcel.setDataPosition(i + j);
            return bundle;
        }
    }

    public static byte[] zzt(Parcel parcel, int i)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            byte abyte0[] = parcel.createByteArray();
            parcel.setDataPosition(i + j);
            return abyte0;
        }
    }

    public static byte[][] zzu(Parcel parcel, int i)
    {
        int k;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        k = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        }
        int l = parcel.readInt();
        byte abyte0[][] = new byte[l][];
        for (int j = 0; j < l; j++)
        {
            abyte0[j] = parcel.createByteArray();
        }

        parcel.setDataPosition(i + k);
        return abyte0;
    }

    public static boolean[] zzv(Parcel parcel, int i)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            boolean aflag[] = parcel.createBooleanArray();
            parcel.setDataPosition(i + j);
            return aflag;
        }
    }

    public static int[] zzw(Parcel parcel, int i)
    {
        int j;
        if ((i & 0xffff0000) != 0xffff0000)
        {
            i = i >> 16 & 0xffff;
        } else
        {
            i = parcel.readInt();
        }
        j = parcel.dataPosition();
        if (i == 0)
        {
            return null;
        } else
        {
            int ai[] = parcel.createIntArray();
            parcel.setDataPosition(i + j);
            return ai;
        }
    }

    private class zza extends RuntimeException
    {

        public zza(String s, Parcel parcel)
        {
            int i = parcel.dataPosition();
            int j = parcel.dataSize();
            super((new StringBuilder(String.valueOf(s).length() + 41)).append(s).append(" Parcel: pos=").append(i).append(" size=").append(j).toString());
        }
    }

}
