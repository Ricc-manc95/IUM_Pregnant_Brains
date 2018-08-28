// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.support.v4.app.Fragment;
import android.view.View;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenOpenCloseHelper

final class this._cls0
    implements Runnable
{

    private final ViewScreenOpenCloseHelper this$0;

    public final void run()
    {
        ViewScreenController viewscreencontroller = controller;
        boolean flag;
        if (((Fragment) (viewscreencontroller)).mHost != null && ((Fragment) (viewscreencontroller)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        while (!flag || contentView == null || !fullscreen) 
        {
            return;
        }
        rootView.setBackgroundResource(0x7f0d021b);
    }

    ()
    {
        this$0 = ViewScreenOpenCloseHelper.this;
        super();
    }
}
