// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.common.http;

import android.text.TextUtils;
import java.util.regex.Pattern;

public final class Rule
    implements Comparable
{

    public static final Rule DEFAULT = new Rule();
    private static final Pattern PATTERN_SPACE_PLUS = Pattern.compile(" +");
    public final String name;
    public final String prefix;
    private final String rewrite;
    public final String type;

    private Rule()
    {
        name = "DEFAULT";
        type = "rewrite";
        prefix = "";
        rewrite = null;
    }

    private Rule(String s, String s1)
        throws RuleFormatException
    {
        String s2;
        byte byte0;
        byte0 = 0;
        super();
        name = s;
        s = PATTERN_SPACE_PLUS.split(s1);
        if (s.length < 2)
        {
            throw new RuleFormatException(s1);
        }
        prefix = s[0];
        type = s[1];
        s2 = type;
        s2.hashCode();
        JVM INSTR lookupswitch 2: default 84
    //                   93832333: 133
    //                   1101148556: 121;
           goto _L1 _L2 _L3
_L1:
        byte0 = -1;
_L4:
        switch (byte0)
        {
        default:
            throw new RuleFormatException(s1);

        case 0: // '\0'
            if (s.length < 3)
            {
                throw new RuleFormatException(s1);
            } else
            {
                rewrite = s[2];
                return;
            }

        case 1: // '\001'
            rewrite = null;
            return;
        }
_L3:
        if (!s2.equals("rewrite")) goto _L1; else goto _L4
_L2:
        if (!s2.equals("block")) goto _L1; else goto _L5
_L5:
        byte0 = 1;
          goto _L4
    }

    public static Rule create(String s, String s1)
        throws RuleFormatException
    {
        if (TextUtils.isEmpty(s1))
        {
            return DEFAULT;
        } else
        {
            return new Rule(s, s1);
        }
    }

    public final String apply(String s)
    {
        String s1;
        byte byte0;
        s1 = type;
        byte0 = -1;
        s1.hashCode();
        JVM INSTR lookupswitch 2: default 36
    //                   93832333: 70
    //                   1101148556: 84;
           goto _L1 _L2 _L3
_L1:
        byte0;
        JVM INSTR tableswitch 0 1: default 60
    //                   0 98
    //                   1 102;
           goto _L4 _L5 _L6
_L4:
        throw new IllegalStateException("Bad rule type");
_L2:
        if (s1.equals("block"))
        {
            byte0 = 0;
        }
          goto _L1
_L3:
        if (s1.equals("rewrite"))
        {
            byte0 = 1;
        }
          goto _L1
_L5:
        s1 = null;
_L8:
        return s1;
_L6:
        s1 = s;
        if (rewrite != null)
        {
            String s2 = String.valueOf(rewrite);
            s = String.valueOf(s.substring(prefix.length()));
            if (s.length() != 0)
            {
                return s2.concat(s);
            } else
            {
                return new String(s2);
            }
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final int compareTo(Object obj)
    {
        return ((Rule)obj).prefix.compareTo(prefix);
    }


    private class RuleFormatException extends Exception
    {

        public RuleFormatException(String s)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Illegal rule: ".concat(s);
            } else
            {
                s = new String("Illegal rule: ");
            }
            super(s);
        }
    }

}
