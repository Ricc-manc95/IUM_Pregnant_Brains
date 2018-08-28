// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.personalization.assist.annotate.AssistType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.EmailAction;
import com.google.personalization.assist.annotate.api.EmailAddress;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder

public final class EmailTaskAssist extends TaskAssistHolder
{

    public EmailTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance, grammarruletype, s, s1);
        mAnalyticsLabel = "email";
    }

    public final String getAssistActionText(Context context)
    {
        return context.getString(0x7f1301c5);
    }

    public final String getAssistInfoText(Context context)
    {
        context = assistance;
        if (((Assistance) (context)).emailAction_ == null)
        {
            context = EmailAction.DEFAULT_INSTANCE;
        } else
        {
            context = ((Assistance) (context)).emailAction_;
        }
        return ((EmailAddress)((EmailAction) (context)).emailAddress_.get(0)).emailAddress_;
    }

    protected final AssistType getAssistType()
    {
        return AssistType.EMAIL_ASSIST;
    }

    public final String getDescription(Context context)
    {
        return context.getString(0x7f13016b);
    }

    public final String getDisplayText(Context context)
    {
        return getAssistInfoText(context);
    }

    public final int getIconId()
    {
        return 0x7f0201ea;
    }

    public final String getImageUrl()
    {
        return FlairAllocatorFactory.getAssistFlairUrlString("email");
    }

    public final void gotoAssist(Context context)
    {
        Object obj = assistance;
        if (((Assistance) (obj)).emailAction_ == null)
        {
            obj = EmailAction.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).emailAction_;
        }
        obj = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", ((EmailAddress)((EmailAction) (obj)).emailAddress_.get(0)).emailAddress_, null));
        ActivityUtils.prepareIntentToOpenLink(((Intent) (obj)));
        ((Intent) (obj)).addFlags(0x10000000);
        ((Intent) (obj)).putExtra("com.android.browser.application_id", context.getPackageName());
        startActivity(context, ((Intent) (obj)));
    }
}
