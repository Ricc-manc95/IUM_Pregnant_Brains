// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ActionProvider;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopup;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuView, LinearLayoutCompat

public final class ActionMenuPresenter extends BaseMenuPresenter
    implements android.support.v4.view.ActionProvider.SubUiVisibilityListener
{

    private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
    public ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    public boolean mExpandedActionViewsExclusive;
    public int mMaxItems;
    private int mMinCellSize;
    public OverflowMenuButton mOverflowButton;
    public OverflowPopup mOverflowPopup;
    private ActionMenuPopupCallback mPopupCallback;
    public final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
    public OpenOverflowRunnable mPostedOpenRunnable;
    public boolean mReserveOverflow;
    public boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private int mWidthLimit;

    public ActionMenuPresenter(Context context)
    {
        super(context, 0x7f050003, 0x7f050002);
    }

    public final void bindItemView(MenuItemImpl menuitemimpl, android.support.v7.view.menu.MenuView.ItemView itemview)
    {
        itemview.initialize(menuitemimpl, 0);
        menuitemimpl = (ActionMenuView)mMenuView;
        itemview = (ActionMenuItemView)itemview;
        itemview.mItemInvoker = menuitemimpl;
        if (mPopupCallback == null)
        {
            mPopupCallback = new ActionMenuPopupCallback();
        }
        itemview.mPopupCallback = mPopupCallback;
    }

    public final boolean filterLeftoverView(ViewGroup viewgroup, int i)
    {
        if (viewgroup.getChildAt(i) == mOverflowButton)
        {
            return false;
        } else
        {
            return super.filterLeftoverView(viewgroup, i);
        }
    }

    public final boolean flagActionItems()
    {
        ArrayList arraylist;
        ViewGroup viewgroup;
        SparseBooleanArray sparsebooleanarray;
        int j;
        int k;
        int l;
        int j2;
        int k2;
        int l3;
        int l2;
label0:
        {
            int i;
            int i1;
            boolean flag;
            if (mMenu != null)
            {
                arraylist = mMenu.getVisibleItems();
                j2 = arraylist.size();
            } else
            {
                j2 = 0;
                arraylist = null;
            }
            i = mMaxItems;
            l2 = mActionItemWidthLimit;
            l3 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            viewgroup = (ViewGroup)mMenuView;
            l = 0;
            i1 = 0;
            flag = false;
            k = 0;
            while (k < j2) 
            {
                MenuItemImpl menuitemimpl = (MenuItemImpl)arraylist.get(k);
                if ((menuitemimpl.mShowAsAction & 2) == 2)
                {
                    l++;
                } else
                if ((menuitemimpl.mShowAsAction & 1) == 1)
                {
                    i1++;
                } else
                {
                    flag = true;
                }
                if (mExpandedActionViewsExclusive && menuitemimpl.isActionViewExpanded())
                {
                    i = 0;
                }
                k++;
            }
            k = i;
            if (!mReserveOverflow)
            {
                break label0;
            }
            if (!flag)
            {
                k = i;
                if (l + i1 <= i)
                {
                    break label0;
                }
            }
            k = i - 1;
        }
        sparsebooleanarray = mActionButtonGroups;
        sparsebooleanarray.clear();
        k2 = 0;
        int j1 = l2;
        j = k - l;
        k = 0;
        l = j1;
_L5:
        if (k2 >= j2) goto _L2; else goto _L1
_L1:
        MenuItemImpl menuitemimpl1 = (MenuItemImpl)arraylist.get(k2);
        if ((menuitemimpl1.mShowAsAction & 2) != 2) goto _L4; else goto _L3
_L3:
        View view = getItemView(menuitemimpl1, mScrapActionButtonView, viewgroup);
        if (mScrapActionButtonView == null)
        {
            mScrapActionButtonView = view;
        }
        view.measure(l3, l3);
        int k1 = view.getMeasuredWidth();
        if (k == 0)
        {
            k = k1;
        }
        int i3 = menuitemimpl1.getGroupId();
        if (i3 != 0)
        {
            sparsebooleanarray.put(i3, true);
        }
        menuitemimpl1.mFlags = menuitemimpl1.mFlags | 0x20;
        k1 = l - k1;
        l = j;
        j = k1;
_L8:
        k2++;
        int l1 = l;
        l = j;
        j = l1;
          goto _L5
_L4:
        if ((menuitemimpl1.mShowAsAction & 1) != 1) goto _L7; else goto _L6
_L6:
        int i4 = menuitemimpl1.getGroupId();
        boolean flag1 = sparsebooleanarray.get(i4);
        int i2;
        int j3;
        if ((j > 0 || flag1) && l > 0)
        {
            i2 = 1;
        } else
        {
            i2 = 0;
        }
        if (i2 != 0)
        {
            View view1 = getItemView(menuitemimpl1, mScrapActionButtonView, viewgroup);
            if (mScrapActionButtonView == null)
            {
                mScrapActionButtonView = view1;
            }
            view1.measure(l3, l3);
            j3 = view1.getMeasuredWidth();
            l -= j3;
            if (k == 0)
            {
                k = j3;
            }
            if (l + k > 0)
            {
                j3 = 1;
            } else
            {
                j3 = 0;
            }
            j3 &= i2;
        } else
        {
            j3 = i2;
        }
        if (j3 != 0 && i4 != 0)
        {
            sparsebooleanarray.put(i4, true);
            i2 = j;
        } else
        {
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_733;
            }
            sparsebooleanarray.put(i4, false);
            for (int k3 = 0; k3 < k2;)
            {
                MenuItemImpl menuitemimpl2 = (MenuItemImpl)arraylist.get(k3);
                i2 = j;
                if (menuitemimpl2.getGroupId() == i4)
                {
                    i2 = j;
                    if ((menuitemimpl2.mFlags & 0x20) == 32)
                    {
                        i2 = j + 1;
                    }
                    menuitemimpl2.mFlags = menuitemimpl2.mFlags & 0xffffffdf;
                }
                k3++;
                j = i2;
            }

            i2 = j;
        }
_L9:
        j = i2;
        if (j3 != 0)
        {
            j = i2 - 1;
        }
        if (j3 != 0)
        {
            menuitemimpl1.mFlags = menuitemimpl1.mFlags | 0x20;
            i2 = l;
            l = j;
            j = i2;
        } else
        {
            menuitemimpl1.mFlags = menuitemimpl1.mFlags & 0xffffffdf;
            i2 = l;
            l = j;
            j = i2;
        }
          goto _L8
_L7:
        menuitemimpl1.mFlags = menuitemimpl1.mFlags & 0xffffffdf;
        i2 = l;
        l = j;
        j = i2;
          goto _L8
_L2:
        return true;
        i2 = j;
          goto _L9
    }

    public final View getItemView(MenuItemImpl menuitemimpl, View view, ViewGroup viewgroup)
    {
        View view1 = menuitemimpl.getActionView();
        if (view1 == null || menuitemimpl.hasCollapsibleActionView())
        {
            view1 = super.getItemView(menuitemimpl, view, viewgroup);
        }
        byte byte0;
        if (menuitemimpl.isActionViewExpanded())
        {
            byte0 = 8;
        } else
        {
            byte0 = 0;
        }
        view1.setVisibility(byte0);
        menuitemimpl = (ActionMenuView)viewgroup;
        view = view1.getLayoutParams();
        if (!menuitemimpl.checkLayoutParams(view))
        {
            view1.setLayoutParams(menuitemimpl.generateLayoutParams(view));
        }
        return view1;
    }

    public final MenuView getMenuView(ViewGroup viewgroup)
    {
        MenuView menuview = mMenuView;
        viewgroup = super.getMenuView(viewgroup);
        if (menuview != viewgroup)
        {
            ActionMenuView actionmenuview = (ActionMenuView)viewgroup;
            actionmenuview.mPresenter = this;
            ActionMenuPresenter actionmenupresenter = actionmenuview.mPresenter;
            actionmenupresenter.mMenuView = actionmenuview;
            actionmenuview.mMenu = actionmenupresenter.mMenu;
        }
        return viewgroup;
    }

    public final boolean hideOverflowMenu()
    {
        if (mPostedOpenRunnable != null && mMenuView != null)
        {
            ((View)mMenuView).removeCallbacks(mPostedOpenRunnable);
            mPostedOpenRunnable = null;
            return true;
        }
        OverflowPopup overflowpopup = mOverflowPopup;
        if (overflowpopup != null)
        {
            overflowpopup.dismiss();
            return true;
        } else
        {
            return false;
        }
    }

    public final void initForMenu(Context context, MenuBuilder menubuilder)
    {
        super.initForMenu(context, menubuilder);
        menubuilder = context.getResources();
        context = new ActionBarPolicy(context);
        if (!mReserveOverflowSet)
        {
            mReserveOverflow = true;
        }
        mWidthLimit = ((ActionBarPolicy) (context)).mContext.getResources().getDisplayMetrics().widthPixels / 2;
        mMaxItems = context.getMaxActionButtons();
        int i = mWidthLimit;
        if (mReserveOverflow)
        {
            if (mOverflowButton == null)
            {
                mOverflowButton = new OverflowMenuButton(mSystemContext);
                int j = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                mOverflowButton.measure(j, j);
            }
            i -= mOverflowButton.getMeasuredWidth();
        } else
        {
            mOverflowButton = null;
        }
        mActionItemWidthLimit = i;
        mMinCellSize = (int)(56F * menubuilder.getDisplayMetrics().density);
        mScrapActionButtonView = null;
    }

    public final boolean isOverflowMenuShowing()
    {
        if (mOverflowPopup != null)
        {
            OverflowPopup overflowpopup = mOverflowPopup;
            boolean flag;
            if (((MenuPopupHelper) (overflowpopup)).mPopup != null && ((MenuPopupHelper) (overflowpopup)).mPopup.isShowing())
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

    public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        hideOverflowMenu();
        boolean flag2;
        if (mActionButtonPopup != null)
        {
            mActionButtonPopup.dismiss();
            boolean flag1 = true;
        } else
        {
            flag2 = false;
        }
        super.onCloseMenu(menubuilder, flag);
    }

    public final boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        boolean flag = false;
        if (submenubuilder.hasVisibleItems()) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        MenuItem menuitem;
        ViewGroup viewgroup;
        SubMenuBuilder submenubuilder1;
        for (submenubuilder1 = submenubuilder; submenubuilder1.mParentMenu != mMenu; submenubuilder1 = (SubMenuBuilder)submenubuilder1.mParentMenu) { }
        menuitem = submenubuilder1.getItem();
        viewgroup = (ViewGroup)mMenuView;
        if (viewgroup == null) goto _L4; else goto _L3
_L3:
        int i;
        int j;
        j = viewgroup.getChildCount();
        i = 0;
_L11:
        if (i >= j) goto _L4; else goto _L5
_L5:
        Object obj = viewgroup.getChildAt(i);
        if (!(obj instanceof android.support.v7.view.menu.MenuView.ItemView) || ((android.support.v7.view.menu.MenuView.ItemView)obj).getItemData() != menuitem) goto _L7; else goto _L6
_L6:
        if (obj == null) goto _L1; else goto _L8
_L8:
        submenubuilder.getItem().getItemId();
        j = submenubuilder.size();
        i = 0;
_L12:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_314;
        }
        menuitem = submenubuilder.getItem(i);
        if (!menuitem.isVisible() || menuitem.getIcon() == null) goto _L10; else goto _L9
