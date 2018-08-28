// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.GooglePrivateData;
import com.google.android.calendar.api.event.attachment.AttachmentModifications;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.api.event.conference.ConferenceDataModifications;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.habit.HabitInstanceModifications;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.List;

public final class GuestNotificationDialogUtils
{

    public static String getGuestNotificationDialogStringForDeletion(Event event, Context context)
    {
        String s = context.getString(0x7f1302d7);
        GooglePrivateData googleprivatedata = event.getGooglePrivateData();
        boolean flag;
        if (googleprivatedata != null && googleprivatedata.getGuestNotification() != com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return s;
        }
        event = event.getGooglePrivateData();
        if (event != null && event.getGuestNotification() == com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return context.getString(0x7f1302d5);
        } else
        {
            return context.getString(0x7f1302de, new Object[] {
                s
            });
        }
    }

    public static boolean hasModifiedGuests(EventModifications eventmodifications)
    {
        List list = removeRooms(eventmodifications.getAttendees());
        for (eventmodifications = removeRooms(eventmodifications.getAttendeeModifications().getOriginal()); list.size() != eventmodifications.size() || !list.containsAll(eventmodifications);)
        {
            return true;
        }

        return false;
    }

    public static boolean hasNewGuests(EventModifications eventmodifications)
    {
        List list = removeRooms(eventmodifications.getAttendees());
        return !eventmodifications.getAttendeeModifications().getOriginal().containsAll(list);
    }

    public static boolean hasRemovedGuests(EventModifications eventmodifications)
    {
        List list = removeRooms(eventmodifications.getAttendeeModifications().getOriginal());
        return !eventmodifications.getAttendees().containsAll(list);
    }

