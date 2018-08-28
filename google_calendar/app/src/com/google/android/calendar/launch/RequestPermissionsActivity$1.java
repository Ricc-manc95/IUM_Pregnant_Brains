// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch;

import android.view.View;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;

// Referenced classes of package com.google.android.calendar.launch:
//            RequestPermissionsActivity

final class this._cls0
    implements android.view.PermissionsActivity._cls1
{

    private final RequestPermissionsActivity this$0;

    public final void onClick(View view)
    {
        AndroidPermissionUtils.requestMandatoryPermissions(RequestPermissionsActivity.this, 1);
    }

    ils()
    {
        this$0 = RequestPermissionsActivity.this;
        super();
    }
}
