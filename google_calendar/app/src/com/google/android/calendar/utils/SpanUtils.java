// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils;

import android.text.SpannableStringBuilder;
import java.util.Iterator;

public final class SpanUtils
{

    public static SpannableStringBuilder join(CharSequence charsequence, Iterable iterable)
    {
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
        iterable = iterable.iterator();
        if (iterable.hasNext())
        {
            spannablestringbuilder.append((CharSequence)iterable.next());
            for (; iterable.hasNext(); spannablestringbuilder.append((CharSequence)iterable.next()))
            {
                spannablestringbuilder.append(charsequence);
            }

        }
        return spannablestringbuilder;
    }
}
