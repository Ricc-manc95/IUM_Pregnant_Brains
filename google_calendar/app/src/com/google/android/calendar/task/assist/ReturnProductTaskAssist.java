// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.res.Resources;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.ReturnProductAssistance;
import com.google.personalization.assist.annotate.api.Time;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.task.assist:
//            UrlTaskAssist, TaskAssistHolder

public final class ReturnProductTaskAssist extends UrlTaskAssist
{

    private final ReturnProductAssistance returnProductAssistance;

    public ReturnProductTaskAssist(Assistance assistance, Assistance assistance1, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance1, grammarruletype, "shipment", s, s1);
        if (assistance.returnProductAssistance_ == null)
        {
            assistance = ReturnProductAssistance.DEFAULT_INSTANCE;
        } else
        {
            assistance = assistance.returnProductAssistance_;
        }
        returnProductAssistance = assistance;
    }

    public final String getAssistInfoText(Context context)
    {
        if (returnProductAssistance.alwaysReturnable_)
        {
            return context.getString(0x7f1303fd);
        }
        if ((returnProductAssistance.bitField0_ & 0x20) == 32)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(10, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            long l = calendar.getTimeInMillis();
            Object obj = returnProductAssistance;
            int i;
            long l1;
            if (((ReturnProductAssistance) (obj)).returnDeadline_ == null)
            {
                obj = Time.DEFAULT_INSTANCE;
            } else
            {
                obj = ((ReturnProductAssistance) (obj)).returnDeadline_;
            }
            calendar.setTimeInMillis(((Time) (obj)).timeMs_);
            calendar.set(10, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            l1 = calendar.getTimeInMillis();
            i = (int)TimeUnit.MILLISECONDS.toDays(l1 - l);
            if (i > 0)
            {
                return context.getResources().getQuantityString(0x7f120043, i, new Object[] {
                    Integer.valueOf(i)
                });
            } else
            {
                return context.getString(0x7f1303fe);
            }
        } else
        {
            return null;
        }
    }

    public final String getDescription(Context context)
    {
        if (getAssistInfoText(context) != null)
        {
            return context.getString(0x7f13017f);
        } else
        {
            return null;
        }
    }

    public final int getIconId()
    {
        return 0x7f020236;
    }
}
