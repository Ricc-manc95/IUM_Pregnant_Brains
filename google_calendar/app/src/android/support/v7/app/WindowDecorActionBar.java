// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.ActionMode;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.app:
//            ActionBar

public class WindowDecorActionBar extends ActionBar
    implements android.support.v7.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
{
    public final class ActionModeImpl extends ActionMode
        implements android.support.v7.view.menu.MenuBuilder.Callback
    {

        private final Context mActionModeContext;
        private android.support.v7.view.ActionMode.Callback mCallback;
        private WeakReference mCustomView;
        private final MenuBuilder mMenu;
        private final WindowDecorActionBar this$0;

        public final boolean dispatchOnCreate()
        {
            MenuBuilder menubuilder = mMenu;
            if (!menubuilder.mPreventDispatchingItemsChanged)
            {
                menubuilder.mPreventDispatchingItemsChanged = true;
                menubuilder.mItemsChangedWhileDispatchPrevented = false;
                menubuilder.mStructureChangedWhileDispatchPrevented = false;
            }
            boolean flag = mCallback.onCreateActionMode(this, mMenu);
            MenuBuilder menubuilder1 = mMenu;
            menubuilder1.mPreventDispatchingItemsChanged = false;
            if (menubuilder1.mItemsChangedWhileDispatchPrevented)
            {
                menubuilder1.mItemsChangedWhileDispatchPrevented = false;
                menubuilder1.onItemsChanged(menubuilder1.mStructureChangedWhileDispatchPrevented);
            }
            return flag;
            Exception exception;
            exception;
            MenuBuilder menubuilder2 = mMenu;
            menubuilder2.mPreventDispatchingItemsChanged = false;
            if (menubuilder2.mItemsChangedWhileDispatchPrevented)
            {
                menubuilder2.mItemsChangedWhileDispatchPrevented = false;
                menubuilder2.onItemsChanged(menubuilder2.mStructureChangedWhileDispatchPrevented);
            }
            throw exception;
        }

        public final void finish()
        {
            if (mActionMode != this)
            {
                return;
            }
            Object obj;
            boolean flag;
            if (!WindowDecorActionBar.checkShowingFlags(mHiddenByApp, mHiddenBySystem, false))
            {
                mDeferredDestroyActionMode = this;
                mDeferredModeDestroyCallback = mCallback;
            } else
            {
                mCallback.onDestroyActionMode(this);
            }
            mCallback = null;
            animateToMode(false);
            obj = mContextView;
            if (((ActionBarContextView) (obj)).mClose == null)
            {
                ((ActionBarContextView) (obj)).removeAllViews();
                obj.mCustomView = null;
                obj.mMenuView = null;
            }
            mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
            obj = mOverlayLayout;
            flag = mHideOnContentScroll;
            if (flag != ((ActionBarOverlayLayout) (obj)).mHideOnContentScroll)
            {
                obj.mHideOnContentScroll = flag;
                if (!flag)
                {
                    ((ActionBarOverlayLayout) (obj)).haltActionBarHideOffsetAnimations();
                    ((ActionBarOverlayLayout) (obj)).setActionBarHideOffset(0);
                }
            }
            mActionMode = null;
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
            return new SupportMenuInflater(mActionModeContext);
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
            if (mActionMode == this) goto _L2; else goto _L1
_L1:
            return;
_L2:
            MenuBuilder menubuilder = mMenu;
            if (!menubuilder.mPreventDispatchingItemsChanged)
            {
                menubuilder.mPreventDispatchingItemsChanged = true;
                menubuilder.mItemsChangedWhileDispatchPrevented = false;
                menubuilder.mStructureChangedWhileDispatchPrevented = false;
            }
            mCallback.onPrepareActionMode(this, mMenu);
            MenuBuilder menubuilder1;
            menubuilder1 = mMenu;
            menubuilder1.mPreventDispatchingItemsChanged = false;
            if (!menubuilder1.mItemsChangedWhileDispatchPrevented) goto _L1; else goto _L3
_L3:
            menubuilder1.mItemsChangedWhileDispatchPrevented = false;
            menubuilder1.onItemsChanged(menubuilder1.mStructureChangedWhileDispatchPrevented);
            return;
            Exception exception;
            exception;
            MenuBuilder menubuilder2 = mMenu;
            menubuilder2.mPreventDispatchingItemsChanged = false;
            if (menubuilder2.mItemsChangedWhileDispatchPrevented)
            {
                menubuilder2.mItemsChangedWhileDispatchPrevented = false;
                menubuilder2.onItemsChanged(menubuilder2.mStructureChangedWhileDispatchPrevented);
            }
            throw exception;
        }

        public final boolean isTitleOptional()
        {
            return mContextView.mTitleOptional;
        }

        public final boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
        {
            if (mCallback != null)
            {
                return mCallback.onActionItemClicked(this, menuitem);
            } else
            {
                return false;
            }
        }

        public final void onMenuModeChange(MenuBuilder menubuilder)
        {
            if (mCallback != null)
            {
                invalidate();
                menubuilder = mContextView;
                if (((ActionBarContextView) (menubuilder)).mActionMenuPresenter != null)
                {
                    ((ActionBarContextView) (menubuilder)).mActionMenuPresenter.showOverflowMenu();
                    return;
                }
            }
        }

        public final void setCustomView(View view)
        {
            mContextView.setCustomView(view);
            mCustomView = new WeakReference(view);
        }

        public final void setSubtitle(int i)
        {
            setSubtitle(mContext.getResources().getString(i));
        }

        public final void setSubtitle(CharSequence charsequence)
        {
            ActionBarContextView actionbarcontextview = mContextView;
            actionbarcontextview.mSubtitle = charsequence;
            actionbarcontextview.initTitle();
        }

        public final void setTitle(int i)
        {
            setTitle(mContext.getResources().getString(i));
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

        public ActionModeImpl(Context context, android.support.v7.view.ActionMode.Callback callback)
        {
            this$0 = WindowDecorActionBar.this;
            super();
            mActionModeContext = context;
            mCallback = callback;
            windowdecoractionbar = new MenuBuilder(context);
            mDefaultShowAsAction = 1;
            mMenu = WindowDecorActionBar.this;
            mMenu.setCallback(this);
        }
    }


    private static final Interpolator sHideInterpolator = new AccelerateInterpolator();
    private static final Interpolator sShowInterpolator = new DecelerateInterpolator();
    public ActionModeImpl mActionMode;
    public ActionBarContainer mContainerView;
    public boolean mContentAnimations;
    public View mContentView;
    public Context mContext;
    public ActionBarContextView mContextView;
    private int mCurWindowVisibility;
    public ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    public DecorToolbar mDecorToolbar;
    public ActionMode mDeferredDestroyActionMode;
    public android.support.v7.view.ActionMode.Callback mDeferredModeDestroyCallback;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    public boolean mHiddenByApp;
    public boolean mHiddenBySystem;
    private final ViewPropertyAnimatorListener mHideListener;
    public boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList mMenuVisibilityListeners;
    private boolean mNowShowing;
    public ActionBarOverlayLayout mOverlayLayout;
    private boolean mShowHideAnimationEnabled;
    private final ViewPropertyAnimatorListener mShowListener;
    private boolean mShowingForMode;
    private Context mThemedContext;
    private final ViewPropertyAnimatorUpdateListener mUpdateListener;

    public WindowDecorActionBar(Activity activity, boolean flag)
    {
        new ArrayList();
        mMenuVisibilityListeners = new ArrayList();
        mCurWindowVisibility = 0;
        mContentAnimations = true;
        mNowShowing = true;
        mHideListener = new _cls1();
        mShowListener = new _cls2();
        mUpdateListener = new _cls3();
        activity = activity.getWindow().getDecorView();
        init(activity);
        if (!flag)
        {
            mContentView = activity.findViewById(0x1020002);
        }
    }

    public WindowDecorActionBar(Dialog dialog)
    {
        new ArrayList();
        mMenuVisibilityListeners = new ArrayList();
        mCurWindowVisibility = 0;
        mContentAnimations = true;
        mNowShowing = true;
        mHideListener = new _cls1();
        mShowListener = new _cls2();
        mUpdateListener = new _cls3();
        init(dialog.getWindow().getDecorView());
    }

    static boolean checkShowingFlags(boolean flag, boolean flag1, boolean flag2)
    {
        while (flag2 || !flag && !flag1) 
        {
            return true;
        }
        return false;
    }

    private final void init(View view)
    {
        View view1;
        mOverlayLayout = (ActionBarOverlayLayout)view.findViewById(0x7f1000ee);
        if (mOverlayLayout != null)
        {
            ActionBarOverlayLayout actionbaroverlaylayout = mOverlayLayout;
            actionbaroverlaylayout.mActionBarVisibilityCallback = this;
            if (actionbaroverlaylayout.getWindowToken() != null)
            {
                actionbaroverlaylayout.mActionBarVisibilityCallback.onWindowVisibilityChanged(actionbaroverlaylayout.mWindowVisibility);
                if (actionbaroverlaylayout.mLastSystemUiVisibility != 0)
                {
                    actionbaroverlaylayout.onWindowSystemUiVisibilityChanged(actionbaroverlaylayout.mLastSystemUiVisibility);
                    ViewCompat.requestApplyInsets(actionbaroverlaylayout);
                }
            }
        }
        view1 = view.findViewById(0x7f1000f0);
        if (!(view1 instanceof DecorToolbar)) goto _L2; else goto _L1
_L1:
        Object obj = (DecorToolbar)view1;
_L4:
        mDecorToolbar = ((DecorToolbar) (obj));
        mContextView = (ActionBarContextView)view.findViewById(0x7f1000f1);
        mContainerView = (ActionBarContainer)view.findViewById(0x7f1000ef);
        if (mDecorToolbar == null || mContextView == null || mContainerView == null)
        {
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used with a compatible window decor layout").toString());
        }
        break MISSING_BLOCK_LABEL_257;
_L2:
        if (!(view1 instanceof Toolbar))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (Toolbar)view1;
        if (((Toolbar) (obj)).mWrapper == null)
        {
            obj.mWrapper = new ToolbarWidgetWrapper(((Toolbar) (obj)), true);
        }
        obj = ((Toolbar) (obj)).mWrapper;
        if (true) goto _L4; else goto _L3
_L3:
        StringBuilder stringbuilder = new StringBuilder("Can't make a decor toolbar out of ");
        if (view1 != null)
        {
            view = view1.getClass().getSimpleName();
        } else
        {
            view = "null";
        }
        throw new IllegalStateException(stringbuilder.append(view).toString());
        mContext = mDecorToolbar.getContext();
        int i;
        boolean flag;
        boolean flag1;
        if ((mDecorToolbar.getDisplayOptions() & 4) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            mDisplayHomeAsUpSet = true;
        }
        view = new ActionBarPolicy(mContext);
        if (((ActionBarPolicy) (view)).mContext.getApplicationInfo().targetSdkVersion < 14)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || i != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        setHomeButtonEnabled(flag1);
        setHasEmbeddedTabs(((ActionBarPolicy) (view)).mContext.getResources().getBoolean(0x7f0c0000));
        view = mContext.obtainStyledAttributes(null, android.support.v7.appcompat.R.styleable.ActionBar, 0x7f01007e, 0);
        if (view.getBoolean(android.support.v7.appcompat.R.styleable.ActionBar_hideOnContentScroll, false))
        {
            setHideOnContentScrollEnabled(true);
        }
        i = view.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.ActionBar_elevation, 0);
        if (i != 0)
        {
            setElevation(i);
        }
        view.recycle();
        return;
    }

    private final void setDisplayOptions(int i, int j)
    {
        int k = mDecorToolbar.getDisplayOptions();
        if ((j & 4) != 0)
        {
            mDisplayHomeAsUpSet = true;
        }
        mDecorToolbar.setDisplayOptions(k & ~j | i & j);
    }

    private final void setHasEmbeddedTabs(boolean flag)
    {
        boolean flag2 = true;
        mHasEmbeddedTabs = flag;
        Object obj;
        boolean flag1;
        if (!mHasEmbeddedTabs)
        {
            mDecorToolbar.setEmbeddedTabView(null);
            mContainerView.setTabContainer(null);
        } else
        {
            mContainerView.setTabContainer(null);
            mDecorToolbar.setEmbeddedTabView(null);
        }
        if (mDecorToolbar.getNavigationMode() == 2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj = mDecorToolbar;
        if (!mHasEmbeddedTabs && flag1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((DecorToolbar) (obj)).setCollapsible(flag);
        obj = mOverlayLayout;
        if (!mHasEmbeddedTabs && flag1)
        {
            flag = flag2;
        } else
        {
            flag = false;
        }
        obj.mHasNonEmbeddedTabs = flag;
    }

    private final void updateVisibility(boolean flag)
    {
        boolean flag2 = mHiddenByApp;
        boolean flag3 = mHiddenBySystem;
        boolean flag1;
        if (!mShowingForMode && (flag2 || flag3))
        {
            flag1 = false;
        } else
        {
            flag1 = true;
        }
        if (flag1)
        {
            if (!mNowShowing)
            {
                mNowShowing = true;
                if (mCurrentShowAnim != null)
                {
                    mCurrentShowAnim.cancel();
                }
                mContainerView.setVisibility(0);
                if (mCurWindowVisibility == 0 && (mShowHideAnimationEnabled || flag))
                {
                    mContainerView.setTranslationY(0.0F);
                    float f = -mContainerView.getHeight();
                    if (flag)
                    {
                        int ai[] = new int[2];
                        int[] _tmp = ai;
                        ai[0] = 0;
                        ai[1] = 0;
                        mContainerView.getLocationInWindow(ai);
                        f -= ai[1];
                    }
                    mContainerView.setTranslationY(f);
                    ViewPropertyAnimatorCompatSet viewpropertyanimatorcompatset = new ViewPropertyAnimatorCompatSet();
                    Object obj1 = ViewCompat.animate(mContainerView);
                    View view = (View)((ViewPropertyAnimatorCompat) (obj1)).mView.get();
                    if (view != null)
                    {
                        view.animate().translationY(0.0F);
                    }
                    ((ViewPropertyAnimatorCompat) (obj1)).setUpdateListener(mUpdateListener);
                    if (!viewpropertyanimatorcompatset.mIsStarted)
                    {
                        viewpropertyanimatorcompatset.mAnimators.add(obj1);
                    }
                    if (mContentAnimations && mContentView != null)
                    {
                        mContentView.setTranslationY(f);
                        obj1 = ViewCompat.animate(mContentView);
                        View view1 = (View)((ViewPropertyAnimatorCompat) (obj1)).mView.get();
                        if (view1 != null)
                        {
                            view1.animate().translationY(0.0F);
                        }
                        if (!viewpropertyanimatorcompatset.mIsStarted)
                        {
                            viewpropertyanimatorcompatset.mAnimators.add(obj1);
                        }
                    }
                    obj1 = sShowInterpolator;
                    if (!viewpropertyanimatorcompatset.mIsStarted)
                    {
                        viewpropertyanimatorcompatset.mInterpolator = ((Interpolator) (obj1));
                    }
                    if (!viewpropertyanimatorcompatset.mIsStarted)
                    {
                        viewpropertyanimatorcompatset.mDuration = 250L;
                    }
                    obj1 = mShowListener;
                    if (!viewpropertyanimatorcompatset.mIsStarted)
                    {
                        viewpropertyanimatorcompatset.mListener = ((ViewPropertyAnimatorListener) (obj1));
                    }
                    mCurrentShowAnim = viewpropertyanimatorcompatset;
                    viewpropertyanimatorcompatset.start();
                } else
                {
                    mContainerView.setAlpha(1.0F);
                    mContainerView.setTranslationY(0.0F);
                    if (mContentAnimations && mContentView != null)
                    {
                        mContentView.setTranslationY(0.0F);
                    }
                    mShowListener.onAnimationEnd(null);
                }
                if (mOverlayLayout != null)
                {
                    ViewCompat.requestApplyInsets(mOverlayLayout);
                }
            }
        } else
        if (mNowShowing)
        {
            mNowShowing = false;
            if (mCurrentShowAnim != null)
            {
                mCurrentShowAnim.cancel();
            }
            if (mCurWindowVisibility == 0 && (mShowHideAnimationEnabled || flag))
            {
                mContainerView.setAlpha(1.0F);
                Object obj = mContainerView;
                obj.mIsTransitioning = true;
                ((ActionBarContainer) (obj)).setDescendantFocusability(0x60000);
                obj = new ViewPropertyAnimatorCompatSet();
                float f1 = -mContainerView.getHeight();
                if (flag)
                {
                    int ai1[] = new int[2];
                    int[] _tmp1 = ai1;
                    ai1[0] = 0;
                    ai1[1] = 0;
                    mContainerView.getLocationInWindow(ai1);
                    f1 -= ai1[1];
                }
                Object obj2 = ViewCompat.animate(mContainerView);
                View view2 = (View)((ViewPropertyAnimatorCompat) (obj2)).mView.get();
                if (view2 != null)
                {
                    view2.animate().translationY(f1);
                }
                ((ViewPropertyAnimatorCompat) (obj2)).setUpdateListener(mUpdateListener);
                if (!((ViewPropertyAnimatorCompatSet) (obj)).mIsStarted)
                {
                    ((ViewPropertyAnimatorCompatSet) (obj)).mAnimators.add(obj2);
                }
                if (mContentAnimations && mContentView != null)
                {
                    obj2 = ViewCompat.animate(mContentView);
                    View view3 = (View)((ViewPropertyAnimatorCompat) (obj2)).mView.get();
                    if (view3 != null)
                    {
                        view3.animate().translationY(f1);
                    }
                    if (!((ViewPropertyAnimatorCompatSet) (obj)).mIsStarted)
                    {
                        ((ViewPropertyAnimatorCompatSet) (obj)).mAnimators.add(obj2);
                    }
                }
                obj2 = sHideInterpolator;
                if (!((ViewPropertyAnimatorCompatSet) (obj)).mIsStarted)
                {
                    obj.mInterpolator = ((Interpolator) (obj2));
                }
                if (!((ViewPropertyAnimatorCompatSet) (obj)).mIsStarted)
                {
                    obj.mDuration = 250L;
                }
                obj2 = mHideListener;
                if (!((ViewPropertyAnimatorCompatSet) (obj)).mIsStarted)
                {
                    obj.mListener = ((ViewPropertyAnimatorListener) (obj2));
                }
                mCurrentShowAnim = ((ViewPropertyAnimatorCompatSet) (obj));
                ((ViewPropertyAnimatorCompatSet) (obj)).start();
                return;
            } else
            {
                mHideListener.onAnimationEnd(null);
                return;
            }
        }
    }

    public final void animateToMode(boolean flag)
    {
        if (flag)
        {
            if (!mShowingForMode)
            {
                mShowingForMode = true;
                if (mOverlayLayout != null)
                {
                    ActionBarOverlayLayout.setShowingForActionMode$51D2ILG_0();
                }
                updateVisibility(false);
            }
        } else
        if (mShowingForMode)
        {
            mShowingForMode = false;
            if (mOverlayLayout != null)
            {
                ActionBarOverlayLayout.setShowingForActionMode$51D2ILG_0();
            }
            updateVisibility(false);
        }
        if (ViewCompat.isLaidOut(mContainerView))
        {
            ViewPropertyAnimatorCompat viewpropertyanimatorcompat;
            Object obj;
            ViewPropertyAnimatorCompatSet viewpropertyanimatorcompatset;
            long l;
            if (flag)
            {
                obj = mDecorToolbar.setupAnimatorToVisibility(4, 100L);
                viewpropertyanimatorcompat = mContextView.setupAnimatorToVisibility(0, 200L);
            } else
            {
                viewpropertyanimatorcompat = mDecorToolbar.setupAnimatorToVisibility(0, 200L);
                obj = mContextView.setupAnimatorToVisibility(8, 100L);
            }
            viewpropertyanimatorcompatset = new ViewPropertyAnimatorCompatSet();
            viewpropertyanimatorcompatset.mAnimators.add(obj);
            obj = (View)((ViewPropertyAnimatorCompat) (obj)).mView.get();
            if (obj != null)
            {
                l = ((View) (obj)).animate().getDuration();
            } else
            {
                l = 0L;
            }
            obj = (View)viewpropertyanimatorcompat.mView.get();
            if (obj != null)
            {
                ((View) (obj)).animate().setStartDelay(l);
            }
            viewpropertyanimatorcompatset.mAnimators.add(viewpropertyanimatorcompat);
            viewpropertyanimatorcompatset.start();
            return;
        }
        if (flag)
        {
            mDecorToolbar.setVisibility(4);
            mContextView.setVisibility(0);
            return;
        } else
        {
            mDecorToolbar.setVisibility(0);
            mContextView.setVisibility(8);
            return;
        }
    }

    public final boolean collapseActionView()
    {
        if (mDecorToolbar != null && mDecorToolbar.hasExpandedActionView())
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

    public final void enableContentAnimations(boolean flag)
    {
        mContentAnimations = flag;
    }

    public final int getDisplayOptions()
    {
        return mDecorToolbar.getDisplayOptions();
    }

    public final int getHeight()
    {
        return mContainerView.getHeight();
    }

    public final Context getThemedContext()
    {
        if (mThemedContext == null)
        {
            TypedValue typedvalue = new TypedValue();
            mContext.getTheme().resolveAttribute(0x7f010081, typedvalue, true);
            int i = typedvalue.resourceId;
            if (i != 0)
            {
                mThemedContext = new ContextThemeWrapper(mContext, i);
            } else
            {
                mThemedContext = mContext;
            }
        }
        return mThemedContext;
    }

    public final void hide()
    {
        if (!mHiddenByApp)
        {
            mHiddenByApp = true;
            updateVisibility(false);
        }
    }

    public final void hideForSystem()
    {
        if (!mHiddenBySystem)
        {
            mHiddenBySystem = true;
            updateVisibility(true);
        }
    }

    public final void onConfigurationChanged(Configuration configuration)
    {
        setHasEmbeddedTabs((new ActionBarPolicy(mContext)).mContext.getResources().getBoolean(0x7f0c0000));
    }

    public final void onContentScrollStarted()
    {
        if (mCurrentShowAnim != null)
        {
            mCurrentShowAnim.cancel();
            mCurrentShowAnim = null;
        }
    }

    public final void onContentScrollStopped()
    {
    }

    public final boolean onKeyShortcut(int i, KeyEvent keyevent)
    {
        Menu menu;
        if (mActionMode != null)
        {
            if ((menu = mActionMode.getMenu()) != null)
            {
                int j;
                boolean flag;
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
                return menu.performShortcut(i, keyevent, 0);
            }
        }
        return false;
    }

    public final void onWindowVisibilityChanged(int i)
    {
        mCurWindowVisibility = i;
    }

    public final void setBackgroundDrawable(Drawable drawable)
    {
        ActionBarContainer actionbarcontainer;
        boolean flag;
        flag = true;
        actionbarcontainer = mContainerView;
        if (actionbarcontainer.mBackground != null)
        {
            actionbarcontainer.mBackground.setCallback(null);
            actionbarcontainer.unscheduleDrawable(actionbarcontainer.mBackground);
        }
        actionbarcontainer.mBackground = drawable;
        if (drawable != null)
        {
            drawable.setCallback(actionbarcontainer);
            if (actionbarcontainer.mActionBarView != null)
            {
                actionbarcontainer.mBackground.setBounds(actionbarcontainer.mActionBarView.getLeft(), actionbarcontainer.mActionBarView.getTop(), actionbarcontainer.mActionBarView.getRight(), actionbarcontainer.mActionBarView.getBottom());
            }
        }
        if (!actionbarcontainer.mIsSplit) goto _L2; else goto _L1
_L1:
        if (actionbarcontainer.mSplitBackground != null)
        {
            flag = false;
        }
_L4:
        actionbarcontainer.setWillNotDraw(flag);
        actionbarcontainer.invalidate();
        return;
_L2:
        if (actionbarcontainer.mBackground != null || actionbarcontainer.mStackedBackground != null)
        {
            flag = false;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setCustomView(View view, ActionBar.LayoutParams layoutparams)
    {
        view.setLayoutParams(layoutparams);
        mDecorToolbar.setCustomView(view);
    }

    public final void setDefaultDisplayHomeAsUpEnabled(boolean flag)
    {
        if (!mDisplayHomeAsUpSet)
        {
            setDisplayHomeAsUpEnabled(flag);
        }
    }

    public final void setDisplayHomeAsUpEnabled(boolean flag)
    {
        byte byte0;
        if (flag)
        {
            byte0 = 4;
        } else
        {
            byte0 = 0;
        }
        setDisplayOptions(byte0, 4);
    }

    public final void setDisplayOptions(int i)
    {
        mDecorToolbar.setDisplayOptions(18);
    }

    public final void setDisplayShowHomeEnabled(boolean flag)
    {
        setDisplayOptions(2, 2);
    }

    public final void setDisplayShowTitleEnabled(boolean flag)
    {
        setDisplayOptions(8, 8);
    }

    public final void setDisplayUseLogoEnabled(boolean flag)
    {
        setDisplayOptions(0, 1);
    }

    public final void setElevation(float f)
    {
        ViewCompat.setElevation(mContainerView, f);
    }

    public final void setHideOnContentScrollEnabled(boolean flag)
    {
        if (!mOverlayLayout.mOverlayMode)
        {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        mHideOnContentScroll = true;
        ActionBarOverlayLayout actionbaroverlaylayout = mOverlayLayout;
        if (!actionbaroverlaylayout.mHideOnContentScroll)
        {
            actionbaroverlaylayout.mHideOnContentScroll = true;
        }
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
        mDecorToolbar.setHomeButtonEnabled$51D2ILG_0();
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
        mShowHideAnimationEnabled = flag;
        if (!flag && mCurrentShowAnim != null)
        {
            mCurrentShowAnim.cancel();
        }
    }

    public final void setTitle(int i)
    {
        setTitle(mContext.getString(i));
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
        if (mHiddenByApp)
        {
            mHiddenByApp = false;
            updateVisibility(false);
        }
    }

    public final void showForSystem()
    {
        if (mHiddenBySystem)
        {
            mHiddenBySystem = false;
            updateVisibility(true);
        }
    }

    public final ActionMode startActionMode(android.support.v7.view.ActionMode.Callback callback)
    {
        if (mActionMode != null)
        {
            mActionMode.finish();
        }
        Object obj = mOverlayLayout;
        if (((ActionBarOverlayLayout) (obj)).mHideOnContentScroll)
        {
            obj.mHideOnContentScroll = false;
            ((ActionBarOverlayLayout) (obj)).haltActionBarHideOffsetAnimations();
            ((ActionBarOverlayLayout) (obj)).setActionBarHideOffset(0);
        }
        obj = mContextView;
        ((ActionBarContextView) (obj)).removeAllViews();
        obj.mCustomView = null;
        obj.mMenuView = null;
        callback = new ActionModeImpl(mContextView.getContext(), callback);
        if (callback.dispatchOnCreate())
        {
            mActionMode = callback;
            callback.invalidate();
            mContextView.initForMode(callback);
            animateToMode(true);
            mContextView.sendAccessibilityEvent(32);
            return callback;
        } else
        {
            return null;
        }
    }


    private class _cls1 extends ViewPropertyAnimatorListenerAdapter
    {

        private final WindowDecorActionBar this$0;

        public final void onAnimationEnd(View view)
        {
            if (mContentAnimations && mContentView != null)
            {
                mContentView.setTranslationY(0.0F);
                mContainerView.setTranslationY(0.0F);
            }
            mContainerView.setVisibility(8);
            view = mContainerView;
            view.mIsTransitioning = false;
            view.setDescendantFocusability(0x40000);
            mCurrentShowAnim = null;
            view = WindowDecorActionBar.this;
            if (((WindowDecorActionBar) (view)).mDeferredModeDestroyCallback != null)
            {
                ((WindowDecorActionBar) (view)).mDeferredModeDestroyCallback.onDestroyActionMode(((WindowDecorActionBar) (view)).mDeferredDestroyActionMode);
                view.mDeferredDestroyActionMode = null;
                view.mDeferredModeDestroyCallback = null;
            }
            if (mOverlayLayout != null)
            {
                ViewCompat.requestApplyInsets(mOverlayLayout);
            }
        }

        _cls1()
        {
            this$0 = WindowDecorActionBar.this;
            super();
        }
    }


    private class _cls2 extends ViewPropertyAnimatorListenerAdapter
    {

        private final WindowDecorActionBar this$0;

        public final void onAnimationEnd(View view)
        {
            mCurrentShowAnim = null;
            mContainerView.requestLayout();
        }

        _cls2()
        {
            this$0 = WindowDecorActionBar.this;
            super();
        }
    }


    private class _cls3
        implements ViewPropertyAnimatorUpdateListener
    {

        private final WindowDecorActionBar this$0;

        public final void onAnimationUpdate$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
        {
            ((View)mContainerView.getParent()).invalidate();
        }

        _cls3()
        {
            this$0 = WindowDecorActionBar.this;
            super();
        }
    }

}
