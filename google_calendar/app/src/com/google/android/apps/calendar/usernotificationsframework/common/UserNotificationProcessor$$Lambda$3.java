// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import com.google.android.apps.calendar.usernotificationsframework.contracts.UserNotification;
import com.google.common.base.Predicate;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.common:
//            UserNotificationProcessor

final class arg._cls1
    implements Predicate
{

    private final Map arg$1;

    public final boolean apply(Object obj)
    {
        return UserNotificationProcessor.lambda$scheduleNextCheck$3$UserNotificationProcessor(arg$1, (UserNotification)obj);
    }

    (Map map)
    {
        arg$1 = map;
    }
}
