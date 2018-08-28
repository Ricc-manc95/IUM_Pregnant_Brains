// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.auth;

import android.content.Intent;

// Referenced classes of package com.google.android.libraries.gcoreclient.auth:
//            GcoreUserRecoverableAuthException

public final class GcoreGooglePlayServicesAvailabilityException extends GcoreUserRecoverableAuthException
{

    public GcoreGooglePlayServicesAvailabilityException(int i, String s, Intent intent, Exception exception)
    {
        super(s, intent, exception);
    }
}
