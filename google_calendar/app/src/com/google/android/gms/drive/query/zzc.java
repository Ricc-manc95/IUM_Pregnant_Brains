// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.zza;
import com.google.android.gms.drive.metadata.zzb;
import com.google.android.gms.drive.query.internal.zzj;
import com.google.android.gms.drive.query.internal.zzx;
import java.util.Iterator;
import java.util.List;

public final class zzc
    implements zzj
{

    public zzc()
    {
    }

    public final Object zzDb()
    {
        return "ownedByMe()";
    }

    public final Object zzDc()
    {
        return "all()";
    }

    public final Object zzF(Object obj)
    {
        return String.format("not(%s)", new Object[] {
            (String)obj
        });
    }

    public final Object zzb(zzb zzb1, Object obj)
    {
        return String.format("contains(%s,%s)", new Object[] {
            ((zza) (zzb1)).zzbbB, obj
        });
    }

    public final Object zzb(zzx zzx1, MetadataField metadatafield, Object obj)
    {
        return String.format("cmp(%s,%s,%s)", new Object[] {
            zzx1.mTag, metadatafield.getName(), obj
        });
    }

    public final Object zzb(zzx zzx1, List list)
    {
        StringBuilder stringbuilder = new StringBuilder(String.valueOf(zzx1.mTag).concat("("));
        list = list.iterator();
        for (zzx1 = ""; list.hasNext(); zzx1 = ",")
        {
            String s = (String)list.next();
            stringbuilder.append(zzx1);
            stringbuilder.append(s);
        }

        return stringbuilder.append(")").toString();
    }

    public final Object zzdB(String s)
    {
        return String.format("fullTextSearch(%s)", new Object[] {
            s
        });
    }

    public final Object zze(MetadataField metadatafield)
    {
        return String.format("fieldOnly(%s)", new Object[] {
            metadatafield.getName()
        });
    }

    public final Object zze(MetadataField metadatafield, Object obj)
    {
        return String.format("has(%s,%s)", new Object[] {
            metadatafield.getName(), obj
        });
    }
}
