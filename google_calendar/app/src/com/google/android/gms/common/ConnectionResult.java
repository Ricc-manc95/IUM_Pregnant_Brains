// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.common:
//            zzb

public final class ConnectionResult extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzb();
    public static final ConnectionResult zzaIj = new ConnectionResult(0);
    public final PendingIntent mPendingIntent;
    public final int mVersionCode;
    public final int zzaEP;
    public final String zzaIk;

    public ConnectionResult(int i)
    {
        this(i, null, null);
    }

    ConnectionResult(int i, int j, PendingIntent pendingintent, String s)
    {
        mVersionCode = i;
        zzaEP = j;
        mPendingIntent = pendingintent;
        zzaIk = s;
    }

    public ConnectionResult(int i, PendingIntent pendingintent)
    {
        this(i, pendingintent, null);
    }

    public ConnectionResult(int i, PendingIntent pendingintent, String s)
    {
        this(1, i, pendingintent, s);
    }

    static String getStatusString(int i)
    {
        switch (i)
        {
        default:
            return (new StringBuilder(31)).append("UNKNOWN_ERROR_CODE(").append(i).append(")").toString();

        case 0: // '\0'
            return "SUCCESS";

        case 1: // '\001'
            return "SERVICE_MISSING";

        case 2: // '\002'
            return "SERVICE_VERSION_UPDATE_REQUIRED";

        case 3: // '\003'
            return "SERVICE_DISABLED";

        case 4: // '\004'
            return "SIGN_IN_REQUIRED";

        case 5: // '\005'
            return "INVALID_ACCOUNT";

        case 6: // '\006'
            return "RESOLUTION_REQUIRED";

        case 7: // '\007'
            return "NETWORK_ERROR";

        case 8: // '\b'
            return "INTERNAL_ERROR";

        case 9: // '\t'
            return "SERVICE_INVALID";

        case 10: // '\n'
            return "DEVELOPER_ERROR";

        case 11: // '\013'
            return "LICENSE_CHECK_FAILED";

        case 13: // '\r'
            return "CANCELED";

        case 14: // '\016'
            return "TIMEOUT";

        case 15: // '\017'
            return "INTERRUPTED";

        case 16: // '\020'
            return "API_UNAVAILABLE";

        case 17: // '\021'
            return "SIGN_IN_FAILED";

        case 18: // '\022'
            return "SERVICE_UPDATING";

        case 19: // '\023'
            return "SERVICE_MISSING_PERMISSION";

        case 20: // '\024'
            return "RESTRICTED_PROFILE";

        case 21: // '\025'
            return "API_VERSION_UPDATE_REQUIRED";

        case 1500: 
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";

        case 99: // 'c'
            return "UNFINISHED";

        case -1: 
            return "UNKNOWN";
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof ConnectionResult))
        {
            return false;
        }
        obj = (ConnectionResult)obj;
        if (zzaEP != ((ConnectionResult) (obj)).zzaEP)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = mPendingIntent;
        PendingIntent pendingintent = ((ConnectionResult) (obj)).mPendingIntent;
        boolean flag;
        if (obj1 == pendingintent || obj1 != null && obj1.equals(pendingintent))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = zzaIk;
        obj = ((ConnectionResult) (obj)).zzaIk;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(zzaEP), mPendingIntent, zzaIk
        });
    }

    public final String toString()
    {
        return (new com.google.android.gms.common.internal.zzaa.zza(this)).zzh("statusCode", getStatusString(zzaEP)).zzh("resolution", mPendingIntent).zzh("message", zzaIk).toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        k = zzaEP;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        Object obj = mPendingIntent;
        if (obj != null)
        {
            parcel.writeInt(-65533);
            parcel.writeInt(0);
            int l = parcel.dataPosition();
            ((Parcelable) (obj)).writeToParcel(parcel, i);
            i = parcel.dataPosition();
            parcel.setDataPosition(l - 4);
            parcel.writeInt(i - l);
            parcel.setDataPosition(i);
        }
        obj = zzaIk;
        if (obj != null)
        {
            parcel.writeInt(-65532);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int i1 = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(i1 - i);
            parcel.setDataPosition(i1);
        }
        i = parcel.dataPosition();
        parcel.setDataPosition(j - 4);
        parcel.writeInt(i - j);
        parcel.setDataPosition(i);
    }

}
