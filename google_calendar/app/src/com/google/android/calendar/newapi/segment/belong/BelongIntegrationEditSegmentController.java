// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.belong;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.belong.BelongUtils;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.newapi.model.HabitModificationsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.belong:
//            BelongIntegrationEditSegment

public class BelongIntegrationEditSegmentController extends EditSegmentController
    implements BelongIntegrationEditSegment.Listener
{

    public BelongIntegrationEditSegmentController()
    {
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (BelongIntegrationEditSegment)layoutinflater.inflate(0x7f0500c2, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    protected final void onInitialize()
    {
        Object obj = (BelongIntegrationEditSegment)super.view;
        boolean flag = ((HabitModificationsHolder)super.model).getHabitModifications().isFitIntegrationEnabled();
        obj = ((BelongIntegrationEditSegment) (obj)).mSwitch;
        obj.stealth = true;
        ((NinjaSwitch) (obj)).setChecked(flag);
        obj.stealth = false;
    }

    public final void onIntegrationToggled(boolean flag)
    {
        HabitModifications habitmodifications = ((HabitModificationsHolder)super.model).getHabitModifications();
        byte byte0;
        if (flag)
        {
            byte0 = 20;
        } else
        {
            byte0 = 10;
        }
        habitmodifications.setFitIntegrationStatus(byte0);
    }

    public final void onLearnMoreClicked()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        BelongUtils.onLearnMoreLinkClicked(((android.content.Context) (obj)), "edit_screen");
    }
}
