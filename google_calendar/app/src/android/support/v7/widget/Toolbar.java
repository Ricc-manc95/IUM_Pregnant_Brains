// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.SubMenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray, RtlSpacingHelper, AppCompatImageView, ActionMenuView, 
//            AppCompatImageButton, ActionMenuPresenter, ViewUtils, AppCompatTextView, 
//            ToolbarWidgetWrapper

public class Toolbar extends ViewGroup
{
    final class ExpandedActionViewMenuPresenter
        implements MenuPresenter
    {

        public MenuItemImpl mCurrentExpandedItem;
        private MenuBuilder mMenu;
        private final Toolbar this$0;

        public final boolean collapseItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
        {
            if (mExpandedActionView instanceof CollapsibleActionView)
            {
                ((CollapsibleActionView)mExpandedActionView).onActionViewCollapsed();
            }
            removeView(mExpandedActionView);
            removeView(mCollapseButtonView);
            mExpandedActionView = null;
            Toolbar toolbar = Toolbar.this;
            for (int i = toolbar.mHiddenViews.size() - 1; i >= 0; i--)
            {
                toolbar.addView((View)toolbar.mHiddenViews.get(i));
            }

            toolbar.mHiddenViews.clear();
            mCurrentExpandedItem = null;
            requestLayout();
            menuitemimpl.mIsActionViewExpanded = false;
            menuitemimpl.mMenu.onItemsChanged(false);
            return true;
        }

        public final boolean expandItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
        {
            Object obj = Toolbar.this;
            if (((Toolbar) (obj)).mCollapseButtonView == null)
            {
                obj.mCollapseButtonView = new AppCompatImageButton(((Toolbar) (obj)).getContext(), null, 0x7f0100a9);
                ((Toolbar) (obj)).mCollapseButtonView.setImageDrawable(((Toolbar) (obj)).mCollapseIcon);
                ((Toolbar) (obj)).mCollapseButtonView.setContentDescription(((Toolbar) (obj)).mCollapseDescription);
                LayoutParams layoutparams = new LayoutParams(-2, -2);
                layoutparams.gravity = ((Toolbar) (obj)).mButtonGravity & 0x70 | 0x800003;
                layoutparams.mViewType = 2;
                ((Toolbar) (obj)).mCollapseButtonView.setLayoutParams(layoutparams);
                ((Toolbar) (obj)).mCollapseButtonView.setOnClickListener(((_cls3) (obj)). new _cls3());
            }
            obj = mCollapseButtonView.getParent();
            if (obj != Toolbar.this)
            {
                if (obj instanceof ViewGroup)
                {
                    ((ViewGroup)obj).removeView(mCollapseButtonView);
                }
                addView(mCollapseButtonView);
            }
            mExpandedActionView = menuitemimpl.getActionView();
            mCurrentExpandedItem = menuitemimpl;
            obj = mExpandedActionView.getParent();
            if (obj != Toolbar.this)
            {
                if (obj instanceof ViewGroup)
                {
                    ((ViewGroup)obj).removeView(mExpandedActionView);
                }
                obj = new LayoutParams(-2, -2);
                obj.gravity = mButtonGravity & 0x70 | 0x800003;
                obj.mViewType = 2;
                mExpandedActionView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                addView(mExpandedActionView);
            }
            obj = Toolbar.this;
            for (int i = ((Toolbar) (obj)).getChildCount() - 1; i >= 0; i--)
            {
                View view = ((Toolbar) (obj)).getChildAt(i);
                if (((LayoutParams)view.getLayoutParams()).mViewType != 2 && view != ((Toolbar) (obj)).mMenuView)
                {
                    ((Toolbar) (obj)).removeViewAt(i);
                    ((Toolbar) (obj)).mHiddenViews.add(view);
                }
            }

            requestLayout();
            menuitemimpl.mIsActionViewExpanded = true;
            menuitemimpl.mMenu.onItemsChanged(false);
            if (mExpandedActionView instanceof CollapsibleActionView)
            {
                ((CollapsibleActionView)mExpandedActionView).onActionViewExpanded();
            }
            return true;
        }

        public final boolean flagActionItems()
        {
            return false;
        }

        public final void initForMenu(Context context, MenuBuilder menubuilder)
        {
            if (mMenu != null && mCurrentExpandedItem != null)
            {
                mMenu.collapseItemActionView(mCurrentExpandedItem);
            }
            mMenu = menubuilder;
        }

        public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
        }

        public final boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
        {
            return false;
        }

        public final void setCallback(android.support.v7.view.menu.MenuPresenter.Callback callback)
        {
        }

        public final void updateMenuView(boolean flag)
        {
            boolean flag2 = false;
            if (mCurrentExpandedItem == null) goto _L2; else goto _L1
_L1:
            boolean flag1 = flag2;
            if (mMenu == null) goto _L4; else goto _L3
_L3:
            int i;
            int j;
            j = mMenu.size();
            i = 0;
_L9:
            flag1 = flag2;
            if (i >= j) goto _L4; else goto _L5
_L5:
            if (mMenu.getItem(i) != mCurrentExpandedItem) goto _L7; else goto _L6
_L6:
            flag1 = true;
_L4:
            if (!flag1)
            {
                collapseItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(mCurrentExpandedItem);
            }
_L2:
            return;
_L7:
            i++;
            if (true) goto _L9; else goto _L8
_L8:
        }

        ExpandedActionViewMenuPresenter()
        {
            this$0 = Toolbar.this;
            super();
        }

