// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import com.android.calendarcommon2.LogUtils;

public final class FitIntegrationOperation extends Enum
    implements com.google.calendar.v2a.android.util.metric.MetricUtils.Operation
{

    private static final FitIntegrationOperation $VALUES[];
    private static final FitIntegrationOperation ACTIVITY_CHECK;
    private static final FitIntegrationOperation INTEGRATION_DISABLE;
    private static final FitIntegrationOperation SUBSCRIPTION_REFRESH;
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/belong/FitIntegrationOperation);
    private final String action;

    private FitIntegrationOperation(String s, int i, String s1)
    {
        super(s, i);
        action = s1;
    }

    public static FitIntegrationOperation getOperationForAction(String s)
    {
        byte byte0;
        if (s == null)
        {
            LogUtils.e(TAG, "Null action", new Object[0]);
            return null;
        }
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 3: default 60
    //                   -28480746: 121
    //                   1692950261: 107
    //                   2077537696: 135;
           goto _L1 _L2 _L3 _L4
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_135;
_L5:
        switch (byte0)
        {
        default:
            LogUtils.e(TAG, "Unknown actions: %s", new Object[] {
                s
            });
            return null;

        case 0: // '\0'
            return ACTIVITY_CHECK;

        case 1: // '\001'
            return SUBSCRIPTION_REFRESH;

        case 2: // '\002'
            return INTEGRATION_DISABLE;
        }
_L3:
        if (s.equals("com.google.android.calendar.intent.action.FIT_ACTIVITY_CHECK"))
        {
            byte0 = 0;
        }
          goto _L5
_L2:
        if (s.equals("com.google.android.calendar.intent.action.FIT_SUBSCRIPTION_REFRESH"))
        {
            byte0 = 1;
        }
          goto _L5
        if (s.equals("com.google.android.calendar.intent.action.FIT_DISABLE_INTEGRATION"))
        {
            byte0 = 2;
        }
          goto _L5
    }

    public static FitIntegrationOperation[] values()
    {
        return (FitIntegrationOperation[])$VALUES.clone();
    }

    public final String getAction()
    {
        return action;
    }

    public final String getCategory()
    {
        return "Fit";
    }

    public final String getFullName()
    {
        return com.google.calendar.v2a.android.util.metric.MetricUtils.Operation..CC.getFullName(this);
    }

    public final double getSampling()
    {
        return 1.0D;
    }

    static 
    {
        ACTIVITY_CHECK = new FitIntegrationOperation("ACTIVITY_CHECK", 0, "Activity.Check");
        SUBSCRIPTION_REFRESH = new FitIntegrationOperation("SUBSCRIPTION_REFRESH", 1, "Subscription.Refresh");
        INTEGRATION_DISABLE = new FitIntegrationOperation("INTEGRATION_DISABLE", 2, "Integration.Disable");
        $VALUES = (new FitIntegrationOperation[] {
            ACTIVITY_CHECK, SUBSCRIPTION_REFRESH, INTEGRATION_DISABLE
        });
    }
}
