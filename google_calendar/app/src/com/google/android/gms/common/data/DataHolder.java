// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.google.android.gms.common.data:
//            zze

public final class DataHolder extends zza
    implements Closeable
{
    public static class Builder
    {

        private Builder(String as[], String s)
        {
            if (as == null)
            {
                throw new NullPointerException("null reference");
            } else
            {
                new ArrayList();
                new HashMap();
                return;
            }
        }

        Builder(String as[], String s, byte byte0)
        {
            this(as, s);
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new zze();
    private boolean mClosed;
    public final int mVersionCode;
    public final int zzaEP;
    public final String zzaNU[];
    public Bundle zzaNV;
    public final CursorWindow zzaNW[];
    public final Bundle zzaNX;
    public int zzaNY[];
    public int zzaNZ;
    private boolean zzaOa;

    DataHolder(int i, String as[], CursorWindow acursorwindow[], int j, Bundle bundle)
    {
        mClosed = false;
        zzaOa = true;
        mVersionCode = i;
        zzaNU = as;
        zzaNW = acursorwindow;
        zzaEP = j;
        zzaNX = bundle;
    }

    public final void close()
    {
        this;
        JVM INSTR monitorenter ;
        if (mClosed) goto _L2; else goto _L1
_L1:
        mClosed = true;
        int i = 0;
_L3:
        if (i >= zzaNW.length)
        {
            break; /* Loop/switch isn't completed */
        }
        zzaNW[i].close();
        i++;
        if (true) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected final void finalize()
        throws Throwable
    {
        if (zzaOa && zzaNW.length > 0 && !isClosed())
        {
            close();
            String s = String.valueOf(toString());
            Log.e("DataBuffer", (new StringBuilder(String.valueOf(s).length() + 178)).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(s).append(")").toString());
        }
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public final boolean isClosed()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mClosed;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        String as[] = zzaNU;
        if (as != null)
        {
            parcel.writeInt(-65535);
            parcel.writeInt(0);
            int k = parcel.dataPosition();
            parcel.writeStringArray(as);
            int i1 = parcel.dataPosition();
            parcel.setDataPosition(k - 4);
            parcel.writeInt(i1 - k);
            parcel.setDataPosition(i1);
        }
        zzc.zza(parcel, 2, zzaNW, i, false);
        i = zzaEP;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(i);
        Bundle bundle = zzaNX;
        if (bundle != null)
        {
            parcel.writeInt(-65532);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeBundle(bundle);
            int l = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(l - i);
            parcel.setDataPosition(l);
        }
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        i = parcel.dataPosition();
        parcel.setDataPosition(j - 4);
        parcel.writeInt(i - j);
        parcel.setDataPosition(i);
    }

    public final int zzfO(int i)
    {
        int j;
        j = 0;
        boolean flag;
        if (i >= 0 && i < zzaNZ)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
          goto _L1
_L3:
        j++;
_L1:
        int k;
        k = j;
        if (j >= zzaNY.length)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i >= zzaNY[j])
        {
            continue; /* Loop/switch isn't completed */
        }
        k = j - 1;
        break; /* Loop/switch isn't completed */
        if (true) goto _L3; else goto _L2
_L2:
        i = k;
        if (k == zzaNY.length)
        {
            i = k - 1;
        }
        return i;
    }

    public final void zzj(String s, int i)
    {
        if (zzaNV == null || !zzaNV.containsKey(s))
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "No such column: ".concat(s);
            } else
            {
                s = new String("No such column: ");
            }
            throw new IllegalArgumentException(s);
        }
        if (isClosed())
        {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= zzaNZ)
        {
            throw new CursorIndexOutOfBoundsException(i, zzaNZ);
        } else
        {
            return;
        }
    }

    static 
    {
        new _cls1(new String[0], null);
    }

    private class _cls1 extends Builder
    {

        _cls1(String as[], String s)
        {
            super(as, null, (byte)0);
        }
    }

}
