// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.flair;

import android.text.TextUtils;
import com.google.common.base.Function;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

final class val.wordIterator
    implements Function
{

    private final Locale val$locale;
    private final BreakIterator val$wordIterator;

    private final String[] apply(String s)
    {
        String s1;
        ArrayList arraylist;
        if (TextUtils.isEmpty(s))
        {
            return new String[0];
        }
        s1 = s.toLowerCase(val$locale);
        arraylist = new ArrayList();
        s = val$wordIterator;
        s;
        JVM INSTR monitorenter ;
        int i;
        int j;
        val$wordIterator.setText(s1);
        j = val$wordIterator.first();
        i = val$wordIterator.next();
_L2:
        if (i == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        int k;
        String s2 = s1.substring(j, i);
        if (!TextUtils.isEmpty(s2) && Character.isLetterOrDigit(s2.charAt(0)))
        {
            arraylist.add(s2);
        }
        k = val$wordIterator.next();
        j = i;
        i = k;
        if (true) goto _L2; else goto _L1
_L1:
        s;
        JVM INSTR monitorexit ;
        return (String[])arraylist.toArray(new String[arraylist.size()]);
        Exception exception;
        exception;
        s;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final volatile Object apply(Object obj)
    {
        return apply((String)obj);
    }

    ()
    {
        val$locale = locale1;
        val$wordIterator = breakiterator;
        super();
    }
}
