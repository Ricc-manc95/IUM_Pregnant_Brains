// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.internal.zzf;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class zzi
{

    static MetadataField zza(MetadataBundle metadatabundle)
    {
        HashSet hashset = new HashSet();
        String s;
        for (metadatabundle = metadatabundle.zzbbM.keySet().iterator(); metadatabundle.hasNext(); hashset.add((MetadataField)zzf.zzbbK.get(s)))
        {
            s = (String)metadatabundle.next();
        }

        if (hashset.size() != 1)
        {
            throw new IllegalArgumentException("bundle should have exactly 1 populated field");
        } else
        {
            return (MetadataField)hashset.iterator().next();
        }
    }
}
