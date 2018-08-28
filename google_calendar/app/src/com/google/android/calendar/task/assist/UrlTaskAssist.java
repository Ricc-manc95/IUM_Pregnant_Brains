// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableMap;
import com.google.personalization.assist.annotate.AssistType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.ViewUrlAction;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder, TaskAssistanceUtils

public class UrlTaskAssist extends TaskAssistHolder
{

    private static final ImmutableMap GRAMMAR_RULES_TYPES_TO_ICON_RES_IDS;
    private final String flair;

    public UrlTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        String s2;
        if (TaskAssistanceUtils.grammarRuleTypeToFlair.containsKey(grammarruletype))
        {
            s2 = (String)TaskAssistanceUtils.grammarRuleTypeToFlair.get(grammarruletype);
        } else
        {
            s2 = "default";
        }
        this(assistance, grammarruletype, s2, s, s1);
    }

    public UrlTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1, String s2)
    {
        super(assistance, grammarruletype, s1, s2);
        flair = s;
        mAnalyticsLabel = "url";
    }

    public final String getAssistActionText(Context context)
    {
        context = assistance;
        String s;
        if (((Assistance) (context)).viewUrlAction_ == null)
        {
            context = ViewUrlAction.DEFAULT_INSTANCE;
        } else
        {
            context = ((Assistance) (context)).viewUrlAction_;
        }
        s = ((ViewUrlAction) (context)).actionText_;
        context = s;
        if (Platform.stringIsNullOrEmpty(s))
        {
            context = assistance;
            if (((Assistance) (context)).viewUrlAction_ == null)
            {
                context = ViewUrlAction.DEFAULT_INSTANCE;
            } else
            {
                context = ((Assistance) (context)).viewUrlAction_;
            }
            context = ((ViewUrlAction) (context)).title_;
        }
        return context;
    }

    public String getAssistInfoText(Context context)
    {
        context = assistance;
        if (((Assistance) (context)).viewUrlAction_ == null)
        {
            context = ViewUrlAction.DEFAULT_INSTANCE;
        } else
        {
            context = ((Assistance) (context)).viewUrlAction_;
        }
        return ((ViewUrlAction) (context)).actionDescription_;
    }

    protected final AssistType getAssistType()
    {
        return AssistType.URL_ASSIST;
    }

    public String getDescription(Context context)
    {
        return null;
    }

    public final String getDisplayText(Context context)
    {
        context = assistance;
        if (((Assistance) (context)).viewUrlAction_ == null)
        {
            context = ViewUrlAction.DEFAULT_INSTANCE;
        } else
        {
            context = ((Assistance) (context)).viewUrlAction_;
        }
        return ((ViewUrlAction) (context)).title_;
    }

    public int getIconId()
    {
        if (GRAMMAR_RULES_TYPES_TO_ICON_RES_IDS.containsKey(grammarRuleType))
        {
            return ((Integer)GRAMMAR_RULES_TYPES_TO_ICON_RES_IDS.get(grammarRuleType)).intValue();
        } else
        {
            return 0x7f020222;
        }
    }

    public final String getImageUrl()
    {
        return FlairAllocatorFactory.getAssistFlairUrlString(flair);
    }

    public final void gotoAssist(Context context)
    {
        Object obj = assistance;
        if (((Assistance) (obj)).viewUrlAction_ == null)
        {
            obj = ViewUrlAction.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).viewUrlAction_;
        }
        obj = new Intent("android.intent.action.VIEW", Uri.parse(((ViewUrlAction) (obj)).url_));
        ActivityUtils.prepareIntentToOpenLink(((Intent) (obj)));
        ((Intent) (obj)).addFlags(0x10000000);
        ((Intent) (obj)).putExtra("com.android.browser.application_id", context.getPackageName());
        startActivity(context, ((Intent) (obj)));
    }

    static 
    {
        GRAMMAR_RULES_TYPES_TO_ICON_RES_IDS = (new com.google.common.collect.ImmutableMap.Builder()).put(GrammarRuleType.BOOK_FLIGHT, Integer.valueOf(0x7f0201fe)).put(GrammarRuleType.BOOK_HOTEL, Integer.valueOf(0x7f020209)).put(GrammarRuleType.CHECKIN_FLIGHT, Integer.valueOf(0x7f0201fe)).put(GrammarRuleType.RETURN_PRODUCT, Integer.valueOf(0x7f020236)).put(GrammarRuleType.WATCH_FILM, Integer.valueOf(0x7f02023d)).build();
    }
}
