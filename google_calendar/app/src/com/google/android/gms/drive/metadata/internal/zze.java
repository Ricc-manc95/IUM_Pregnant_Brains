// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.zza;
import com.google.android.gms.drive.metadata.zzd;
import java.util.Date;

public class zze extends zzd
{

    public zze(String s, int i)
    {
        super(s, i);
    }

    protected final Object zzz(Bundle bundle)
    {
        return new Date(bundle.getLong(super.zzbbB));
    }
}
