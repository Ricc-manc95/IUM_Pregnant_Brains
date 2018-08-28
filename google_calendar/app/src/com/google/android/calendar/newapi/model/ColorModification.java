// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.color.EventColor;

public interface ColorModification
{

    public abstract boolean canModifyColorOverride();

    public abstract EntityColor getColor();

    public abstract void setColorOverride(EventColor eventcolor);
}
