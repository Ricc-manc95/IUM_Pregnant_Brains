// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attachment;


// Referenced classes of package com.google.android.calendar.api.event.attachment:
//            AttachmentPermissions

public class ReadOnlyAttachmentPermissionsImpl
    implements AttachmentPermissions
{

    public ReadOnlyAttachmentPermissionsImpl()
    {
    }

    public boolean canAddAttachment()
    {
        return false;
    }

    public boolean canRemoveAttachment()
    {
        return false;
    }

    public boolean isReadOnly()
    {
        return true;
    }
}
