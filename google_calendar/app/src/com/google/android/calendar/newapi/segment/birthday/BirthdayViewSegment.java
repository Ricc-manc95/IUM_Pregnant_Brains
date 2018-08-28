// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.birthday;

import android.app.Activity;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.calendar.Utils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.event.smartprofile.SmartProfileHelper;
import com.google.android.calendar.newapi.model.TimelineItemHolder;
import com.google.android.calendar.newapi.segment.attendee.AttendeesUtils;
import com.google.android.calendar.newapi.segment.attendee.ContactTileView;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.timebox.Birthday;
import com.google.android.calendar.timely.TimelineBirthday;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class BirthdayViewSegment extends LinearLayout
    implements android.view.View.OnClickListener, ViewSegment
{

    private final Activity activity;
    private final TimelineItemHolder model;

    public BirthdayViewSegment(Activity activity1, TimelineItemHolder timelineitemholder)
    {
        super(activity1);
        activity = activity1;
        model = timelineitemholder;
        setOrientation(1);
    }

    private static boolean shouldShowContactCard(Birthday birthday)
    {
        while (!birthday.isBirthday() || birthday.isSelfBirthday() || TextUtils.isEmpty(birthday.email()) && (!birthday.isGPlusUser() || TextUtils.isEmpty(birthday.profileId()))) 
        {
            return false;
        }
        return true;
    }

    public final void onClick(View view)
    {
label0:
        {
            if (view.getTag() instanceof Birthday)
            {
                AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)analyticslogger).trackEvent(getContext(), "event_action", "tap_person_bday");
                if (AttendeesUtils.requestContactsPermissionsIfMissing(activity))
                {
                    break label0;
                }
            }
            return;
        }
        view = (Birthday)view.getTag();
        String s = Utils.getCalendarOwnerAccount(view.calendarId());
        SmartProfileHelper.showSmartProfile(activity, s, view);
    }

    public final void updateUi()
    {
        removeAllViews();
        List list = Collections.unmodifiableList(((TimelineBirthday)model.getTimelineItem()).birthdays);
        if (list == null || list.isEmpty())
        {
            if (this != null)
            {
                setVisibility(8);
            }
        } else
        {
            if (this != null)
            {
                setVisibility(0);
            }
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) 
            {
                Birthday birthday = (Birthday)iterator.next();
                String s = birthday.email();
                Object obj;
                Object obj1;
                boolean flag;
                if (birthday.isSelfBirthday())
                {
                    obj = getResources().getString(0x7f1302e6);
                } else
                {
                    obj = birthday.fullName();
                }
                if (shouldShowContactCard(birthday) && !birthday.isSelfBirthday())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj1 = getResources().getString(0x7f1300e2);
                } else
                {
                    obj1 = null;
                }
                obj = (new ContactTileView(getContext())).setData(((TimelineBirthday)model.getTimelineItem()).sourceAccountName, s, ((CharSequence) (obj)), ((CharSequence) (obj1)));
                if (shouldShowContactCard(birthday))
                {
                    ((TileView) (obj)).treatAsButton(true).setOnClickListener(this);
                }
                obj1 = new StringBuilder();
                ((StringBuilder) (obj1)).append(birthday.fullName());
                if (shouldShowContactCard(birthday) && !birthday.isSelfBirthday())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    ((StringBuilder) (obj1)).append(", ").append(getResources().getString(0x7f1300e2));
                }
                ((TileView) (obj)).setContentDescription(((CharSequence) (obj1)));
                ((TileView) (obj)).setTag(birthday);
                addView(((View) (obj)));
            }
        }
    }
}
