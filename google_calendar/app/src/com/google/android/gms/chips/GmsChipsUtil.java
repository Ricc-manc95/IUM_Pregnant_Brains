// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;

import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;

public final class GmsChipsUtil
{

    public static final ImmutableMap PACKAGE_NAME_TO_LABEL;

    static 
    {
        Integer integer = Integer.valueOf(1);
        CollectPreconditions.checkEntryNotNull("com.google.android.gm", integer);
        PACKAGE_NAME_TO_LABEL = RegularImmutableMap.create(1, new Object[] {
            "com.google.android.gm", integer
        });
    }
}
