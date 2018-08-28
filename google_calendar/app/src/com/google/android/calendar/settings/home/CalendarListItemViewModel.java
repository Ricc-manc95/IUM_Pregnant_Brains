// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.content.res.Resources;
import com.google.android.calendar.api.color.CalendarColor;

public interface CalendarListItemViewModel
{

    public abstract CalendarColor getColor();

    public abstract CharSequence getDisplayName(Resources resources);
}
