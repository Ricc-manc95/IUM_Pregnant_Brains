// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attachment;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.attachment:
//            Attachment

public interface AttachmentModifications
    extends Parcelable
{

    public abstract void addAttachment(Attachment attachment);

    public abstract boolean isModified();

    public abstract void removeAttachment(Attachment attachment);
}
