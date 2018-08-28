// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attachment;


// Referenced classes of package com.google.android.calendar.api.event.attachment:
//            Attachment

public static final class title
{

    public String fileId;
    public String fileUrl;
    public String iconLink;
    public String mimeType;
    public String title;

    public final Attachment build()
    {
        return new Attachment(fileId, fileUrl, iconLink, mimeType, title);
    }

    public I()
    {
        fileId = "";
        fileUrl = "";
        iconLink = "";
        mimeType = null;
        title = "";
    }
}
