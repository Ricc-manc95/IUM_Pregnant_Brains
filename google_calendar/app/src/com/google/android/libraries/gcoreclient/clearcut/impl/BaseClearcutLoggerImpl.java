// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.clearcut.impl;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer;
import com.google.android.libraries.gcoreclient.common.api.support.ResultWrapper;

// Referenced classes of package com.google.android.libraries.gcoreclient.clearcut.impl:
//            GcoreClearcutLogEventBuilderImpl

class BaseClearcutLoggerImpl
    implements GcoreClearcutLogger
{

    public static final ResultWrapper RESULT_WRAPPER = new _cls1();
    private final ClearcutLogger clearcutLogger;

    BaseClearcutLoggerImpl(Context context, String s, String s1)
    {
        clearcutLogger = new ClearcutLogger(context, s, s1, null);
    }

    public GcoreClearcutLogEventBuilder newEvent(final GcoreClearcutMessageProducer extensionProducer)
    {
        return new GcoreClearcutLogEventBuilderImpl(clearcutLogger, new _cls2());
    }

    public final GcoreClearcutLogEventBuilder newEvent(byte abyte0[])
    {
        return new GcoreClearcutLogEventBuilderImpl(clearcutLogger, abyte0);
    }


    private class _cls2
        implements com.google.android.gms.clearcut.ClearcutLogger.MessageProducer
    {

        private final GcoreClearcutMessageProducer val$extensionProducer;

        public final byte[] toProtoBytes()
        {
            return extensionProducer.toProtoBytes();
        }

        _cls2()
        {
            extensionProducer = gcoreclearcutmessageproducer;
            super();
        }
    }


    private class _cls1
        implements ResultWrapper
    {

        public final GcoreResult wrap(Result result)
        {
            return new GcoreStatusImpl((Status)result);
        }

        _cls1()
        {
        }
    }

}
