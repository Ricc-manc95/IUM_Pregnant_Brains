// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.availability;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.common.dialog.SingleChoiceTextDialog;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.availability:
//            AvailabilityUtils, AvailabilityEditSegment

public class AvailabilityEditSegmentController extends EditSegmentController
    implements SingleChoiceDialogListener, AvailabilityEditSegment.Listener
{

    private ArrayList labels;
    private ArrayList values;

    public AvailabilityEditSegmentController()
    {
    }

    private static int availabilityToIndex(int i)
    {
        switch (i)
        {
        case 0: // '\0'
        default:
            return 0;

        case 1: // '\001'
            return 1;
        }
    }

    private final void updateUi()
    {
        View view = super.view;
        if (((EventModificationsHolder)super.model).getEventModifications().getCalendarListEntry().isPotentiallyShared()) goto _L2; else goto _L1
_L1:
        if (((EventModificationsHolder)super.model).getEventModifications().isNewEvent()) goto _L4; else goto _L3
_L3:
        Event event = ((EventModificationsHolder)super.model).getEventModifications().getOriginal();
        if (event.getAvailability() != AvailabilityUtils.getDefaultAvailability(event)) goto _L2; else goto _L4
_L4:
        boolean flag = false;
_L5:
        if (view != null)
        {
            List list;
            int j;
            if (flag)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            view.setVisibility(j);
        }
        if (!flag)
        {
            return;
        } else
        {
            AvailabilityEditSegment availabilityeditsegment = (AvailabilityEditSegment)super.view;
            int i = ((EventModificationsHolder)super.model).getEventModifications().getAvailability();
            String s = (String)labels.get(availabilityToIndex(i));
            availabilityeditsegment.tile.setPrimaryText(new CharSequence[] {
                s
            });
            return;
        }
_L2:
label0:
        {
            if (((EventModificationsHolder)model).getEventModifications().getVisibility() == 3)
            {
                break label0;
            }
            list = ((PermissionHolder)(EventModificationsHolder)super.model).getPermissions().getAllowedAvailabilityValues();
            if (list == null || list.isEmpty())
            {
                flag = false;
            } else
            {
                if (!list.contains(Integer.valueOf(1)) || !list.contains(Integer.valueOf(0)))
                {
                    break label0;
                }
                flag = true;
            }
        }
          goto _L5
        flag = false;
          goto _L5
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (AvailabilityEditSegment)layoutinflater.inflate(0x7f0500c1, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onAvailabilityChanged()
    {
        updateUi();
    }

    public final void onAvailabilityClicked()
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        if (this == null)
        {
            break MISSING_BLOCK_LABEL_107;
        }
        fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L3; else goto _L2
_L2:
        flag = false;
_L4:
        FragmentActivity fragmentactivity;
        if (!flag)
        {
            return;
        } else
        {
            SingleChoiceTextDialog singlechoicetextdialog = SingleChoiceTextDialog.newIntegerBasedInstance(labels, values, availabilityToIndex(((EventModificationsHolder)super.model).getEventModifications().getAvailability()), this, -1);
            super.mFragmentManager.beginTransaction().add(singlechoicetextdialog, "SingleChoiceTextDialog").commitAllowingStateLoss();
            ((AvailabilityEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f130015));
            return;
        }
_L3:
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
        } else
        {
            if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed())
            {
                break MISSING_BLOCK_LABEL_107;
            }
            flag = true;
        }
          goto _L4
        flag = false;
          goto _L4
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        updateUi();
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        Object obj1 = (Integer)obj;
        ((EventModificationsHolder)super.model).getEventModifications().setAvailability(((Integer) (obj1)).intValue());
        updateUi();
        obj = (AvailabilityEditSegment)super.view;
        i = ((Integer) (obj1)).intValue();
        obj1 = (String)labels.get(availabilityToIndex(i));
        ((AvailabilityEditSegment) (obj)).announceForAccessibility(requireContext().getResources().getString(0x7f130002, new Object[] {
            obj1
        }));
    }

    protected final void onInitialize()
    {
        String as[] = requireContext().getResources().getStringArray(0x7f0b0011);
        labels = new ArrayList(Arrays.asList(new String[] {
            as[0], as[1]
        }));
        values = new ArrayList();
        values.add(Integer.valueOf(0));
        values.add(Integer.valueOf(1));
        updateUi();
    }

    public final void onVisibilityChanged()
    {
        updateUi();
    }
}
