// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attachment;


public interface AttachmentPermissions
{

    public abstract boolean canAddAttachment();

    public abstract boolean canRemoveAttachment();

    public abstract boolean isReadOnly();
}
