// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.visualelements;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewParent;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.libraries.social.analytics.AnalyticsLogger;
import com.google.android.libraries.social.analytics.binder.extensions.FragmentDeferredVisualElementProvider;
import com.google.android.libraries.social.analytics.events.UserEvent;
import com.google.android.libraries.social.analytics.visualelement.VisualElement;
import com.google.android.libraries.social.analytics.visualelement.VisualElementPath;
import com.google.android.libraries.social.analytics.visualelement.VisualElementProvider;
import com.google.android.libraries.social.analytics.visualelement.VisualElementTag;
import com.google.android.libraries.social.analytics.visualelement.VisualElementUtil;
import com.google.android.libraries.stitch.binder.Binder;
import com.google.logs.calendar.config.CalendarConstants;
import com.google.logs.calendar.config.EventCreateEditConstants;
import com.google.logs.calendar.config.EventDetailsConstants;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.loggers.visualelements:
//            VisualElementAttacher, CalendarAnalyticsLoggerProvider, EventVisualElement, ConferenceVisualElement

public class RealVisualElementAttacher
    implements VisualElementAttacher
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/loggers/visualelements/RealVisualElementAttacher);

    public RealVisualElementAttacher()
    {
    }

    private static transient void record(Context context, int i, VisualElementPath visualelementpath, Account aaccount[])
    {
        if (aaccount.length > 0)
        {
            AnalyticsLogger analyticslogger = CalendarAnalyticsLoggerProvider.get(context);
            int k = aaccount.length;
            for (int j = 0; j < k; j++)
            {
                Account account = aaccount[j];
                UserEvent userevent = new UserEvent(i, visualelementpath);
                userevent.accountName = account.name;
                analyticslogger.recordEvent(context, userevent);
            }

        }
    }

    public final void attachCreateEventButton(View view)
    {
        VisualElementUtil.attach(view.getRootView(), new VisualElement(CalendarConstants.MAIN_CALENDAR_PAGE));
        VisualElementUtil.attach(view, new VisualElement(CalendarConstants.CREATE_EVENT_BUTTON));
    }

    public final void attachDayView(View view)
    {
        VisualElementUtil.attach(view, new VisualElement(CalendarConstants.DAY_GRID_VIEW));
    }

    public final void attachEditElements$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78GB3EHKNCQBKF4TKOOBECHP6UQB45TR6IPBN5TB6IPBN7D662RJ4E9NMIP1FEPKMATPFAPKMATPR9HGMSP3IDTKM8BRMD5INEBQMD5INEEQQ9HL62TJ15TM62RJ75T9N8SJ9DPJJMJ3AC5R62BRCC5N6EBQJEHP6IRJ77D66KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(View view, View view1, View view2, boolean flag, String s, String s1, String s2)
    {
        if (flag)
        {
            s1 = new EventVisualElement(EventCreateEditConstants.EVENT_UPDATE_PAGE, s, s1, null);
            s = new VisualElement(EventCreateEditConstants.UPDATE_EVENT_BUTTON);
        } else
        {
            VisualElement visualelement = new VisualElement(EventCreateEditConstants.EVENT_CREATION_PAGE);
            s = new EventVisualElement(EventCreateEditConstants.SAVE_NEW_EVENT_BUTTON, s, s1, s2);
            s1 = visualelement;
        }
        VisualElementUtil.attach(view, s1);
        VisualElementUtil.attach(view1, s);
        VisualElementUtil.attach(view2, new VisualElement(EventCreateEditConstants.CANCEL_EVENT_CHANGES_BUTTON));
    }

    public final void attachEditEventButton(View view, String s, String s1)
    {
        VisualElementUtil.attach(view, new EventVisualElement(EventDetailsConstants.EDIT_EXISTING_EVENT_BUTTON, s, s1, null));
    }

    public final void attachEventChip(View view)
    {
        VisualElementUtil.attach(view, new VisualElement(CalendarConstants.EVENT_CHIP));
    }

    public final void attachEventDetailsPage$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78GB3EHKNCQBKF4TKOOBECHP6UQB45TR6IPBN5TB6IPBN7D66KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIILG_0(View view, String s, String s1)
    {
        VisualElementUtil.attach(view, new EventVisualElement(EventDetailsConstants.EVENT_DETAILS_PAGE, s, s1, null));
    }

    public final void attachJoinConference(View view, View view1, View view2, View view3, View view4, PhoneNumberDetails phonenumberdetails)
    {
        VisualElementUtil.attach(view, new VisualElement(EventDetailsConstants.CONFERENCE_CONNECT_ROOM_BUTTON));
        VisualElementUtil.attach(view1, new VisualElement(EventDetailsConstants.CONFERENCE_JOIN_VIDEO_CALL_BUTTON));
        VisualElementUtil.attach(view2, new VisualElement(EventDetailsConstants.CONFERENCE_JOIN_LIVE_STREAM_BUTTON));
        view1 = EventDetailsConstants.CONFERENCE_JOIN_BY_PHONE_NUMBER_BUTTON;
        if (phonenumberdetails != null)
        {
            view = phonenumberdetails.source();
        } else
        {
            view = null;
        }
        VisualElementUtil.attach(view3, new ConferenceVisualElement(view1, view));
        VisualElementUtil.attach(view4, new VisualElement(EventDetailsConstants.CONFERENCE_SHOW_MORE_PHONE_NUMBERS_BUTTON));
    }

    public final void attachMainCalendarPage(FragmentActivity fragmentactivity)
    {
        VisualElement visualelement = new VisualElement(CalendarConstants.MAIN_CALENDAR_PAGE);
        VisualElementUtil.attach(fragmentactivity.findViewById(0x1020002), visualelement);
    }

    public final void attachMonthView(View view)
    {
        VisualElementUtil.attach(view, new VisualElement(CalendarConstants.MONTH_GRID_VIEW));
    }

    public final void attachPhoneNumberItem(View view)
    {
        VisualElementUtil.attach(view, new VisualElement(EventDetailsConstants.CONFERENCE_DIAL_IN_PHONE_NUMBER_LIST_ITEM));
    }

    public final void attachPhoneNumbersActivity(View view)
    {
        VisualElementUtil.attach(view, new VisualElement(EventDetailsConstants.CONFERENCE_DIAL_IN_PHONE_NUMBERS_PAGE));
    }

    public final void attachScheduleView(View view)
    {
        VisualElementUtil.attach(view, new VisualElement(CalendarConstants.SCHEDULE_VIEW));
    }

    public final void attachThreeDayView(View view)
    {
        VisualElementUtil.attach(view, new VisualElement(CalendarConstants.THREE_DAY_GRID_VIEW));
    }

    public final void attachWeekView(View view)
    {
        VisualElementUtil.attach(view, new VisualElement(CalendarConstants.WEEK_GRID_VIEW));
    }

    public final void detachChip(View view)
    {
        view.setTag(0x7f100006, null);
    }

    public final void recordAppVisibilityEvent(Context context, boolean flag)
    {
        Object obj;
        VisualElementPath visualelementpath;
        if (flag)
        {
            obj = CalendarConstants.APP_TO_FOREGROUND;
        } else
        {
            obj = CalendarConstants.APP_TO_BACKGROUND;
        }
        obj = new VisualElement(((VisualElementTag) (obj)));
        visualelementpath = new VisualElementPath();
        visualelementpath.visualElements.add(obj);
        record(context, 25, visualelementpath, AccountsUtil.getGoogleAccounts(context));
    }

    public final void recordChipTap(Context context, View view)
    {
        Object obj;
        boolean flag;
        boolean flag1;
        if (view instanceof VisualElementProvider)
        {
            obj = ((VisualElementProvider)view).getVisualElement();
        } else
        {
            obj = (VisualElement)view.getTag(0x7f100006);
        }
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (context != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Account aaccount[] = AccountsUtil.getGoogleAccounts(context.getApplicationContext());
            VisualElementPath visualelementpath = new VisualElementPath();
            visualelementpath.addVisualElement(view);
            for (obj = view.getParent(); obj != null; obj = ((ViewParent) (obj)).getParent())
            {
                if (obj instanceof View)
                {
                    visualelementpath.addVisualElement((View)obj);
                }
            }

            view = view.getContext();
            obj = Binder.findBinder(view);
            List list = ((Binder) (obj)).getChain(com/google/android/libraries/social/analytics/visualelement/VisualElementProvider);
            for (int i = 0; i < list.size(); i++)
            {
                VisualElement visualelement = ((VisualElementProvider)list.get(i)).getVisualElement();
                if (visualelement != null)
                {
                    visualelementpath.visualElements.add(visualelement);
                }
            }

            if (visualelementpath.visualElements.isEmpty() || !((VisualElement)visualelementpath.visualElements.get(visualelementpath.visualElements.size() - 1)).tag.isRootPage)
            {
                obj = (FragmentDeferredVisualElementProvider)((Binder) (obj)).getOptional(com/google/android/libraries/social/analytics/binder/extensions/FragmentDeferredVisualElementProvider);
                if (obj != null)
                {
                    obj = ((FragmentDeferredVisualElementProvider) (obj)).getVisualElement();
                    if (obj != null)
                    {
                        visualelementpath.visualElements.add(obj);
                    }
                }
            }
            view = VisualElementPath.findIntent(view);
            if (view != null)
            {
                view = (VisualElementPath)view.getSerializableExtra(VisualElementPath.EXTRA_VISUAL_ELEMENT_PATH);
                if (view != null)
                {
                    visualelementpath.visualElements.addAll(((VisualElementPath) (view)).visualElements);
                }
            }
            record(context, 4, visualelementpath, aaccount);
        }
    }

    public final void recordImpression(Context context, View view)
    {
        Object obj;
        boolean flag;
        boolean flag1;
        if (view instanceof VisualElementProvider)
        {
            obj = ((VisualElementProvider)view).getVisualElement();
        } else
        {
            obj = (VisualElement)view.getTag(0x7f100006);
        }
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (context != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Account aaccount[] = AccountsUtil.getGoogleAccounts(context);
            VisualElementPath visualelementpath = new VisualElementPath();
            visualelementpath.addVisualElement(view);
            for (obj = view.getParent(); obj != null; obj = ((ViewParent) (obj)).getParent())
            {
                if (obj instanceof View)
                {
                    visualelementpath.addVisualElement((View)obj);
                }
            }

            view = view.getContext();
            obj = Binder.findBinder(view);
            List list = ((Binder) (obj)).getChain(com/google/android/libraries/social/analytics/visualelement/VisualElementProvider);
            for (int i = 0; i < list.size(); i++)
            {
                VisualElement visualelement = ((VisualElementProvider)list.get(i)).getVisualElement();
                if (visualelement != null)
                {
                    visualelementpath.visualElements.add(visualelement);
                }
            }

            if (visualelementpath.visualElements.isEmpty() || !((VisualElement)visualelementpath.visualElements.get(visualelementpath.visualElements.size() - 1)).tag.isRootPage)
            {
                obj = (FragmentDeferredVisualElementProvider)((Binder) (obj)).getOptional(com/google/android/libraries/social/analytics/binder/extensions/FragmentDeferredVisualElementProvider);
                if (obj != null)
                {
                    obj = ((FragmentDeferredVisualElementProvider) (obj)).getVisualElement();
                    if (obj != null)
                    {
                        visualelementpath.visualElements.add(obj);
                    }
                }
            }
            view = VisualElementPath.findIntent(view);
            if (view != null)
            {
                view = (VisualElementPath)view.getSerializableExtra(VisualElementPath.EXTRA_VISUAL_ELEMENT_PATH);
                if (view != null)
                {
                    visualelementpath.visualElements.addAll(((VisualElementPath) (view)).visualElements);
                }
            }
            record(context, -1, visualelementpath, aaccount);
        }
    }

    public final void recordImpression(Context context, View view, Account account)
    {
        Object obj;
        boolean flag;
        boolean flag1;
        if (view instanceof VisualElementProvider)
        {
            obj = ((VisualElementProvider)view).getVisualElement();
        } else
        {
            obj = (VisualElement)view.getTag(0x7f100006);
        }
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (context != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && account != null && AccountUtil.isGoogleAccount(account))
        {
            VisualElementPath visualelementpath = new VisualElementPath();
            visualelementpath.addVisualElement(view);
            for (obj = view.getParent(); obj != null; obj = ((ViewParent) (obj)).getParent())
            {
                if (obj instanceof View)
                {
                    visualelementpath.addVisualElement((View)obj);
                }
            }

            view = view.getContext();
            obj = Binder.findBinder(view);
            List list = ((Binder) (obj)).getChain(com/google/android/libraries/social/analytics/visualelement/VisualElementProvider);
            for (int i = 0; i < list.size(); i++)
            {
                VisualElement visualelement = ((VisualElementProvider)list.get(i)).getVisualElement();
                if (visualelement != null)
                {
                    visualelementpath.visualElements.add(visualelement);
                }
            }

            if (visualelementpath.visualElements.isEmpty() || !((VisualElement)visualelementpath.visualElements.get(visualelementpath.visualElements.size() - 1)).tag.isRootPage)
            {
                obj = (FragmentDeferredVisualElementProvider)((Binder) (obj)).getOptional(com/google/android/libraries/social/analytics/binder/extensions/FragmentDeferredVisualElementProvider);
                if (obj != null)
                {
                    obj = ((FragmentDeferredVisualElementProvider) (obj)).getVisualElement();
                    if (obj != null)
                    {
                        visualelementpath.visualElements.add(obj);
                    }
                }
            }
            view = VisualElementPath.findIntent(view);
            if (view != null)
            {
                view = (VisualElementPath)view.getSerializableExtra(VisualElementPath.EXTRA_VISUAL_ELEMENT_PATH);
                if (view != null)
                {
                    visualelementpath.visualElements.addAll(((VisualElementPath) (view)).visualElements);
                }
            }
            record(context, -1, visualelementpath, new Account[] {
                account
            });
        }
    }

    public final void recordTap(Context context, View view)
    {
        Object obj;
        boolean flag;
        boolean flag1;
        if (view instanceof VisualElementProvider)
        {
            obj = ((VisualElementProvider)view).getVisualElement();
        } else
        {
            obj = (VisualElement)view.getTag(0x7f100006);
        }
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (context != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Account aaccount[] = AccountsUtil.getGoogleAccounts(context.getApplicationContext());
            VisualElementPath visualelementpath = new VisualElementPath();
            visualelementpath.addVisualElement(view);
            for (obj = view.getParent(); obj != null; obj = ((ViewParent) (obj)).getParent())
            {
                if (obj instanceof View)
                {
                    visualelementpath.addVisualElement((View)obj);
                }
            }

            view = view.getContext();
            obj = Binder.findBinder(view);
            List list = ((Binder) (obj)).getChain(com/google/android/libraries/social/analytics/visualelement/VisualElementProvider);
            for (int i = 0; i < list.size(); i++)
            {
                VisualElement visualelement = ((VisualElementProvider)list.get(i)).getVisualElement();
                if (visualelement != null)
                {
                    visualelementpath.visualElements.add(visualelement);
                }
            }

            if (visualelementpath.visualElements.isEmpty() || !((VisualElement)visualelementpath.visualElements.get(visualelementpath.visualElements.size() - 1)).tag.isRootPage)
            {
                obj = (FragmentDeferredVisualElementProvider)((Binder) (obj)).getOptional(com/google/android/libraries/social/analytics/binder/extensions/FragmentDeferredVisualElementProvider);
                if (obj != null)
                {
                    obj = ((FragmentDeferredVisualElementProvider) (obj)).getVisualElement();
                    if (obj != null)
                    {
                        visualelementpath.visualElements.add(obj);
                    }
                }
            }
            view = VisualElementPath.findIntent(view);
            if (view != null)
            {
                view = (VisualElementPath)view.getSerializableExtra(VisualElementPath.EXTRA_VISUAL_ELEMENT_PATH);
                if (view != null)
                {
                    visualelementpath.visualElements.addAll(((VisualElementPath) (view)).visualElements);
                }
            }
            record(context, 4, visualelementpath, aaccount);
        }
    }

    public final void recordTap(Context context, View view, Account account)
    {
        Object obj;
        boolean flag;
        boolean flag1;
        if (view instanceof VisualElementProvider)
        {
            obj = ((VisualElementProvider)view).getVisualElement();
        } else
        {
            obj = (VisualElement)view.getTag(0x7f100006);
        }
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (context != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && account != null && AccountUtil.isGoogleAccount(account))
        {
            VisualElementPath visualelementpath = new VisualElementPath();
            visualelementpath.addVisualElement(view);
            for (obj = view.getParent(); obj != null; obj = ((ViewParent) (obj)).getParent())
            {
                if (obj instanceof View)
                {
                    visualelementpath.addVisualElement((View)obj);
                }
            }

            view = view.getContext();
            obj = Binder.findBinder(view);
            List list = ((Binder) (obj)).getChain(com/google/android/libraries/social/analytics/visualelement/VisualElementProvider);
            for (int i = 0; i < list.size(); i++)
            {
                VisualElement visualelement = ((VisualElementProvider)list.get(i)).getVisualElement();
                if (visualelement != null)
                {
                    visualelementpath.visualElements.add(visualelement);
                }
            }

            if (visualelementpath.visualElements.isEmpty() || !((VisualElement)visualelementpath.visualElements.get(visualelementpath.visualElements.size() - 1)).tag.isRootPage)
            {
                obj = (FragmentDeferredVisualElementProvider)((Binder) (obj)).getOptional(com/google/android/libraries/social/analytics/binder/extensions/FragmentDeferredVisualElementProvider);
                if (obj != null)
                {
                    obj = ((FragmentDeferredVisualElementProvider) (obj)).getVisualElement();
                    if (obj != null)
                    {
                        visualelementpath.visualElements.add(obj);
                    }
                }
            }
            view = VisualElementPath.findIntent(view);
            if (view != null)
            {
                view = (VisualElementPath)view.getSerializableExtra(VisualElementPath.EXTRA_VISUAL_ELEMENT_PATH);
                if (view != null)
                {
                    visualelementpath.visualElements.addAll(((VisualElementPath) (view)).visualElements);
                }
            }
            record(context, 4, visualelementpath, new Account[] {
                account
            });
        }
    }

}
