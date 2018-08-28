// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.SpannableString;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.net.taskassist.TaskAssistService;
import com.google.personalization.assist.annotate.AssistType;
import com.google.personalization.assist.annotate.ClientType;
import com.google.personalization.assist.annotate.DeviceType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.AssistClick;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.LoggingRequest;
import com.google.protobuf.GeneratedMessageLite;
import java.util.Locale;

public abstract class TaskAssistHolder
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/task/assist/TaskAssistHolder);
    public final Assistance assistance;
    public final GrammarRuleType grammarRuleType;
    public String mAnalyticsLabel;
    private final String taskTitle;
    private final String taskUser;

    public TaskAssistHolder(Assistance assistance1, GrammarRuleType grammarruletype, String s, String s1)
    {
        mAnalyticsLabel = "";
        assistance = assistance1;
        grammarRuleType = grammarruletype;
        taskTitle = s;
        taskUser = s1;
    }

    public abstract String getAssistActionText(Context context);

    public abstract String getAssistInfoText(Context context);

    protected abstract AssistType getAssistType();

    public abstract String getDescription(Context context);

    public abstract String getDisplayText(Context context);

    public abstract int getIconId();

    public abstract String getImageUrl();

    public SpannableString getSecondaryDisplayText(Context context)
    {
        return null;
    }

    public abstract void gotoAssist(Context context);

    public boolean isClickable(Context context)
    {
        return true;
    }

    public final void logClick(Context context, String s, boolean flag)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)analyticslogger).trackEvent(context, s, "tap_task_assistance", mAnalyticsLabel, Long.valueOf(grammarRuleType.value));
        if (taskUser != null)
        {
            com.google.personalization.assist.annotate.api.LoggingRequest.Builder builder = (com.google.personalization.assist.annotate.api.LoggingRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)LoggingRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            s = ClientType.TIMELY;
            builder.copyOnWrite();
            Object obj = (LoggingRequest)builder.instance;
            if (s == null)
            {
                throw new NullPointerException();
            }
            obj.bitField0_ = ((LoggingRequest) (obj)).bitField0_ | 1;
            obj.clientType_ = ((ClientType) (s)).value;
            if (context.getResources().getBoolean(0x7f0c0016))
            {
                s = DeviceType.ANDROID_TABLET;
            } else
            {
                s = DeviceType.ANDROID_PHONE;
            }
            builder.copyOnWrite();
            obj = (LoggingRequest)builder.instance;
            if (s == null)
            {
                throw new NullPointerException();
            }
            obj.bitField0_ = ((LoggingRequest) (obj)).bitField0_ | 2;
            obj.deviceType_ = ((DeviceType) (s)).value;
            s = (com.google.personalization.assist.annotate.api.AssistClick.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)AssistClick.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            obj = taskTitle;
            s.copyOnWrite();
            AssistClick assistclick = (AssistClick)((com.google.personalization.assist.annotate.api.AssistClick.Builder) (s)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            assistclick.bitField0_ = assistclick.bitField0_ | 1;
            assistclick.taskString_ = ((String) (obj));
            obj = getAssistType();
            s.copyOnWrite();
            assistclick = (AssistClick)((com.google.personalization.assist.annotate.api.AssistClick.Builder) (s)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            assistclick.bitField0_ = assistclick.bitField0_ | 2;
            assistclick.assistType_ = ((AssistType) (obj)).value;
            obj = grammarRuleType;
            s.copyOnWrite();
            assistclick = (AssistClick)((com.google.personalization.assist.annotate.api.AssistClick.Builder) (s)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            assistclick.bitField0_ = assistclick.bitField0_ | 4;
            assistclick.grammarRuleType_ = ((GrammarRuleType) (obj)).value;
            builder.copyOnWrite();
            obj = (LoggingRequest)builder.instance;
            obj.assistClick_ = (AssistClick)(GeneratedMessageLite)s.build();
            obj.bitField0_ = ((LoggingRequest) (obj)).bitField0_ | 0x40;
            s = context.getResources().getConfiguration().locale;
            if (s != null)
            {
                Object obj1 = s.getLanguage();
                builder.copyOnWrite();
                LoggingRequest loggingrequest = (LoggingRequest)builder.instance;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                loggingrequest.bitField0_ = loggingrequest.bitField0_ | 4;
                loggingrequest.language_ = ((String) (obj1));
                s = s.getCountry();
                builder.copyOnWrite();
                obj1 = (LoggingRequest)builder.instance;
                if (s == null)
                {
                    throw new NullPointerException();
                }
                obj1.bitField0_ = ((LoggingRequest) (obj1)).bitField0_ | 8;
                obj1.country_ = s;
            }
            s = (LoggingRequest)(GeneratedMessageLite)builder.build();
            LogUtils.logOnFailure((new TaskAssistService(context, taskUser, flag)).startRequest(new com.google.android.calendar.net.taskassist.TaskAssistService..Lambda._cls2(s)), TAG, "Error logging assist click: %s", new Object[] {
                s
            });
        }
    }

    protected final void startActivity(Context context, Intent intent)
    {
        try
        {
            context.getApplicationContext().startActivity(intent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Intent intent)
        {
            LogUtils.i(TAG, intent, "Unable to find activity for intent", new Object[0]);
            Toast.makeText(context, context.getString(0x7f130089, new Object[] {
                getAssistActionText(context)
            }), 0).show();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e(TAG, context, "Unexpected error starting an activity for intent", new Object[0]);
        }
    }

}
