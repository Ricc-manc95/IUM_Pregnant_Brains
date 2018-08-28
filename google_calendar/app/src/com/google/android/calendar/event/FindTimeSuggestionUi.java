// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import com.google.android.calendar.timely.SuggestionRows;
import com.google.android.calendar.timely.findatime.ui.DurationTimeframe;
import java.util.List;

public interface FindTimeSuggestionUi
{

    public abstract void customizeSystemDecorations();

    public abstract String getLoadingString();

    public abstract void setBackFromFilters();

    public abstract void setBackFromGrid();

    public abstract void setException();

    public abstract void setListener(Listener listener);

    public abstract void setLoading(DurationTimeframe durationtimeframe);

    public abstract void setNoSuggestion(List list, List list1);

    public abstract void setSuggestions$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL6TB7CTIN6T39DTN54RRNECTKOQJ1EPGIUTBKD5M2UJ39EDQ3MJ3AC5R62BRLEHKMOBQCD5PN8EP9AO______0(SuggestionRows suggestionrows);

    public abstract void showMore();
}
