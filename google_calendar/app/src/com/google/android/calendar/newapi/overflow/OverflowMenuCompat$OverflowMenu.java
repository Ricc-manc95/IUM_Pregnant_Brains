// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.overflow;

import android.view.Menu;

public interface mClickCallback
{

    public abstract Menu getMenu();

    public abstract void init(int i);

    public abstract void setCallback(mClickCallback mclickcallback);
}
