// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;


public interface 
    extends 
{

    public abstract void onAddNoteClicked();

    public abstract void onProposeNewTimeClicked();

    public abstract void onRsvpClicked(com.google.android.calendar.api.event.attendee. , int i, boolean flag);

    public abstract void showRsvpUpdateScopeSelectionDialog(com.google.android.calendar.api.event.attendee. );
}
