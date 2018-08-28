// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;

import java.util.logging.Level;

public final class LogUtils
{

    public static final Level LOGGING_LEVELS_BY_INDEX[];

    static 
    {
        LOGGING_LEVELS_BY_INDEX = (new Level[] {
            Level.FINER, Level.FINE, Level.INFO, Level.WARNING, Level.SEVERE
        });
    }
}
