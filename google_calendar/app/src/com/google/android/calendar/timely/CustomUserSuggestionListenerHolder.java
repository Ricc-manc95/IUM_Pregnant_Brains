// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.ArrayList;
import java.util.List;

public final class CustomUserSuggestionListenerHolder
{

    public static final CustomUserSuggestionListenerHolder INSTANCE = new CustomUserSuggestionListenerHolder();
    public final List createCustomSuggestionChangedListeners = new ArrayList();

    private CustomUserSuggestionListenerHolder()
    {
    }

}