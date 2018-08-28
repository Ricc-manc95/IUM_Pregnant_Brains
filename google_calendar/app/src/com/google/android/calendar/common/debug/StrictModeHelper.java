// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.debug;

import android.os.StrictMode;

public final class StrictModeHelper
{

    public static void configureStrictMode()
    {
        StrictMode.setThreadPolicy((new android.os.StrictMode.ThreadPolicy.Builder()).permitAll().build());
        StrictMode.setVmPolicy((new android.os.StrictMode.VmPolicy.Builder()).penaltyLog().build());
    }
}
