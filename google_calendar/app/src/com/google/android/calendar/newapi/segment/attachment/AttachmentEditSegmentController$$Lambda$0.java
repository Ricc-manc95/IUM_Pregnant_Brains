// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentEditSegmentController

final class arg._cls1
    implements Predicate
{

    private final String arg$1;

    public final boolean apply(Object obj)
    {
        return AttachmentEditSegmentController.lambda$containsAttachment$0$AttachmentEditSegmentController(arg$1, (Attachment)obj);
    }

    (String s)
    {
        arg$1 = s;
    }
}
