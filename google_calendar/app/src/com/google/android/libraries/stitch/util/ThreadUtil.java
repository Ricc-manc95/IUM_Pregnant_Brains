// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.util;

import android.os.Handler;

public final class ThreadUtil
{

    public static volatile Thread sMainThread;
    public static volatile Handler sMainThreadHandler;
}
