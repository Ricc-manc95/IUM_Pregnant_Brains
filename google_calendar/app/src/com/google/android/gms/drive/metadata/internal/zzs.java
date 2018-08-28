// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import java.util.Arrays;
import java.util.Collections;

// Referenced classes of package com.google.android.gms.drive.metadata.internal:
//            zzl

public final class zzs extends zzl
{

    public zzs(String s, int i)
    {
        super(s, Arrays.asList(new String[] {
            zzO(s, "permissionId"), zzO(s, "displayName"), zzO(s, "picture"), zzO(s, "isAuthenticatedUser"), zzO(s, "emailAddress")
        }), Collections.emptyList(), 0x5b8d80);
    }

    private static String zzO(String s, String s1)
    {
        return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append(".").append(s1).toString();
    }
}