    public static boolean isStronglyModified(EventModifications eventmodifications)
    {
label0:
        {
            if (!eventmodifications.isSummaryModified() && !eventmodifications.isStartModified() && !eventmodifications.isEndModified() && !eventmodifications.isAllDayModified() && !eventmodifications.isEndTimeUnspecifiedModified() && !eventmodifications.isTimeZoneModified() && !eventmodifications.isEndTimeZoneModified() && !eventmodifications.isRecurrenceModified() && !eventmodifications.isDescriptionModified() && !eventmodifications.getLocationModification().isModified() && !eventmodifications.getAttachmentModifications().isModified() && !eventmodifications.getConferenceDataModifications().isModified() && !eventmodifications.isCanAttendeesAddAttendeesModified())
            {
                Feature feature = eventmodifications.getHabitInstanceModifications();
                boolean flag;
                if (feature.isSupported() && ((HabitInstanceModifications)feature.getValue()).isModified())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    Object obj = eventmodifications.getAttendees();
                    class .Lambda._cls2
                        implements Predicate
                    {

                        public static final Predicate $instance = new .Lambda._cls2();

                        public final boolean apply(Object obj1)
                        {
                            return AttendeeUtils.isRoom((Attendee)obj1);
                        }


            private .Lambda._cls2()
            {
            }
                    }

                    Predicate predicate;
                    if (obj instanceof FluentIterable)
                    {
                        obj = (FluentIterable)obj;
                    } else
                    {
                        obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                    }
                    predicate = .Lambda._cls2..instance;
                    obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
                    if (obj == null)
                    {
                        throw new NullPointerException();
                    }
                    if (predicate == null)
                    {
                        throw new NullPointerException();
                    }
                    obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), predicate);
                    if (obj instanceof FluentIterable)
                    {
                        obj = (FluentIterable)obj;
                    } else
                    {
                        obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                    }
                    obj = ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
                    eventmodifications = eventmodifications.getAttendeeModifications().getOriginal();
                    if (eventmodifications instanceof FluentIterable)
                    {
                        eventmodifications = (FluentIterable)eventmodifications;
                    } else
                    {
                        eventmodifications = new com.google.common.collect.FluentIterable._cls1(eventmodifications, eventmodifications);
                    }
                    predicate = .Lambda._cls2..instance;
                    eventmodifications = (Iterable)((FluentIterable) (eventmodifications)).iterableDelegate.or(eventmodifications);
                    if (eventmodifications == null)
                    {
                        throw new NullPointerException();
                    }
                    if (predicate == null)
                    {
                        throw new NullPointerException();
                    }
                    eventmodifications = new com.google.common.collect.Iterables._cls4(eventmodifications, predicate);
                    if (eventmodifications instanceof FluentIterable)
                    {
                        eventmodifications = (FluentIterable)eventmodifications;
                    } else
                    {
                        eventmodifications = new com.google.common.collect.FluentIterable._cls1(eventmodifications, eventmodifications);
                    }
                    eventmodifications = ImmutableList.copyOf((Iterable)((FluentIterable) (eventmodifications)).iterableDelegate.or(eventmodifications));
                    if (((List) (obj)).size() != eventmodifications.size())
                    {
                        flag = true;
                    } else
                    if (!((List) (obj)).containsAll(eventmodifications))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        break label0;
                    }
                }
            }
            return true;
        }
        return false;
    }

    static void logDialogCancel(Context context, String s)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "guest_notification_dialog", "cancel", s, Long.valueOf(0L));
            return;
        }
    }

    static void logUserDecision(Context context, String s, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification guestnotification)
    {
        AnalyticsLogger analyticslogger;
        if (guestnotification == com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED)
        {
            guestnotification = "send";
        } else
        {
            guestnotification = "don't send";
        }
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "guest_notification_dialog", guestnotification, s, Long.valueOf(0L));
            return;
        }
    }

    public static String maybeExtendDialogText(Event event, Context context, String s)
    {
        GooglePrivateData googleprivatedata = event.getGooglePrivateData();
        boolean flag;
        if (googleprivatedata != null && googleprivatedata.getGuestNotification() != com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return s;
        }
        event = event.getGooglePrivateData();
        if (event != null && event.getGuestNotification() == com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return context.getString(0x7f1302e2);
        } else
        {
            return context.getString(0x7f1302de, new Object[] {
                s
            });
        }
    }

    public static ListenableFuture maybeReadEvent(Event event)
    {
        GooglePrivateData googleprivatedata = event.getGooglePrivateData();
        boolean flag;
        if (googleprivatedata != null && googleprivatedata.getGuestNotification() != com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            if (event == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(event);
            }
        } else
        {
            return CalendarApi.Events.read(event.getDescriptor());
        }
    }

    private static List removeRooms(List list)
    {
        class .Lambda._cls1
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls1();

            public final boolean apply(Object obj)
            {
                return !AttendeeUtils.isRoom((Attendee)obj);
            }


            private .Lambda._cls1()
            {
            }
        }

        Predicate predicate;
        if (list instanceof FluentIterable)
        {
            list = (FluentIterable)list;
        } else
        {
            list = new com.google.common.collect.FluentIterable._cls1(list, list);
        }
        predicate = .Lambda._cls1..instance;
        list = (Iterable)((FluentIterable) (list)).iterableDelegate.or(list);
        if (list == null)
        {
            throw new NullPointerException();
        }
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        list = new com.google.common.collect.Iterables._cls4(list, predicate);
        if (list instanceof FluentIterable)
        {
            list = (FluentIterable)list;
        } else
        {
            list = new com.google.common.collect.FluentIterable._cls1(list, list);
        }
        return ImmutableList.copyOf((Iterable)((FluentIterable) (list)).iterableDelegate.or(list));
    }

    public static boolean shouldShowDialogForEventDeletion(Event event)
    {
        return AccountUtil.isGoogleAccount(event.getCalendar().account) && AttendeeUtils.hasGuests(event) && AttendeeUtils.isOrganizerCopy(event);
    }

    public static ListenableFuture showNotificationDialogAsync(Event event, Context context, String s, String s1)
    {
        SettableFuture settablefuture;
        settablefuture = new SettableFuture();
        Object obj = new android.support.v7.app.AlertDialog.Builder(context);
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mMessage = s;
        class .Lambda._cls3
            implements android.content.DialogInterface.OnClickListener
        {

            private final SettableFuture arg$1;
            private final Context arg$2;
            private final String arg$3;

            public final void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface = arg$1;
                Context context1 = arg$2;
                String s2 = arg$3;
                dialoginterface.set(com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.DISABLED);
                GuestNotificationDialogUtils.logUserDecision(context1, s2, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.DISABLED);
            }

            .Lambda._cls3(SettableFuture settablefuture, Context context, String s)
            {
                arg$1 = settablefuture;
                arg$2 = context;
                arg$3 = s;
            }
        }

        s = new .Lambda._cls3(settablefuture, context, s1);
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mNegativeButtonText = ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mContext.getText(0x7f1302dc);
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mNegativeButtonListener = s;
        class .Lambda._cls4
            implements android.content.DialogInterface.OnClickListener
        {

            private final SettableFuture arg$1;
            private final Context arg$2;
            private final String arg$3;

            public final void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface = arg$1;
                Context context1 = arg$2;
                String s2 = arg$3;
                dialoginterface.set(com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED);
                GuestNotificationDialogUtils.logUserDecision(context1, s2, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED);
            }

            .Lambda._cls4(SettableFuture settablefuture, Context context, String s)
            {
                arg$1 = settablefuture;
                arg$2 = context;
                arg$3 = s;
            }
        }

        s = new .Lambda._cls4(settablefuture, context, s1);
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mPositiveButtonText = ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mContext.getText(0x7f1302dd);
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mPositiveButtonListener = s;
        class .Lambda._cls5
            implements android.content.DialogInterface.OnClickListener
        {

            private final SettableFuture arg$1;
            private final Context arg$2;
            private final String arg$3;

            public final void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface = arg$1;
                Context context1 = arg$2;
                String s2 = arg$3;
                dialoginterface.cancel(true);
                GuestNotificationDialogUtils.logDialogCancel(context1, s2);
            }

            .Lambda._cls5(SettableFuture settablefuture, Context context, String s)
            {
                arg$1 = settablefuture;
                arg$2 = context;
                arg$3 = s;
            }
        }

        s = new .Lambda._cls5(settablefuture, context, s1);
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mNeutralButtonText = ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mContext.getText(0x7f1301ae);
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mNeutralButtonListener = s;
        class .Lambda._cls6
            implements android.content.DialogInterface.OnCancelListener
        {

            private final SettableFuture arg$1;
            private final Context arg$2;
            private final String arg$3;

            public final void onCancel(DialogInterface dialoginterface)
            {
                dialoginterface = arg$1;
                Context context1 = arg$2;
                String s2 = arg$3;
                dialoginterface.cancel(true);
                GuestNotificationDialogUtils.logDialogCancel(context1, s2);
            }

            .Lambda._cls6(SettableFuture settablefuture, Context context, String s)
            {
                arg$1 = settablefuture;
                arg$2 = context;
                arg$3 = s;
            }
        }

        s = new .Lambda._cls6(settablefuture, context, s1);
        ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mOnCancelListener = s;
        s = ((android.support.v7.app.AlertDialog.Builder) (obj)).create();
        s.setCanceledOnTouchOutside(false);
        obj = event.getGooglePrivateData();
        class .Lambda._cls7
            implements android.content.DialogInterface.OnShowListener
        {

            public static final android.content.DialogInterface.OnShowListener $instance = new .Lambda._cls7();

            public final void onShow(DialogInterface dialoginterface)
            {
                ((AlertDialog)dialoginterface).getButton(-2).setVisibility(4);
                ((AlertDialog)dialoginterface).getButton(-1).setText(0x104000a);
            }


            private .Lambda._cls7()
            {
            }
        }

        boolean flag;
        if (obj != null && ((GooglePrivateData) (obj)).getGuestNotification() == com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.ENABLED)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            s.setOnShowListener(.Lambda._cls7..instance);
        }
        event.getGooglePrivateData().getGuestNotification().ordinal();
        JVM INSTR tableswitch 1 2: default 264
    //                   1 319
    //                   2 312;
           goto _L1 _L2 _L3
_L2:
        break MISSING_BLOCK_LABEL_319;
_L1:
        event = "undecided";
_L4:
        event = String.format("show:%s", new Object[] {
            event
        });
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "guest_notification_dialog", event, s1, Long.valueOf(0L));
            s.show();
            return settablefuture;
        }
_L3:
        event = "enabled";
          goto _L4
        event = "disabled";
          goto _L4
    }
}
