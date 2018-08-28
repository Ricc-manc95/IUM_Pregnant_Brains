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
import android.widget.LinearLayout;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.api.event.notification.NotificationModifications;
import com.google.android.calendar.api.event.notification.NotificationPermissions;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;
import com.google.android.calendar.api.settings.SettingsModifications;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.common.dialog.SingleChoiceTextDialog;
import com.google.android.calendar.event.CustomNotificationBaseDialog;
import com.google.android.calendar.event.CustomNotificationSupportDialog;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.newapi.logging.EventEditLogMetrics;
import com.google.android.calendar.newapi.model.AccountHolder;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.edit.EventEditLogMetricsHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.model.edit.SettingsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.notification:
//            EventNotificationChoiceCreator, NotificationList, EventNotificationEditSegment

public class EventNotificationEditSegmentController extends EditSegmentController
    implements SingleChoiceDialogListener, com.google.android.calendar.event.CustomNotificationDialog.OnNotificationSetListener, EventNotificationEditSegment.Listener
{

    private boolean allDay;
    private int customRequestCode;
    private String defaultAllowedNotifications;
    private String defaultExchangeAllowedNotifications;
    private SettingsClient mSettingsClient;
    private NotificationList notificationList;

    public EventNotificationEditSegmentController()
    {
        mSettingsClient = CalendarApi.Settings;
        customRequestCode = -1;
    }

    private final List getDefaultNotifications(boolean flag)
    {
        CalendarListEntry calendarlistentry = ((EventModificationsHolder)super.model).getEventModifications().getCalendarListEntry();
        byte byte0;
        if (flag)
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        return new ArrayList(calendarlistentry.getDefaultNotifications(byte0));
    }

    private final void onDialogItemChosen(Notification notification, int i)
    {
        if (!EventNotificationChoiceCreator.isCustomNotification(notification)) goto _L2; else goto _L1
_L1:
        showCustomNotificationDialog(i);
_L4:
        return;
_L2:
        Object obj;
        ArrayList arraylist;
        boolean flag;
        ((EventEditLogMetricsHolder)(EventModificationsHolder)super.model).getLogMetrics().didChangeNotification = true;
        obj = notificationList;
        boolean flag2;
        if (((NotificationList) (obj)).allDayEvent)
        {
            obj = ((NotificationList) (obj)).allDayNotifications;
        } else
        {
            obj = ((NotificationList) (obj)).timedNotifications;
        }
        arraylist = new ArrayList(((Collection) (obj)));
        if (notification == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (i != -1)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag2 = arraylist.contains(notification);
        if (flag || flag2) goto _L4; else goto _L3
_L3:
        obj = notificationList;
        if (((NotificationList) (obj)).allDayEvent)
        {
            obj = ((NotificationList) (obj)).allDayNotifications;
        } else
        {
            obj = ((NotificationList) (obj)).timedNotifications;
        }
        ((List) (obj)).add(notification);
        obj = (EventNotificationEditSegment)super.view;
        ((EventNotificationEditSegment) (obj)).addNotificationStealthy(notification);
        notification = ((EventNotificationEditSegment) (obj)).reminderUtils.constructLabel(notification.minutesBefore, notification.method, ((EventNotificationEditSegment) (obj)).allDay);
        ((EventNotificationEditSegment) (obj)).announceForAccessibility(((EventNotificationEditSegment) (obj)).getResources().getString(0x7f130001, new Object[] {
            notification
        }));
        writeEventNotifications();
        return;
        if (i >= arraylist.size()) goto _L4; else goto _L5
_L5:
        Notification notification1 = (Notification)arraylist.get(i);
        boolean flag1;
        if (arraylist.contains(notification) && !notification1.equals(notification))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag || flag1)
        {
            notification = notificationList;
            if (((NotificationList) (notification)).allDayEvent)
            {
                notification = ((NotificationList) (notification)).allDayNotifications;
            } else
            {
                notification = ((NotificationList) (notification)).timedNotifications;
            }
            notification.remove(notification1);
            ((EventNotificationEditSegment)super.view).removeNotification(i);
        } else
        {
            Object obj1 = notificationList;
            TextTileView texttileview;
            if (((NotificationList) (obj1)).allDayEvent)
            {
                obj1 = ((NotificationList) (obj1)).allDayNotifications;
            } else
            {
                obj1 = ((NotificationList) (obj1)).timedNotifications;
            }
            ((List) (obj1)).set(i, notification);
            obj1 = (EventNotificationEditSegment)super.view;
            texttileview = (TextTileView)((EventNotificationEditSegment) (obj1)).notificationList.getChildAt(i);
            texttileview.setPrimaryText(new CharSequence[] {
                ((EventNotificationEditSegment) (obj1)).reminderUtils.constructLabel(notification.minutesBefore, notification.method, ((EventNotificationEditSegment) (obj1)).allDay)
            });
            texttileview.setTag(notification);
            notification = ((EventNotificationEditSegment) (obj1)).reminderUtils.constructLabel(notification.minutesBefore, notification.method, ((EventNotificationEditSegment) (obj1)).allDay);
            ((EventNotificationEditSegment) (obj1)).announceForAccessibility(((EventNotificationEditSegment) (obj1)).getResources().getString(0x7f130004, new Object[] {
                notification
            }));
        }
        writeEventNotifications();
        return;
    }

    private final void showChooseNotificationDialog(Notification notification, int i)
    {
        Object obj;
        Object obj1;
        Object obj2;
        int j;
        boolean flag;
        obj1 = null;
        flag = true;
        ((EventNotificationEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f130019));
        HashSet hashset;
        if (i >= 0)
        {
            j = i;
        } else
        {
            j = -1;
        }
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj2 = new EventNotificationChoiceCreator(((Context) (obj)), ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent());
        hashset = new HashSet();
        hashset.addAll(getDefaultNotifications(((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent()));
        if (((SettingsHolder)(EventModificationsHolder)super.model).getSettings() != null)
        {
            obj = ((SettingsHolder)(EventModificationsHolder)super.model).getSettings();
            if (((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent())
            {
                i = 2;
            } else
            {
                i = 1;
            }
            obj = ((Settings) (obj)).getPreferredNotifications(i);
            if (AccountUtil.isExchangeAccount(((EventModificationsHolder)super.model).getEventModifications().getCalendar().account))
            {
                i = 5;
            } else
            {
                i = 3;
            }
            hashset.addAll(((List) (obj)).subList(0, Math.min(i, ((List) (obj)).size())));
        }
        obj = notificationList;
        if (((NotificationList) (obj)).allDayEvent)
        {
            obj = ((NotificationList) (obj)).allDayNotifications;
        } else
        {
            obj = ((NotificationList) (obj)).timedNotifications;
        }
        hashset.removeAll(((Collection) (obj)));
        obj = new ArrayList(hashset);
        if (!((ArrayList) (obj)).isEmpty() || j != -1) goto _L2; else goto _L1
_L1:
        showCustomNotificationDialog(-1);
_L8:
        return;
_L2:
        int k;
        notification = ((EventNotificationChoiceCreator) (obj2)).createList(((ArrayList) (obj)), notification);
        obj = ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (notification)).labels;
        obj2 = ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (notification)).values;
        k = ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (notification)).selectedPosition;
        if (this == null) goto _L4; else goto _L3
_L3:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L6; else goto _L5
_L5:
        if (super.mHost != null && super.mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L7; else goto _L6
_L6:
        i = 0;
_L9:
        if (i != 0)
        {
            notification = SingleChoiceTextDialog.newParcelableBaseInstance(((ArrayList) (obj)), ((ArrayList) (obj2)), k, this, j);
            super.mFragmentManager.beginTransaction().add(notification, "SingleChoiceTextDialog").commitAllowingStateLoss();
            return;
        }
          goto _L8
_L7:
label0:
        {
            if (super.mHost == null)
            {
                notification = obj1;
            } else
            {
                notification = (FragmentActivity)super.mHost.mActivity;
            }
            if (notification != null && !notification.isDestroyed() && !notification.isFinishing())
            {
                break label0;
            }
            i = 0;
        }
          goto _L9
        if (fragmentmanagerimpl == null) goto _L4; else goto _L10
_L10:
        i = ((flag) ? 1 : 0);
        if (!fragmentmanagerimpl.isDestroyed()) goto _L9; else goto _L4
_L4:
        i = 0;
          goto _L9
    }

    private final void showCustomNotificationDialog(int i)
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
        customRequestCode = i;
        Object obj;
        boolean flag1;
        boolean flag2;
        if (AccountUtil.isExchangeAccount(((EventModificationsHolder)super.model).getEventModifications().getCalendar().account))
        {
            obj = defaultExchangeAllowedNotifications;
        } else
        {
            obj = defaultAllowedNotifications;
        }
        flag2 = ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent();
        if (!AccountUtil.isExchangeAccount(((EventModificationsHolder)super.model).getEventModifications().getCalendar().account))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj = CustomNotificationSupportDialog.newInstance(flag2, ((String) (obj)), flag1);
        ((CustomNotificationSupportDialog) (obj)).dialog.listener = this;
        super.mFragmentManager.beginTransaction().add(((Fragment) (obj)), "CustomNotificationDialog").commitAllowingStateLoss();
        return;
    }

    private final void updateAllNotificationsInUi()
    {
        boolean flag1 = true;
        int j = 0;
        View view;
        boolean flag;
        if (!((PermissionHolder)(EventModificationsHolder)super.model).getPermissions().getNotificationPermissions().isReadOnly())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        view = super.view;
        if (view != null)
        {
            if (!flag)
            {
                j = 8;
            }
            view.setVisibility(j);
        }
        if (flag)
        {
            ((EventNotificationEditSegment)super.view).allDay = ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent();
            EventNotificationEditSegment eventnotificationeditsegment = (EventNotificationEditSegment)super.view;
            Object obj = notificationList;
            int i;
            if (((NotificationList) (obj)).allDayEvent)
            {
                obj = ((NotificationList) (obj)).allDayNotifications;
            } else
            {
                obj = ((NotificationList) (obj)).timedNotifications;
            }
            if (AccountUtil.isExchangeAccount(((AccountHolder)(EventModificationsHolder)super.model).getAccount()))
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 5;
            }
            eventnotificationeditsegment.maxNotifications = i;
            for (i = eventnotificationeditsegment.notificationList.getChildCount() - 2; i >= 0; i--)
            {
                eventnotificationeditsegment.notificationList.removeViewAt(i);
            }

            eventnotificationeditsegment.updateAddNotificationLabel();
            obj = ((List) (obj)).iterator();
            while (((Iterator) (obj)).hasNext()) 
            {
                eventnotificationeditsegment.addNotificationStealthy((Notification)((Iterator) (obj)).next());
            }
        }
    }

    private final void writeEventNotifications()
    {
        Object obj;
        NotificationModifications notificationmodifications;
        boolean flag;
        notificationmodifications = ((EventModificationsHolder)super.model).getEventModifications().getNotificationModifications();
        Object obj1 = notificationList;
        if (((NotificationList) (obj1)).allDayEvent)
        {
            obj = ((NotificationList) (obj1)).allDayNotifications;
        } else
        {
            obj = ((NotificationList) (obj1)).timedNotifications;
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        if (((NotificationList) (obj1)).allDayEvent)
        {
            obj1 = ((NotificationList) (obj1)).defaultAllDayNotifications;
        } else
        {
            obj1 = ((NotificationList) (obj1)).defaultTimedNotifications;
        }
        if (((List) (obj)).size() != ((List) (obj1)).size() || !((List) (obj)).containsAll(((Collection) (obj1))))
        {
            break MISSING_BLOCK_LABEL_113;
        }
        flag = true;
_L1:
        if (flag)
        {
            obj = null;
        } else
        {
            obj = ImmutableList.copyOf(((Collection) (obj)));
        }
        if (obj == null)
        {
            notificationmodifications.useCalendarDefaults();
            return;
        } else
        {
            notificationmodifications.setNotifications(((List) (obj)));
            return;
        }
        flag = false;
          goto _L1
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (EventNotificationEditSegment)layoutinflater.inflate(0x7f0500cf, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onAddNotificationClicked()
    {
        showChooseNotificationDialog(null, -1);
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        NotificationList notificationlist = notificationList;
        List list = getDefaultNotifications(true);
        List list1 = getDefaultNotifications(false);
        notificationlist.defaultAllDayNotifications = ImmutableList.copyOf(list);
        notificationlist.defaultTimedNotifications = ImmutableList.copyOf(list1);
        notificationlist = notificationList;
        notificationlist.timedNotifications.clear();
        notificationlist.allDayNotifications.clear();
        notificationlist.timedNotifications.addAll(notificationlist.defaultTimedNotifications);
        notificationlist.allDayNotifications.addAll(notificationlist.defaultAllDayNotifications);
        writeEventNotifications();
        updateAllNotificationsInUi();
    }

    public final void onCancel()
    {
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            notificationList = (NotificationList)bundle.getParcelable("INSTANCE_NOTIFICATIONS");
        }
        bundle = super.mFragmentManager.findFragmentByTag("CustomNotificationDialog");
        if (bundle instanceof CustomNotificationSupportDialog)
        {
            ((CustomNotificationSupportDialog)bundle).dialog.listener = this;
        }
    }

    public final void onCustomNotificationSet(int i, int j)
    {
        Notification notification = new Notification(j, i);
        onDialogItemChosen(notification, customRequestCode);
        if (((SettingsHolder)(EventModificationsHolder)super.model).getSettings() != null)
        {
            SettingsModifications settingsmodifications = CalendarApi.SettingsFactory.modifySettings(((SettingsHolder)(EventModificationsHolder)super.model).getSettings());
            if (((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent())
            {
                i = 2;
            } else
            {
                i = 1;
            }
            settingsmodifications.addPreferredNotification(i, notification);
            mSettingsClient.update(settingsmodifications);
        }
    }

    public final volatile void onDialogItemChosen(Object obj, int i)
    {
        onDialogItemChosen((Notification)obj, i);
    }

    protected final void onInitialize()
    {
        defaultExchangeAllowedNotifications = requireContext().getResources().getString(0x7f1301a7);
        defaultAllowedNotifications = requireContext().getResources().getString(0x7f1301a6);
        allDay = ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent();
        if (notificationList == null)
        {
            notificationList = new NotificationList();
        }
        NotificationList notificationlist = notificationList;
        Object obj2 = ((EventModificationsHolder)super.model).getEventModifications().getNotifications();
        boolean flag = ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent();
        Object obj = getDefaultNotifications(false);
        Object obj1 = getDefaultNotifications(true);
        notificationlist.allDayEvent = flag;
        notificationlist.defaultAllDayNotifications = ImmutableList.copyOf(((Collection) (obj1)));
        notificationlist.defaultTimedNotifications = ImmutableList.copyOf(((Collection) (obj)));
        if (notificationlist.timedNotifications == null)
        {
            notificationlist.timedNotifications = new ArrayList(notificationlist.defaultTimedNotifications);
        }
        if (notificationlist.allDayNotifications == null)
        {
            notificationlist.allDayNotifications = new ArrayList(notificationlist.defaultAllDayNotifications);
        }
        if (notificationlist.allDayEvent)
        {
            obj = notificationlist.defaultAllDayNotifications;
        } else
        {
            obj = notificationlist.defaultTimedNotifications;
        }
        if (notificationlist.allDayEvent)
        {
            obj1 = notificationlist.allDayNotifications;
        } else
        {
            obj1 = notificationlist.timedNotifications;
        }
        ((List) (obj1)).clear();
        if (notificationlist.allDayEvent)
        {
            obj1 = notificationlist.allDayNotifications;
        } else
        {
            obj1 = notificationlist.timedNotifications;
        }
        if (obj2 == null)
        {
            obj2 = Absent.INSTANCE;
        } else
        {
            obj2 = new Present(obj2);
        }
        ((List) (obj1)).addAll((Collection)((Optional) (obj2)).or(obj));
        updateAllNotificationsInUi();
    }

    public final void onNotificationClicked(Notification notification, int i)
    {
        showChooseNotificationDialog(notification, i);
    }

    public final void onRemoveButtonClicked(Notification notification)
    {
        Object obj = notificationList;
        int i;
        if (((NotificationList) (obj)).allDayEvent)
        {
            obj = ((NotificationList) (obj)).allDayNotifications;
        } else
        {
            obj = ((NotificationList) (obj)).timedNotifications;
        }
        i = ((List) (obj)).indexOf(notification);
        obj = notificationList;
        if (((NotificationList) (obj)).allDayEvent)
        {
            obj = ((NotificationList) (obj)).allDayNotifications;
        } else
        {
            obj = ((NotificationList) (obj)).timedNotifications;
        }
        ((List) (obj)).remove(notification);
        ((EventNotificationEditSegment)super.view).removeNotification(i);
        writeEventNotifications();
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("INSTANCE_NOTIFICATIONS", notificationList);
        super.onSaveInstanceState(bundle);
    }

    public final void onTimeRelatedFieldChanged(boolean flag, boolean flag1)
    {
        boolean flag2;
        if (allDay != ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent())
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2)
        {
            return;
        } else
        {
            allDay = ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent();
            notificationList.allDayEvent = allDay;
            writeEventNotifications();
            updateAllNotificationsInUi();
            return;
        }
    }
}
