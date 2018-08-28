// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import java.util.List;

public interface ResultCreator
{

    public abstract Object createResult(String s, List list, boolean flag);

    public abstract void processSelectedSuggestion(List list, String s, String s1);
}
