// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.inject;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.common.io.BaseEncoding;
import java.security.MessageDigest;

public final class GrowthKitInternalProdModule
{

    private static final Logger logger = new Logger();

    static String provideAppCertificateFingerprint(Context context, String s)
    {
        context = context.getPackageManager().getPackageInfo(s, 64);
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_122;
        }
        if (((PackageInfo) (context)).signatures == null || ((PackageInfo) (context)).signatures.length <= 0)
        {
            break MISSING_BLOCK_LABEL_122;
        }
        s = MessageDigest.getInstance("SHA-1");
        if (s == null)
        {
            return null;
        }
        context = s.digest(((PackageInfo) (context)).signatures[0].toByteArray());
        if (context == null)
        {
            return null;
        }
        context = BaseEncoding.BASE16.encode(context, 0, context.length);
        return context;
        Exception exception;
        exception;
        context = logger;
        s = "Error getting certificate fingerprint.";
        Object aobj[] = new Object[0];
        String s1 = ((Logger) (context)).tag;
        context = s;
        if (aobj != null)
        {
            context = s;
            if (aobj.length > 0)
            {
                context = String.format("Error getting certificate fingerprint.", aobj);
            }
        }
        Log.e(s1, context, exception);
        return null;
    }

}
