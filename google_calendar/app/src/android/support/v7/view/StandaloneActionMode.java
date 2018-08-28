// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionMenuPresenter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.view:
//            ActionMode, SupportMenuInflater

public final class StandaloneActionMode extends ActionMode
    implements android.support.v7.view.menu.MenuBuilder.Callback
{

    private ActionMode.Callback mCallback;
    private Context mContext;
    private ActionBarContextView mContextView;
    private WeakReference mCustomView;
    private boolean mFinished;
    private MenuBuilder mMenu;

    public StandaloneActionMode(Context context, ActionBarContextView actionbarcontextview, ActionMode.Callback callback, boolean flag)
    {
        mContext = context;
        mContextView = actionbarcontextview;
        mCallback = callback;
        context = new MenuBuilder(actionbarcontextview.getContext());
        context.mDefaultShowAsAction = 1;
        mMenu = context;
        mMenu.setCallback(this);
    }

    public final void finish()
    {
        if (mFinished)
        {
            return;
        } else
        {
            mFinished = true;
            mContextView.sendAccessibilityEvent(32);
            mCallback.onDestroyActionMode(this);
            return;
        }
    }

    public final View getCustomView()
    {
        if (mCustomView != null)
        {
            return (View)mCustomView.get();
        } else
        {
            return null;
        }
    }

    public final Menu getMenu()
    {
        return mMenu;
    }

    public final MenuInflater getMenuInflater()
    {
        return new SupportMenuInflater(mContextView.getContext());
    }

    public final CharSequence getSubtitle()
    {
        return mContextView.mSubtitle;
    }

    public final CharSequence getTitle()
    {
        return mContextView.mTitle;
    }

    public final void invalidate()
    {
        mCallback.onPrepareActionMode(this, mMenu);
    }

    public final boolean isTitleOptional()
    {
        return mContextView.mTitleOptional;
    }

    public final boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
    {
        return mCallback.onActionItemClicked(this, menuitem);
    }

    public final void onMenuModeChange(MenuBuilder menubuilder)
    {
        invalidate();
        menubuilder = mContextView;
        if (((ActionBarContextView) (menubuilder)).mActionMenuPresenter != null)
        {
            ((ActionBarContextView) (menubuilder)).mActionMenuPresenter.showOverflowMenu();
        }
    }

    public final void setCustomView(View view)
    {
        mContextView.setCustomView(view);
        if (view != null)
        {
            view = new WeakReference(view);
        } else
        {
            view = null;
        }
        mCustomView = view;
    }

    public final void setSubtitle(int i)
    {
        setSubtitle(mContext.getString(i));
    }

    public final void setSubtitle(CharSequence charsequence)
    {
        ActionBarContextView actionbarcontextview = mContextView;
        actionbarcontextview.mSubtitle = charsequence;
        actionbarcontextview.initTitle();
    }

    public final void setTitle(int i)
    {
        setTitle(mContext.getString(i));
    }

    public final void setTitle(CharSequence charsequence)
    {
        ActionBarContextView actionbarcontextview = mContextView;
        actionbarcontextview.mTitle = charsequence;
        actionbarcontextview.initTitle();
    }

    public final void setTitleOptionalHint(boolean flag)
    {
        super.setTitleOptionalHint(flag);
        ActionBarContextView actionbarcontextview = mContextView;
        if (flag != actionbarcontextview.mTitleOptional)
        {
            actionbarcontextview.requestLayout();
        }
        actionbarcontextview.mTitleOptional = flag;
    }
}
