// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.location;


// Referenced classes of package com.google.android.calendar.api.event.location:
//            StructuredLocation, EventLocation

public interface StructuredLocationModifications
    extends StructuredLocation
{

    public abstract void addEventLocation(EventLocation eventlocation);

    public abstract boolean isModified();

    public abstract void removeEventLocation(EventLocation eventlocation);
}