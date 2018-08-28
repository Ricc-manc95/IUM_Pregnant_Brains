// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.notification;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.android.calendar.api.habit.HabitReminders;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.common.dialog.SingleChoiceTextDialog;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.event.CustomNotificationBaseDialog;
import com.google.android.calendar.event.CustomNotificationSupportDialog;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.model.CalendarListHolder;
import com.google.android.calendar.newapi.model.HabitModificationsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.notification:
//            GrooveNotificationEditSegment, GrooveNotificationChoiceCreator

public class GrooveNotificationEditSegmentController extends EditSegmentController
    implements SingleChoiceDialogListener, com.google.android.calendar.event.CustomNotificationDialog.OnNotificationSetListener, GrooveNotificationEditSegment.Listener
{

    private GrooveNotificationChoiceCreator notificationChoiceCreator;
    private ReminderUtils reminderUtils;

    public GrooveNotificationEditSegmentController()
    {
    }

    private final Integer getCurrentNotification()
    {
        Object obj = ((HabitModificationsHolder)super.model).getHabitModifications().getReminders();
        if (obj == null || ((HabitReminders) (obj)).useDefaultReminders)
        {
            obj = getNotificationMinutes();
            int i;
            if (((ArrayList) (obj)).isEmpty())
            {
                i = 0;
            } else
            {
                i = ((Integer)((ArrayList) (obj)).get(0)).intValue();
            }
            return Integer.valueOf(i);
        } else
        {
            return ((HabitReminders) (obj)).overrideMinutes;
        }
    }

    private final ArrayList getNotificationMinutes()
    {
        Object obj = ((CalendarListHolder)(HabitModificationsHolder)super.model).getCalendarList().findEntry(((HabitModificationsHolder)super.model).getHabitModifications().getDescriptor().calendar.calendarId);
        ArrayList arraylist = new ArrayList();
        if (obj != null)
        {
            obj = ((CalendarListEntry) (obj)).getDefaultNotifications(1);
            for (int i = 0; i < ((List) (obj)).size(); i++)
            {
                arraylist.add(Integer.valueOf(((Notification)((List) (obj)).get(i)).minutesBefore));
            }

        }
        return arraylist;
    }

    private final void setNotification(Integer integer)
    {
        HabitReminders habitreminders = ((HabitModificationsHolder)super.model).getHabitModifications().getReminders();
        if (habitreminders == null)
        {
            habitreminders = new HabitReminders(true, Integer.valueOf(0), false, false);
        }
        if (integer == null)
        {
            ((HabitModificationsHolder)super.model).getHabitModifications().setReminders(new HabitReminders(false, null, habitreminders.enableRecommit, habitreminders.enableFollowup));
        } else
        {
            ((HabitModificationsHolder)super.model).getHabitModifications().setReminders(new HabitReminders(false, integer, habitreminders.enableRecommit, habitreminders.enableFollowup));
        }
        updateNotificationText();
    }

    private final void updateNotificationText()
    {
        Object obj = getCurrentNotification();
        if (obj == null)
        {
            GrooveNotificationEditSegment groovenotificationeditsegment = (GrooveNotificationEditSegment)super.view;
            groovenotificationeditsegment.addNotificationTile.setVisibility(0);
            groovenotificationeditsegment.notificationTile.setVisibility(8);
            return;
        } else
        {
            GrooveNotificationEditSegment groovenotificationeditsegment1 = (GrooveNotificationEditSegment)super.view;
            obj = reminderUtils.constructLabel(((Integer) (obj)).intValue(), 1, false);
            groovenotificationeditsegment1.addNotificationTile.setVisibility(8);
            groovenotificationeditsegment1.notificationTile.setVisibility(0);
            groovenotificationeditsegment1.notificationTile.setPrimaryText(new CharSequence[] {
                obj
            });
            return;
        }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (GrooveNotificationEditSegment)layoutinflater.inflate(0x7f0500d6, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onCancel()
    {
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        reminderUtils = new ReminderUtils(bundle);
        notificationChoiceCreator = new GrooveNotificationChoiceCreator(requireContext().getResources(), reminderUtils);
        bundle = super.mFragmentManager.findFragmentByTag("CustomNotificationDialog");
        if (bundle != null && (bundle instanceof CustomNotificationSupportDialog))
        {
            ((CustomNotificationSupportDialog)bundle).dialog.listener = this;
        }
    }

    public final void onCustomNotificationSet(int i, int j)
    {
        setNotification(Integer.valueOf(i));
    }

    public final void onDialogItemChosen(Object obj, int i)
    {
        Object obj1;
        obj1 = null;
        obj = (Integer)obj;
        if (!((Integer) (obj)).equals(GrooveNotificationChoiceCreator.NO_NOTIFICATION_CHOICE)) goto _L2; else goto _L1
_L1:
        setNotification(null);
_L4:
        return;
_L2:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        if (!((Integer) (obj)).equals(GrooveNotificationChoiceCreator.CUSTOM_CHOICE))
        {
            break MISSING_BLOCK_LABEL_192;
        }
        if (this == null)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        fragmentmanagerimpl = super.mFragmentManager;
        if (this != null)
        {
            if (super.mHost != null && super.mAdded)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                break; /* Loop/switch isn't completed */
            }
        }
        i = 0;
_L5:
        if (i != 0)
        {
            obj = CustomNotificationSupportDialog.newInstance(false, requireContext().getResources().getString(0x7f1302ba), true);
            ((CustomNotificationSupportDialog) (obj)).dialog.listener = this;
            super.mFragmentManager.beginTransaction().add(((Fragment) (obj)), "CustomNotificationDialog").commitAllowingStateLoss();
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (super.mHost == null)
        {
            obj = obj1;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj == null || ((Activity) (obj)).isDestroyed() || ((Activity) (obj)).isFinishing())
        {
            i = 0;
        } else
        {
            if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed())
            {
                break MISSING_BLOCK_LABEL_187;
            }
            i = 1;
        }
          goto _L5
        i = 0;
          goto _L5
        setNotification(((Integer) (obj)));
        return;
    }

    protected final void onInitialize()
    {
        Object obj = (GrooveNotificationEditSegment)super.view;
        HabitReminders habitreminders = ((HabitModificationsHolder)super.model).getHabitModifications().getReminders();
        boolean flag;
        if (habitreminders != null && (habitreminders.enableFollowup || habitreminders.enableRecommit))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = ((GrooveNotificationEditSegment) (obj)).smartNotificationSwitch;
        obj.stealth = true;
        ((NinjaSwitch) (obj)).setChecked(flag);
        obj.stealth = false;
        updateNotificationText();
    }

    public final void onNotificationClicked()
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
            Object obj = getCurrentNotification();
            ArrayList arraylist = getNotificationMinutes();
            obj = notificationChoiceCreator.createList(arraylist, ((Integer) (obj)));
            obj = SingleChoiceTextDialog.newIntegerBasedInstance(((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).labels, ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).values, ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).selectedPosition, this, -1);
            super.mFragmentManager.beginTransaction().add(((Fragment) (obj)), "SingleChoiceTextDialog").commitAllowingStateLoss();
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

    public final void onRemoveNotificationClicked()
    {
        setNotification(null);
    }

    public final void onSmartNotificationsToggled(boolean flag)
    {
        HabitReminders habitreminders = ((HabitModificationsHolder)super.model).getHabitModifications().getReminders();
        if (habitreminders == null)
        {
            habitreminders = new HabitReminders(true, Integer.valueOf(0), flag, flag);
        } else
        {
            habitreminders = new HabitReminders(habitreminders.useDefaultReminders, habitreminders.overrideMinutes, flag, flag);
        }
        ((HabitModificationsHolder)super.model).getHabitModifications().setReminders(habitreminders);
    }
}
