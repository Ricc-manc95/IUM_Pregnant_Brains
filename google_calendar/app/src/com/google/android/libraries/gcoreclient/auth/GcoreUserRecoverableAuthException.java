// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.auth;

import android.content.Intent;

// Referenced classes of package com.google.android.libraries.gcoreclient.auth:
//            GcoreGoogleAuthException

public class GcoreUserRecoverableAuthException extends GcoreGoogleAuthException
{

    public GcoreUserRecoverableAuthException(String s, Intent intent)
    {
        super(s);
    }

    public GcoreUserRecoverableAuthException(String s, Intent intent, Exception exception)
    {
        super(s, exception);
    }
}
