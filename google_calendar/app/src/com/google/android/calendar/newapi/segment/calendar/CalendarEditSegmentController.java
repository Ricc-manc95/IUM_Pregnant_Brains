// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.calendar.common.dialog.SingleChoiceDialog;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.newapi.screen.EditScreenController;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            CalendarEditSegment

public abstract class CalendarEditSegmentController extends EditSegmentController
    implements SingleChoiceDialogListener, CalendarEditSegment.Listener
{

    public CalendarEditSegmentController()
    {
    }

    protected abstract DialogFragment createDialog();

    public volatile View createView(LayoutInflater layoutinflater)
    {
        return createView(layoutinflater);
    }

    public CalendarEditSegment createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (CalendarEditSegment)layoutinflater.inflate(0x7f0500c3, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    protected abstract UiCalendarUtils.UiCalendar getCurrentCalendar();

    protected abstract Iterable getUiCalendars();

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        updateUi();
    }

    protected abstract void onCalendarChosen(UiCalendarUtils.UiCalendar uicalendar);

    public final void onCalendarClicked()
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl1;
        if (this == null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        fragmentmanagerimpl1 = super.mFragmentManager;
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
            android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
            DialogFragment dialogfragment = createDialog();
            String s = SingleChoiceDialog.TAG;
            fragmentmanagerimpl.beginTransaction().add(dialogfragment, s).commitAllowingStateLoss();
            ((CalendarEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f130016));
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
            if (fragmentmanagerimpl1 == null || fragmentmanagerimpl1.isDestroyed())
            {
                break MISSING_BLOCK_LABEL_114;
            }
            flag = true;
        }
          goto _L4
        flag = false;
          goto _L4
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        Object obj1 = (UiCalendarUtils.UiCalendar)obj;
        onCalendarChosen(((UiCalendarUtils.UiCalendar) (obj1)));
        super.editScreenController.notifyCalendarChanged(this, true, true);
        obj = ((UiCalendarUtils.UiCalendar) (obj1)).displayName();
        obj1 = ((UiCalendarUtils.UiCalendar) (obj1)).accountName();
        obj = (new StringBuilder(String.valueOf(obj).length() + 2 + String.valueOf(obj1).length())).append(((String) (obj))).append(", ").append(((String) (obj1))).toString();
        ((CalendarEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f130003, new Object[] {
            obj
        }));
        updateUi();
    }

    protected final void onInitialize()
    {
        updateUi();
    }

    protected boolean showSegment()
    {
        Object obj = getUiCalendars();
        int i;
        if (obj instanceof Collection)
        {
            i = ((Collection)obj).size();
        } else
        {
            obj = ((Iterable) (obj)).iterator();
            long l;
            for (l = 0L; ((Iterator) (obj)).hasNext(); l++)
            {
                ((Iterator) (obj)).next();
            }

            if (l > 0x7fffffffL)
            {
                i = 0x7fffffff;
            } else
            if (l < 0xffffffff80000000L)
            {
                i = 0x80000000;
            } else
            {
                i = (int)l;
            }
        }
        return i > 1;
    }

    protected final void updateUi()
    {
        Object obj = super.view;
        boolean flag = showSegment();
        if (obj != null)
        {
            String s;
            Object obj1;
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            ((View) (obj)).setVisibility(i);
        }
        obj1 = getCurrentCalendar();
        if (obj1 != null)
        {
            obj = (CalendarEditSegment)super.view;
            s = ((UiCalendarUtils.UiCalendar) (obj1)).displayName();
            obj1 = ((UiCalendarUtils.UiCalendar) (obj1)).accountName();
            ((CalendarEditSegment) (obj)).textView.setPrimaryText(new CharSequence[] {
                s
            });
            ((CalendarEditSegment) (obj)).textView.setSecondaryText(new CharSequence[] {
                obj1
            });
        }
    }
}
