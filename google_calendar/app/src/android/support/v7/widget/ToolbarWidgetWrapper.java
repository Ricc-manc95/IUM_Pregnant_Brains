// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageButton;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v7.widget:
//            DecorToolbar, Toolbar, TintTypedArray, RtlSpacingHelper, 
//            ActionMenuView, ActionMenuPresenter, ScrollingTabContainerView

public final class ToolbarWidgetWrapper
    implements DecorToolbar
{

    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    public boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode;
    private CharSequence mSubtitle;
    private View mTabView;
    public CharSequence mTitle;
    private boolean mTitleSet;
    public Toolbar mToolbar;
    public android.view.Window.Callback mWindowCallback;

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean flag)
    {
        this(toolbar, flag, 0x7f130020);
    }

    private ToolbarWidgetWrapper(Toolbar toolbar, boolean flag, int i)
    {
        Object obj1 = null;
        super();
        mNavigationMode = 0;
        mDefaultNavigationContentDescription = 0;
        mToolbar = toolbar;
        mTitle = toolbar.mTitleText;
        mSubtitle = toolbar.mSubtitleText;
        Object obj;
        boolean flag1;
        if (mTitle != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mTitleSet = flag1;
        if (toolbar.mNavButtonView != null)
        {
            obj = toolbar.mNavButtonView.getDrawable();
        } else
        {
            obj = null;
        }
        mNavIcon = ((Drawable) (obj));
        toolbar = toolbar.getContext();
        obj = new TintTypedArray(toolbar, toolbar.obtainStyledAttributes(null, android.support.v7.appcompat.R.styleable.ActionBar, 0x7f01007e, 0));
        mDefaultNavigationIcon = ((TintTypedArray) (obj)).getDrawable(android.support.v7.appcompat.R.styleable.ActionBar_homeAsUpIndicator);
        if (flag)
        {
            int j = android.support.v7.appcompat.R.styleable.ActionBar_title;
            toolbar = ((TintTypedArray) (obj)).mWrapped.getText(j);
            if (!TextUtils.isEmpty(toolbar))
            {
                mTitleSet = true;
                mTitle = toolbar;
                if ((mDisplayOpts & 8) != 0)
                {
                    mToolbar.setTitle(toolbar);
                }
            }
            j = android.support.v7.appcompat.R.styleable.ActionBar_subtitle;
            toolbar = ((TintTypedArray) (obj)).mWrapped.getText(j);
            if (!TextUtils.isEmpty(toolbar))
            {
                mSubtitle = toolbar;
                if ((mDisplayOpts & 8) != 0)
                {
                    mToolbar.setSubtitle(toolbar);
                }
            }
            toolbar = ((TintTypedArray) (obj)).getDrawable(android.support.v7.appcompat.R.styleable.ActionBar_logo);
            if (toolbar != null)
            {
                mLogo = toolbar;
                updateToolbarLogo();
            }
            toolbar = ((TintTypedArray) (obj)).getDrawable(android.support.v7.appcompat.R.styleable.ActionBar_icon);
            if (toolbar != null)
            {
                mIcon = toolbar;
                updateToolbarLogo();
            }
            if (mNavIcon == null && mDefaultNavigationIcon != null)
            {
                mNavIcon = mDefaultNavigationIcon;
                updateNavigationIcon();
            }
            j = android.support.v7.appcompat.R.styleable.ActionBar_displayOptions;
            setDisplayOptions(((TintTypedArray) (obj)).mWrapped.getInt(j, 0));
            j = android.support.v7.appcompat.R.styleable.ActionBar_customNavigationLayout;
            j = ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0);
            if (j != 0)
            {
                setCustomView(LayoutInflater.from(mToolbar.getContext()).inflate(j, mToolbar, false));
                setDisplayOptions(mDisplayOpts | 0x10);
            }
            j = android.support.v7.appcompat.R.styleable.ActionBar_height;
            j = ((TintTypedArray) (obj)).mWrapped.getLayoutDimension(j, 0);
            if (j > 0)
            {
                toolbar = mToolbar.getLayoutParams();
                toolbar.height = j;
                mToolbar.setLayoutParams(toolbar);
            }
            j = android.support.v7.appcompat.R.styleable.ActionBar_contentInsetStart;
            j = ((TintTypedArray) (obj)).mWrapped.getDimensionPixelOffset(j, -1);
            int l = android.support.v7.appcompat.R.styleable.ActionBar_contentInsetEnd;
            l = ((TintTypedArray) (obj)).mWrapped.getDimensionPixelOffset(l, -1);
            if (j >= 0 || l >= 0)
            {
                toolbar = mToolbar;
                j = Math.max(j, 0);
                l = Math.max(l, 0);
                if (toolbar.mContentInsets == null)
                {
                    toolbar.mContentInsets = new RtlSpacingHelper();
                }
                toolbar.mContentInsets.setRelative(j, l);
            }
            j = android.support.v7.appcompat.R.styleable.ActionBar_titleTextStyle;
            j = ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0);
            if (j != 0)
            {
                toolbar = mToolbar;
                Context context = mToolbar.getContext();
                toolbar.mTitleTextAppearance = j;
                if (toolbar.mTitleTextView != null)
                {
                    toolbar.mTitleTextView.setTextAppearance(context, j);
                }
            }
            j = android.support.v7.appcompat.R.styleable.ActionBar_subtitleTextStyle;
            j = ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0);
            if (j != 0)
            {
                toolbar = mToolbar;
                Context context1 = mToolbar.getContext();
                toolbar.mSubtitleTextAppearance = j;
                if (toolbar.mSubtitleTextView != null)
                {
                    toolbar.mSubtitleTextView.setTextAppearance(context1, j);
                }
            }
            j = android.support.v7.appcompat.R.styleable.ActionBar_popupTheme;
            j = ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0);
            if (j != 0)
            {
                mToolbar.setPopupTheme(j);
            }
        } else
        {
            int k = 11;
            toolbar = mToolbar;
            if (toolbar.mNavButtonView != null)
            {
                toolbar = toolbar.mNavButtonView.getDrawable();
            } else
            {
                toolbar = null;
            }
            if (toolbar != null)
            {
                toolbar = mToolbar;
                if (toolbar.mNavButtonView != null)
                {
                    toolbar = toolbar.mNavButtonView.getDrawable();
                } else
                {
                    toolbar = null;
                }
                mDefaultNavigationIcon = toolbar;
                k = 15;
            }
            mDisplayOpts = k;
        }
        ((TintTypedArray) (obj)).mWrapped.recycle();
        if (i != mDefaultNavigationContentDescription)
        {
            mDefaultNavigationContentDescription = i;
            toolbar = mToolbar;
            if (toolbar.mNavButtonView != null)
            {
                toolbar = toolbar.mNavButtonView.getContentDescription();
            } else
            {
                toolbar = null;
            }
            if (TextUtils.isEmpty(toolbar))
            {
                i = mDefaultNavigationContentDescription;
                if (i == 0)
                {
                    toolbar = null;
                } else
                {
                    toolbar = mToolbar.getContext().getString(i);
                }
                mHomeDescription = toolbar;
                updateHomeAccessibility();
            }
        }
        obj = mToolbar;
        toolbar = obj1;
        if (((Toolbar) (obj)).mNavButtonView != null)
        {
            toolbar = ((Toolbar) (obj)).mNavButtonView.getContentDescription();
        }
        mHomeDescription = toolbar;
        toolbar = mToolbar;
        obj = new _cls1();
        toolbar.ensureNavButtonView();
        toolbar.mNavButtonView.setOnClickListener(((android.view.View.OnClickListener) (obj)));
    }

    private final void updateHomeAccessibility()
    {
        if ((mDisplayOpts & 4) != 0)
        {
            if (TextUtils.isEmpty(mHomeDescription))
            {
                mToolbar.setNavigationContentDescription(mDefaultNavigationContentDescription);
            } else
            {
                Toolbar toolbar = mToolbar;
                CharSequence charsequence = mHomeDescription;
                if (!TextUtils.isEmpty(charsequence))
                {
                    toolbar.ensureNavButtonView();
                }
                if (toolbar.mNavButtonView != null)
                {
                    toolbar.mNavButtonView.setContentDescription(charsequence);
                    return;
                }
            }
        }
    }

    private final void updateNavigationIcon()
    {
        if ((mDisplayOpts & 4) != 0)
        {
            Toolbar toolbar = mToolbar;
            Drawable drawable;
            if (mNavIcon != null)
            {
                drawable = mNavIcon;
            } else
            {
                drawable = mDefaultNavigationIcon;
            }
            toolbar.setNavigationIcon(drawable);
            return;
        } else
        {
            mToolbar.setNavigationIcon(null);
            return;
        }
    }

    private final void updateToolbarLogo()
    {
        Drawable drawable = null;
        if ((mDisplayOpts & 2) != 0)
        {
            if ((mDisplayOpts & 1) != 0)
            {
                if (mLogo != null)
                {
                    drawable = mLogo;
                } else
                {
                    drawable = mIcon;
                }
            } else
            {
                drawable = mIcon;
            }
        }
        mToolbar.setLogo(drawable);
    }

    public final boolean canShowOverflowMenu()
    {
        Toolbar toolbar = mToolbar;
        return toolbar.getVisibility() == 0 && toolbar.mMenuView != null && toolbar.mMenuView.mReserveOverflow;
    }

    public final void collapseActionView()
    {
        Object obj = mToolbar;
        if (((Toolbar) (obj)).mExpandedMenuPresenter == null)
        {
            obj = null;
        } else
        {
            obj = ((Toolbar) (obj)).mExpandedMenuPresenter.mCurrentExpandedItem;
        }
        if (obj != null)
        {
            ((MenuItemImpl) (obj)).collapseActionView();
        }
    }

    public final void dismissPopupMenus()
    {
        Toolbar toolbar = mToolbar;
        if (toolbar.mMenuView != null)
        {
            toolbar.mMenuView.dismissPopupMenus();
        }
    }

    public final Context getContext()
    {
        return mToolbar.getContext();
    }

    public final int getDisplayOptions()
    {
        return mDisplayOpts;
    }

    public final int getHeight()
    {
        return mToolbar.getHeight();
    }

    public final Menu getMenu()
    {
        return mToolbar.getMenu();
    }

    public final int getNavigationMode()
    {
        return 0;
    }

    public final ViewGroup getViewGroup()
    {
        return mToolbar;
    }

    public final boolean hasExpandedActionView()
    {
        Toolbar toolbar = mToolbar;
        return toolbar.mExpandedMenuPresenter != null && toolbar.mExpandedMenuPresenter.mCurrentExpandedItem != null;
    }

    public final boolean hideOverflowMenu()
    {
        Object obj = mToolbar;
        if (((Toolbar) (obj)).mMenuView != null)
        {
            obj = ((Toolbar) (obj)).mMenuView;
            boolean flag;
            if (((ActionMenuView) (obj)).mPresenter != null && ((ActionMenuView) (obj)).mPresenter.hideOverflowMenu())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public final void initIndeterminateProgress()
    {
    }

    public final void initProgress()
    {
    }

    public final boolean isOverflowMenuShowPending()
    {
        Object obj = mToolbar;
        if (((Toolbar) (obj)).mMenuView == null) goto _L2; else goto _L1
_L1:
        obj = ((Toolbar) (obj)).mMenuView;
        if (((ActionMenuView) (obj)).mPresenter == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        obj = ((ActionMenuView) (obj)).mPresenter;
        if (((ActionMenuPresenter) (obj)).mPostedOpenRunnable != null || ((ActionMenuPresenter) (obj)).isOverflowMenuShowing())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L5
_L5:
        flag = true;
_L6:
        if (flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L6; else goto _L2
_L2:
        return false;
    }

    public final boolean isOverflowMenuShowing()
    {
        return mToolbar.isOverflowMenuShowing();
    }

    public final void setBackgroundDrawable(Drawable drawable)
    {
        ViewCompat.setBackground(mToolbar, drawable);
    }

    public final void setCollapsible(boolean flag)
    {
        Toolbar toolbar = mToolbar;
        toolbar.mCollapsible = flag;
        toolbar.requestLayout();
    }

    public final void setCustomView(View view)
    {
        if (mCustomView != null && (mDisplayOpts & 0x10) != 0)
        {
            mToolbar.removeView(mCustomView);
        }
        mCustomView = view;
        if (view != null && (mDisplayOpts & 0x10) != 0)
        {
            mToolbar.addView(mCustomView);
        }
    }

    public final void setDisplayOptions(int i)
    {
label0:
        {
            int j = mDisplayOpts ^ i;
            mDisplayOpts = i;
            if (j != 0)
            {
                if ((j & 4) != 0)
                {
                    if ((i & 4) != 0)
                    {
                        updateHomeAccessibility();
                    }
                    updateNavigationIcon();
                }
                if ((j & 3) != 0)
                {
                    updateToolbarLogo();
                }
                if ((j & 8) != 0)
                {
                    if ((i & 8) != 0)
                    {
                        mToolbar.setTitle(mTitle);
                        mToolbar.setSubtitle(mSubtitle);
                    } else
                    {
                        mToolbar.setTitle(null);
                        mToolbar.setSubtitle(null);
                    }
                }
                if ((j & 0x10) != 0 && mCustomView != null)
                {
                    if ((i & 0x10) == 0)
                    {
                        break label0;
                    }
                    mToolbar.addView(mCustomView);
                }
            }
            return;
        }
        mToolbar.removeView(mCustomView);
    }

    public final void setEmbeddedTabView(ScrollingTabContainerView scrollingtabcontainerview)
    {
        if (mTabView != null && mTabView.getParent() == mToolbar)
        {
            mToolbar.removeView(mTabView);
        }
        mTabView = scrollingtabcontainerview;
    }

    public final void setHomeButtonEnabled$51D2ILG_0()
    {
    }

    public final void setIcon(int i)
    {
        Drawable drawable;
        if (i != 0)
        {
            drawable = AppCompatResources.getDrawable(mToolbar.getContext(), i);
        } else
        {
            drawable = null;
        }
        mIcon = drawable;
        updateToolbarLogo();
    }

    public final void setIcon(Drawable drawable)
    {
        mIcon = drawable;
        updateToolbarLogo();
    }

    public final void setLogo(int i)
    {
        Drawable drawable;
        if (i != 0)
        {
            drawable = AppCompatResources.getDrawable(mToolbar.getContext(), i);
        } else
        {
            drawable = null;
        }
        mLogo = drawable;
        updateToolbarLogo();
    }

    public final void setLogo(Drawable drawable)
    {
        mLogo = drawable;
        updateToolbarLogo();
    }

    public final void setMenu(Menu menu, android.support.v7.view.menu.MenuPresenter.Callback callback)
    {
        if (mActionMenuPresenter == null)
        {
            mActionMenuPresenter = new ActionMenuPresenter(mToolbar.getContext());
        }
        mActionMenuPresenter.mCallback = callback;
        callback = mToolbar;
        menu = (MenuBuilder)menu;
        ActionMenuPresenter actionmenupresenter = mActionMenuPresenter;
        if (menu != null || ((Toolbar) (callback)).mMenuView != null)
        {
            callback.ensureMenuView();
            MenuBuilder menubuilder = ((Toolbar) (callback)).mMenuView.mMenu;
            if (menubuilder != menu)
            {
                if (menubuilder != null)
                {
                    menubuilder.removeMenuPresenter(((Toolbar) (callback)).mOuterActionMenuPresenter);
                    menubuilder.removeMenuPresenter(((Toolbar) (callback)).mExpandedMenuPresenter);
                }
                if (((Toolbar) (callback)).mExpandedMenuPresenter == null)
                {
                    callback.mExpandedMenuPresenter = new Toolbar.ExpandedActionViewMenuPresenter(callback);
                }
                actionmenupresenter.mExpandedActionViewsExclusive = true;
                if (menu != null)
                {
                    Object obj = ((Toolbar) (callback)).mPopupContext;
                    ((MenuBuilder) (menu)).mPresenters.add(new WeakReference(actionmenupresenter));
                    actionmenupresenter.initForMenu(((Context) (obj)), menu);
                    menu.mIsActionItemsStale = true;
                    obj = ((Toolbar) (callback)).mExpandedMenuPresenter;
                    Context context = ((Toolbar) (callback)).mPopupContext;
                    ((MenuBuilder) (menu)).mPresenters.add(new WeakReference(obj));
                    ((MenuPresenter) (obj)).initForMenu(context, menu);
                    menu.mIsActionItemsStale = true;
                } else
                {
                    actionmenupresenter.initForMenu(((Toolbar) (callback)).mPopupContext, null);
                    ((Toolbar) (callback)).mExpandedMenuPresenter.initForMenu(((Toolbar) (callback)).mPopupContext, null);
                    actionmenupresenter.updateMenuView(true);
                    ((Toolbar) (callback)).mExpandedMenuPresenter.updateMenuView(true);
                }
                ((Toolbar) (callback)).mMenuView.setPopupTheme(((Toolbar) (callback)).mPopupTheme);
                ((Toolbar) (callback)).mMenuView.setPresenter(actionmenupresenter);
                callback.mOuterActionMenuPresenter = actionmenupresenter;
            }
        }
    }

    public final void setMenuCallbacks(android.support.v7.view.menu.MenuPresenter.Callback callback, android.support.v7.view.menu.MenuBuilder.Callback callback1)
    {
        Object obj = mToolbar;
        obj.mActionMenuPresenterCallback = callback;
        obj.mMenuBuilderCallback = callback1;
        if (((Toolbar) (obj)).mMenuView != null)
        {
            obj = ((Toolbar) (obj)).mMenuView;
            obj.mActionMenuPresenterCallback = callback;
            obj.mMenuBuilderCallback = callback1;
        }
    }

    public final void setMenuPrepared()
    {
        mMenuPrepared = true;
    }

    public final void setNavigationContentDescription(int i)
    {
        Object obj;
        if (i == 0)
        {
            obj = null;
        } else
        {
            obj = mToolbar.getContext().getString(i);
        }
        mHomeDescription = ((CharSequence) (obj));
        updateHomeAccessibility();
    }

    public final void setNavigationIcon(Drawable drawable)
    {
        mNavIcon = drawable;
        updateNavigationIcon();
    }

    public final void setTitle(CharSequence charsequence)
    {
        mTitleSet = true;
        mTitle = charsequence;
        if ((mDisplayOpts & 8) != 0)
        {
            mToolbar.setTitle(charsequence);
        }
    }

    public final void setVisibility(int i)
    {
        mToolbar.setVisibility(i);
    }

    public final void setWindowCallback(android.view.Window.Callback callback)
    {
        mWindowCallback = callback;
    }

    public final void setWindowTitle(CharSequence charsequence)
    {
        if (!mTitleSet)
        {
            mTitle = charsequence;
            if ((mDisplayOpts & 8) != 0)
            {
                mToolbar.setTitle(charsequence);
            }
        }
    }

    public final ViewPropertyAnimatorCompat setupAnimatorToVisibility(final int visibility, long l)
    {
        ViewPropertyAnimatorCompat viewpropertyanimatorcompat = ViewCompat.animate(mToolbar);
        float f;
        View view;
        if (visibility == 0)
        {
            f = 1.0F;
        } else
        {
            f = 0.0F;
        }
        view = (View)viewpropertyanimatorcompat.mView.get();
        if (view != null)
        {
            view.animate().alpha(f);
        }
        view = (View)viewpropertyanimatorcompat.mView.get();
        if (view != null)
        {
            view.animate().setDuration(l);
        }
        return viewpropertyanimatorcompat.setListener(new _cls2());
    }

    public final boolean showOverflowMenu()
    {
        return mToolbar.showOverflowMenu();
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final ActionMenuItem mNavItem;
        private final ToolbarWidgetWrapper this$0;

        public final void onClick(View view)
        {
            if (mWindowCallback != null && mMenuPrepared)
            {
                mWindowCallback.onMenuItemSelected(0, mNavItem);
            }
        }

        _cls1()
        {
            this$0 = ToolbarWidgetWrapper.this;
            super();
            mNavItem = new ActionMenuItem(mToolbar.getContext(), 0, 0x102002c, 0, 0, mTitle);
        }
    }


    private class _cls2 extends ViewPropertyAnimatorListenerAdapter
    {

        private boolean mCanceled;
        private final ToolbarWidgetWrapper this$0;
        private final int val$visibility;

        public final void onAnimationCancel(View view)
        {
            mCanceled = true;
        }

        public final void onAnimationEnd(View view)
        {
            if (!mCanceled)
            {
                mToolbar.setVisibility(visibility);
            }
        }

        public final void onAnimationStart(View view)
        {
            mToolbar.setVisibility(0);
        }

        _cls2()
        {
            this$0 = ToolbarWidgetWrapper.this;
            visibility = i;
            super();
            mCanceled = false;
        }
    }

}
