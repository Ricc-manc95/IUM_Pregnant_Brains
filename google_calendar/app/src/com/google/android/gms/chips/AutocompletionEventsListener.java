// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;


public interface AutocompletionEventsListener
{

    public abstract void queryUpdated(String s);

    public abstract void resultsReceived(int i, boolean flag);

    public abstract void selectionSessionStarted();
}
