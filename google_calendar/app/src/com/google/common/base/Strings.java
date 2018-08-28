// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Strings
{

    public static transient String lenientFormat(String s, Object aobj[])
    {
        String s1;
        int k;
        k = 0;
        s1 = String.valueOf(s);
        if (aobj != null) goto _L2; else goto _L1
_L1:
        s = ((String) (new Object[1]));
        s[0] = "(Object[])null";
_L4:
        int i;
        aobj = new StringBuilder(s1.length() + s.length * 16);
        boolean flag = false;
        i = k;
        k = ((flag) ? 1 : 0);
        do
        {
            if (i >= s.length)
            {
                break;
            }
            int i1 = s1.indexOf("%s", k);
            if (i1 == -1)
            {
                break;
            }
            ((StringBuilder) (aobj)).append(s1, k, i1);
            ((StringBuilder) (aobj)).append(s[i]);
            k = i1 + 2;
            i++;
        } while (true);
        break MISSING_BLOCK_LABEL_129;
_L2:
        i = 0;
        do
        {
            s = ((String) (aobj));
            if (i >= aobj.length)
            {
                break;
            }
            aobj[i] = lenientToString(aobj[i]);
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        ((StringBuilder) (aobj)).append(s1, k, s1.length());
        if (i < s.length)
        {
            ((StringBuilder) (aobj)).append(" [");
            int l = i + 1;
            ((StringBuilder) (aobj)).append(s[i]);
            for (int j = l; j < s.length; j++)
            {
                ((StringBuilder) (aobj)).append(", ");
                ((StringBuilder) (aobj)).append(s[j]);
            }

            ((StringBuilder) (aobj)).append(']');
        }
        return ((StringBuilder) (aobj)).toString();
    }

    private static String lenientToString(Object obj)
    {
        String s;
        try
        {
            s = String.valueOf(obj);
        }
        catch (Exception exception)
        {
            String s1 = obj.getClass().getName();
            obj = Integer.toHexString(System.identityHashCode(obj));
            s1 = (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(obj).length())).append(s1).append('@').append(((String) (obj))).toString();
            Logger logger = Logger.getLogger("com.google.common.base.Strings");
            Level level = Level.WARNING;
            obj = String.valueOf(s1);
            if (((String) (obj)).length() != 0)
            {
                obj = "Exception during lenientFormat for ".concat(((String) (obj)));
            } else
            {
                obj = new String("Exception during lenientFormat for ");
            }
            logger.logp(level, "com.google.common.base.Strings", "lenientToString", ((String) (obj)), exception);
            obj = exception.getClass().getName();
            return (new StringBuilder(String.valueOf(s1).length() + 9 + String.valueOf(obj).length())).append("<").append(s1).append(" threw ").append(((String) (obj))).append(">").toString();
        }
        return s;
    }
}
