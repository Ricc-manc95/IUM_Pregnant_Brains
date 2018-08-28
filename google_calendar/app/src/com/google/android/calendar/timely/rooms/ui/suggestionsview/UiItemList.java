// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.UiItem;

public interface UiItemList
{

    public abstract void addItem(UiItem uiitem);

    public abstract void clear();

    public abstract boolean contains(UiItem uiitem);

    public abstract void removeItem(UiItem uiitem);

    public abstract void replaceItem(UiItem uiitem, UiItem uiitem1);
}
