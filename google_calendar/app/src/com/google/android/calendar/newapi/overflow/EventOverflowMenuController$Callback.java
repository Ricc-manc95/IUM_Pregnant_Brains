// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import java.util.List;

public interface 
{

    public abstract void onCopyToClicked(List list);

    public abstract void onDeleteClicked();

    public abstract void onDuplicateClicked();

    public abstract void onEmailGuestsClicked(boolean flag);

    public abstract void onForwardEventClicked();
}