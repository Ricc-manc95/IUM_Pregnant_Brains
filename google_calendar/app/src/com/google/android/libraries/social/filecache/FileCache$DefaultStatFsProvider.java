// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.filecache;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;

final class 
    implements 
{

    public final long getFreeBytes()
    {
        return (new StatFs(Environment.getDataDirectory().getPath())).getFreeBytes();
    }

    public final long getTotalBytes()
    {
        return (new StatFs(Environment.getDataDirectory().getPath())).getTotalBytes();
    }

    ()
    {
    }
}
