// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apiary;

import com.google.android.common.http.Rule;
import java.io.IOException;

public final class  extends IOException
{

    (Rule rule)
    {
        rule = String.valueOf(rule.name);
        if (rule.length() != 0)
        {
            rule = "Blocked by rule: ".concat(rule);
        } else
        {
            rule = new String("Blocked by rule: ");
        }
        super(rule);
    }
}
