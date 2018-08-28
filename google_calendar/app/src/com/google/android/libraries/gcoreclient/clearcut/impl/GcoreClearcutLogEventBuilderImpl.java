// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.clearcut.impl;

import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;
import com.google.android.libraries.gcoreclient.common.api.support.GcorePendingResultImpl;

// Referenced classes of package com.google.android.libraries.gcoreclient.clearcut.impl:
//            BaseClearcutLogEventBuilderImpl, BaseClearcutLoggerImpl

public final class GcoreClearcutLogEventBuilderImpl extends BaseClearcutLogEventBuilderImpl
{

    GcoreClearcutLogEventBuilderImpl(ClearcutLogger clearcutlogger, com.google.android.gms.clearcut.ClearcutLogger.MessageProducer messageproducer)
    {
        super(clearcutlogger, messageproducer);
    }

    GcoreClearcutLogEventBuilderImpl(ClearcutLogger clearcutlogger, byte abyte0[])
    {
        super(clearcutlogger, abyte0);
    }

    public final GcorePendingResult logAsync()
    {
        return new GcorePendingResultImpl(logEventBuilder.logAsync(), BaseClearcutLoggerImpl.RESULT_WRAPPER);
    }

    public final volatile GcoreClearcutLogEventBuilder setEventCode(int i)
    {
        return super.setEventCode(i);
    }

    public final volatile GcoreClearcutLogEventBuilder setLogSourceName(String s)
    {
        return super.setLogSourceName(s);
    }

    public final volatile GcoreClearcutLogEventBuilder setUploadAccountName(String s)
    {
        return super.setUploadAccountName(s);
    }
}
