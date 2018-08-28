// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.personalization.assist.annotate.AssistType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.CallAction;
import com.google.personalization.assist.annotate.api.PhoneNumber;
import java.util.List;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder

public class CallTaskAssist extends TaskAssistHolder
{

    public CallTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance, grammarruletype, s, s1);
        mAnalyticsLabel = "call";
    }

    public final String getAssistActionText(Context context)
    {
        String s = null;
        boolean flag = false;
        Intent intent = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", getPhoneNumber().phoneNumber_, null));
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0)
        {
            flag = true;
        }
        if (flag)
        {
            s = context.getString(0x7f1300ff);
        }
        return s;
    }

    public String getAssistInfoText(Context context)
    {
        return getPhoneNumber().phoneNumber_;
    }

    protected final AssistType getAssistType()
    {
        return AssistType.CALL;
    }

    public final String getDescription(Context context)
    {
        return context.getString(0x7f13017b);
    }

    public String getDisplayText(Context context)
    {
        String s = getPhoneNumber().phoneNumber_;
        if (getPhoneNumber().phoneNumber_ != null)
        {
            return s;
        } else
        {
            return context.getString(0x7f1300fb);
        }
    }

    public final int getIconId()
    {
        return 0x7f0201cd;
    }

    public final String getImageUrl()
    {
        return FlairAllocatorFactory.getAssistFlairUrlString("call");
    }

    protected PhoneNumber getPhoneNumber()
    {
        Object obj = assistance;
        if (((Assistance) (obj)).callAction_ == null)
        {
            obj = CallAction.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).callAction_;
        }
        return (PhoneNumber)((CallAction) (obj)).phoneNumber_.get(0);
    }

    public final void gotoAssist(Context context)
    {
        boolean flag = false;
        Intent intent = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", getPhoneNumber().phoneNumber_, null));
        ActivityUtils.prepareIntentToOpenLink(intent);
        intent.addFlags(0x10000000);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0)
        {
            flag = true;
        }
        if (flag)
        {
            startActivity(context, intent);
            return;
        } else
        {
            intent.setAction("android.intent.action.VIEW");
            startActivity(context, intent);
            return;
        }
    }

    public final boolean isClickable(Context context)
    {
        boolean flag = false;
        Intent intent = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", getPhoneNumber().phoneNumber_, null));
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0)
        {
            flag = true;
        }
        return flag;
    }
}
