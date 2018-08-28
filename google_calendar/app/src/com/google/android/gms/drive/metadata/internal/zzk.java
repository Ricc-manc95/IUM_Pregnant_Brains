// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.zza;
import com.google.android.gms.drive.metadata.zzb;
import java.util.Collection;

public class zzk extends zzb
{

    public zzk(String s, Collection collection, Collection collection1, int i)
    {
        super(s, collection, collection1, i);
    }

    protected Collection zzE(Bundle bundle)
    {
        return bundle.getParcelableArrayList(super.zzbbB);
    }

    protected Object zzz(Bundle bundle)
    {
        return zzE(bundle);
    }
}
