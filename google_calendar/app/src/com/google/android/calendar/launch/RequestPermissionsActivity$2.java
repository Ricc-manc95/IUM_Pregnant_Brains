// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.View;

// Referenced classes of package com.google.android.calendar.launch:
//            RequestPermissionsActivity

final class this._cls0
    implements android.view.PermissionsActivity._cls2
{

    private final RequestPermissionsActivity this$0;

    public final void onClick(View view)
    {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        view = String.valueOf(getPackageName());
        if (view.length() != 0)
        {
            view = "package:".concat(view);
        } else
        {
            view = new String("package:");
        }
        intent.setData(Uri.parse(view));
        startActivityForResult(intent, 1003);
    }

    ()
    {
        this$0 = RequestPermissionsActivity.this;
        super();
    }
}
