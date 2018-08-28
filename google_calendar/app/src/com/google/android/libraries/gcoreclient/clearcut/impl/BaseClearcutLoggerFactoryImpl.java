// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.clearcut.impl;

import android.content.Context;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLoggerFactory;

// Referenced classes of package com.google.android.libraries.gcoreclient.clearcut.impl:
//            GcoreClearcutLoggerImpl

class BaseClearcutLoggerFactoryImpl
    implements GcoreClearcutLoggerFactory
{

    BaseClearcutLoggerFactoryImpl()
    {
    }

    public GcoreClearcutLogger getGcoreClearcutLogger(Context context, String s, String s1)
    {
        return new GcoreClearcutLoggerImpl(context, s, s1);
    }
}
