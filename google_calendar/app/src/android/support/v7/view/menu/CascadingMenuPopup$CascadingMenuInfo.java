// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.support.v7.widget.MenuPopupWindow;

// Referenced classes of package android.support.v7.view.menu:
//            MenuBuilder

final class position
{

    public final MenuBuilder menu;
    public final int position;
    public final MenuPopupWindow window;

    public (MenuPopupWindow menupopupwindow, MenuBuilder menubuilder, int i)
    {
        window = menupopupwindow;
        menu = menubuilder;
        position = i;
    }
}
