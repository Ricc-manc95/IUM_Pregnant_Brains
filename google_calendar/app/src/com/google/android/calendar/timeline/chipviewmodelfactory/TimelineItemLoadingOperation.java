// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import android.content.Context;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;

final class TimelineItemLoadingOperation extends TimelineItemOperation
{

    private final Context context;

    TimelineItemLoadingOperation(Context context1)
    {
        context = context1.getApplicationContext();
    }

    private final transient ListenableFuture onBirthdayBundle$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ589KN4T38CHGNIEQR9HL62TJ15TM62RJ75TB6UQB47CKKOORFDKNMERRFCTM6ABR3DTMMQRRE5TQN8QBC5THMURJ3ELP74PBEEGNKOQBJEHIMSOB2DHIKCTBKELP6AEO_0(TimelineBirthday timelinebirthday)
    {
        timelinebirthday.validate(context);
        class .Lambda._cls0
            implements Function
        {

            private final TimelineItemLoadingOperation arg$1;
            private final TimelineBirthday arg$2;

            public final Object apply(Object obj)
            {
                return arg$1.lambda$onBirthdayBundle$0$TimelineItemLoadingOperation$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ589KN4T38CHGNIEQCD9GNCO9FDHGMSPPFAPNMIP1R55666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ589KN4T38CHGNIEO_0(arg$2);
            }

            .Lambda._cls0(TimelineBirthday timelinebirthday)
            {
                arg$1 = TimelineItemLoadingOperation.this;
                arg$2 = timelinebirthday;
            }
        }

        return AbstractTransformFuture.create(timelinebirthday.loadBirthdaysAsync(context), new .Lambda._cls0(timelinebirthday), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    final TimelineBirthday lambda$onBirthdayBundle$0$TimelineItemLoadingOperation$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ589KN4T38CHGNIEQCD9GNCO9FDHGMSPPFAPNMIP1R55666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ589KN4T38CHGNIEO_0(TimelineBirthday timelinebirthday)
    {
        timelinebirthday.validate(context);
        return timelinebirthday;
    }

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        if (timelineitem == null)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(timelineitem);
        }
    }

    public final volatile Object onBirthdayBundle(TimelineBirthday timelinebirthday, Object aobj[])
    {
        return onBirthdayBundle$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL8QBDCLM6IRJ589KN4T38CHGNIEQR9HL62TJ15TM62RJ75TB6UQB47CKKOORFDKNMERRFCTM6ABR3DTMMQRRE5TQN8QBC5THMURJ3ELP74PBEEGNKOQBJEHIMSOB2DHIKCTBKELP6AEO_0(timelinebirthday);
    }

    public final Object onReminderBundle(TimelineTaskBundle timelinetaskbundle, Object aobj[])
    {
        timelinetaskbundle.updateTitles(context);
        return (ListenableFuture)super.onReminderBundle(timelinetaskbundle, new Void[0]);
    }
}
