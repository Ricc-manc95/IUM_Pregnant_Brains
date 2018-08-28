// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.internal.zzl;
import java.util.Arrays;

public class zzakt extends zzl
{

    public static final com.google.android.gms.drive.metadata.internal.zzf.zza zzbcH = new _cls1();

    public zzakt(int i)
    {
        super("customProperties", Arrays.asList(new String[] {
            "hasCustomProperties", "sqlId"
        }), Arrays.asList(new String[] {
            "customPropertiesExtra", "customPropertiesExtraHolder"
        }), i);
    }


    private class _cls1
        implements com.google.android.gms.drive.metadata.internal.zzf.zza
    {

        public final String zzCR()
        {
            return "customPropertiesExtraHolder";
        }

        _cls1()
        {
        }
    }

}
