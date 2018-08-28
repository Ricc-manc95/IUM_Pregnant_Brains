// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.personalization.assist.annotate.AssistType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.WatchYoutubeAction;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder

public final class WatchVideoTaskAssist extends TaskAssistHolder
{

    public WatchVideoTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance, grammarruletype, s, s1);
        mAnalyticsLabel = "watchVideo";
    }

    public final String getAssistActionText(Context context)
    {
        return context.getString(0x7f1304ba);
    }

    public final String getAssistInfoText(Context context)
    {
        return null;
    }

    protected final AssistType getAssistType()
    {
        return AssistType.URL_ASSIST;
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
        return 0x7f020242;
    }

    public final String getImageUrl()
    {
        Object obj = assistance;
        if (((Assistance) (obj)).watchYoutubeAction_ == null)
        {
            obj = WatchYoutubeAction.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).watchYoutubeAction_;
        }
        return ((WatchYoutubeAction) (obj)).thumbnailUrl_;
    }

    public final void gotoAssist(Context context)
    {
        Object obj = assistance;
        if (((Assistance) (obj)).watchYoutubeAction_ == null)
        {
            obj = WatchYoutubeAction.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).watchYoutubeAction_;
        }
        obj = new Intent("android.intent.action.VIEW", Uri.parse(((WatchYoutubeAction) (obj)).videoUrl_));
        ActivityUtils.prepareIntentToOpenLink(((Intent) (obj)));
        ((Intent) (obj)).addFlags(0x10000000);
        ((Intent) (obj)).putExtra("com.android.browser.application_id", context.getPackageName());
        startActivity(context, ((Intent) (obj)));
    }
}
