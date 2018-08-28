// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.common.data:
//            DataHolder

public class zzc
{

    public final DataHolder zzaKT;
    public int zzaNQ;
    private int zzaNR;

    public zzc(DataHolder dataholder, int i)
    {
        if (dataholder == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            zzaKT = (DataHolder)dataholder;
            zzfM(i);
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof zzc)
        {
            obj = (zzc)obj;
            Integer integer = Integer.valueOf(((zzc) (obj)).zzaNQ);
            Integer integer2 = Integer.valueOf(zzaNQ);
            boolean flag;
            if (integer == integer2 || integer != null && integer.equals(integer2))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Integer integer1 = Integer.valueOf(((zzc) (obj)).zzaNR);
                Integer integer3 = Integer.valueOf(zzaNR);
                if (integer1 == integer3 || integer1 != null && integer1.equals(integer3))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && ((zzc) (obj)).zzaKT == zzaKT)
                {
                    return true;
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public final boolean getBoolean(String s)
    {
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        dataholder.zzj(s, i);
        return Long.valueOf(dataholder.zzaNW[j].getLong(i, dataholder.zzaNV.getInt(s))).longValue() == 1L;
    }

    public final byte[] getByteArray(String s)
    {
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        dataholder.zzj(s, i);
        return dataholder.zzaNW[j].getBlob(i, dataholder.zzaNV.getInt(s));
    }

    public double getDouble(String s)
    {
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        dataholder.zzj(s, i);
        return dataholder.zzaNW[j].getDouble(i, dataholder.zzaNV.getInt(s));
    }

    public int getInteger(String s)
    {
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        dataholder.zzj(s, i);
        return dataholder.zzaNW[j].getInt(i, dataholder.zzaNV.getInt(s));
    }

    public long getLong(String s)
    {
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        dataholder.zzj(s, i);
        return dataholder.zzaNW[j].getLong(i, dataholder.zzaNV.getInt(s));
    }

    public final String getString(String s)
    {
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        dataholder.zzj(s, i);
        return dataholder.zzaNW[j].getString(i, dataholder.zzaNV.getInt(s));
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(zzaNQ), Integer.valueOf(zzaNR), zzaKT
        });
    }

    public final boolean zzcO(String s)
    {
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        dataholder.zzj(s, i);
        return dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s));
    }

    public void zzfM(int i)
    {
        boolean flag;
        if (i >= 0 && i < zzaKT.zzaNZ)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            zzaNQ = i;
            zzaNR = zzaKT.zzfO(zzaNQ);
            return;
        }
    }
}
