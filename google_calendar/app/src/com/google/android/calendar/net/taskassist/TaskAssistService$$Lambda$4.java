// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.net.taskassist;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.auth.UserRecoverableAuthException;

// Referenced classes of package com.google.android.calendar.net.taskassist:
//            TaskAssistService

final class arg._cls2
    implements Runnable
{

    private final TaskAssistService arg$1;
    private final UserRecoverableAuthException arg$2;

    public final void run()
    {
        TaskAssistService taskassistservice = arg$1;
        Object obj = arg$2;
        if (((UserRecoverableAuthException) (obj)).mIntent == null)
        {
            obj = null;
        } else
        {
            obj = new Intent(((UserRecoverableAuthException) (obj)).mIntent);
        }
        ((Activity)taskassistservice.context).startActivityForResult(((Intent) (obj)), 1007);
    }

    (TaskAssistService taskassistservice, UserRecoverableAuthException userrecoverableauthexception)
    {
        arg$1 = taskassistservice;
        arg$2 = userrecoverableauthexception;
    }
}
