// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.net.Uri;

public class RecipientEntry
{

    public final long contactId;
    public final String destination;
    private final String destinationLabel;
    private final int destinationType;
    public final String displayName;
    public final int entryType;
    private int indicatorIconId;
    private String indicatorText;
    private boolean isFirstLevel;
    public boolean isValid;
    public final String lookupKey;
    private byte photoBytes[];
    public final Uri photoThumbnailUri;

    public RecipientEntry(int i, String s, String s1, int j, String s2, long l, 
            Long long1, long l1, Uri uri, boolean flag, boolean flag1, String s3, 
            String as[])
    {
        this(i, s, s1, j, s2, l, long1, l1, uri, true, flag, flag1, s3, as);
    }

    private RecipientEntry(int i, String s, String s1, int j, String s2, long l, 
            Long long1, long l1, Uri uri, boolean flag, boolean flag1, boolean flag2, 
            String s3, String as[])
    {
        entryType = i;
        isFirstLevel = flag1;
        displayName = s;
        destination = s1;
        destinationType = j;
        destinationLabel = s2;
        contactId = l;
        photoThumbnailUri = uri;
        photoBytes = null;
        isValid = flag2;
        lookupKey = s3;
        indicatorIconId = 0;
        indicatorText = null;
    }

    public static RecipientEntry constructTopLevelEntry(String s, int i, String s1, int j, String s2, long l, Long long1, 
            long l1, String s3, boolean flag, String s4)
    {
        if (i <= 20)
        {
            s = s1;
        }
        if (s3 != null)
        {
            s3 = Uri.parse(s3);
        } else
        {
            s3 = null;
        }
        return new RecipientEntry(0, s, s1, j, s2, l, long1, l1, s3, true, true, s4, null);
    }

    public final byte[] getPhotoBytes()
    {
        this;
        JVM INSTR monitorenter ;
        byte abyte0[] = photoBytes;
        this;
        JVM INSTR monitorexit ;
        return abyte0;
        Exception exception;
        exception;
        throw exception;
    }

    public final void setPhotoBytes(byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        photoBytes = abyte0;
        this;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        throw abyte0;
    }

    public String toString()
    {
        String s = displayName;
        String s1 = destination;
        boolean flag = isValid;
        return (new StringBuilder(String.valueOf(s).length() + 18 + String.valueOf(s1).length())).append(s).append(" <").append(s1).append(">, isValid=").append(flag).toString();
    }
}