_L9:
        boolean flag1 = true;
_L13:
        mActionButtonPopup = new ActionButtonSubmenu(mContext, submenubuilder, ((View) (obj)));
        obj = mActionButtonPopup;
        obj.mForceShowIcon = flag1;
        if (((MenuPopupHelper) (obj)).mPopup != null)
        {
            ((MenuPopupHelper) (obj)).mPopup.setForceShowIcon(flag1);
        }
        obj = mActionButtonPopup;
        if (((MenuPopupHelper) (obj)).mPopup != null && ((MenuPopupHelper) (obj)).mPopup.isShowing())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = 1;
        } else
        {
            i = ((flag) ? 1 : 0);
            if (((MenuPopupHelper) (obj)).mAnchorView != null)
            {
                ((MenuPopupHelper) (obj)).showPopup(0, 0, false, false);
                i = 1;
            }
        }
        if (i == 0)
        {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        } else
        {
            super.onSubMenuSelected(submenubuilder);
            return true;
        }
_L7:
        i++;
          goto _L11
_L4:
        obj = null;
          goto _L6
_L10:
        i++;
          goto _L12
        flag1 = false;
          goto _L13
    }

    public final boolean shouldIncludeItem$514KOOBECHP6UQB45TPNAS3GDTP78BRM6SNNCQB5ESNMQPBEEKNKQPBEEL4N8PBD95MN0R1R55D0____0(MenuItemImpl menuitemimpl)
    {
        return (menuitemimpl.mFlags & 0x20) == 32;
    }

    public final boolean showOverflowMenu()
    {
        if (!mReserveOverflow) goto _L2; else goto _L1
_L1:
        if (mOverflowPopup == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        Object obj = mOverflowPopup;
        if (((MenuPopupHelper) (obj)).mPopup != null && ((MenuPopupHelper) (obj)).mPopup.isShowing())
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
        if (!flag && mMenu != null && mMenuView != null && mPostedOpenRunnable == null)
        {
            obj = mMenu;
            ((MenuBuilder) (obj)).flagActionItems();
            if (!((MenuBuilder) (obj)).mNonActionItems.isEmpty())
            {
                mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(mContext, mMenu, mOverflowButton, true));
                ((View)mMenuView).post(mPostedOpenRunnable);
                super.onSubMenuSelected(null);
                return true;
            }
        }
        break; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L6; else goto _L2
_L2:
        return false;
    }

    public final void updateMenuView(boolean flag)
    {
        boolean flag2 = false;
        super.updateMenuView(flag);
        ((View)mMenuView).requestLayout();
        if (mMenu != null)
        {
            Object obj = mMenu;
            ((MenuBuilder) (obj)).flagActionItems();
            obj = ((MenuBuilder) (obj)).mActionItems;
            int j = ((ArrayList) (obj)).size();
            for (int i = 0; i < j; i++)
            {
                ActionProvider actionprovider = ((MenuItemImpl)((ArrayList) (obj)).get(i)).mActionProvider;
                if (actionprovider != null)
                {
                    actionprovider.mSubUiVisibilityListener = this;
                }
            }

        }
        Object obj1;
        boolean flag1;
        if (mMenu != null)
        {
            obj1 = mMenu;
            ((MenuBuilder) (obj1)).flagActionItems();
            obj1 = ((MenuBuilder) (obj1)).mNonActionItems;
        } else
        {
            obj1 = null;
        }
        flag1 = flag2;
        if (mReserveOverflow)
        {
            flag1 = flag2;
            if (obj1 != null)
            {
                int k = ((ArrayList) (obj1)).size();
                OverflowMenuButton overflowmenubutton;
                ActionMenuView.LayoutParams layoutparams;
                if (k == 1)
                {
                    if (!((MenuItemImpl)((ArrayList) (obj1)).get(0)).isActionViewExpanded())
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                } else
                {
                    flag1 = flag2;
                    if (k > 0)
                    {
                        flag1 = true;
                    }
                }
            }
        }
        if (flag1)
        {
            if (mOverflowButton == null)
            {
                mOverflowButton = new OverflowMenuButton(mSystemContext);
            }
            obj1 = (ViewGroup)mOverflowButton.getParent();
            if (obj1 != mMenuView)
            {
                if (obj1 != null)
                {
                    ((ViewGroup) (obj1)).removeView(mOverflowButton);
                }
                obj1 = (ActionMenuView)mMenuView;
                overflowmenubutton = mOverflowButton;
                layoutparams = new ActionMenuView.LayoutParams(-2, -2);
                layoutparams.gravity = 16;
                layoutparams.isOverflowButton = true;
                ((ActionMenuView) (obj1)).addView(overflowmenubutton, layoutparams);
            }
        } else
        if (mOverflowButton != null && mOverflowButton.getParent() == mMenuView)
        {
            ((ViewGroup)mMenuView).removeView(mOverflowButton);
        }
        ((ActionMenuView)mMenuView).mReserveOverflow = mReserveOverflow;
    }

    private class PopupPresenterCallback
        implements android.support.v7.view.menu.MenuPresenter.Callback
    {

        private final ActionMenuPresenter this$0;

        public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            if (menubuilder instanceof SubMenuBuilder)
            {
                menubuilder.getRootMenu().close(false);
            }
            android.support.v7.view.menu.MenuPresenter.Callback callback = mCallback;
            if (callback != null)
            {
                callback.onCloseMenu(menubuilder, flag);
            }
        }

        public final boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            if (menubuilder == null)
            {
                return false;
            }
            ((SubMenuBuilder)menubuilder).getItem().getItemId();
            android.support.v7.view.menu.MenuPresenter.Callback callback = mCallback;
            if (callback != null)
            {
                return callback.onOpenSubMenu(menubuilder);
            } else
            {
                return false;
            }
        }

        PopupPresenterCallback()
        {
            this$0 = ActionMenuPresenter.this;
            super();
        }
    }


    private class ActionMenuPopupCallback extends android.support.v7.view.menu.ActionMenuItemView.PopupCallback
    {

        private final ActionMenuPresenter this$0;

        public final ShowableListMenu getPopup()
        {
            if (mActionButtonPopup != null)
            {
                return mActionButtonPopup.getPopup();
            } else
            {
                return null;
            }
        }

        ActionMenuPopupCallback()
        {
            this$0 = ActionMenuPresenter.this;
            super();
        }
    }


    private class OverflowMenuButton extends AppCompatImageView
        implements ActionMenuView.ActionMenuChildView
    {

        public final ActionMenuPresenter this$0;

        public final boolean needsDividerAfter()
        {
            return false;
        }

        public final boolean needsDividerBefore()
        {
            return false;
        }

        public final boolean performClick()
        {
            if (super.performClick())
            {
                return true;
            } else
            {
                playSoundEffect(0);
                showOverflowMenu();
                return true;
            }
        }

        protected final boolean setFrame(int i, int j, int k, int l)
        {
            boolean flag = super.setFrame(i, j, k, l);
            Drawable drawable = getDrawable();
            Drawable drawable1 = getBackground();
            if (drawable != null && drawable1 != null)
            {
                int i1 = getWidth();
                j = getHeight();
                i = Math.max(i1, j) / 2;
                int j1 = getPaddingLeft();
                int k1 = getPaddingRight();
                k = getPaddingTop();
                l = getPaddingBottom();
                i1 = (i1 + (j1 - k1)) / 2;
                j = (j + (k - l)) / 2;
                drawable1.setHotspotBounds(i1 - i, j - i, i1 + i, j + i);
            }
            return flag;
        }

        public OverflowMenuButton(Context context)
        {
            this$0 = ActionMenuPresenter.this;
            super(context, null, 0x7f01007b);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            context = getContentDescription();
            class _cls1 extends ForwardingListener
            {

                private final OverflowMenuButton this$1;

                public final ShowableListMenu getPopup()
                {
                    if (mOverflowPopup == null)
                    {
                        return null;
                    } else
                    {
                        return mOverflowPopup.getPopup();
                    }
                }

                public final boolean onForwardingStarted()
                {
                    showOverflowMenu();
                    return true;
                }

                public final boolean onForwardingStopped()
                {
                    if (mPostedOpenRunnable != null)
                    {
                        return false;
                    } else
                    {
                        hideOverflowMenu();
                        return true;
                    }
                }

                _cls1(View view, ActionMenuPresenter actionmenupresenter)
                {
                    this$1 = OverflowMenuButton.this;
                    super(view);
                }
            }

            if (android.os.Build.VERSION.SDK_INT >= 26)
            {
                setTooltipText(context);
            } else
            {
                if (TooltipCompatHandler.sPendingHandler != null && TooltipCompatHandler.sPendingHandler.mAnchor == this)
                {
                    if (TooltipCompatHandler.sPendingHandler != null)
                    {
                        TooltipCompatHandler tooltipcompathandler = TooltipCompatHandler.sPendingHandler;
                        tooltipcompathandler.mAnchor.removeCallbacks(tooltipcompathandler.mShowRunnable);
                    }
                    TooltipCompatHandler.sPendingHandler = null;
                    if (false)
                    {
                        TooltipCompatHandler tooltipcompathandler1 = TooltipCompatHandler.sPendingHandler;
                        tooltipcompathandler1.mAnchor.postDelayed(tooltipcompathandler1.mShowRunnable, ViewConfiguration.getLongPressTimeout());
                    }
                }
                if (TextUtils.isEmpty(context))
                {
                    if (TooltipCompatHandler.sActiveHandler != null && TooltipCompatHandler.sActiveHandler.mAnchor == this)
                    {
                        TooltipCompatHandler.sActiveHandler.hide();
                    }
                    setOnLongClickListener(null);
                    setLongClickable(false);
                    setOnHoverListener(null);
                } else
                {
                    new TooltipCompatHandler(this, context);
                }
            }
            setOnTouchListener(new _cls1(this, ActionMenuPresenter.this));
        }
    }


    private class ActionButtonSubmenu extends MenuPopupHelper
    {

        private final ActionMenuPresenter this$0;

        protected final void onDismiss()
        {
            mActionButtonPopup = null;
            super.onDismiss();
        }

        public ActionButtonSubmenu(Context context, SubMenuBuilder submenubuilder, View view)
        {
            boolean flag = false;
            this$0 = ActionMenuPresenter.this;
            super(context, submenubuilder, view, false, 0x7f01007c);
            if ((((MenuItemImpl)submenubuilder.getItem()).mFlags & 0x20) == 32)
            {
                flag = true;
            }
            if (!flag)
            {
                if (mOverflowButton == null)
                {
                    context = (View)mMenuView;
                } else
                {
                    context = mOverflowButton;
                }
                super.mAnchorView = context;
            }
            actionmenupresenter = mPopupPresenterCallback;
            super.mPresenterCallback = ActionMenuPresenter.this;
            if (super.mPopup != null)
            {
                super.mPopup.setCallback(ActionMenuPresenter.this);
            }
        }
    }


    private class OpenOverflowRunnable
        implements Runnable
    {

        private OverflowPopup mPopup;
        private final ActionMenuPresenter this$0;

        public final void run()
        {
            if (mMenu != null)
            {
                MenuBuilder menubuilder = mMenu;
                if (menubuilder.mCallback != null)
                {
                    menubuilder.mCallback.onMenuModeChange(menubuilder);
                }
            }
            View view = (View)mMenuView;
            if (view != null && view.getWindowToken() != null && mPopup.tryShow())
            {
                mOverflowPopup = mPopup;
            }
            mPostedOpenRunnable = null;
        }

        public OpenOverflowRunnable(OverflowPopup overflowpopup)
        {
            this$0 = ActionMenuPresenter.this;
            super();
            mPopup = overflowpopup;
        }
    }


    private class OverflowPopup extends MenuPopupHelper
    {

        private final ActionMenuPresenter this$0;

        protected final void onDismiss()
        {
            if (mMenu != null)
            {
                mMenu.close();
            }
            mOverflowPopup = null;
            super.onDismiss();
        }

        public OverflowPopup(Context context, MenuBuilder menubuilder, View view, boolean flag)
        {
            this$0 = ActionMenuPresenter.this;
            super(context, menubuilder, view, true, 0x7f01007c);
            super.mDropDownGravity = 0x800005;
            actionmenupresenter = mPopupPresenterCallback;
            super.mPresenterCallback = ActionMenuPresenter.this;
            if (super.mPopup != null)
            {
                super.mPopup.setCallback(ActionMenuPresenter.this);
            }
        }
    }

}
