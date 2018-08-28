// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.text.format.Time;
import android.view.View;

public final class Utils
{

    public static int convertDayOfWeekFromCalendarToTime(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Argument must be between Calendar.SUNDAY and Calendar.SATURDAY");

        case 1: // '\001'
            return 0;

        case 2: // '\002'
            return 1;

        case 3: // '\003'
            return 2;

        case 4: // '\004'
            return 3;

        case 5: // '\005'
            return 4;

        case 6: // '\006'
            return 5;

        case 7: // '\007'
            return 6;
        }
    }

    public static int getDaysInMonth(int i, int j)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("Invalid Month");

        case 0: // '\0'
        case 2: // '\002'
        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
        case 11: // '\013'
            return 31;

        case 3: // '\003'
        case 5: // '\005'
        case 8: // '\b'
        case 10: // '\n'
            return 30;

        case 1: // '\001'
            break;
        }
        return j % 4 != 0 ? 28 : 29;
    }

    public static ObjectAnimator getPulseAnimator(View view, float f, float f1)
    {
        Keyframe keyframe = Keyframe.ofFloat(0.0F, 1.0F);
        Keyframe keyframe1 = Keyframe.ofFloat(0.275F, f);
        Keyframe keyframe2 = Keyframe.ofFloat(0.69F, f1);
        Keyframe keyframe3 = Keyframe.ofFloat(1.0F, 1.0F);
        view = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofKeyframe("scaleX", new Keyframe[] {
                keyframe, keyframe1, keyframe2, keyframe3
            }), PropertyValuesHolder.ofKeyframe("scaleY", new Keyframe[] {
                keyframe, keyframe1, keyframe2, keyframe3
            })
        });
        view.setDuration(544L);
        return view;
    }

    public static int getWeekNumberInYear(int i, int j)
    {
        int k = 4 - j;
        j = k;
        if (k < 0)
        {
            j = k + 7;
        }
        i = (i - (0x253d8c - j)) / 7;
        Time time = new Time("UTC");
        time.setJulianDay(i * 7 + 0x253d89);
        return time.getWeekNumber();
    }
}
