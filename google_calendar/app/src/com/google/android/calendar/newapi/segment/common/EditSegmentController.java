// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.newapi.screen.EditScreenController;
import com.google.android.calendar.newapi.screen.SegmentMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.common:
//            SegmentController

public abstract class EditSegmentController extends SegmentController
{

    public static boolean animationsOn = true;
    public EditScreenController editScreenController;

    public EditSegmentController()
    {
    }

    public Integer getErrorMessageId()
    {
        return null;
    }

    public final void notifyAttendeesChanged()
    {
        Consumer consumer;
        Iterator iterator;
        EditScreenController editscreencontroller = editScreenController;
        consumer = com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls4.$instance;
        iterator = editscreencontroller.segments.segmentControllers.values().iterator();
_L10:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag;
        editsegmentcontroller = (EditSegmentController)iterator.next();
        if (editsegmentcontroller == this || editsegmentcontroller != null && editsegmentcontroller.equals(this))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L6; else goto _L5
_L5:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L7; else goto _L6
_L6:
        flag = false;
_L8:
        if (flag)
        {
            consumer.accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        FragmentActivity fragmentactivity;
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
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
_L4:
        flag = false;
        if (true) goto _L8; else goto _L2
_L2:
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final void notifyTaskAssistChanged()
    {
        Consumer consumer;
        Iterator iterator;
        EditScreenController editscreencontroller = editScreenController;
        consumer = com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls5.$instance;
        iterator = editscreencontroller.segments.segmentControllers.values().iterator();
_L10:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag;
        editsegmentcontroller = (EditSegmentController)iterator.next();
        if (editsegmentcontroller == this || editsegmentcontroller != null && editsegmentcontroller.equals(this))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L6; else goto _L5
_L5:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L7; else goto _L6
_L6:
        flag = false;
_L8:
        if (flag)
        {
            consumer.accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        FragmentActivity fragmentactivity;
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
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
_L4:
        flag = false;
        if (true) goto _L8; else goto _L2
_L2:
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final void notifyTimeChanged(boolean flag, boolean flag1)
    {
        com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls2 _lcls2;
        Iterator iterator;
        EditScreenController editscreencontroller = editScreenController;
        _lcls2 = new com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls2(flag, flag1);
        iterator = editscreencontroller.segments.segmentControllers.values().iterator();
_L10:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        boolean flag2;
        editsegmentcontroller = (EditSegmentController)iterator.next();
        if (editsegmentcontroller == this || editsegmentcontroller != null && editsegmentcontroller.equals(this))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (editsegmentcontroller == null) goto _L4; else goto _L3
_L3:
        fragmentmanagerimpl = ((Fragment) (editsegmentcontroller)).mFragmentManager;
        if (editsegmentcontroller == null) goto _L6; else goto _L5
_L5:
        if (((Fragment) (editsegmentcontroller)).mHost != null && ((Fragment) (editsegmentcontroller)).mAdded)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2) goto _L7; else goto _L6
_L6:
        flag2 = false;
_L8:
        if (flag2)
        {
            _lcls2.accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        FragmentActivity fragmentactivity;
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag2 = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag2 = true;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        flag2 = false;
        if (true) goto _L8; else goto _L2
_L2:
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void onAttendeesChanged()
    {
    }

    public void onAvailabilityChanged()
    {
    }

    public void onCalendarChanged(boolean flag, boolean flag1)
    {
    }

    public void onColorChanged()
    {
    }

    public void onDismissQuickCreate()
    {
    }

    public void onEventSaved()
    {
    }

    public void onLocationChanged(boolean flag)
    {
    }

    public void onTaskAssistChanged()
    {
    }

    public void onTimeRelatedFieldChanged(boolean flag, boolean flag1)
    {
    }

    public void onVisibilityChanged()
    {
    }

}
