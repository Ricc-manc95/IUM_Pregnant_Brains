// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController;

// Referenced classes of package com.google.android.calendar.newapi.segment.common:
//            EditSegmentController

public abstract class FullScreenEditSegmentController extends EditSegmentController
    implements com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController.OnFullScreenResultListener
{

    public FullScreenEditSegmentController()
    {
    }

    private final void setSegmentFocusability(boolean flag)
    {
        ViewGroup viewgroup;
        if (((Fragment) (super.editScreenController)).mView != null)
        {
            viewgroup = (ViewGroup)((Fragment) (super.editScreenController)).mView.findViewById(0x7f100263);
        } else
        {
            viewgroup = null;
        }
        if (viewgroup != null)
        {
            int i;
            if (flag)
            {
                i = 0x20000;
            } else
            {
                i = 0x60000;
            }
            viewgroup.setDescendantFocusability(i);
        }
    }

    public final void onFullScreenClosed()
    {
        Object obj = ((Fragment) (super.editScreenController)).mView;
        if (obj != null)
        {
            obj = ((View) (obj)).findViewById(0x7f10026b);
            if (obj != null)
            {
                ((View) (obj)).setVisibility(8);
            }
        }
        if (((Fragment) (super.editScreenController)).mView != null)
        {
            obj = (ViewGroup)((Fragment) (super.editScreenController)).mView.findViewById(0x7f100263);
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            ViewCompat.setImportantForAccessibility(((View) (obj)), 0);
        }
        setSegmentFocusability(true);
    }

    public final void openInFullScreen(EditFullScreenController editfullscreencontroller)
    {
        Object obj = null;
        if (this == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L10:
        if (flag) goto _L7; else goto _L6
_L6:
        return;
_L5:
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L7:
        editfullscreencontroller.setTargetFragment(this, 0);
        super.mFragmentManager.beginTransaction().add(0x7f10026b, editfullscreencontroller, EditFullScreenController.TAG).commitNowAllowingStateLoss();
        editfullscreencontroller = ((Fragment) (super.editScreenController)).mView;
        if (editfullscreencontroller != null)
        {
            editfullscreencontroller = editfullscreencontroller.findViewById(0x7f10026b);
            if (editfullscreencontroller != null)
            {
                editfullscreencontroller.setVisibility(0);
            }
        }
        if (((Fragment) (super.editScreenController)).mView != null)
        {
            editfullscreencontroller = (ViewGroup)((Fragment) (super.editScreenController)).mView.findViewById(0x7f100263);
        } else
        {
            editfullscreencontroller = null;
        }
        if (editfullscreencontroller != null)
        {
            ViewCompat.setImportantForAccessibility(editfullscreencontroller, 4);
        }
        editfullscreencontroller = obj;
        if (((Fragment) (super.editScreenController)).mView != null)
        {
            editfullscreencontroller = (ViewGroup)((Fragment) (super.editScreenController)).mView.findViewById(0x7f100263);
        }
        if (editfullscreencontroller == null) goto _L6; else goto _L8
_L8:
        editfullscreencontroller.setDescendantFocusability(0x60000);
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }
}
