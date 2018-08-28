// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.view.MenuItem;
import com.google.android.calendar.api.event.attachment.Attachment;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentEditSegment

final class val.removeMenuItem
    implements android.widget.kListener
{

    private final AttachmentEditSegment this$0;
    private final Attachment val$item;
    private final MenuItem val$removeMenuItem;
    private final MenuItem val$viewMenuItem;

    public final boolean onMenuItemClick(MenuItem menuitem)
    {
        if (menuitem == val$viewMenuItem)
        {
            ((stener)mListener).onOpenAttachment(val$item);
            return true;
        }
        if (menuitem == val$removeMenuItem)
        {
            ((stener)mListener).onRemoveAttachment(val$item);
            return true;
        } else
        {
            return false;
        }
    }

    stener()
    {
        this$0 = final_attachmenteditsegment;
        val$viewMenuItem = menuitem;
        val$item = attachment;
        val$removeMenuItem = MenuItem.this;
        super();
    }
}
