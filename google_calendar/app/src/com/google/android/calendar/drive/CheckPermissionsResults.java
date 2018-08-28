// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import java.util.ArrayList;

public final class CheckPermissionsResults
{

    public final String fixabilitySummary;
    public final ArrayList potentialFixes;

    public CheckPermissionsResults(String s, ArrayList arraylist)
    {
        fixabilitySummary = s;
        potentialFixes = arraylist;
    }
}
