// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import com.google.android.calendar.api.event.attachment.Attachment;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentEditSegment

public static interface 
{

    public abstract void onNewAttachmentClicked();

    public abstract void onOpenAttachment(Attachment attachment);

    public abstract void onRemoveAttachment(Attachment attachment);
}
