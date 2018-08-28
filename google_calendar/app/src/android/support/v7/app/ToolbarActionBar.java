// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.app:
//            ActionBar

final class ToolbarActionBar extends ActionBar
{

    public DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private boolean mMenuCallbackSet;
    private final android.support.v7.widget.Toolbar.OnMenuItemClickListener mMenuClicker = new _cls2();
    private final Runnable mMenuInvalidator = new _cls1();
    private ArrayList mMenuVisibilityListeners;
    public boolean mToolbarMenuPrepared;
    public android.view.Window.Callback mWindowCallback;

    ToolbarActionBar(Toolbar toolbar, CharSequence charsequence, android.view.Window.Callback callback)
    {
        mMenuVisibilityListeners = new ArrayList();
        mDecorToolbar = new ToolbarWidgetWrapper(toolbar, false);
        mWindowCallback = new ToolbarCallbackWrapper(callback);
        mDecorToolbar.setWindowCallback(mWindowCallback);
        toolbar.mOnMenuItemClickListener = mMenuClicker;
        mDecorToolbar.setWindowTitle(charsequence);
    }

    public final boolean closeOptionsMenu()
    {
        return mDecorToolbar.hideOverflowMenu();
    }

    public final boolean collapseActionView()
    {
        if (mDecorToolbar.hasExpandedActionView())
        {
            mDecorToolbar.collapseActionView();
            return true;
        } else
        {
            return false;
        }
    }

    public final void dispatchMenuVisibilityChanged(boolean flag)
    {
        if (flag != mLastMenuVisibility)
        {
            mLastMenuVisibility = flag;
            int j = mMenuVisibilityListeners.size();
            int i = 0;
            while (i < j) 
            {
                ((ActionBar.OnMenuVisibilityListener)mMenuVisibilityListeners.get(i)).onMenuVisibilityChanged$51D2ILG_0();
                i++;
            }
        }
    }

    public final int getDisplayOptions()
    {
        return mDecorToolbar.getDisplayOptions();
    }

    public final int getHeight()
    {
        return mDecorToolbar.getHeight();
    }

    final Menu getMenu()
    {
        if (!mMenuCallbackSet)
        {
            mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            mMenuCallbackSet = true;
        }
        return mDecorToolbar.getMenu();
    }

    public final Context getThemedContext()
    {
        return mDecorToolbar.getContext();
    }

    public final void hide()
    {
        mDecorToolbar.setVisibility(8);
    }

    public final boolean invalidateOptionsMenu()
    {
        mDecorToolbar.getViewGroup().removeCallbacks(mMenuInvalidator);
        ViewCompat.postOnAnimation(mDecorToolbar.getViewGroup(), mMenuInvalidator);
        return true;
    }

    final void onDestroy()
    {
        mDecorToolbar.getViewGroup().removeCallbacks(mMenuInvalidator);
    }

