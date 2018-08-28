// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments;

import java.util.HashMap;
import java.util.Map;

public final class ExperimentFlagsOverride
{

    public static final Map overrides = new HashMap();

    public static Object get(String s, Class class1)
    {
        return class1.cast(overrides.get(s));
    }

}
