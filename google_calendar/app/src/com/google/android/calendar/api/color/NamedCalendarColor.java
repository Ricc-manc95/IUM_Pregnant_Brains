// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;


// Referenced classes of package com.google.android.calendar.api.color:
//            CalendarColor

public abstract class NamedCalendarColor
    implements CalendarColor
{

    public NamedCalendarColor()
    {
    }

    public abstract int getNameIndex();

    public abstract int getNamesArray();
}
