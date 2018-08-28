// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.SyncResult;
import android.content.SyncStats;

final class  extends Enum
{

    private static final IO_EXCEPTION $VALUES[];
    private static final IO_EXCEPTION AUTH_EXCEPTION;
    private static final IO_EXCEPTION IO_EXCEPTION;
    private static final IO_EXCEPTION PARSE_EXCEPTION;
    private static final IO_EXCEPTION UNKNOWN;

    static  fromResult(SyncResult syncresult)
    {
        if (syncresult.stats.numAuthExceptions > 0L)
        {
            return AUTH_EXCEPTION;
        }
        if (syncresult.stats.numParseExceptions > 0L)
        {
            return PARSE_EXCEPTION;
        }
        if (syncresult.stats.numIoExceptions > 0L)
        {
            return IO_EXCEPTION;
        } else
        {
            return UNKNOWN;
        }
    }

    public static UNKNOWN[] values()
    {
        return (UNKNOWN[])$VALUES.clone();
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0);
        AUTH_EXCEPTION = new <init>("AUTH_EXCEPTION", 1);
        PARSE_EXCEPTION = new <init>("PARSE_EXCEPTION", 2);
        IO_EXCEPTION = new <init>("IO_EXCEPTION", 3);
        $VALUES = (new .VALUES[] {
            UNKNOWN, AUTH_EXCEPTION, PARSE_EXCEPTION, IO_EXCEPTION
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