        private class _cls3
            implements android.view.View.OnClickListener
        {

            private final Toolbar this$0;

            public final void onClick(View view)
            {
                view = Toolbar.this;
                if (((Toolbar) (view)).mExpandedMenuPresenter == null)
                {
                    view = null;
                } else
                {
                    view = ((Toolbar) (view)).mExpandedMenuPresenter.mCurrentExpandedItem;
                }
                if (view != null)
                {
                    view.collapseActionView();
                }
            }

            _cls3()
            {
                this$0 = Toolbar.this;
                super();
            }
        }

    }

    public static final class LayoutParams extends android.support.v7.app.ActionBar.LayoutParams
    {

        public int mViewType;

        public LayoutParams(int i, int j)
        {
            super(-2, -2);
            mViewType = 0;
            gravity = 0x800013;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mViewType = 0;
        }

        public LayoutParams(android.support.v7.app.ActionBar.LayoutParams layoutparams)
        {
            super(layoutparams);
            mViewType = 0;
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            mViewType = 0;
            mViewType = layoutparams.mViewType;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mViewType = 0;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mViewType = 0;
            leftMargin = marginlayoutparams.leftMargin;
            topMargin = marginlayoutparams.topMargin;
            rightMargin = marginlayoutparams.rightMargin;
            bottomMargin = marginlayoutparams.bottomMargin;
        }
    }

    public static interface OnMenuItemClickListener
    {

        public abstract boolean onMenuItemClick(MenuItem menuitem);
    }

    public static final class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public int expandedMenuItemId;
        public boolean isOverflowOpen;

        public final void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(expandedMenuItemId);
            if (isOverflowOpen)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
        }


        public SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            expandedMenuItemId = parcel.readInt();
            boolean flag;
            if (parcel.readInt() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            isOverflowOpen = flag;
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements android.os.Parcelable.ClassLoaderCreator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public final Object[] newArray(int i)
            {
                return new SavedState[i];
            }

                _cls1()
                {
                }
        }

    }


    public android.support.v7.view.menu.MenuPresenter.Callback mActionMenuPresenterCallback;
    public int mButtonGravity;
    public ImageButton mCollapseButtonView;
    public CharSequence mCollapseDescription;
    public Drawable mCollapseIcon;
    public boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    public RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    public View mExpandedActionView;
    public ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    public final ArrayList mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    public android.support.v7.view.menu.MenuBuilder.Callback mMenuBuilderCallback;
    public ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    public ImageButton mNavButtonView;
    public OnMenuItemClickListener mOnMenuItemClickListener;
    public ActionMenuPresenter mOuterActionMenuPresenter;
    public Context mPopupContext;
    public int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    public CharSequence mSubtitleText;
    public int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    public TextView mSubtitleTextView;
    private final int mTempMargins[];
    private final ArrayList mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    public CharSequence mTitleText;
    public int mTitleTextAppearance;
    private int mTitleTextColor;
    public TextView mTitleTextView;
    public ToolbarWidgetWrapper mWrapper;

    public Toolbar(Context context)
    {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100a8);
    }

    public Toolbar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mGravity = 0x800013;
        mTempViews = new ArrayList();
        mHiddenViews = new ArrayList();
        mTempMargins = new int[2];
        mMenuViewItemClickListener = new _cls1();
        mShowOverflowMenuRunnable = new _cls2();
        context = getContext();
        context = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.Toolbar, i, 0));
        i = android.support.v7.appcompat.R.styleable.Toolbar_titleTextAppearance;
        mTitleTextAppearance = ((TintTypedArray) (context)).mWrapped.getResourceId(i, 0);
        i = android.support.v7.appcompat.R.styleable.Toolbar_subtitleTextAppearance;
        mSubtitleTextAppearance = ((TintTypedArray) (context)).mWrapped.getResourceId(i, 0);
        i = android.support.v7.appcompat.R.styleable.Toolbar_android_gravity;
        int j = mGravity;
        mGravity = ((TintTypedArray) (context)).mWrapped.getInteger(i, j);
        i = android.support.v7.appcompat.R.styleable.Toolbar_buttonGravity;
        mButtonGravity = ((TintTypedArray) (context)).mWrapped.getInteger(i, 48);
        i = android.support.v7.appcompat.R.styleable.Toolbar_titleMargin;
        j = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(i, 0);
        int k = android.support.v7.appcompat.R.styleable.Toolbar_titleMargins;
        i = j;
        if (((TintTypedArray) (context)).mWrapped.hasValue(k))
        {
            i = android.support.v7.appcompat.R.styleable.Toolbar_titleMargins;
            i = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(i, j);
        }
        mTitleMarginBottom = i;
        mTitleMarginTop = i;
        mTitleMarginEnd = i;
        mTitleMarginStart = i;
        i = android.support.v7.appcompat.R.styleable.Toolbar_titleMarginStart;
        i = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(i, -1);
        if (i >= 0)
        {
            mTitleMarginStart = i;
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_titleMarginEnd;
        i = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(i, -1);
        if (i >= 0)
        {
            mTitleMarginEnd = i;
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_titleMarginTop;
        i = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(i, -1);
        if (i >= 0)
        {
            mTitleMarginTop = i;
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_titleMarginBottom;
        i = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(i, -1);
        if (i >= 0)
        {
            mTitleMarginBottom = i;
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_maxButtonHeight;
        mMaxButtonHeight = ((TintTypedArray) (context)).mWrapped.getDimensionPixelSize(i, -1);
        i = android.support.v7.appcompat.R.styleable.Toolbar_contentInsetStart;
        i = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(i, 0x80000000);
        j = android.support.v7.appcompat.R.styleable.Toolbar_contentInsetEnd;
        j = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(j, 0x80000000);
        k = android.support.v7.appcompat.R.styleable.Toolbar_contentInsetLeft;
        k = ((TintTypedArray) (context)).mWrapped.getDimensionPixelSize(k, 0);
        int l = android.support.v7.appcompat.R.styleable.Toolbar_contentInsetRight;
        l = ((TintTypedArray) (context)).mWrapped.getDimensionPixelSize(l, 0);
        if (mContentInsets == null)
        {
            mContentInsets = new RtlSpacingHelper();
        }
        attributeset = mContentInsets;
        attributeset.mIsRelative = false;
        if (k != 0x80000000)
        {
            attributeset.mExplicitLeft = k;
            attributeset.mLeft = k;
        }
        if (l != 0x80000000)
        {
            attributeset.mExplicitRight = l;
            attributeset.mRight = l;
        }
        if (i != 0x80000000 || j != 0x80000000)
        {
            mContentInsets.setRelative(i, j);
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_contentInsetStartWithNavigation;
        mContentInsetStartWithNavigation = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(i, 0x80000000);
        i = android.support.v7.appcompat.R.styleable.Toolbar_contentInsetEndWithActions;
        mContentInsetEndWithActions = ((TintTypedArray) (context)).mWrapped.getDimensionPixelOffset(i, 0x80000000);
        mCollapseIcon = context.getDrawable(android.support.v7.appcompat.R.styleable.Toolbar_collapseIcon);
        i = android.support.v7.appcompat.R.styleable.Toolbar_collapseContentDescription;
        mCollapseDescription = ((TintTypedArray) (context)).mWrapped.getText(i);
        i = android.support.v7.appcompat.R.styleable.Toolbar_title;
        attributeset = ((TintTypedArray) (context)).mWrapped.getText(i);
        if (!TextUtils.isEmpty(attributeset))
        {
            setTitle(attributeset);
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_subtitle;
        attributeset = ((TintTypedArray) (context)).mWrapped.getText(i);
        if (!TextUtils.isEmpty(attributeset))
        {
            setSubtitle(attributeset);
        }
        mPopupContext = getContext();
        i = android.support.v7.appcompat.R.styleable.Toolbar_popupTheme;
        setPopupTheme(((TintTypedArray) (context)).mWrapped.getResourceId(i, 0));
        attributeset = context.getDrawable(android.support.v7.appcompat.R.styleable.Toolbar_navigationIcon);
        if (attributeset != null)
        {
            setNavigationIcon(attributeset);
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_navigationContentDescription;
        attributeset = ((TintTypedArray) (context)).mWrapped.getText(i);
        if (!TextUtils.isEmpty(attributeset))
        {
            if (!TextUtils.isEmpty(attributeset))
            {
                ensureNavButtonView();
            }
            if (mNavButtonView != null)
            {
                mNavButtonView.setContentDescription(attributeset);
            }
        }
        attributeset = context.getDrawable(android.support.v7.appcompat.R.styleable.Toolbar_logo);
        if (attributeset != null)
        {
            setLogo(attributeset);
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_logoDescription;
        attributeset = ((TintTypedArray) (context)).mWrapped.getText(i);
        if (!TextUtils.isEmpty(attributeset))
        {
            if (!TextUtils.isEmpty(attributeset) && mLogoView == null)
            {
                mLogoView = new AppCompatImageView(getContext());
            }
            if (mLogoView != null)
            {
                mLogoView.setContentDescription(attributeset);
            }
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_titleTextColor;
        if (((TintTypedArray) (context)).mWrapped.hasValue(i))
        {
            i = android.support.v7.appcompat.R.styleable.Toolbar_titleTextColor;
            setTitleTextColor(((TintTypedArray) (context)).mWrapped.getColor(i, -1));
        }
        i = android.support.v7.appcompat.R.styleable.Toolbar_subtitleTextColor;
        if (((TintTypedArray) (context)).mWrapped.hasValue(i))
        {
            i = android.support.v7.appcompat.R.styleable.Toolbar_subtitleTextColor;
            setSubtitleTextColor(((TintTypedArray) (context)).mWrapped.getColor(i, -1));
        }
        ((TintTypedArray) (context)).mWrapped.recycle();
    }

    private final void addCustomViewsWithGravity(List list, int i)
    {
        boolean flag;
        int j;
        int k;
        if (ViewCompat.getLayoutDirection(this) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        k = getChildCount();
        j = Gravity.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this));
        list.clear();
        if (flag)
        {
            i = k - 1;
            while (i >= 0) 
            {
                View view = getChildAt(i);
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                if (layoutparams.mViewType == 0)
                {
                    if (view != null && view.getParent() == this && view.getVisibility() != 8)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag && getChildHorizontalGravity(layoutparams.gravity) == j)
                    {
                        list.add(view);
                    }
                }
                i--;
            }
        } else
        {
            i = 0;
            while (i < k) 
            {
                View view1 = getChildAt(i);
                LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
                if (layoutparams1.mViewType != 0)
                {
                    continue;
                }
                boolean flag1;
                if (view1 != null && view1.getParent() == this && view1.getVisibility() != 8)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1 && getChildHorizontalGravity(layoutparams1.gravity) == j)
                {
                    list.add(view1);
                }
                i++;
            }
        }
    }

    private final void addSystemView(View view, boolean flag)
    {
        Object obj = view.getLayoutParams();
        if (obj == null)
        {
            obj = new LayoutParams(-2, -2);
        } else
        if (!checkLayoutParams(((android.view.ViewGroup.LayoutParams) (obj))))
        {
            obj = generateLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
        } else
        {
            obj = (LayoutParams)obj;
        }
        obj.mViewType = 1;
        if (flag && mExpandedActionView != null)
        {
            view.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            mHiddenViews.add(view);
            return;
        } else
        {
            addView(view, ((android.view.ViewGroup.LayoutParams) (obj)));
            return;
        }
    }

    private static LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (layoutparams instanceof LayoutParams)
        {
            return new LayoutParams((LayoutParams)layoutparams);
        }
        if (layoutparams instanceof android.support.v7.app.ActionBar.LayoutParams)
        {
            return new LayoutParams((android.support.v7.app.ActionBar.LayoutParams)layoutparams);
        }
        if (layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        } else
        {
            return new LayoutParams(layoutparams);
        }
    }

    private final int getChildHorizontalGravity(int i)
    {
label0:
        {
            int k = ViewCompat.getLayoutDirection(this);
            int j = Gravity.getAbsoluteGravity(i, k) & 7;
            i = j;
            switch (j)
            {
            case 2: // '\002'
            case 4: // '\004'
            default:
                if (k != 1)
                {
                    break label0;
                }
                i = 5;
                break;

            case 1: // '\001'
            case 3: // '\003'
            case 5: // '\005'
                break;
            }
            return i;
        }
        return 3;
    }

    private final int getChildTop(View view, int i)
    {
        LayoutParams layoutparams;
        int j;
        int k;
        int l;
        int i1;
        layoutparams = (LayoutParams)view.getLayoutParams();
        l = view.getMeasuredHeight();
        if (i > 0)
        {
            i = (l - i) / 2;
        } else
        {
            i = 0;
        }
        k = layoutparams.gravity & 0x70;
        j = k;
        k;
        JVM INSTR lookupswitch 3: default 76
    //                   16: 85
    //                   48: 85
    //                   80: 85;
           goto _L1 _L2 _L2 _L2
_L1:
        j = mGravity & 0x70;
_L2:
        j;
        JVM INSTR lookupswitch 2: default 112
    //                   48: 167
    //                   80: 174;
           goto _L3 _L4 _L5
_L3:
        j = getPaddingTop();
        k = getPaddingBottom();
        i1 = getHeight();
        i = (i1 - j - k - l) / 2;
        if (i >= layoutparams.topMargin) goto _L7; else goto _L6
_L6:
        i = layoutparams.topMargin;
_L9:
        return i + j;
_L4:
        return getPaddingTop() - i;
_L5:
        return getHeight() - getPaddingBottom() - l - layoutparams.bottomMargin - i;
_L7:
        k = i1 - k - l - i - j;
        if (k < layoutparams.bottomMargin)
        {
            i = Math.max(0, i - (layoutparams.bottomMargin - k));
        }
        if (true) goto _L9; else goto _L8
_L8:
    }

    private final int getContentInsetEnd()
    {
        if (mContentInsets != null)
        {
            RtlSpacingHelper rtlspacinghelper = mContentInsets;
            if (rtlspacinghelper.mIsRtl)
            {
                return rtlspacinghelper.mLeft;
            } else
            {
                return rtlspacinghelper.mRight;
            }
        } else
        {
            return 0;
        }
    }

    private final int getContentInsetStart()
    {
        if (mContentInsets != null)
        {
            RtlSpacingHelper rtlspacinghelper = mContentInsets;
            if (rtlspacinghelper.mIsRtl)
            {
                return rtlspacinghelper.mRight;
            } else
            {
                return rtlspacinghelper.mLeft;
            }
        } else
        {
            return 0;
        }
    }

    private final int getCurrentContentInsetEnd()
    {
        boolean flag;
        if (mMenuView != null)
        {
            MenuBuilder menubuilder = mMenuView.mMenu;
            if (menubuilder != null && menubuilder.hasVisibleItems())
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return Math.max(getContentInsetEnd(), Math.max(mContentInsetEndWithActions, 0));
        } else
        {
            return getContentInsetEnd();
        }
    }

    private final int getCurrentContentInsetStart()
    {
        Drawable drawable;
        if (mNavButtonView != null)
        {
            drawable = mNavButtonView.getDrawable();
        } else
        {
            drawable = null;
        }
        if (drawable != null)
        {
            return Math.max(getContentInsetStart(), Math.max(mContentInsetStartWithNavigation, 0));
        } else
        {
            return getContentInsetStart();
        }
    }

    private final int layoutChildLeft(View view, int i, int ai[], int j)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        int k = layoutparams.leftMargin - ai[0];
        i = Math.max(0, k) + i;
        ai[0] = Math.max(0, -k);
        j = getChildTop(view, j);
        k = view.getMeasuredWidth();
        view.layout(i, j, i + k, view.getMeasuredHeight() + j);
        return layoutparams.rightMargin + k + i;
    }

    private final int layoutChildRight(View view, int i, int ai[], int j)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        int k = layoutparams.rightMargin - ai[1];
        i -= Math.max(0, k);
        ai[1] = Math.max(0, -k);
        j = getChildTop(view, j);
        k = view.getMeasuredWidth();
        view.layout(i - k, j, i, view.getMeasuredHeight() + j);
        return i - (layoutparams.leftMargin + k);
    }

    private final int measureChildCollapseMargins(View view, int i, int j, int k, int l, int ai[])
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        int i1 = marginlayoutparams.leftMargin - ai[0];
        int j1 = marginlayoutparams.rightMargin - ai[1];
        int k1 = Math.max(0, i1) + Math.max(0, j1);
        ai[0] = Math.max(0, -i1);
        ai[1] = Math.max(0, -j1);
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + k1 + j, marginlayoutparams.width), getChildMeasureSpec(k, getPaddingTop() + getPaddingBottom() + marginlayoutparams.topMargin + marginlayoutparams.bottomMargin + l, marginlayoutparams.height));
        return view.getMeasuredWidth() + k1;
    }

    private final void measureChildConstrained(View view, int i, int j, int k, int l, int i1)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        l = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginlayoutparams.leftMargin + marginlayoutparams.rightMargin + j, marginlayoutparams.width);
        j = getChildMeasureSpec(k, getPaddingTop() + getPaddingBottom() + marginlayoutparams.topMargin + marginlayoutparams.bottomMargin, marginlayoutparams.height);
        k = android.view.View.MeasureSpec.getMode(j);
        i = j;
        if (k != 0x40000000)
        {
            i = j;
            if (i1 >= 0)
            {
                i = i1;
                if (k != 0)
                {
                    i = Math.min(android.view.View.MeasureSpec.getSize(j), i1);
                }
                i = android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
            }
        }
        view.measure(l, i);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return super.checkLayoutParams(layoutparams) && (layoutparams instanceof LayoutParams);
    }

    final void ensureMenuView()
    {
        if (mMenuView == null)
        {
            mMenuView = new ActionMenuView(getContext());
            mMenuView.setPopupTheme(mPopupTheme);
            mMenuView.mOnMenuItemClickListener = mMenuViewItemClickListener;
            Object obj = mMenuView;
            android.support.v7.view.menu.MenuPresenter.Callback callback = mActionMenuPresenterCallback;
            android.support.v7.view.menu.MenuBuilder.Callback callback1 = mMenuBuilderCallback;
            obj.mActionMenuPresenterCallback = callback;
            obj.mMenuBuilderCallback = callback1;
            obj = new LayoutParams(-2, -2);
            obj.gravity = 0x800005 | mButtonGravity & 0x70;
            mMenuView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            addSystemView(mMenuView, false);
        }
    }

    public final void ensureNavButtonView()
    {
        if (mNavButtonView == null)
        {
            mNavButtonView = new AppCompatImageButton(getContext(), null, 0x7f0100a9);
            LayoutParams layoutparams = new LayoutParams(-2, -2);
            layoutparams.gravity = 0x800003 | mButtonGravity & 0x70;
            mNavButtonView.setLayoutParams(layoutparams);
        }
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public final Menu getMenu()
    {
        ensureMenuView();
        if (mMenuView.mMenu == null)
        {
            MenuBuilder menubuilder = (MenuBuilder)mMenuView.getMenu();
            if (mExpandedMenuPresenter == null)
            {
                mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
            }
            mMenuView.mPresenter.mExpandedActionViewsExclusive = true;
            ExpandedActionViewMenuPresenter expandedactionviewmenupresenter = mExpandedMenuPresenter;
            Context context = mPopupContext;
            menubuilder.mPresenters.add(new WeakReference(expandedactionviewmenupresenter));
            expandedactionviewmenupresenter.initForMenu(context, menubuilder);
            menubuilder.mIsActionItemsStale = true;
        }
        return mMenuView.getMenu();
    }

    public final boolean isOverflowMenuShowing()
    {
        if (mMenuView != null)
        {
            ActionMenuView actionmenuview = mMenuView;
            boolean flag;
            if (actionmenuview.mPresenter != null && actionmenuview.mPresenter.isOverflowMenuShowing())
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

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        removeCallbacks(mShowOverflowMenuRunnable);
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if (i == 9)
        {
            mEatingHover = false;
        }
        if (!mEatingHover)
        {
            boolean flag = super.onHoverEvent(motionevent);
            if (i == 9 && !flag)
            {
                mEatingHover = true;
            }
        }
        if (i == 10 || i == 3)
        {
            mEatingHover = false;
        }
        return true;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        Object obj;
        Object obj1;
        int ai[];
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        if (ViewCompat.getLayoutDirection(this) == 1)
        {
            k1 = 1;
        } else
        {
            k1 = 0;
        }
        l2 = getWidth();
        k3 = getHeight();
        j1 = getPaddingLeft();
        i3 = getPaddingRight();
        j3 = getPaddingTop();
        l3 = getPaddingBottom();
        i = l2 - i3;
        ai = mTempMargins;
        ai[1] = 0;
        ai[0] = 0;
        k = ViewCompat.getMinimumHeight(this);
        if (k >= 0)
        {
            i1 = Math.min(k, l - j);
        } else
        {
            i1 = 0;
        }
        obj = mNavButtonView;
        if (obj != null && ((View) (obj)).getParent() == this && ((View) (obj)).getVisibility() != 8)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        LayoutParams layoutparams;
        if (j != 0)
        {
            if (k1 != 0)
            {
                i = layoutChildRight(mNavButtonView, i, ai, i1);
                j = j1;
            } else
            {
                j = layoutChildLeft(mNavButtonView, j1, ai, i1);
            }
        } else
        {
            j = j1;
        }
        obj = mCollapseButtonView;
        if (obj != null && ((View) (obj)).getParent() == this && ((View) (obj)).getVisibility() != 8)
        {
            l1 = 1;
        } else
        {
            l1 = 0;
        }
        k = i;
        l = j;
        if (l1 != 0)
        {
            if (k1 != 0)
            {
                k = layoutChildRight(mCollapseButtonView, i, ai, i1);
                l = j;
            } else
            {
                l = layoutChildLeft(mCollapseButtonView, j, ai, i1);
                k = i;
            }
        }
        obj = mMenuView;
        if (obj != null && ((View) (obj)).getParent() == this && ((View) (obj)).getVisibility() != 8)
        {
            l1 = 1;
        } else
        {
            l1 = 0;
        }
        i = k;
        j = l;
        if (l1 != 0)
        {
            if (k1 != 0)
            {
                j = layoutChildLeft(mMenuView, l, ai, i1);
                i = k;
            } else
            {
                i = layoutChildRight(mMenuView, k, ai, i1);
                j = l;
            }
        }
        if (ViewCompat.getLayoutDirection(this) == 1)
        {
            k = getCurrentContentInsetEnd();
        } else
        {
            k = getCurrentContentInsetStart();
        }
        if (ViewCompat.getLayoutDirection(this) == 1)
        {
            l = getCurrentContentInsetStart();
        } else
        {
            l = getCurrentContentInsetEnd();
        }
        ai[0] = Math.max(0, k - j);
        ai[1] = Math.max(0, l - (l2 - i3 - i));
        l1 = Math.max(j, k);
        l = Math.min(i, l2 - i3 - l);
        obj = mExpandedActionView;
        if (obj != null && ((View) (obj)).getParent() == this && ((View) (obj)).getVisibility() != 8)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        j = l;
        i = l1;
        if (k != 0)
        {
            if (k1 != 0)
            {
                j = layoutChildRight(mExpandedActionView, l, ai, i1);
                i = l1;
            } else
            {
                i = layoutChildLeft(mExpandedActionView, l1, ai, i1);
                j = l;
            }
        }
        obj = mLogoView;
        if (obj != null && ((View) (obj)).getParent() == this && ((View) (obj)).getVisibility() != 8)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            if (k1 != 0)
            {
                j = layoutChildRight(mLogoView, j, ai, i1);
                k = i;
            } else
            {
                k = layoutChildLeft(mLogoView, i, ai, i1);
            }
        } else
        {
            k = i;
        }
        obj = mTitleTextView;
        if (obj != null && ((View) (obj)).getParent() == this && ((View) (obj)).getVisibility() != 8)
        {
            j2 = 1;
        } else
        {
            j2 = 0;
        }
        obj = mSubtitleTextView;
        if (obj != null && ((View) (obj)).getParent() == this && ((View) (obj)).getVisibility() != 8)
        {
            i2 = 1;
        } else
        {
            i2 = 0;
        }
        i = 0;
        if (j2 != 0)
        {
            obj = (LayoutParams)mTitleTextView.getLayoutParams();
            i = ((LayoutParams) (obj)).topMargin;
            l = mTitleTextView.getMeasuredHeight();
            i = ((LayoutParams) (obj)).bottomMargin + (i + l) + 0;
        }
        if (i2 != 0)
        {
            obj = (LayoutParams)mSubtitleTextView.getLayoutParams();
            l = ((LayoutParams) (obj)).topMargin;
            l1 = mSubtitleTextView.getMeasuredHeight();
            k2 = ((LayoutParams) (obj)).bottomMargin + (l + l1) + i;
        } else
        {
            k2 = i;
        }
        if (j2 != 0) goto _L2; else goto _L1
_L1:
        l = j;
        i = k;
        if (i2 == 0) goto _L3; else goto _L2
_L2:
        if (j2 != 0)
        {
            obj = mTitleTextView;
        } else
        {
            obj = mSubtitleTextView;
        }
        if (i2 != 0)
        {
            obj1 = mSubtitleTextView;
        } else
        {
            obj1 = mTitleTextView;
        }
        obj = (LayoutParams)((View) (obj)).getLayoutParams();
        obj1 = (LayoutParams)((View) (obj1)).getLayoutParams();
        if (j2 != 0 && mTitleTextView.getMeasuredWidth() > 0 || i2 != 0 && mSubtitleTextView.getMeasuredWidth() > 0)
        {
            l1 = 1;
        } else
        {
            l1 = 0;
        }
        mGravity & 0x70;
        JVM INSTR lookupswitch 2: default 776
    //                   48: 1281
    //                   80: 1355;
           goto _L4 _L5 _L6
_L4:
        i = (k3 - j3 - l3 - k2) / 2;
        if (i < ((LayoutParams) (obj)).topMargin + mTitleMarginTop)
        {
            i = ((LayoutParams) (obj)).topMargin + mTitleMarginTop;
        } else
        {
            l = k3 - l3 - k2 - i - j3;
            if (l < ((LayoutParams) (obj)).bottomMargin + mTitleMarginBottom)
            {
                i = Math.max(0, i - ((((LayoutParams) (obj1)).bottomMargin + mTitleMarginBottom) - l));
            }
        }
        i = j3 + i;
_L8:
        if (k1 != 0)
        {
            if (l1 != 0)
            {
                l = mTitleMarginStart;
            } else
            {
                l = 0;
            }
            l -= ai[1];
            j -= Math.max(0, l);
            ai[1] = Math.max(0, -l);
            if (j2 != 0)
            {
                obj = (LayoutParams)mTitleTextView.getLayoutParams();
                l = j - mTitleTextView.getMeasuredWidth();
                k1 = mTitleTextView.getMeasuredHeight() + i;
                mTitleTextView.layout(l, i, j, k1);
                j2 = mTitleMarginEnd;
                i = k1 + ((LayoutParams) (obj)).bottomMargin;
                l -= j2;
            } else
            {
                l = j;
            }
            if (i2 != 0)
            {
                obj = (LayoutParams)mSubtitleTextView.getLayoutParams();
                i = ((LayoutParams) (obj)).topMargin + i;
                k1 = mSubtitleTextView.getMeasuredWidth();
                i2 = mSubtitleTextView.getMeasuredHeight();
                mSubtitleTextView.layout(j - k1, i, j, i2 + i);
                i = mTitleMarginEnd;
                k1 = ((LayoutParams) (obj)).bottomMargin;
                i = j - i;
            } else
            {
                i = j;
            }
            if (l1 != 0)
            {
                i = Math.min(l, i);
            } else
            {
                i = j;
            }
            l = i;
            i = k;
        } else
        {
            if (l1 != 0)
            {
                l = mTitleMarginStart;
            } else
            {
                l = 0;
            }
            l -= ai[0];
            k += Math.max(0, l);
            ai[0] = Math.max(0, -l);
            if (j2 != 0)
            {
                obj = (LayoutParams)mTitleTextView.getLayoutParams();
                k1 = mTitleTextView.getMeasuredWidth() + k;
                l = mTitleTextView.getMeasuredHeight() + i;
                mTitleTextView.layout(k, i, k1, l);
                j2 = mTitleMarginEnd;
                i = ((LayoutParams) (obj)).bottomMargin;
                k1 += j2;
                i += l;
            } else
            {
                k1 = k;
            }
            if (i2 != 0)
            {
                obj = (LayoutParams)mSubtitleTextView.getLayoutParams();
                l = i + ((LayoutParams) (obj)).topMargin;
                i = mSubtitleTextView.getMeasuredWidth() + k;
                i2 = mSubtitleTextView.getMeasuredHeight();
                mSubtitleTextView.layout(k, l, i, i2 + l);
                l = mTitleMarginEnd;
                i2 = ((LayoutParams) (obj)).bottomMargin;
                i2 = l + i;
            } else
            {
                i2 = k;
            }
            l = j;
            i = k;
            if (l1 != 0)
            {
                i = Math.max(k1, i2);
                l = j;
            }
        }
_L3:
        addCustomViewsWithGravity(mTempViews, 3);
        k = mTempViews.size();
        for (j = 0; j < k; j++)
        {
            i = layoutChildLeft((View)mTempViews.get(j), i, ai, i1);
        }

        addCustomViewsWithGravity(mTempViews, 5);
        k1 = mTempViews.size();
        k = 0;
        j = l;
        for (; k < k1; k++)
        {
            j = layoutChildRight((View)mTempViews.get(k), j, ai, i1);
        }

        addCustomViewsWithGravity(mTempViews, 1);
        obj = mTempViews;
        l1 = ai[0];
        k1 = ai[1];
        i2 = ((List) (obj)).size();
        l = 0;
        for (k = 0; l < i2; k += j3 + j2 + k2)
        {
            obj1 = (View)((List) (obj)).get(l);
            layoutparams = (LayoutParams)((View) (obj1)).getLayoutParams();
            l1 = layoutparams.leftMargin - l1;
            k1 = layoutparams.rightMargin - k1;
            j2 = Math.max(0, l1);
            k2 = Math.max(0, k1);
            l1 = Math.max(0, -l1);
            k1 = Math.max(0, -k1);
            j3 = ((View) (obj1)).getMeasuredWidth();
            l++;
        }

        l = ((l2 - j1 - i3) / 2 + j1) - k / 2;
        k = l + k;
          goto _L7
_L5:
        i = getPaddingTop();
        i = ((LayoutParams) (obj)).topMargin + i + mTitleMarginTop;
        break MISSING_BLOCK_LABEL_820;
_L6:
        i = k3 - l3 - ((LayoutParams) (obj1)).bottomMargin - mTitleMarginBottom - k2;
          goto _L8
_L7:
        if (l >= i) goto _L10; else goto _L9
_L9:
        l = mTempViews.size();
        k = 0;
        j = i;
        for (i = k; i < l; i++)
        {
            j = layoutChildLeft((View)mTempViews.get(i), j, ai, i1);
        }

        break; /* Loop/switch isn't completed */
_L10:
        i = l;
        if (k > j)
        {
            i = l - (k - j);
        }
        if (true) goto _L9; else goto _L11
_L11:
        mTempViews.clear();
        return;
          goto _L8
    }

    protected void onMeasure(int i, int j)
    {
        int ai[] = mTempMargins;
        Object obj1;
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int j2;
        int k2;
        if (ViewUtils.isLayoutRtl(this))
        {
            k1 = 0;
            l1 = 1;
        } else
        {
            k1 = 1;
            l1 = 0;
        }
        l = 0;
        obj1 = mNavButtonView;
        if (obj1 != null && ((View) (obj1)).getParent() == this && ((View) (obj1)).getVisibility() != 8)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        Object obj;
        int j3;
        boolean flag;
        int k3;
        int l3;
        if (k != 0)
        {
            measureChildConstrained(mNavButtonView, i, 0, j, 0, mMaxButtonHeight);
            k = mNavButtonView.getMeasuredWidth();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mNavButtonView.getLayoutParams();
            l = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).getMarginStart();
            int i2 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).getMarginEnd();
            i1 = mNavButtonView.getMeasuredHeight();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mNavButtonView.getLayoutParams();
            j1 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).topMargin;
            j1 = Math.max(0, ((android.view.ViewGroup.MarginLayoutParams) (obj1)).bottomMargin + j1 + i1);
            i1 = View.combineMeasuredStates(0, mNavButtonView.getMeasuredState());
            k += i2 + l;
        } else
        {
            i1 = 0;
            j1 = 0;
            k = l;
        }
        obj1 = mCollapseButtonView;
        if (obj1 != null && ((View) (obj1)).getParent() == this && ((View) (obj1)).getVisibility() != 8)
        {
            k2 = 1;
        } else
        {
            k2 = 0;
        }
        j2 = k;
        k = i1;
        l = j1;
        if (k2 != 0)
        {
            measureChildConstrained(mCollapseButtonView, i, 0, j, 0, mMaxButtonHeight);
            j2 = mCollapseButtonView.getMeasuredWidth();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mCollapseButtonView.getLayoutParams();
            k2 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).getMarginStart();
            int l2 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).getMarginEnd();
            k = mCollapseButtonView.getMeasuredHeight();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mCollapseButtonView.getLayoutParams();
            l = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).topMargin;
            l = Math.max(j1, ((android.view.ViewGroup.MarginLayoutParams) (obj1)).bottomMargin + l + k);
            k = View.combineMeasuredStates(i1, mCollapseButtonView.getMeasuredState());
            j2 += l2 + k2;
        }
        i1 = getCurrentContentInsetStart();
        k2 = Math.max(i1, j2) + 0;
        ai[l1] = Math.max(0, i1 - j2);
        l1 = 0;
        obj1 = mMenuView;
        if (obj1 != null && ((View) (obj1)).getParent() == this && ((View) (obj1)).getVisibility() != 8)
        {
            j2 = 1;
        } else
        {
            j2 = 0;
        }
        i1 = k;
        j1 = l;
        if (j2 != 0)
        {
            measureChildConstrained(mMenuView, i, k2, j, 0, mMaxButtonHeight);
            l1 = mMenuView.getMeasuredWidth();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mMenuView.getLayoutParams();
            j2 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).getMarginStart();
            int i3 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).getMarginEnd();
            i1 = mMenuView.getMeasuredHeight();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mMenuView.getLayoutParams();
            j1 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).topMargin;
            j1 = Math.max(l, ((android.view.ViewGroup.MarginLayoutParams) (obj1)).bottomMargin + j1 + i1);
            i1 = View.combineMeasuredStates(k, mMenuView.getMeasuredState());
            l1 += i3 + j2;
        }
        k = getCurrentContentInsetEnd();
        j2 = k2 + Math.max(k, l1);
        ai[k1] = Math.max(0, k - l1);
        obj1 = mExpandedActionView;
        if (obj1 != null && ((View) (obj1)).getParent() == this && ((View) (obj1)).getVisibility() != 8)
        {
            l1 = 1;
        } else
        {
            l1 = 0;
        }
        k1 = j2;
        k = i1;
        l = j1;
        if (l1 != 0)
        {
            k1 = j2 + measureChildCollapseMargins(mExpandedActionView, i, j2, j, 0, ai);
            k = mExpandedActionView.getMeasuredHeight();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mExpandedActionView.getLayoutParams();
            l = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).topMargin;
            l = Math.max(j1, ((android.view.ViewGroup.MarginLayoutParams) (obj1)).bottomMargin + l + k);
            k = View.combineMeasuredStates(i1, mExpandedActionView.getMeasuredState());
        }
        obj1 = mLogoView;
        if (obj1 != null && ((View) (obj1)).getParent() == this && ((View) (obj1)).getVisibility() != 8)
        {
            j2 = 1;
        } else
        {
            j2 = 0;
        }
        i1 = k1;
        j1 = k;
        l1 = l;
        if (j2 != 0)
        {
            i1 = k1 + measureChildCollapseMargins(mLogoView, i, k1, j, 0, ai);
            j1 = mLogoView.getMeasuredHeight();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mLogoView.getLayoutParams();
            k1 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).topMargin;
            l1 = Math.max(l, ((android.view.ViewGroup.MarginLayoutParams) (obj1)).bottomMargin + k1 + j1);
            j1 = View.combineMeasuredStates(k, mLogoView.getMeasuredState());
        }
        j2 = getChildCount();
        k1 = 0;
        l = l1;
        k = j1;
        j1 = k1;
        k1 = i1;
        if (j1 >= j2) goto _L2; else goto _L1
