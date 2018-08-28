// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.common.http;

import java.util.Arrays;

// Referenced classes of package com.google.android.common.http:
//            RuleMatcher, Rule

public final class UrlRules
{

    public RuleMatcher ruleMatcher;
    public final Rule rules[];

    UrlRules(Rule arule[])
    {
        Arrays.sort(arule);
        rules = arule;
    }
}
