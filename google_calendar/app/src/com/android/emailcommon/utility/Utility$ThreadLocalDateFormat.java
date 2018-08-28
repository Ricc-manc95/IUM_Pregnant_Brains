// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.utility;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

final class formatStr extends ThreadLocal
{

    private final String formatStr;

    protected final Object initialValue()
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(formatStr);
        simpledateformat.setCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT")));
        return simpledateformat;
    }

    public (String s)
    {
        formatStr = s;
    }
}