_L1:
        obj1 = getChildAt(j1);
        if (((LayoutParams)((View) (obj1)).getLayoutParams()).mViewType != 0) goto _L4; else goto _L3
_L3:
        if (obj1 != null && ((View) (obj1)).getParent() == this && ((View) (obj1)).getVisibility() != 8)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 == 0) goto _L4; else goto _L5
_L5:
        k1 += measureChildCollapseMargins(((View) (obj1)), i, k1, j, 0, ai);
        i1 = ((View) (obj1)).getMeasuredHeight();
        marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)((View) (obj1)).getLayoutParams();
        l1 = marginlayoutparams.topMargin;
        i1 = Math.max(l, marginlayoutparams.bottomMargin + l1 + i1);
        l = View.combineMeasuredStates(k, ((View) (obj1)).getMeasuredState());
        k = i1;
_L10:
        j1++;
        i1 = k;
        k = l;
        l = i1;
        break MISSING_BLOCK_LABEL_864;
_L2:
        k3 = mTitleMarginTop + mTitleMarginBottom;
        l3 = mTitleMarginStart + mTitleMarginEnd;
        obj1 = mTitleTextView;
        if (obj1 != null && ((View) (obj1)).getParent() == this && ((View) (obj1)).getVisibility() != 8)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 != 0)
        {
            measureChildCollapseMargins(mTitleTextView, i, k1 + l3, j, k3, ai);
            j1 = mTitleTextView.getMeasuredWidth();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mTitleTextView.getLayoutParams();
            k2 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).getMarginStart();
            j3 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).getMarginEnd();
            i1 = mTitleTextView.getMeasuredHeight();
            obj1 = (android.view.ViewGroup.MarginLayoutParams)mTitleTextView.getLayoutParams();
            l1 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).topMargin;
            j2 = ((android.view.ViewGroup.MarginLayoutParams) (obj1)).bottomMargin;
            k = View.combineMeasuredStates(k, mTitleTextView.getMeasuredState());
            j1 += j3 + k2;
            i1 = j2 + l1 + i1;
        } else
        {
            j1 = 0;
            i1 = 0;
        }
        obj1 = mSubtitleTextView;
        if (obj1 != null && ((View) (obj1)).getParent() == this && ((View) (obj1)).getVisibility() != 8)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j2 = j1;
        k2 = i1;
        l1 = k;
        if (flag)
        {
            j2 = Math.max(j1, measureChildCollapseMargins(mSubtitleTextView, i, k1 + l3, j, k3 + i1, ai));
            j1 = mSubtitleTextView.getMeasuredHeight();
            obj = (android.view.ViewGroup.MarginLayoutParams)mSubtitleTextView.getLayoutParams();
            l1 = ((android.view.ViewGroup.MarginLayoutParams) (obj)).topMargin;
            k2 = i1 + (((android.view.ViewGroup.MarginLayoutParams) (obj)).bottomMargin + l1 + j1);
            l1 = View.combineMeasuredStates(k, mSubtitleTextView.getMeasuredState());
        }
        k = Math.max(l, k2);
        l = getPaddingLeft();
        k2 = getPaddingRight();
        i1 = getPaddingTop();
        j1 = getPaddingBottom();
        l = View.resolveSizeAndState(Math.max(j2 + k1 + (l + k2), getSuggestedMinimumWidth()), i, 0xff000000 & l1);
        k = View.resolveSizeAndState(Math.max(k + (i1 + j1), getSuggestedMinimumHeight()), j, l1 << 16);
        if (mCollapsible) goto _L7; else goto _L6
