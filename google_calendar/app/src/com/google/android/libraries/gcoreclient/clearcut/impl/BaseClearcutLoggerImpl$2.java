// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.clearcut.impl;

import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer;

final class val.extensionProducer
    implements com.google.android.gms.clearcut.ducer
{

    private final GcoreClearcutMessageProducer val$extensionProducer;

    public final byte[] toProtoBytes()
    {
        return val$extensionProducer.toProtoBytes();
    }

    ()
    {
        val$extensionProducer = gcoreclearcutmessageproducer;
        super();
    }
}
