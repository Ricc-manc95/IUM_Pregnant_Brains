// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.timezone;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import com.android.timezonepicker.fullscreen.TimeZonePickerHelper;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.TimeZoneHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.newapi.segment.time.TimeUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.time.CalendarUtils;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.newapi.segment.timezone:
//            TimeZoneEditSegment

public class TimeZoneEditSegmentController extends EditSegmentController
    implements TimeZoneEditSegment.Listener
{

    public TimeZoneEditSegmentController()
    {
    }

    private final void updateUi()
    {
        Object obj2;
        boolean flag;
        obj2 = null;
        if (!((PermissionHolder)(EventModificationsHolder)super.model).getPermissions().canModifyTimeZone())
        {
            break MISSING_BLOCK_LABEL_94;
        }
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (!TimeUtils.shouldExpandMoreTimeOptions(((Context) (obj)), (EventModificationsHolder)super.model))
        {
            break MISSING_BLOCK_LABEL_94;
        }
        flag = true;
_L1:
        obj = super.view;
        if (obj != null)
        {
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
        if (!flag)
        {
            return;
        }
        break MISSING_BLOCK_LABEL_107;
        flag = false;
          goto _L1
        TimeZoneHolder timezoneholder = (TimeZoneHolder)(EventModificationsHolder)super.model;
        Object obj1;
        boolean flag1;
        if (super.mHost == null)
        {
            obj1 = obj2;
        } else
        {
            obj1 = (FragmentActivity)super.mHost.mActivity;
        }
        obj2 = TimeZone.getTimeZone(timezoneholder.getTimeZoneId(((Context) (obj1))));
        flag1 = ((TimeZone) (obj2)).inDaylightTime(new Date(((EventModificationsHolder)super.model).getEventModifications().getStartMillis()));
        obj1 = (TimeZoneEditSegment)super.view;
        obj2 = StringUtils.capitalizeStandalone(((TimeZone) (obj2)).getDisplayName(flag1, 1), Locale.getDefault());
        ((TimeZoneEditSegment) (obj1)).tile.setPrimaryText(new CharSequence[] {
            obj2
        });
        return;
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (TimeZoneEditSegment)layoutinflater.inflate(0x7f0500ef, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onActivityResult(int i, int j, Intent intent)
    {
        Object obj = null;
        if (i == 0)
        {
            intent = TimeZonePickerHelper.processResultsIntent(getContext(), j, intent);
            if (intent.timeZoneWasSelected())
            {
                String s = intent.getId();
                String s1 = intent.getName();
                long l = ((EventModificationsHolder)super.model).getEventModifications().getStartMillis();
                TimeZoneHolder timezoneholder = (TimeZoneHolder)(EventModificationsHolder)super.model;
                if (super.mHost == null)
                {
                    intent = null;
                } else
                {
                    intent = (FragmentActivity)super.mHost.mActivity;
                }
                intent = CalendarUtils.createTimeInNewTimeZoneRetainingFields(l, TimeZone.getTimeZone(timezoneholder.getTimeZoneId(intent)), TimeZone.getTimeZone(s));
                ((EventModificationsHolder)super.model).getEventModifications().setStartMillis(intent.getTimeInMillis());
                l = ((EventModificationsHolder)super.model).getEventModifications().getEndMillis();
                timezoneholder = (TimeZoneHolder)(EventModificationsHolder)super.model;
                if (super.mHost == null)
                {
                    intent = obj;
                } else
                {
                    intent = (FragmentActivity)super.mHost.mActivity;
                }
                intent = CalendarUtils.createTimeInNewTimeZoneRetainingFields(l, TimeZone.getTimeZone(timezoneholder.getTimeZoneId(intent)), TimeZone.getTimeZone(s));
                ((EventModificationsHolder)super.model).getEventModifications().setEndMillis(intent.getTimeInMillis());
                ((EventModificationsHolder)super.model).getEventModifications().setTimeZoneId(s);
                notifyTimeChanged(false, false);
                updateUi();
                ((TimeZoneEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f13001d, new Object[] {
                    s1
                }));
            }
            return;
        } else
        {
            super.onActivityResult(i, j, intent);
            return;
        }
    }

    public final void onInitialize()
    {
        updateUi();
    }

    public final void onTimeRelatedFieldChanged(boolean flag, boolean flag1)
    {
        updateUi();
    }

    public final void onTimeZoneButtonClicked()
    {
        startActivityForResult(TimeZonePickerHelper.createIntent(getContext(), Integer.valueOf(((EventModificationsHolder)super.model).getEventModifications().getColor().getValue()), Long.valueOf(((EventModificationsHolder)super.model).getEventModifications().getStartMillis()), ((EventModificationsHolder)super.model).getEventModifications().getTimeZoneId()), 0);
    }
}
