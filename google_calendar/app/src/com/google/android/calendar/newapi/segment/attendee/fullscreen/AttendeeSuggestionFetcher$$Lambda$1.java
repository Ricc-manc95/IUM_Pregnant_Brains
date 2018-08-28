// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attendee.fullscreen;

import android.util.Patterns;
import com.android.ex.chips.RecipientEntry;
import com.google.common.base.Platform;
import com.google.common.base.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class 
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        obj = (RecipientEntry)obj;
        String s = Platform.nullToEmpty(((RecipientEntry) (obj)).destination).toLowerCase();
        if (((RecipientEntry) (obj)).isValid)
        {
            boolean flag;
            if (((RecipientEntry) (obj)).entryType == 0 || ((RecipientEntry) (obj)).entryType == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && Patterns.EMAIL_ADDRESS.matcher(s).matches())
            {
                return true;
            }
        }
        return false;
    }


    private ()
    {
    }
}
