// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            BottomBarController, CommandBar, BottomBar

public abstract class CommandBarController extends BottomBarController
{

    public CommandBarController(Object obj)
    {
        super(obj);
    }

    protected final BottomBar inflateCommandBar(Context context, ViewGroup viewgroup)
    {
        return (CommandBar)LayoutInflater.from(context).inflate(0x7f0500c5, viewgroup, false);
    }
}
