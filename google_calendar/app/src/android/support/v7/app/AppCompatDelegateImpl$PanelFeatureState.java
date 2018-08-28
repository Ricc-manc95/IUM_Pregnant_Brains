// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

public final class refreshDecorView
{

    public int background;
    public View createdPanelView;
    public ViewGroup decorView;
    public int featureId;
    public Bundle frozenActionViewState;
    public int gravity;
    public boolean isHandled;
    public boolean isOpen;
    public boolean isPrepared;
    public ListMenuPresenter listMenuPresenter;
    public Context listPresenterContext;
    public MenuBuilder menu;
    public boolean qwertyMode;
    public boolean refreshDecorView;
    public boolean refreshMenuContent;
    public View shownPanelView;
    public int windowAnimations;

    final void setMenu(MenuBuilder menubuilder)
    {
        if (menubuilder != menu)
        {
            if (menu != null)
            {
                menu.removeMenuPresenter(listMenuPresenter);
            }
            menu = menubuilder;
            if (menubuilder != null && listMenuPresenter != null)
            {
                ListMenuPresenter listmenupresenter = listMenuPresenter;
                Context context = menubuilder.mContext;
                menubuilder.mPresenters.add(new WeakReference(listmenupresenter));
                listmenupresenter.initForMenu(context, menubuilder);
                menubuilder.mIsActionItemsStale = true;
                return;
            }
        }
    }

    (int i)
    {
        featureId = i;
        refreshDecorView = false;
    }
}
