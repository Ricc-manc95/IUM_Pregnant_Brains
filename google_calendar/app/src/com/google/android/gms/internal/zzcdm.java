// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.ByteBuffer;

// Referenced classes of package com.google.android.gms.internal:
//            zzcde, zzcdl, zzcdd, zzcdn

public abstract class zzcdm
{

    public volatile int AL;

    public zzcdm()
    {
        AL = -1;
    }

    public static final void zza(zzcdm zzcdm1, byte abyte0[], int i, int j)
    {
        try
        {
            abyte0 = new zzcde(abyte0, 0, j);
            zzcdm1.writeTo(abyte0);
            if (((zzcde) (abyte0)).AB.remaining() != 0)
            {
                throw new IllegalStateException("Did not write as much data as expected.");
            }
        }
        // Misplaced declaration of an exception variable
        catch (zzcdm zzcdm1)
        {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", zzcdm1);
        }
    }

    public static final zzcdm zzb(zzcdm zzcdm1, byte abyte0[], int i, int j)
        throws zzcdl
    {
        try
        {
            abyte0 = new zzcdd(abyte0, 0, j);
            zzcdm1.mergeFrom(abyte0);
            if (((zzcdd) (abyte0)).Aw != 0)
            {
                throw new zzcdl("Protocol message end-group tag did not match expected tag.");
            }
        }
        // Misplaced declaration of an exception variable
        catch (zzcdm zzcdm1)
        {
            throw zzcdm1;
        }
        // Misplaced declaration of an exception variable
        catch (zzcdm zzcdm1)
        {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
        return zzcdm1;
    }

    public static final byte[] zzf(zzcdm zzcdm1)
    {
        int i = zzcdm1.computeSerializedSize();
        zzcdm1.AL = i;
        byte abyte0[] = new byte[i];
        i = abyte0.length;
        try
        {
            zzcde zzcde1 = new zzcde(abyte0, 0, i);
            zzcdm1.writeTo(zzcde1);
            if (zzcde1.AB.remaining() != 0)
            {
                throw new IllegalStateException("Did not write as much data as expected.");
            }
        }
        // Misplaced declaration of an exception variable
        catch (zzcdm zzcdm1)
        {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", zzcdm1);
        }
        return abyte0;
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        return zzakU();
    }

    public int computeSerializedSize()
    {
        return 0;
    }

    public abstract zzcdm mergeFrom(zzcdd zzcdd1)
        throws IOException;

    public String toString()
    {
        return zzcdn.zzg(this);
    }

    public void writeTo(zzcde zzcde1)
        throws IOException
    {
    }

    public zzcdm zzakU()
        throws CloneNotSupportedException
    {
        return (zzcdm)super.clone();
    }
}