    public final boolean onKeyShortcut(int i, KeyEvent keyevent)
    {
        boolean flag = false;
        Menu menu = getMenu();
        if (menu != null)
        {
            int j;
            if (keyevent != null)
            {
                j = keyevent.getDeviceId();
            } else
            {
                j = -1;
            }
            if (KeyCharacterMap.load(j).getKeyboardType() != 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            menu.setQwertyMode(flag);
            flag = menu.performShortcut(i, keyevent, 0);
        }
        return flag;
    }

    public final boolean onMenuKeyEvent(KeyEvent keyevent)
    {
        if (keyevent.getAction() == 1)
        {
            openOptionsMenu();
        }
        return true;
    }

    public final boolean openOptionsMenu()
    {
        return mDecorToolbar.showOverflowMenu();
    }

    public final void setBackgroundDrawable(Drawable drawable)
    {
        mDecorToolbar.setBackgroundDrawable(drawable);
    }

    public final void setCustomView(View view, ActionBar.LayoutParams layoutparams)
    {
        if (view != null)
        {
            view.setLayoutParams(layoutparams);
        }
        mDecorToolbar.setCustomView(view);
    }

    public final void setDefaultDisplayHomeAsUpEnabled(boolean flag)
    {
    }

    public final void setDisplayHomeAsUpEnabled(boolean flag)
    {
        byte byte0;
        int i;
        if (flag)
        {
            byte0 = 4;
        } else
        {
            byte0 = 0;
        }
        i = mDecorToolbar.getDisplayOptions();
        mDecorToolbar.setDisplayOptions(byte0 & 4 | i & -5);
    }

    public final void setDisplayOptions(int i)
    {
        i = mDecorToolbar.getDisplayOptions();
        mDecorToolbar.setDisplayOptions(i & 0 | 0x12);
    }

    public final void setDisplayShowHomeEnabled(boolean flag)
    {
        int i = mDecorToolbar.getDisplayOptions();
        mDecorToolbar.setDisplayOptions(i & -3 | 2);
    }

    public final void setDisplayShowTitleEnabled(boolean flag)
    {
        int i = mDecorToolbar.getDisplayOptions();
        mDecorToolbar.setDisplayOptions(i & -9 | 8);
    }

    public final void setDisplayUseLogoEnabled(boolean flag)
    {
        int i = mDecorToolbar.getDisplayOptions();
        mDecorToolbar.setDisplayOptions(i & -2 | 0);
    }

    public final void setElevation(float f)
    {
        ViewCompat.setElevation(mDecorToolbar.getViewGroup(), f);
    }

    public final void setHomeActionContentDescription(int i)
    {
        mDecorToolbar.setNavigationContentDescription(i);
    }

    public final void setHomeAsUpIndicator(Drawable drawable)
    {
        mDecorToolbar.setNavigationIcon(drawable);
    }

    public final void setHomeButtonEnabled(boolean flag)
    {
    }

    public final void setIcon(int i)
    {
        mDecorToolbar.setIcon(i);
    }

    public final void setIcon(Drawable drawable)
    {
        mDecorToolbar.setIcon(drawable);
    }

    public final void setLogo(Drawable drawable)
    {
        mDecorToolbar.setLogo(null);
    }

    public final void setShowHideAnimationEnabled(boolean flag)
    {
    }

    public final void setTitle(int i)
    {
        DecorToolbar decortoolbar = mDecorToolbar;
        CharSequence charsequence;
        if (i != 0)
        {
            charsequence = mDecorToolbar.getContext().getText(i);
        } else
        {
            charsequence = null;
        }
        decortoolbar.setTitle(charsequence);
    }

    public final void setTitle(CharSequence charsequence)
    {
        mDecorToolbar.setTitle(charsequence);
    }

    public final void setWindowTitle(CharSequence charsequence)
    {
        mDecorToolbar.setWindowTitle(charsequence);
    }

    public final void show()
    {
        mDecorToolbar.setVisibility(0);
    }

    private class _cls1
        implements Runnable
    {

        private final ToolbarActionBar this$0;

        public final void run()
        {
            MenuBuilder menubuilder;
            ToolbarActionBar toolbaractionbar = ToolbarActionBar.this;
            Menu menu = toolbaractionbar.getMenu();
            if (menu instanceof MenuBuilder)
            {
                menubuilder = (MenuBuilder)menu;
            } else
            {
                menubuilder = null;
            }
            if (menubuilder != null && !menubuilder.mPreventDispatchingItemsChanged)
            {
                menubuilder.mPreventDispatchingItemsChanged = true;
                menubuilder.mItemsChangedWhileDispatchPrevented = false;
                menubuilder.mStructureChangedWhileDispatchPrevented = false;
            }
            menu.clear();
            if (!toolbaractionbar.mWindowCallback.onCreatePanelMenu(0, menu) || !toolbaractionbar.mWindowCallback.onPreparePanel(0, null, menu))
            {
                menu.clear();
            }
            if (menubuilder != null)
            {
                menubuilder.mPreventDispatchingItemsChanged = false;
                if (menubuilder.mItemsChangedWhileDispatchPrevented)
                {
                    menubuilder.mItemsChangedWhileDispatchPrevented = false;
                    menubuilder.onItemsChanged(menubuilder.mStructureChangedWhileDispatchPrevented);
                }
            }
            return;
            Exception exception;
            exception;
            if (menubuilder != null)
            {
                menubuilder.mPreventDispatchingItemsChanged = false;
                if (menubuilder.mItemsChangedWhileDispatchPrevented)
                {
                    menubuilder.mItemsChangedWhileDispatchPrevented = false;
                    menubuilder.onItemsChanged(menubuilder.mStructureChangedWhileDispatchPrevented);
                }
            }
            throw exception;
        }

        _cls1()
        {
            this$0 = ToolbarActionBar.this;
            super();
        }
    }


    private class _cls2
        implements android.support.v7.widget.Toolbar.OnMenuItemClickListener
    {

        private final ToolbarActionBar this$0;

        public final boolean onMenuItemClick(MenuItem menuitem)
        {
            return mWindowCallback.onMenuItemSelected(0, menuitem);
        }

        _cls2()
        {
            this$0 = ToolbarActionBar.this;
            super();
        }
    }


    private class ToolbarCallbackWrapper extends WindowCallbackWrapper
    {

        private final ToolbarActionBar this$0;

        public final View onCreatePanelView(int i)
        {
            if (i == 0)
            {
                return new View(mDecorToolbar.getContext());
            } else
            {
                return super.onCreatePanelView(i);
            }
        }

        public final boolean onPreparePanel(int i, View view, Menu menu)
        {
            boolean flag = super.onPreparePanel(i, view, menu);
            if (flag && !mToolbarMenuPrepared)
            {
                mDecorToolbar.setMenuPrepared();
                mToolbarMenuPrepared = true;
            }
            return flag;
        }

        public ToolbarCallbackWrapper(android.view.Window.Callback callback)
        {
            this$0 = ToolbarActionBar.this;
            super(callback);
        }
    }


    private class ActionMenuPresenterCallback
        implements android.support.v7.view.menu.MenuPresenter.Callback
    {

        private boolean mClosingActionMenu;
        private final ToolbarActionBar this$0;

        public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            if (mClosingActionMenu)
            {
                return;
            }
            mClosingActionMenu = true;
            mDecorToolbar.dismissPopupMenus();
            if (mWindowCallback != null)
            {
                mWindowCallback.onPanelClosed(108, menubuilder);
            }
            mClosingActionMenu = false;
        }

        public final boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            if (mWindowCallback != null)
            {
                mWindowCallback.onMenuOpened(108, menubuilder);
                return true;
            } else
            {
                return false;
            }
        }

        ActionMenuPresenterCallback()
        {
            this$0 = ToolbarActionBar.this;
            super();
        }
    }


    private class MenuBuilderCallback
        implements android.support.v7.view.menu.MenuBuilder.Callback
    {

        private final ToolbarActionBar this$0;

        public final boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
        {
            return false;
        }

        public final void onMenuModeChange(MenuBuilder menubuilder)
        {
            if (mWindowCallback != null)
            {
                if (mDecorToolbar.isOverflowMenuShowing())
                {
                    mWindowCallback.onPanelClosed(108, menubuilder);
                } else
                if (mWindowCallback.onPreparePanel(0, null, menubuilder))
                {
                    mWindowCallback.onMenuOpened(108, menubuilder);
                    return;
                }
            }
        }

        MenuBuilderCallback()
        {
            this$0 = ToolbarActionBar.this;
            super();
        }
    }

}
