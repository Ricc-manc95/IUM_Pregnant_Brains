// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.widget.ListView;

public interface ShowableListMenu
{

    public abstract void dismiss();

    public abstract ListView getListView();

    public abstract boolean isShowing();

    public abstract void show();
}
