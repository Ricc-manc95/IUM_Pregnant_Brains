// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.support;

import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.libraries.gcoreclient.common.api.support:
//            BaseGcoreWrapper

final class stener
    implements com.google.android.gms.common.api.onnectionFailedListener
{

    private final BaseGcoreWrapper this$0;
    private final com.google.android.libraries.gcoreclient.common.api.t.GcoreOnConnectionFailedListener val$listener;

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        val$listener.onConnectionFailed(wrap(connectionresult));
    }

    stener()
    {
        this$0 = final_basegcorewrapper;
        val$listener = com.google.android.libraries.gcoreclient.common.api.t.GcoreOnConnectionFailedListener.this;
        super();
    }
}
