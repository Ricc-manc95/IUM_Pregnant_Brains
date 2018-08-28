// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import android.content.Context;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.avatar.ContactPhotoRequestKey;
import com.google.android.calendar.timely.TimelineItem;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.timeline.chipviewmodelfactory:
//            ChipViewModelFactory

final class arg._cls1
    implements Function
{

    private final ChipViewModelFactory arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (TimelineItem)obj;
        obj1 = ((ChipViewModelFactory) (obj1)).context.getApplicationContext();
        com.google.android.calendar.avatar.ModelFactory modelfactory = ContactInfo.newBuilder();
        modelfactory.ntName = ((TimelineItem) (obj)).getSourceAccountName();
        modelfactory.l = ((TimelineItem) (obj)).getOrganizer();
        return new ContactPhotoRequestKey(((Context) (obj1)), new ContactInfo(modelfactory));
    }

    (ChipViewModelFactory chipviewmodelfactory)
    {
        arg$1 = chipviewmodelfactory;
    }
}
