// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            DrivePermissionChecker

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return DrivePermissionChecker.lambda$getAttachmentIds$1$DrivePermissionChecker((Attachment)obj);
    }


    private ()
    {
    }
}
