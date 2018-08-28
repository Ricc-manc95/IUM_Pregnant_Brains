// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.pseudonymous.impl;

import com.google.android.libraries.gcoreclient.common.api.support.GcoreWrapper;
import com.google.android.libraries.gcoreclient.pseudonymous.GcorePseudonymousId;

// Referenced classes of package com.google.android.libraries.gcoreclient.pseudonymous.impl:
//            GcorePseudonymousIdImpl

public final class 
    implements com.google.android.libraries.gcoreclient.pseudonymous.lder
{

    public final GcorePseudonymousId build()
    {
        return new GcorePseudonymousIdImpl(new GcoreWrapper());
    }

    public ()
    {
    }
}
