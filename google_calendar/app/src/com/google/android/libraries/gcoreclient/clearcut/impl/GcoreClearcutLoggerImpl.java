// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.clearcut.impl;

import android.content.Context;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer;

// Referenced classes of package com.google.android.libraries.gcoreclient.clearcut.impl:
//            BaseClearcutLoggerImpl

public final class GcoreClearcutLoggerImpl extends BaseClearcutLoggerImpl
{

    public GcoreClearcutLoggerImpl(Context context, String s, String s1)
    {
        super(context, s, s1);
    }

    public final volatile GcoreClearcutLogEventBuilder newEvent(GcoreClearcutMessageProducer gcoreclearcutmessageproducer)
    {
        return super.newEvent(gcoreclearcutmessageproducer);
    }
}
