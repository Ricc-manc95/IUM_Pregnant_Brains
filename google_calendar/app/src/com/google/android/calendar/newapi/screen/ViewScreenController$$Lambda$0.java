// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreenController

final class arg._cls1
    implements android.support.v4.app.gedListener
{

    private final ViewScreenController arg$1;

    public final void onBackStackChanged()
    {
        ViewScreenController viewscreencontroller = arg$1;
        Object obj = ((Fragment) (viewscreencontroller)).mFragmentManager;
        int i = ((FragmentManager) (obj)).getBackStackEntryCount();
        if (i > 0)
        {
            String s = ((Fragment) (viewscreencontroller)).mTag;
            obj = ((FragmentManager) (obj)).getBackStackEntryAt(i - 1).getName();
            boolean flag;
            if (s == obj || s != null && s.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                viewscreencontroller.editOpeningInitiated = false;
            }
        }
    }

    (ViewScreenController viewscreencontroller)
    {
        arg$1 = viewscreencontroller;
    }
}
