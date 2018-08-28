// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import java.io.Closeable;
import java.io.IOException;

final class this._cls0
    implements Closeable
{

    private final l.client this$0;

    public final void close()
        throws IOException
    {
        client.disconnect();
    }

    a()
    {
        this$0 = this._cls0.this;
        super();
    }
}
