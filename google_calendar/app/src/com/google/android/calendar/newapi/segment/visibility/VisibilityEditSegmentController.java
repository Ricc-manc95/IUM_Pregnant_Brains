// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.visibility;

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
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.common.dialog.SingleChoiceTextDialog;
import com.google.android.calendar.newapi.model.VisibilityModification;
import com.google.android.calendar.newapi.screen.EditScreenController;
import com.google.android.calendar.newapi.screen.SegmentMap;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.segment.visibility:
//            VisibilityEditSegment

public class VisibilityEditSegmentController extends EditSegmentController
    implements SingleChoiceDialogListener, VisibilityEditSegment.Listener
{

    private ArrayList labels;
    private ArrayList values;

    public VisibilityEditSegmentController()
    {
    }

    private final void updateUi()
    {
        Object obj = super.view;
        Object obj1 = ((VisibilityModification)super.model).getAllowedVisibilityValues();
        boolean flag;
        if (obj1 == null || ((List) (obj1)).isEmpty())
        {
            flag = false;
        } else
        if (((List) (obj1)).contains(Integer.valueOf(1)) && ((List) (obj1)).contains(Integer.valueOf(0)) && ((List) (obj1)).contains(Integer.valueOf(2)))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (obj != null)
        {
            int j;
            if (flag)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            ((View) (obj)).setVisibility(j);
        }
        if (!flag)
        {
            return;
        }
        String as[] = requireContext().getResources().getStringArray(0x7f0b0058);
        labels = new ArrayList(4);
        labels.add(as[0]);
        labels.add(as[1]);
        labels.add(as[2]);
        values = new ArrayList(4);
        values.add(Integer.valueOf(1));
        values.add(Integer.valueOf(0));
        values.add(Integer.valueOf(2));
        if (((VisibilityModification)model).getAllowedVisibilityValues().contains(Integer.valueOf(3)))
        {
            labels.add(as[3]);
            values.add(Integer.valueOf(3));
        }
        as = (VisibilityEditSegment)super.view;
        int k = ((VisibilityModification)super.model).getVisibility();
        obj1 = labels;
        int i = values.indexOf(Integer.valueOf(k));
        if (i < 0)
        {
            if (k == 3)
            {
                i = 2;
            } else
            {
                i = 1;
            }
        }
        obj1 = (String)((ArrayList) (obj1)).get(i);
        ((VisibilityEditSegment) (as)).tile.setPrimaryText(new CharSequence[] {
            obj1
        });
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (VisibilityEditSegment)layoutinflater.inflate(0x7f0500f5, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        updateUi();
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        Object obj1;
        Consumer consumer;
        Iterator iterator;
        obj1 = (Integer)obj;
        ((VisibilityModification)super.model).setVisibility(((Integer) (obj1)).intValue());
        updateUi();
        obj = super.editScreenController;
        consumer = com.google.android.calendar.newapi.screen.EditScreenController..Lambda._cls6.$instance;
        iterator = ((EditScreenController) (obj)).segments.segmentControllers.values().iterator();
_L10:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        EditSegmentController editsegmentcontroller;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        editsegmentcontroller = (EditSegmentController)iterator.next();
        if (editsegmentcontroller == this || editsegmentcontroller != null && editsegmentcontroller.equals(this))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
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
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L7; else goto _L6
_L6:
        i = 0;
_L8:
        if (i != 0)
        {
            consumer.accept(editsegmentcontroller);
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (((Fragment) (editsegmentcontroller)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (editsegmentcontroller)).mHost.mActivity;
        }
        if (obj == null || ((Activity) (obj)).isDestroyed() || ((Activity) (obj)).isFinishing())
        {
            i = 0;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            i = 1;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        i = 0;
        if (true) goto _L8; else goto _L2
_L2:
        obj = (VisibilityEditSegment)super.view;
        int j = ((Integer) (obj1)).intValue();
        obj1 = labels;
        i = values.indexOf(Integer.valueOf(j));
        if (i < 0)
        {
            if (j == 3)
            {
                i = 2;
            } else
            {
                i = 1;
            }
        }
        obj1 = (String)((ArrayList) (obj1)).get(i);
        ((VisibilityEditSegment) (obj)).announceForAccessibility(requireContext().getResources().getString(0x7f13001e, new Object[] {
            obj1
        }));
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected final void onInitialize()
    {
        updateUi();
    }

    public final void onVisibilityClicked()
    {
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
_L7:
        if (!flag)
        {
            return;
        }
        break; /* Loop/switch isn't completed */
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
        if (true) goto _L7; else goto _L6
_L6:
        ((VisibilityEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f13001a));
        Object obj = labels;
        ArrayList arraylist = values;
        int j = ((VisibilityModification)super.model).getVisibility();
        int i = values.indexOf(Integer.valueOf(j));
        if (i < 0)
        {
            if (j == 3)
            {
                i = 2;
            } else
            {
                i = 1;
            }
        }
        obj = SingleChoiceTextDialog.newIntegerBasedInstance(((ArrayList) (obj)), arraylist, i, this, -1);
        super.mFragmentManager.beginTransaction().add(((Fragment) (obj)), "SingleChoiceTextDialog").commitAllowingStateLoss();
        return;
    }
}
