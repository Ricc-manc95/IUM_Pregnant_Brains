// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.logging;

import android.content.Context;
import com.google.android.apps.calendar.proposenewtime.utils.ProposeNewTimeUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.newapi.model.AccountHolder;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class LoggingUtils
{

    public static void addAccountType(Context context, AccountHolder accountholder)
    {
        if (context != null && accountholder != null && accountholder.getAccount() != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).addAccountTypeCustomDimensions(context, accountholder.getAccount());
        }
    }

    public static void logDelete(Context context, boolean flag, ViewScreenModel viewscreenmodel, int i)
    {
        if (viewscreenmodel instanceof EventViewScreenModel)
        {
            addAccountType(context, (EventViewScreenModel)viewscreenmodel);
        }
        if (context == null) goto _L2; else goto _L1
_L1:
        Object obj;
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        obj = (AnalyticsLogger)analyticslogger;
        i;
        JVM INSTR tableswitch 1 2: default 72
    //                   1 129
    //                   2 136;
           goto _L3 _L4 _L5
_L3:
        String s = "only_this_instance";
_L7:
        ((AnalyticsLogger) (obj)).setCustomDimension(context, 46, s);
_L2:
        AnalyticsLogger analyticslogger2;
        if (flag)
        {
            s = "delete_event";
        } else
        {
            s = "delete_event_failed";
        }
        obj = viewscreenmodel.getViewType();
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_165;
        }
        analyticslogger2 = AnalyticsLoggerHolder.instance;
        if (analyticslogger2 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break; /* Loop/switch isn't completed */
_L4:
        s = "all_following_instances";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "all_instances";
        if (true) goto _L7; else goto _L6
_L6:
        ((AnalyticsLogger)analyticslogger2).trackEvent(context, s, ((String) (obj)));
        viewscreenmodel = viewscreenmodel.getCategory();
        if (context != null)
        {
            AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
            if (analyticslogger1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger1).trackEvent(context, viewscreenmodel, "delete");
        }
        return;
    }

    public static void logDeleteClicked(Context context, ViewScreenModel viewscreenmodel)
    {
        viewscreenmodel = viewscreenmodel.getCategory();
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, viewscreenmodel, "delete_pressed");
        }
    }

    public static void logEveryoneDeclined(Context context, String s, boolean flag, List list)
    {
        class .Lambda._cls0
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls0();

            public final boolean apply(Object obj)
            {
                obj = ((Attendee)obj).attendeeDescriptor.email;
                boolean flag1;
                if (obj != null && ((String) (obj)).endsWith("@resource.calendar.google.com"))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                return !flag1;
            }


            private .Lambda._cls0()
            {
            }
        }

        String s1;
        Predicate predicate;
        if (flag)
        {
            s1 = "bottom_bar";
        } else
        {
            s1 = "notification";
        }
        if (list instanceof FluentIterable)
        {
            list = (FluentIterable)list;
        } else
        {
            list = new com.google.common.collect.FluentIterable._cls1(list, list);
        }
        predicate = .Lambda._cls0..instance;
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
        int i;
        long l;
        if (list instanceof FluentIterable)
        {
            list = (FluentIterable)list;
        } else
        {
            list = new com.google.common.collect.FluentIterable._cls1(list, list);
        }
        list = (Iterable)((FluentIterable) (list)).iterableDelegate.or(list);
        if (list instanceof Collection)
        {
            i = ((Collection)list).size();
        } else
        {
            list = list.iterator();
            for (l = 0L; list.hasNext(); l++)
            {
                list.next();
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
        l = i;
        if (context != null)
        {
            list = AnalyticsLoggerHolder.instance;
            if (list == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)list).trackEvent(context, "everyone_declined", s, s1, Long.valueOf(l));
        }
    }

    public static void logRsvp(Context context, boolean flag, EventViewScreenModel eventviewscreenmodel, int i, Response response)
    {
        addAccountType(context, eventviewscreenmodel);
        if (context == null) goto _L2; else goto _L1
_L1:
        AnalyticsLogger analyticslogger2;
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger2 = (AnalyticsLogger)analyticslogger;
        i;
        JVM INSTR tableswitch 1 2: default 64
    //                   1 114
    //                   2 121;
           goto _L3 _L4 _L5
_L3:
        Object obj = "only_this_instance";
_L7:
        analyticslogger2.setCustomDimension(context, 46, ((String) (obj)));
          goto _L2
_L4:
        obj = "all_following_instances";
        continue; /* Loop/switch isn't completed */
_L5:
        obj = "all_instances";
        if (true) goto _L7; else goto _L6
_L2:
        obj = response.status;
        if (context == null)
        {
            break; /* Loop/switch isn't completed */
        }
        analyticslogger2 = AnalyticsLoggerHolder.instance;
        if (analyticslogger2 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger2 = (AnalyticsLogger)analyticslogger2;
        ((com.google.android.calendar.api.event.attendee.Response.ResponseStatus) (obj)).ordinal();
        JVM INSTR tableswitch 0 3: default 172
    //                   0 242
    //                   1 221
    //                   2 235
    //                   3 228;
           goto _L8 _L9 _L10 _L11 _L12
_L8:
        obj = null;
_L14:
        analyticslogger2.setCustomDimension(context, 52, ((String) (obj)));
_L6:
        boolean flag1;
        flag1 = ProposeNewTimeUtils.responseHasProposal(response);
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_267;
        }
        response = AnalyticsLoggerHolder.instance;
        if (response == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break; /* Loop/switch isn't completed */
_L10:
        obj = "yes";
        continue; /* Loop/switch isn't completed */
_L12:
        obj = "no";
        continue; /* Loop/switch isn't completed */
_L11:
        obj = "maybe";
        continue; /* Loop/switch isn't completed */
_L9:
        obj = "needs_action";
        if (true) goto _L14; else goto _L13
_L13:
        ((AnalyticsLogger)response).setCustomDimension(context, 53, Boolean.toString(flag1));
        if (flag)
        {
            response = "rsvp_event";
        } else
        {
            response = "rsvp_event_failed";
        }
        eventviewscreenmodel = eventviewscreenmodel.getViewType();
        if (context != null)
        {
            AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
            if (analyticslogger1 == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger1).trackEvent(context, response, eventviewscreenmodel);
        }
        return;
    }

    public static void logSaveFailure(Context context, EditScreenModel editscreenmodel, int i)
    {
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).setCustomDimension(context, 47, "new");
        }
        if (context == null) goto _L2; else goto _L1
_L1:
        AnalyticsLogger analyticslogger2;
        AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
        if (analyticslogger1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        analyticslogger2 = (AnalyticsLogger)analyticslogger1;
        i;
        JVM INSTR tableswitch 1 2: default 92
    //                   1 141
    //                   2 147;
           goto _L3 _L4 _L5
_L3:
        Object obj = "only_this_instance";
_L7:
        analyticslogger2.setCustomDimension(context, 46, ((String) (obj)));
_L2:
        addAccountType(context, editscreenmodel);
        editscreenmodel = editscreenmodel.getViewType();
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_166;
        }
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break; /* Loop/switch isn't completed */
_L4:
        obj = "all_following_instances";
        continue; /* Loop/switch isn't completed */
_L5:
        obj = "all_instances";
        if (true) goto _L7; else goto _L6
_L6:
        ((AnalyticsLogger)obj).trackEvent(context, "save_event_failed", editscreenmodel);
    }
}
