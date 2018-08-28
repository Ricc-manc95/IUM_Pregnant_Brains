// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzagw;
import com.google.android.gms.internal.zzakq;
import com.google.android.gms.internal.zzcdl;
import com.google.android.gms.internal.zzcdm;

// Referenced classes of package com.google.android.gms.drive:
//            zze

public class DriveId extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zze();
    public final int mVersionCode;
    public final long zzaWC;
    private volatile String zzaWE;
    public final String zzaWQ;
    public final long zzaWR;
    public final int zzaWS;
    private volatile String zzaWT;

    DriveId(int i, String s, long l, long l1, int j)
    {
label0:
        {
            boolean flag = false;
            super();
            zzaWE = null;
            zzaWT = null;
            mVersionCode = i;
            zzaWQ = s;
            if (!"".equals(s))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                throw new IllegalArgumentException();
            }
            if (s == null)
            {
                i = ((flag) ? 1 : 0);
                if (l == -1L)
                {
                    break label0;
                }
            }
            i = 1;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            zzaWR = l;
            zzaWC = l1;
            zzaWS = j;
            return;
        }
    }

    public static DriveId decodeFromString(String s)
    {
        boolean flag = s.startsWith("DriveId:");
        String s1 = String.valueOf(s);
        if (s1.length() != 0)
        {
            s1 = "Invalid DriveId: ".concat(s1);
        } else
        {
            s1 = new String("Invalid DriveId: ");
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf(s1));
        } else
        {
            return zzv(Base64.decode(s.substring(8), 10));
        }
    }

    private static DriveId zzv(byte abyte0[])
    {
        zzakq zzakq1;
        try
        {
            zzakq1 = (zzakq)zzcdm.zzb(new zzakq(), abyte0, 0, abyte0.length);
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new IllegalArgumentException();
        }
        if ("".equals(zzakq1.zzbby))
        {
            abyte0 = null;
        } else
        {
            abyte0 = zzakq1.zzbby;
        }
        return new DriveId(zzakq1.versionCode, abyte0, zzakq1.zzbbz, zzakq1.zzbbw, zzakq1.zzbbA);
    }

    public final String encodeToString()
    {
        if (zzaWE == null)
        {
            Object obj1 = new zzakq();
            obj1.versionCode = mVersionCode;
            Object obj;
            int i;
            if (zzaWQ == null)
            {
                obj = "";
            } else
            {
                obj = zzaWQ;
            }
            obj1.zzbby = ((String) (obj));
            obj1.zzbbz = zzaWR;
            obj1.zzbbw = zzaWC;
            obj1.zzbbA = zzaWS;
            i = ((zzcdm) (obj1)).computeSerializedSize();
            obj1.AL = i;
            obj = new byte[i];
            zzcdm.zza(((zzcdm) (obj1)), ((byte []) (obj)), 0, obj.length);
            obj1 = Base64.encodeToString(((byte []) (obj)), 10);
            obj = String.valueOf("DriveId:");
            obj1 = String.valueOf(obj1);
            if (((String) (obj1)).length() != 0)
            {
                obj = ((String) (obj)).concat(((String) (obj1)));
            } else
            {
                obj = new String(((String) (obj)));
            }
            zzaWE = ((String) (obj));
        }
        return zzaWE;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if (obj instanceof DriveId) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        obj = (DriveId)obj;
        if (((DriveId) (obj)).zzaWC != zzaWC)
        {
            return false;
        }
        if (((DriveId) (obj)).zzaWR == -1L && zzaWR == -1L)
        {
            return ((DriveId) (obj)).zzaWQ.equals(zzaWQ);
        }
        if (zzaWQ != null && ((DriveId) (obj)).zzaWQ != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (((DriveId) (obj)).zzaWR != zzaWR)
        {
            return false;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (((DriveId) (obj)).zzaWR != zzaWR)
        {
            break; /* Loop/switch isn't completed */
        }
        if (((DriveId) (obj)).zzaWQ.equals(zzaWQ))
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = "Unexpected unequal resourceId for same DriveId object.";
        zzq zzq1 = zzagw.zzbad;
        if (Log.isLoggable(zzq1.zzaQE, 5))
        {
            if (zzq1.zzaQF != null)
            {
                obj = zzq1.zzaQF.concat("Unexpected unequal resourceId for same DriveId object.");
            }
            Log.w("DriveId", ((String) (obj)));
        }
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L5
_L5:
        return false;
    }

    public int hashCode()
    {
        if (zzaWR == -1L)
        {
            return zzaWQ.hashCode();
        }
        String s = String.valueOf(String.valueOf(zzaWC));
        String s1 = String.valueOf(String.valueOf(zzaWR));
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        return s.hashCode();
    }

    public String toString()
    {
        return encodeToString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzaWQ, false);
        long l = zzaWR;
        zzc.zzb(parcel, 3, 8);
        parcel.writeLong(l);
        l = zzaWC;
        zzc.zzb(parcel, 4, 8);
        parcel.writeLong(l);
        j = zzaWS;
        zzc.zzb(parcel, 5, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

}
