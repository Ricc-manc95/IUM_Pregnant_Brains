// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;


// Referenced classes of package com.google.android.calendar.api.event.conference:
//            ConferenceData, CreatedConference

public abstract class ConferenceDataModifications extends ConferenceData
{

    public ConferenceDataModifications()
    {
    }

    public abstract void clear();

    public abstract void cloneFrom(CreatedConference createdconference);

    public abstract void createNewConference(int i);

    public abstract ConferenceData getOriginal();

    public abstract boolean isModified();
}
