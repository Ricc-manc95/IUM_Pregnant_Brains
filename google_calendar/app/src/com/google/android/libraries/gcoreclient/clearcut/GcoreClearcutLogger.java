// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.clearcut;


// Referenced classes of package com.google.android.libraries.gcoreclient.clearcut:
//            GcoreClearcutMessageProducer, GcoreClearcutLogEventBuilder

public interface GcoreClearcutLogger
{

    public abstract GcoreClearcutLogEventBuilder newEvent(GcoreClearcutMessageProducer gcoreclearcutmessageproducer);

    public abstract GcoreClearcutLogEventBuilder newEvent(byte abyte0[]);
}
