// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.pseudonymous;

import com.google.android.libraries.gcoreclient.common.api.GcoreResult;

// Referenced classes of package com.google.android.libraries.gcoreclient.pseudonymous:
//            GcorePseudonymousIdToken

public interface GcorePseudonymousIdTokenResult
    extends GcoreResult
{

    public abstract GcorePseudonymousIdToken getPseudonymousIdToken();
}
