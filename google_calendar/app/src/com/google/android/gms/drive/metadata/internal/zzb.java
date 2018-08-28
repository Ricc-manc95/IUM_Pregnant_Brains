// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.zza;
import java.util.Collection;

public class zzb extends zza
{

    public zzb(String s, int i)
    {
        super(s, i);
    }

    public zzb(String s, Collection collection, Collection collection1, int i)
    {
        super(s, collection, collection1, i);
    }

    protected final Object zzz(Bundle bundle)
    {
        return Boolean.valueOf(bundle.getBoolean(super.zzbbB));
    }
}
