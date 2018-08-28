// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.zzaks;
import com.google.android.gms.internal.zzakt;
import com.google.android.gms.internal.zzaku;
import com.google.android.gms.internal.zzakw;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.gms.drive.metadata.internal:
//            zzn

public final class zzf
{

    public static final Map zzbbK = new HashMap();
    private static final Map zzbbL = new HashMap();

    private static void zza(zza zza1)
    {
        if (zzbbL.put(zza1.zzCR(), zza1) != null)
        {
            zza1 = String.valueOf(zza1.zzCR());
            throw new IllegalStateException((new StringBuilder(String.valueOf(zza1).length() + 46)).append("A cleaner for key ").append(zza1).append(" has already been registered").toString());
        } else
        {
            return;
        }
    }

    private static void zzb(MetadataField metadatafield)
    {
        if (zzbbK.containsKey(metadatafield.getName()))
        {
            metadatafield = String.valueOf(metadatafield.getName());
            if (metadatafield.length() != 0)
            {
                metadatafield = "Duplicate field name registered: ".concat(metadatafield);
            } else
            {
                metadatafield = new String("Duplicate field name registered: ");
            }
            throw new IllegalArgumentException(metadatafield);
        } else
        {
            zzbbK.put(metadatafield.getName(), metadatafield);
            return;
        }
    }

    static 
    {
        zzb(zzaks.zzbbQ);
        zzb(zzaks.zzbcw);
        zzb(zzaks.zzbcn);
        zzb(zzaks.zzbcu);
        zzb(zzaks.zzbcx);
        zzb(zzaks.zzbcd);
        zzb(zzaks.zzbcc);
        zzb(zzaks.zzbce);
        zzb(zzaks.zzbcf);
        zzb(zzaks.zzbcg);
        zzb(zzaks.zzbca);
        zzb(zzaks.zzbci);
        zzb(zzaks.zzbcj);
        zzb(zzaks.zzbck);
        zzb(zzaks.zzbcs);
        zzb(zzaks.zzbbR);
        zzb(zzaks.zzbcp);
        zzb(zzaks.zzbbT);
        zzb(zzaks.zzbcb);
        zzb(zzaks.zzbbU);
        zzb(zzaks.zzbbV);
        zzb(zzaks.zzbbW);
        zzb(zzaks.zzbbX);
        zzb(zzaks.zzbcm);
        zzb(zzaks.zzbch);
        zzb(zzaks.zzbco);
        zzb(zzaks.zzbcq);
        zzb(zzaks.zzbcr);
        zzb(zzaks.zzbct);
        zzb(zzaks.zzbcy);
        zzb(zzaks.zzbcz);
        zzb(zzaks.zzbbZ);
        zzb(zzaks.zzbbY);
        zzb(zzaks.zzbcv);
        zzb(zzaks.zzbcl);
        zzb(zzaks.zzbbS);
        zzb(zzaks.zzbcA);
        zzb(zzaks.zzbcB);
        zzb(zzaks.zzbcC);
        zzb(zzaks.zzbcD);
        zzb(zzaks.zzbcE);
        zzb(zzaks.zzbcF);
        zzb(zzaks.zzbcG);
        zzb(zzaku.zzbcI);
        zzb(zzaku.zzbcK);
        zzb(zzaku.zzbcL);
        zzb(zzaku.zzbcM);
        zzb(zzaku.zzbcJ);
        zzb(zzaku.zzbcN);
        zzb(zzakw.zzbcP);
        zzb(zzakw.zzbcQ);
        zza(zzn.zzbbP);
        zza(zzakt.zzbcH);
    }

    private class zza
    {

        public abstract String zzCR();
    }

}