_L6:
        i = 0;
_L8:
        j = k;
        if (i != 0)
        {
            j = 0;
        }
        setMeasuredDimension(l, j);
        return;
_L7:
        i1 = getChildCount();
        i = 0;
        do
        {
            if (i >= i1)
            {
                break;
            }
            obj = getChildAt(i);
            if (obj != null && ((View) (obj)).getParent() == this && ((View) (obj)).getVisibility() != 8)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0 && ((View) (obj)).getMeasuredWidth() > 0 && ((View) (obj)).getMeasuredHeight() > 0)
            {
                i = 0;
                continue; /* Loop/switch isn't completed */
            }
            i++;
        } while (true);
        i = 1;
        if (true) goto _L8; else goto _L4
_L4:
        i1 = l;
        l = k;
        k = i1;
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            SavedState savedstate = (SavedState)parcelable;
            super.onRestoreInstanceState(((AbsSavedState) (savedstate)).mSuperState);
            if (mMenuView != null)
            {
                parcelable = mMenuView.mMenu;
            } else
            {
                parcelable = null;
            }
            if (savedstate.expandedMenuItemId != 0 && mExpandedMenuPresenter != null && parcelable != null)
            {
                parcelable = parcelable.findItem(savedstate.expandedMenuItemId);
                if (parcelable != null)
                {
                    parcelable.expandActionView();
                }
            }
            if (savedstate.isOverflowOpen)
            {
                removeCallbacks(mShowOverflowMenuRunnable);
                post(mShowOverflowMenuRunnable);
                return;
            }
        }
    }

    public void onRtlPropertiesChanged(int i)
    {
        RtlSpacingHelper rtlspacinghelper;
label0:
        {
label1:
            {
                boolean flag = true;
                super.onRtlPropertiesChanged(i);
                if (mContentInsets == null)
                {
                    mContentInsets = new RtlSpacingHelper();
                }
                rtlspacinghelper = mContentInsets;
                if (i != 1)
                {
                    flag = false;
                }
                if (flag != rtlspacinghelper.mIsRtl)
                {
                    rtlspacinghelper.mIsRtl = flag;
                    if (!rtlspacinghelper.mIsRelative)
                    {
                        break label0;
                    }
                    if (!flag)
                    {
                        break label1;
                    }
                    if (rtlspacinghelper.mEnd != 0x80000000)
                    {
                        i = rtlspacinghelper.mEnd;
                    } else
                    {
                        i = rtlspacinghelper.mExplicitLeft;
                    }
                    rtlspacinghelper.mLeft = i;
                    if (rtlspacinghelper.mStart != 0x80000000)
                    {
                        i = rtlspacinghelper.mStart;
                    } else
                    {
                        i = rtlspacinghelper.mExplicitRight;
                    }
                    rtlspacinghelper.mRight = i;
                }
                return;
            }
            if (rtlspacinghelper.mStart != 0x80000000)
            {
                i = rtlspacinghelper.mStart;
            } else
            {
                i = rtlspacinghelper.mExplicitLeft;
            }
            rtlspacinghelper.mLeft = i;
            if (rtlspacinghelper.mEnd != 0x80000000)
            {
                i = rtlspacinghelper.mEnd;
            } else
            {
                i = rtlspacinghelper.mExplicitRight;
            }
            rtlspacinghelper.mRight = i;
            return;
        }
        rtlspacinghelper.mLeft = rtlspacinghelper.mExplicitLeft;
        rtlspacinghelper.mRight = rtlspacinghelper.mExplicitRight;
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if (mExpandedMenuPresenter != null && mExpandedMenuPresenter.mCurrentExpandedItem != null)
        {
            savedstate.expandedMenuItemId = mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }
        savedstate.isOverflowOpen = isOverflowMenuShowing();
        return savedstate;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if (i == 0)
        {
            mEatingTouch = false;
        }
        if (!mEatingTouch)
        {
            boolean flag = super.onTouchEvent(motionevent);
            if (i == 0 && !flag)
            {
                mEatingTouch = true;
            }
        }
        if (i == 1 || i == 3)
        {
            mEatingTouch = false;
        }
        return true;
    }

    public void setContentInsetEndWithActions(int i)
    {
        int j = i;
        if (i < 0)
        {
            j = 0x80000000;
        }
        if (j != mContentInsetEndWithActions)
        {
            mContentInsetEndWithActions = j;
            Drawable drawable;
            if (mNavButtonView != null)
            {
                drawable = mNavButtonView.getDrawable();
            } else
            {
                drawable = null;
            }
            if (drawable != null)
            {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i)
    {
        int j = i;
        if (i < 0)
        {
            j = 0x80000000;
        }
        if (j != mContentInsetStartWithNavigation)
        {
            mContentInsetStartWithNavigation = j;
            Drawable drawable;
            if (mNavButtonView != null)
            {
                drawable = mNavButtonView.getDrawable();
            } else
            {
                drawable = null;
            }
            if (drawable != null)
            {
                requestLayout();
            }
        }
    }

    public void setLogo(int i)
    {
        setLogo(AppCompatResources.getDrawable(getContext(), i));
    }

    public final void setLogo(Drawable drawable)
    {
        boolean flag;
        boolean flag2;
        flag2 = false;
        flag = false;
        if (drawable == null) goto _L2; else goto _L1
_L1:
        if (mLogoView == null)
        {
            mLogoView = new AppCompatImageView(getContext());
        }
        ImageView imageview = mLogoView;
        if (imageview.getParent() == this || mHiddenViews.contains(imageview))
        {
            flag = true;
        }
        if (!flag)
        {
            addSystemView(mLogoView, true);
        }
_L4:
        if (mLogoView != null)
        {
            mLogoView.setImageDrawable(drawable);
        }
        return;
_L2:
        boolean flag1;
label0:
        {
            if (mLogoView == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            ImageView imageview1 = mLogoView;
            if (imageview1.getParent() != this)
            {
                flag1 = flag2;
                if (!mHiddenViews.contains(imageview1))
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        if (flag1)
        {
            removeView(mLogoView);
            mHiddenViews.remove(mLogoView);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setLogoDescription(int i)
    {
        CharSequence charsequence = getContext().getText(i);
        if (!TextUtils.isEmpty(charsequence) && mLogoView == null)
        {
            mLogoView = new AppCompatImageView(getContext());
        }
        if (mLogoView != null)
        {
            mLogoView.setContentDescription(charsequence);
        }
    }

    public void setNavigationContentDescription(int i)
    {
        CharSequence charsequence;
        if (i != 0)
        {
            charsequence = getContext().getText(i);
        } else
        {
            charsequence = null;
        }
        if (!TextUtils.isEmpty(charsequence))
        {
            ensureNavButtonView();
        }
        if (mNavButtonView != null)
        {
            mNavButtonView.setContentDescription(charsequence);
        }
    }

    public void setNavigationIcon(int i)
    {
        setNavigationIcon(AppCompatResources.getDrawable(getContext(), i));
    }

    public final void setNavigationIcon(Drawable drawable)
    {
        boolean flag;
        boolean flag2;
        flag2 = false;
        flag = false;
        if (drawable == null) goto _L2; else goto _L1
_L1:
        ensureNavButtonView();
        ImageButton imagebutton = mNavButtonView;
        if (imagebutton.getParent() == this || mHiddenViews.contains(imagebutton))
        {
            flag = true;
        }
        if (!flag)
        {
            addSystemView(mNavButtonView, true);
        }
_L4:
        if (mNavButtonView != null)
        {
            mNavButtonView.setImageDrawable(drawable);
        }
        return;
_L2:
        boolean flag1;
label0:
        {
            if (mNavButtonView == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            ImageButton imagebutton1 = mNavButtonView;
            if (imagebutton1.getParent() != this)
            {
                flag1 = flag2;
                if (!mHiddenViews.contains(imagebutton1))
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        if (flag1)
        {
            removeView(mNavButtonView);
            mHiddenViews.remove(mNavButtonView);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setPopupTheme(int i)
    {
label0:
        {
            if (mPopupTheme != i)
            {
                mPopupTheme = i;
                if (i != 0)
                {
                    break label0;
                }
                mPopupContext = getContext();
            }
            return;
        }
        mPopupContext = new ContextThemeWrapper(getContext(), i);
    }

    public void setSubtitle(int i)
    {
        setSubtitle(getContext().getText(i));
    }

    public final void setSubtitle(CharSequence charsequence)
    {
        boolean flag;
        boolean flag2;
        flag2 = false;
        flag = false;
        if (TextUtils.isEmpty(charsequence)) goto _L2; else goto _L1
_L1:
        if (mSubtitleTextView == null)
        {
            Context context = getContext();
            mSubtitleTextView = new AppCompatTextView(context);
            mSubtitleTextView.setSingleLine();
            mSubtitleTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
            if (mSubtitleTextAppearance != 0)
            {
                mSubtitleTextView.setTextAppearance(context, mSubtitleTextAppearance);
            }
            if (mSubtitleTextColor != 0)
            {
                mSubtitleTextView.setTextColor(mSubtitleTextColor);
            }
        }
        TextView textview = mSubtitleTextView;
        if (textview.getParent() == this || mHiddenViews.contains(textview))
        {
            flag = true;
        }
        if (!flag)
        {
            addSystemView(mSubtitleTextView, true);
        }
_L4:
        if (mSubtitleTextView != null)
        {
            mSubtitleTextView.setText(charsequence);
        }
        mSubtitleText = charsequence;
        return;
_L2:
        boolean flag1;
label0:
        {
            if (mSubtitleTextView == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            TextView textview1 = mSubtitleTextView;
            if (textview1.getParent() != this)
            {
                flag1 = flag2;
                if (!mHiddenViews.contains(textview1))
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        if (flag1)
        {
            removeView(mSubtitleTextView);
            mHiddenViews.remove(mSubtitleTextView);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setSubtitleTextColor(int i)
    {
        mSubtitleTextColor = i;
        if (mSubtitleTextView != null)
        {
            mSubtitleTextView.setTextColor(i);
        }
    }

    public void setTitle(int i)
    {
        setTitle(getContext().getText(i));
    }

    public final void setTitle(CharSequence charsequence)
    {
        boolean flag;
        boolean flag2;
        flag2 = false;
        flag = false;
        if (TextUtils.isEmpty(charsequence)) goto _L2; else goto _L1
_L1:
        if (mTitleTextView == null)
        {
            Context context = getContext();
            mTitleTextView = new AppCompatTextView(context);
            mTitleTextView.setSingleLine();
            mTitleTextView.setEllipsize(android.text.TextUtils.TruncateAt.END);
            if (mTitleTextAppearance != 0)
            {
                mTitleTextView.setTextAppearance(context, mTitleTextAppearance);
            }
            if (mTitleTextColor != 0)
            {
                mTitleTextView.setTextColor(mTitleTextColor);
            }
        }
        TextView textview = mTitleTextView;
        if (textview.getParent() == this || mHiddenViews.contains(textview))
        {
            flag = true;
        }
        if (!flag)
        {
            addSystemView(mTitleTextView, true);
        }
_L4:
        if (mTitleTextView != null)
        {
            mTitleTextView.setText(charsequence);
        }
        mTitleText = charsequence;
        return;
_L2:
        boolean flag1;
label0:
        {
            if (mTitleTextView == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            TextView textview1 = mTitleTextView;
            if (textview1.getParent() != this)
            {
                flag1 = flag2;
                if (!mHiddenViews.contains(textview1))
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        if (flag1)
        {
            removeView(mTitleTextView);
            mHiddenViews.remove(mTitleTextView);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setTitleMarginBottom(int i)
    {
        mTitleMarginBottom = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i)
    {
        mTitleMarginEnd = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i)
    {
        mTitleMarginStart = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i)
    {
        mTitleMarginTop = i;
        requestLayout();
    }

    public void setTitleTextColor(int i)
    {
        mTitleTextColor = i;
        if (mTitleTextView != null)
        {
            mTitleTextView.setTextColor(i);
        }
    }

    public final boolean showOverflowMenu()
    {
        if (mMenuView != null)
        {
            ActionMenuView actionmenuview = mMenuView;
            boolean flag;
            if (actionmenuview.mPresenter != null && actionmenuview.mPresenter.showOverflowMenu())
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

    private class _cls1
        implements ActionMenuView.OnMenuItemClickListener
    {

        private final Toolbar this$0;

        public final boolean onMenuItemClick(MenuItem menuitem)
        {
            if (mOnMenuItemClickListener != null)
            {
                return mOnMenuItemClickListener.onMenuItemClick(menuitem);
            } else
            {
                return false;
            }
        }

        _cls1()
        {
            this$0 = Toolbar.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final Toolbar this$0;

        public final void run()
        {
            showOverflowMenu();
        }

        _cls2()
        {
            this$0 = Toolbar.this;
            super();
        }
    }

}
