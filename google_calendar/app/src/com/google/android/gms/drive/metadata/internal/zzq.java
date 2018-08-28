// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.drive.metadata.zza;
import com.google.android.gms.drive.metadata.zzb;
import java.util.Collections;

public final class zzq extends zzb
{

    public zzq(String s, int i)
    {
        super(s, Collections.singleton(s), Collections.emptySet(), 0x419ce0);
    }

    protected final Object zzz(Bundle bundle)
    {
        return bundle.getStringArrayList(super.zzbbB);
    }
}
