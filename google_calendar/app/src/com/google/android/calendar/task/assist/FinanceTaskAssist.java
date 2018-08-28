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
import com.google.personalization.assist.annotate.api.FinanceAssistance;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder

public final class FinanceTaskAssist extends TaskAssistHolder
{

    public FinanceTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance, grammarruletype, s, s1);
        mAnalyticsLabel = "finance";
    }

    public final String getAssistActionText(Context context)
    {
        context = assistance;
        if (((Assistance) (context)).financeAssistance_ == null)
        {
            context = FinanceAssistance.DEFAULT_INSTANCE;
        } else
        {
            context = ((Assistance) (context)).financeAssistance_;
        }
        return ((FinanceAssistance) (context)).actionText_;
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
        Object obj = assistance;
        boolean flag;
        if (((Assistance) (obj)).financeAssistance_ == null)
        {
            obj = FinanceAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).financeAssistance_;
        }
        if (((FinanceAssistance) (obj)).changeDbl_ > 0.0D)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return !flag ? 0x7f02023e : 0x7f020240;
    }

    public final String getImageUrl()
    {
        Object obj = assistance;
        boolean flag;
        if (((Assistance) (obj)).financeAssistance_ == null)
        {
            obj = FinanceAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).financeAssistance_;
        }
        if (((FinanceAssistance) (obj)).changeDbl_ > 0.0D)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = "stock_up";
        } else
        {
            obj = "stock_down";
        }
        return FlairAllocatorFactory.getAssistFlairUrlString(((String) (obj)));
    }

    public final void gotoAssist(Context context)
    {
        Object obj = assistance;
        if (((Assistance) (obj)).financeAssistance_ == null)
        {
            obj = FinanceAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).financeAssistance_;
        }
        obj = new Intent("android.intent.action.VIEW", Uri.parse(((FinanceAssistance) (obj)).financeUrl_));
        ActivityUtils.prepareIntentToOpenLink(((Intent) (obj)));
        ((Intent) (obj)).addFlags(0x10000000);
        ((Intent) (obj)).putExtra("com.android.browser.application_id", context.getPackageName());
        startActivity(context, ((Intent) (obj)));
    }
}
