// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.common.http;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.common.http:
//            Rule

public final class RuleMatcher
{

    private final Matcher allRulesMatcher;
    private final Matcher nonWordCharacterMatcher = Pattern.compile("\\W").matcher("");
    private final List rules = new ArrayList();

    public RuleMatcher(Rule arule[])
    {
        ArrayList arraylist = new ArrayList();
        int j = arule.length;
        for (int i = 0; i < j; i++)
        {
            Rule rule = arule[i];
            rules.add(rule);
            nonWordCharacterMatcher.reset(rule.prefix);
            arraylist.add(nonWordCharacterMatcher.replaceAll("\\\\$0"));
        }

        allRulesMatcher = Pattern.compile(String.format("(%s)", new Object[] {
            TextUtils.join(")|(", arraylist)
        })).matcher("");
        if (allRulesMatcher.groupCount() != rules.size())
        {
            Log.wtf("RuleMatcher", "Capture group / rule count mismatch");
        }
    }

    public final Rule match(String s)
    {
        this;
        JVM INSTR monitorenter ;
        s = allRulesMatcher.reset(s);
        if (!s.lookingAt()) goto _L2; else goto _L1
_L1:
        int i = 0;
_L9:
        if (i >= rules.size()) goto _L2; else goto _L3
_L3:
        if (s.group(i + 1) == null) goto _L5; else goto _L4
_L4:
        s = (Rule)rules.get(i);
_L7:
        this;
        JVM INSTR monitorexit ;
        return s;
_L5:
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        s = null;
        if (true) goto _L7; else goto _L6
_L6:
        s;
        throw s;
        if (true) goto _L9; else goto _L8
_L8:
    }
}
