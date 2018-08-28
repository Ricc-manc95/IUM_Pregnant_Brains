// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net;

import android.content.Intent;
import com.google.android.gms.auth.UserRecoverableAuthException;

public final class AuthExceptionUtils
{

    public static Intent getAuthIntent(Throwable throwable)
    {
        throwable = throwable.getCause();
_L8:
        if (throwable == null) goto _L2; else goto _L1
_L1:
        if (!(throwable instanceof UserRecoverableAuthException)) goto _L4; else goto _L3
_L3:
        throwable = (UserRecoverableAuthException)throwable;
_L6:
        if (throwable == null)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L4:
        throwable = throwable.getCause();
        continue; /* Loop/switch isn't completed */
_L2:
        throwable = null;
        if (true) goto _L6; else goto _L5
_L5:
        if (((UserRecoverableAuthException) (throwable)).mIntent == null)
        {
            return null;
        }
        return new Intent(((UserRecoverableAuthException) (throwable)).mIntent);
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static boolean isUserRecoverableAuthException(Throwable throwable)
    {
        throwable = throwable.getCause();
_L3:
        if (throwable == null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        if (!(throwable instanceof UserRecoverableAuthException)) goto _L2; else goto _L1
_L1:
        throwable = (UserRecoverableAuthException)throwable;
_L4:
        return throwable != null;
_L2:
        throwable = throwable.getCause();
          goto _L3
        throwable = null;
          goto _L4
    }
}
