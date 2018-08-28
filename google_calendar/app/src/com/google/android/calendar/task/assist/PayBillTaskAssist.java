// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.SpannableString;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.personalization.assist.annotate.AssistType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.BillAssistance;
import com.google.personalization.assist.annotate.api.Time;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder

public final class PayBillTaskAssist extends TaskAssistHolder
{

    public PayBillTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance, grammarruletype, s, s1);
        mAnalyticsLabel = "payBill";
    }

    public final String getAssistActionText(Context context)
    {
        context = assistance;
        if (((Assistance) (context)).billAssistance_ == null)
        {
            context = BillAssistance.DEFAULT_INSTANCE;
        } else
        {
            context = ((Assistance) (context)).billAssistance_;
        }
        return ((BillAssistance) (context)).payText_;
    }

    public final String getAssistInfoText(Context context)
    {
        Object obj = assistance;
        boolean flag;
        if (((Assistance) (obj)).billAssistance_ == null)
        {
            obj = BillAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).billAssistance_;
        }
        if ((((BillAssistance) (obj)).bitField0_ & 2) == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return null;
        }
        obj = assistance;
        int i;
        long l;
        long l1;
        if (((Assistance) (obj)).billAssistance_ == null)
        {
            obj = BillAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).billAssistance_;
        }
        if (((BillAssistance) (obj)).dueDate_ == null)
        {
            obj = Time.DEFAULT_INSTANCE;
        } else
        {
            obj = ((BillAssistance) (obj)).dueDate_;
        }
        l1 = ((Time) (obj)).timeMs_;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        i = (int)((l1 - l) / 0x5265c00L);
        if (i > 0)
        {
            return context.getResources().getQuantityString(0x7f12004a, i, new Object[] {
                Integer.valueOf(i)
            });
        } else
        {
            return null;
        }
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
        return 0x7f020222;
    }

    public final String getImageUrl()
    {
        return FlairAllocatorFactory.getAssistFlairUrlString("pay");
    }

    public final SpannableString getSecondaryDisplayText(Context context)
    {
        context = getAssistInfoText(context);
        if (context == null)
        {
            return null;
        } else
        {
            return new SpannableString(context);
        }
    }

    public final void gotoAssist(Context context)
    {
        Object obj = assistance;
        if (((Assistance) (obj)).billAssistance_ == null)
        {
            obj = BillAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).billAssistance_;
        }
        obj = new Intent("android.intent.action.VIEW", Uri.parse(((BillAssistance) (obj)).payText_));
        ActivityUtils.prepareIntentToOpenLink(((Intent) (obj)));
        ((Intent) (obj)).addFlags(0x10000000);
        ((Intent) (obj)).putExtra("com.android.browser.application_id", context.getPackageName());
        startActivity(context, ((Intent) (obj)));
    }
}
