// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.impl;

import android.content.Context;
import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;

// Referenced classes of package com.google.android.libraries.gcoreclient.common.api.impl:
//            GcoreGoogleApiClientImpl

public final class der extends der
{

    public final GcoreGoogleApiClient build()
    {
        return new GcoreGoogleApiClientImpl(context, builder.builder(), wrapper);
    }

    public der(Context context)
    {
        super(context);
    }
}
