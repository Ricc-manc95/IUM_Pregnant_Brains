// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.Intent;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.personalization.assist.annotate.AssistType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.CalendarAssistance;
import com.google.personalization.assist.annotate.api.EmailAddress;
import java.util.List;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder

public final class CalendarTaskAssist extends TaskAssistHolder
{

    public CalendarTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance, grammarruletype, s, s1);
        mAnalyticsLabel = "meet";
    }

    public final String getAssistActionText(Context context)
    {
        context = assistance;
        if (((Assistance) (context)).calendarAssistance_ == null)
        {
            context = CalendarAssistance.DEFAULT_INSTANCE;
        } else
        {
            context = ((Assistance) (context)).calendarAssistance_;
        }
        return ((CalendarAssistance) (context)).urlText_;
    }

    public final String getAssistInfoText(Context context)
    {
        return null;
    }

    protected final AssistType getAssistType()
    {
        return AssistType.CREATE_EVENT;
    }

    public final String getDescription(Context context)
    {
        return null;
    }

    public final String getDisplayText(Context context)
    {
        return getAssistActionText(context);
    }

    public final int getIconId()
    {
        return 0x7f0201f3;
    }

    public final String getImageUrl()
    {
        return FlairAllocatorFactory.getAssistFlairUrlString("meet");
    }

    public final void gotoAssist(Context context)
    {
        Object obj = assistance;
        Intent intent;
        String as[];
        if (((Assistance) (obj)).calendarAssistance_ == null)
        {
            obj = CalendarAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).calendarAssistance_;
        }
        intent = (new Intent("android.intent.action.INSERT")).setData(android.provider.CalendarContract.Events.CONTENT_URI).putExtra("title", ((CalendarAssistance) (obj)).title_).putExtra("description", ((CalendarAssistance) (obj)).description_);
        obj = ((CalendarAssistance) (obj)).attendee_;
        as = new String[((List) (obj)).size()];
        for (int i = 0; i < ((List) (obj)).size(); i++)
        {
            as[i] = ((EmailAddress)((List) (obj)).get(i)).emailAddress_;
        }

        obj = intent.putExtra("android.intent.extra.EMAIL", as);
        ActivityUtils.prepareIntentToOpenLink(((Intent) (obj)));
        ((Intent) (obj)).addFlags(0x10000000);
        ((Intent) (obj)).putExtra("com.android.browser.application_id", context.getPackageName());
        startActivity(context, ((Intent) (obj)));
    }
}
