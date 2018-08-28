// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils;

import android.util.SparseIntArray;
import com.android.calendarcommon2.LogUtils;

public class NumberUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/NumberUtils);

    public NumberUtils()
    {
    }

    public static float clamp(float f, float f1, float f2)
    {
        if (f <= f1)
        {
            return f1;
        }
        if (f >= f2)
        {
            return f2;
        } else
        {
            return f;
        }
    }

    public static int clamp(int i, int j, int k)
    {
        if (i <= j)
        {
            return j;
        }
        if (i >= k)
        {
            return k;
        } else
        {
            return i;
        }
    }

    public static int closestDistanceTo(SparseIntArray sparseintarray, int i)
    {
        int k = 0x7fffffff;
        int j = 0;
        do
        {
            if (j >= sparseintarray.size())
            {
                break;
            }
            int l = Math.abs(sparseintarray.keyAt(j) - i);
            if (l >= k)
            {
                break;
            }
            j++;
            k = l;
        } while (true);
        return k;
    }

    public static float linearInterpolate(int i, int j, float f)
    {
        if (f < 0.0F || f > 1.0F)
        {
            LogUtils.wtf(TAG, "Value must be between %f and %f, found %f", new Object[] {
                Float.valueOf(0.0F), Float.valueOf(1.0F), Float.valueOf(f)
            });
        }
        if (f > 0.0F) goto _L2; else goto _L1
_L1:
        float f1 = 0.0F;
_L4:
        return (float)i + (float)(j - i) * f1;
_L2:
        f1 = f;
        if (f >= 1.0F)
        {
            f1 = 1.0F;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

}
