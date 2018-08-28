// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.common;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class BaseAppUtil
{

    private static final List ANDROID_DOGFOOD_SUFFIXES = Arrays.asList(new String[] {
        ".beta", ".dev", ".alpha", ".qa", ".teamfood"
    });

    public static String normalizeAndroidPackageName(String s)
    {
        Object obj = ANDROID_DOGFOOD_SUFFIXES;
        if (s != null) goto _L2; else goto _L1
_L1:
        obj = "";
_L4:
        return ((String) (obj));
_L2:
        Iterator iterator = ((List) (obj)).iterator();
        do
        {
            obj = s;
            if (!iterator.hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            obj = (String)iterator.next();
            if (s.endsWith(((String) (obj))))
            {
                return s.substring(0, s.length() - ((String) (obj)).length());
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    static 
    {
        Arrays.asList(new String[] {
            ".ios.dev", ".dev", ".devel", ".dogfood", ".earlgrey", ".earlgreytestrig", ".scary.enterprise", ".enterprise", ".fishfood", ".kif", 
            ".scubatestrig", ".ios.beta", ".ios.canary", ".ios.herebedragons", ".qa", ".prodalpha", ".prodbeta"
        });
    }
}
