// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.calendar.newapi.model.AccountListHolder;
import com.google.android.calendar.newapi.model.AccountModification;
import com.google.android.calendar.newapi.model.ColorHolder;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.SettingsMapHolder;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TileView;
import java.util.RandomAccess;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            CalendarEditSegmentController, CalendarDialog, CalendarEditSegment, AutoValue_UiCalendarUtils_UiReminderCalendar

public class ReminderCalendarEditSegmentController extends CalendarEditSegmentController
{

    public String calendarName;
    private UiCalendarUtils.CalendarPickerFactory calendarPickerFactory;

    public ReminderCalendarEditSegmentController()
    {
        calendarPickerFactory = new UiCalendarUtils.CalendarPickerFactory();
    }

    protected final DialogFragment createDialog()
    {
        Context context = getContext();
        Object obj = ((AccountListHolder)(EditScreenModel)super.model).getAccounts();
        Object obj1 = ((SettingsMapHolder)(EditScreenModel)super.model).getSettingsMap();
        obj1 = new UiCalendarUtils.CalendarPickerFactory..Lambda._cls1(context.getString(0x7f1303e2), ((com.google.android.calendar.newapi.model.SettingsMap) (obj1)));
        if (obj instanceof RandomAccess)
        {
            obj = new com.google.common.collect.Lists.TransformingRandomAccessList(((java.util.List) (obj)), ((com.google.common.base.Function) (obj1)));
        } else
        {
            obj = new com.google.common.collect.Lists.TransformingSequentialList(((java.util.List) (obj)), ((com.google.common.base.Function) (obj1)));
        }
        return CalendarDialog.newInstance(context, ((java.util.List) (obj)), this, -1);
    }

    public final volatile View createView(LayoutInflater layoutinflater)
    {
        return createView(layoutinflater);
    }

    public final CalendarEditSegment createView(LayoutInflater layoutinflater)
    {
        layoutinflater = super.createView(layoutinflater);
        ((CalendarEditSegment) (layoutinflater)).textView.setIconDrawable(0x7f020227);
        return layoutinflater;
    }

    protected final UiCalendarUtils.UiCalendar getCurrentCalendar()
    {
        String s = calendarName;
        Account account = ((EditScreenModel)super.model).getAccount();
        int i = ((ColorHolder)(EditScreenModel)super.model).getColor(getContext());
        return new AutoValue_UiCalendarUtils_UiReminderCalendar(s, account.name, i, account);
    }

    protected final Iterable getUiCalendars()
    {
        java.util.List list = ((AccountListHolder)(EditScreenModel)super.model).getAccounts();
        class .Lambda._cls0
            implements Function
        {

            private final ReminderCalendarEditSegmentController arg$1;

            public final Object apply(Object obj)
            {
                Object obj1 = arg$1;
                obj = (Account)obj;
                String s = ((ReminderCalendarEditSegmentController) (obj1)).calendarName;
                obj1 = (GoogleSettings)(Settings)((SettingsMapHolder)(EditScreenModel)((SegmentController) (obj1)).model).getSettingsMap().settingsMap.get(obj);
                int i;
                if (obj1 == null)
                {
                    i = CalendarApi.getColorFactory().defaultTaskColor().getValue();
                } else
                {
                    i = ((GoogleSettings) (obj1)).getTaskColor().getValue();
                }
                return new AutoValue_UiCalendarUtils_UiReminderCalendar(s, ((Account) (obj)).name, i, ((Account) (obj)));
            }

            .Lambda._cls0()
            {
                arg$1 = ReminderCalendarEditSegmentController.this;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0();
        if (list instanceof RandomAccess)
        {
            return new com.google.common.collect.Lists.TransformingRandomAccessList(list, _lcls0);
        } else
        {
            return new com.google.common.collect.Lists.TransformingSequentialList(list, _lcls0);
        }
    }

    protected final void onCalendarChosen(UiCalendarUtils.UiCalendar uicalendar)
    {
        uicalendar = (UiCalendarUtils.UiReminderCalendar)uicalendar;
        ((AccountModification)(EditScreenModel)super.model).setAccount(uicalendar.value());
        updateUi();
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        calendarName = requireContext().getResources().getString(0x7f1303e2);
    }

    protected final boolean showSegment()
    {
        return ((EditScreenModel)super.model).isNew() && super.showSegment();
    }
}
