// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.contracts;

import com.android.calendarcommon2.LogUtils;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.contracts:
//            UserNotification, UserNotificationState

public interface UserNotificationPlugin
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/usernotificationsframework/contracts/UserNotificationPlugin);

    public abstract int getId();

    public abstract ListenableFuture getRelevantNotifications(long l, long l1);

    public abstract boolean onNotificationStateUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELPMASJEDTQ6IPJ9CDGN8QBFDPPMCSJ1DLINERRIDCNM6RREEHP62ORKECNLASR5E976UT39CPKM6OBKD5NMSEQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UTBJCLP6SRRKD5J6IOR1EHKMURJJCPP62RB5ETNN4QPFCDNMST3IC5HN8SPFALPMASIEDTQ6IPJ9CDGN8QBFDP9N8OBKCKTKOORFDKNMERRFCTM6ABR1DPI74RR9CGNM2S3GECNM6OBCCLN68OBI5TQN6PBIDPNN8QB6D5HM2T39DTN76PJIC5MMATRFE9LIUORFDPQ74OB3EHPIULBJCLP4SRRKD5J6IOR1EHKMURIJEHGN8P9R9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQFE1Q6IRREC5M3MAAQ0(UserNotification usernotification, UserNotificationState usernotificationstate, UserNotificationState usernotificationstate1);

    public abstract Optional relevanceIntervalMillis(UserNotification usernotification);

    public abstract boolean shouldReshowNotification(UserNotification usernotification, UserNotificationState usernotificationstate);

}
