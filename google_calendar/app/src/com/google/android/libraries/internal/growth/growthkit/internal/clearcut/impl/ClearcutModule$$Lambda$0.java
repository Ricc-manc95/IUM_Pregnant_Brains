// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.clearcut.impl;

import android.content.Context;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLoggerFactory;
import javax.inject.Provider;

final class arg._cls2
    implements com.google.android.libraries.internal.growth.growthkit.internal.common.y
{

    private final Provider arg$1;
    private final Context arg$2;

    public final Object createForAccount(String s)
    {
        Provider provider = arg$1;
        Context context = arg$2;
        return ((GcoreClearcutLoggerFactory)provider.get()).getGcoreClearcutLogger(context, "ANDROID_GROWTH", s);
    }

    (Provider provider, Context context)
    {
        arg$1 = provider;
        arg$2 = context;
    }
}
