// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzagk extends Metadata
{

    private final MetadataBundle zzaYJ;

    public zzagk(MetadataBundle metadatabundle)
    {
        zzaYJ = metadatabundle;
    }

    public final Object freeze()
    {
        return new zzagk(new MetadataBundle(new Bundle(zzaYJ.zzbbM)));
    }

    public final String toString()
    {
        String s = String.valueOf(zzaYJ);
        return (new StringBuilder(String.valueOf(s).length() + 17)).append("Metadata [mImpl=").append(s).append("]").toString();
    }

    public final Object zza(MetadataField metadatafield)
    {
        return metadatafield.zzy(zzaYJ.zzbbM);
    }
}
