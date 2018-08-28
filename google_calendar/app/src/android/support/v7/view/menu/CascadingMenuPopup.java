// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.MenuPopupWindow;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v7.view.menu:
//            MenuPopup, MenuPresenter, MenuAdapter, MenuBuilder, 
//            MenuItemImpl, SubMenuBuilder

final class CascadingMenuPopup extends MenuPopup
    implements MenuPresenter, android.view.View.OnKeyListener, android.widget.PopupWindow.OnDismissListener
{

    private static final int ITEM_LAYOUT = 0x7f05000b;
    private View mAnchorView;
    private final android.view.View.OnAttachStateChangeListener mAttachStateChangeListener = new _cls2();
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    public final android.view.ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new _cls1();
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private int mLastPosition;
    private final MenuItemHoverListener mMenuItemHoverListener = new _cls3();
    private final int mMenuMaxWidth;
    private android.widget.PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final List mPendingMenus = new ArrayList();
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private int mRawDropDownGravity;
    public boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    public final List mShowingMenus = new ArrayList();
    public View mShownAnchorView;
    public final Handler mSubMenuHoverHandler = new Handler();
    public ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;

    public CascadingMenuPopup(Context context, View view, int i, int j, boolean flag)
    {
        boolean flag1 = false;
        super();
        mRawDropDownGravity = 0;
        mDropDownGravity = 0;
        mContext = context;
        mAnchorView = view;
        mPopupStyleAttr = i;
        mPopupStyleRes = j;
        mOverflowOnly = flag;
        mForceShowIcon = false;
        if (ViewCompat.getLayoutDirection(mAnchorView) == 1)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 1;
        }
        mLastPosition = i;
        context = context.getResources();
        mMenuMaxWidth = Math.max(context.getDisplayMetrics().widthPixels / 2, context.getDimensionPixelSize(0x7f0e0017));
    }

    private final void showMenu(MenuBuilder menubuilder)
    {
        Object obj;
        Object obj1;
        Object obj2;
        MenuPopupWindow menupopupwindow;
        int i;
        int i1;
        LayoutInflater layoutinflater = LayoutInflater.from(mContext);
        obj = new MenuAdapter(menubuilder, layoutinflater, mOverflowOnly, ITEM_LAYOUT);
        Rect rect;
        int j;
        if (!isShowing() && mForceShowIcon)
        {
            obj.mForceShowIcon = true;
        } else
        if (isShowing())
        {
            obj.mForceShowIcon = MenuPopup.shouldPreserveIconSpacing(menubuilder);
        }
        i1 = measureIndividualMenuWidth(((android.widget.ListAdapter) (obj)), null, mContext, mMenuMaxWidth);
        menupopupwindow = new MenuPopupWindow(mContext, null, mPopupStyleAttr, mPopupStyleRes);
        menupopupwindow.mHoverListener = mMenuItemHoverListener;
        menupopupwindow.mItemClickListener = this;
        ((ListPopupWindow) (menupopupwindow)).mPopup.setOnDismissListener(this);
        menupopupwindow.mDropDownAnchorView = mAnchorView;
        menupopupwindow.mDropDownGravity = mDropDownGravity;
        menupopupwindow.mModal = true;
        ((ListPopupWindow) (menupopupwindow)).mPopup.setFocusable(true);
        ((ListPopupWindow) (menupopupwindow)).mPopup.setInputMethodMode(2);
        menupopupwindow.setAdapter(((android.widget.ListAdapter) (obj)));
        menupopupwindow.setContentWidth(i1);
        menupopupwindow.mDropDownGravity = mDropDownGravity;
        if (mShowingMenus.size() <= 0) goto _L2; else goto _L1
_L1:
        obj2 = (CascadingMenuInfo)mShowingMenus.get(mShowingMenus.size() - 1);
        obj1 = ((CascadingMenuInfo) (obj2)).menu;
        j = ((MenuBuilder) (obj1)).size();
        i = 0;
_L15:
        if (i >= j) goto _L4; else goto _L3
_L3:
        obj = ((MenuBuilder) (obj1)).getItem(i);
        if (!((MenuItem) (obj)).hasSubMenu() || menubuilder != ((MenuItem) (obj)).getSubMenu()) goto _L6; else goto _L5
_L5:
        if (obj != null) goto _L8; else goto _L7
_L7:
        obj = null;
_L18:
        obj1 = obj;
        obj = obj2;
_L20:
        if (obj1 == null) goto _L10; else goto _L9
_L9:
        int ai[];
        Object obj3;
        int ai1[];
        int k;
        int l;
        if (MenuPopupWindow.sSetTouchModalMethod != null)
        {
            try
            {
                MenuPopupWindow.sSetTouchModalMethod.invoke(menupopupwindow.mPopup, new Object[] {
                    Boolean.valueOf(false)
                });
            }
            catch (Exception exception) { }
        }
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            menupopupwindow.mPopup.setEnterTransition(null);
        }
        obj2 = ((ListPopupWindow) (((CascadingMenuInfo)mShowingMenus.get(mShowingMenus.size() - 1)).window)).mDropDownList;
        obj3 = new int[2];
        ((ListView) (obj2)).getLocationOnScreen(((int []) (obj3)));
        rect = new Rect();
        mShownAnchorView.getWindowVisibleDisplayFrame(rect);
        if (mLastPosition != 1) goto _L12; else goto _L11
_L11:
        i = obj3[0];
        if (((ListView) (obj2)).getWidth() + i + i1 > rect.right) goto _L14; else goto _L13
_L13:
        i = 1;
_L22:
        if (i == 1)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        mLastPosition = i;
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            menupopupwindow.mDropDownAnchorView = ((View) (obj1));
            i = 0;
            k = 0;
        } else
        {
            ai = new int[2];
            mAnchorView.getLocationOnScreen(ai);
            ai1 = new int[2];
            ((View) (obj1)).getLocationOnScreen(ai1);
            if ((mDropDownGravity & 7) == 5)
            {
                ai[0] = ai[0] + mAnchorView.getWidth();
                ai1[0] = ai1[0] + ((View) (obj1)).getWidth();
            }
            i = ai1[0] - ai[0];
            k = ai1[1] - ai[1];
        }
        if ((mDropDownGravity & 5) == 5)
        {
            if (l != 0)
            {
                i += i1;
            } else
            {
                i -= ((View) (obj1)).getWidth();
            }
        } else
        if (l != 0)
        {
            i = ((View) (obj1)).getWidth() + i;
        } else
        {
            i -= i1;
        }
        menupopupwindow.mDropDownHorizontalOffset = i;
        menupopupwindow.mOverlapAnchorSet = true;
        menupopupwindow.mOverlapAnchor = true;
        menupopupwindow.mDropDownVerticalOffset = k;
        menupopupwindow.mDropDownVerticalOffsetSet = true;
_L23:
        obj1 = new CascadingMenuInfo(menupopupwindow, menubuilder, mLastPosition);
        mShowingMenus.add(obj1);
        menupopupwindow.show();
        obj1 = ((ListPopupWindow) (menupopupwindow)).mDropDownList;
        ((ListView) (obj1)).setOnKeyListener(this);
        if (obj == null && mShowTitle && menubuilder.mHeaderTitle != null)
        {
            obj = (FrameLayout)layoutinflater.inflate(0x7f050012, ((android.view.ViewGroup) (obj1)), false);
            obj2 = (TextView)((FrameLayout) (obj)).findViewById(0x1020016);
            ((FrameLayout) (obj)).setEnabled(false);
            ((TextView) (obj2)).setText(menubuilder.mHeaderTitle);
            ((ListView) (obj1)).addHeaderView(((View) (obj)), null, false);
            menupopupwindow.show();
        }
        return;
_L6:
        i++;
          goto _L15
_L4:
        obj = null;
          goto _L5
_L8:
        obj3 = ((ListPopupWindow) (((CascadingMenuInfo) (obj2)).window)).mDropDownList;
        obj1 = ((ListView) (obj3)).getAdapter();
        if (obj1 instanceof HeaderViewListAdapter)
        {
            obj1 = (HeaderViewListAdapter)obj1;
            k = ((HeaderViewListAdapter) (obj1)).getHeadersCount();
            obj1 = (MenuAdapter)((HeaderViewListAdapter) (obj1)).getWrappedAdapter();
        } else
        {
            obj1 = (MenuAdapter)obj1;
            k = 0;
        }
        i = 0;
        l = ((MenuAdapter) (obj1)).getCount();
_L19:
        if (i >= l)
        {
            break MISSING_BLOCK_LABEL_1013;
        }
        if (obj != (MenuItemImpl)((MenuAdapter) (obj1)).getItem(i)) goto _L17; else goto _L16
_L16:
        if (i == -1)
        {
            obj = null;
        } else
        {
            i = (i + k) - ((ListView) (obj3)).getFirstVisiblePosition();
            if (i < 0 || i >= ((ListView) (obj3)).getChildCount())
            {
                obj = null;
            } else
            {
                obj = ((ListView) (obj3)).getChildAt(i);
            }
        }
          goto _L18
_L17:
        i++;
          goto _L19
_L2:
        obj1 = null;
        obj = null;
          goto _L20
_L12:
        if (obj3[0] - i1 >= 0) goto _L14; else goto _L21
_L21:
        i = 1;
          goto _L22
_L14:
        i = 0;
          goto _L22
_L10:
        if (mHasXOffset)
        {
            menupopupwindow.mDropDownHorizontalOffset = mXOffset;
        }
        if (mHasYOffset)
        {
            menupopupwindow.mDropDownVerticalOffset = mYOffset;
            menupopupwindow.mDropDownVerticalOffsetSet = true;
        }
        menupopupwindow.mEpicenterBounds = super.mEpicenterBounds;
          goto _L23
        i = -1;
          goto _L16
    }

    public final void addMenu(MenuBuilder menubuilder)
    {
        Context context = mContext;
        menubuilder.mPresenters.add(new WeakReference(this));
        initForMenu(context, menubuilder);
        menubuilder.mIsActionItemsStale = true;
        if (isShowing())
        {
            showMenu(menubuilder);
            return;
        } else
        {
            mPendingMenus.add(menubuilder);
            return;
        }
    }

    protected final boolean closeMenuOnSubMenuOpened()
    {
        return false;
    }

    public final void dismiss()
    {
        int i = mShowingMenus.size();
        if (i > 0)
        {
            CascadingMenuInfo acascadingmenuinfo[] = (CascadingMenuInfo[])mShowingMenus.toArray(new CascadingMenuInfo[i]);
            for (i--; i >= 0; i--)
            {
                CascadingMenuInfo cascadingmenuinfo = acascadingmenuinfo[i];
                if (((ListPopupWindow) (cascadingmenuinfo.window)).mPopup.isShowing())
                {
                    cascadingmenuinfo.window.dismiss();
                }
            }

        }
    }

    public final boolean flagActionItems()
    {
        return false;
    }

    public final ListView getListView()
    {
        if (mShowingMenus.isEmpty())
        {
            return null;
        } else
        {
            return ((ListPopupWindow) (((CascadingMenuInfo)mShowingMenus.get(mShowingMenus.size() - 1)).window)).mDropDownList;
        }
    }

    public final boolean isShowing()
    {
        return mShowingMenus.size() > 0 && ((ListPopupWindow) (((CascadingMenuInfo)mShowingMenus.get(0)).window)).mPopup.isShowing();
    }

    public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        int i;
        int j;
        j = mShowingMenus.size();
        i = 0;
_L9:
        if (i >= j) goto _L2; else goto _L1
_L1:
        if (menubuilder != ((CascadingMenuInfo)mShowingMenus.get(i)).menu) goto _L4; else goto _L3
_L3:
        if (i >= 0) goto _L6; else goto _L5
_L5:
        return;
_L4:
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        i = -1;
          goto _L3
_L6:
        int k = i + 1;
        if (k < mShowingMenus.size())
        {
            ((CascadingMenuInfo)mShowingMenus.get(k)).menu.close(false);
        }
        CascadingMenuInfo cascadingmenuinfo = (CascadingMenuInfo)mShowingMenus.remove(i);
        cascadingmenuinfo.menu.removeMenuPresenter(this);
        if (mShouldCloseImmediately)
        {
            MenuPopupWindow menupopupwindow = cascadingmenuinfo.window;
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                menupopupwindow.mPopup.setExitTransition(null);
            }
            ((ListPopupWindow) (cascadingmenuinfo.window)).mPopup.setAnimationStyle(0);
        }
        cascadingmenuinfo.window.dismiss();
        k = mShowingMenus.size();
        if (k > 0)
        {
            i = ((CascadingMenuInfo)mShowingMenus.get(k - 1)).position;
        } else
        if (ViewCompat.getLayoutDirection(mAnchorView) == 1)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        mLastPosition = i;
        if (k == 0)
        {
            dismiss();
            if (mPresenterCallback != null)
            {
                mPresenterCallback.onCloseMenu(menubuilder, true);
            }
            if (mTreeObserver != null)
            {
                if (mTreeObserver.isAlive())
                {
                    mTreeObserver.removeGlobalOnLayoutListener(mGlobalLayoutListener);
                }
                mTreeObserver = null;
            }
            mShownAnchorView.removeOnAttachStateChangeListener(mAttachStateChangeListener);
            mOnDismissListener.onDismiss();
            return;
        }
        if (!flag) goto _L5; else goto _L7
_L7:
        ((CascadingMenuInfo)mShowingMenus.get(0)).menu.close(false);
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public final void onDismiss()
    {
        int i;
        int j;
        j = mShowingMenus.size();
        i = 0;
_L3:
        CascadingMenuInfo cascadingmenuinfo;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        cascadingmenuinfo = (CascadingMenuInfo)mShowingMenus.get(i);
        if (((ListPopupWindow) (cascadingmenuinfo.window)).mPopup.isShowing()) goto _L2; else goto _L1
_L1:
        if (cascadingmenuinfo != null)
        {
            cascadingmenuinfo.menu.close(false);
        }
        return;
_L2:
        i++;
          goto _L3
        cascadingmenuinfo = null;
          goto _L1
    }

    public final boolean onKey(View view, int i, KeyEvent keyevent)
    {
        if (keyevent.getAction() == 1 && i == 82)
        {
            dismiss();
            return true;
        } else
        {
            return false;
        }
    }

    public final boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        for (Iterator iterator = mShowingMenus.iterator(); iterator.hasNext();)
        {
            CascadingMenuInfo cascadingmenuinfo = (CascadingMenuInfo)iterator.next();
            if (submenubuilder == cascadingmenuinfo.menu)
            {
                ((ListPopupWindow) (cascadingmenuinfo.window)).mDropDownList.requestFocus();
                return true;
            }
        }

        if (submenubuilder.hasVisibleItems())
        {
            addMenu(submenubuilder);
            if (mPresenterCallback != null)
            {
                mPresenterCallback.onOpenSubMenu(submenubuilder);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public final void setAnchorView(View view)
    {
        if (mAnchorView != view)
        {
            mAnchorView = view;
            mDropDownGravity = Gravity.getAbsoluteGravity(mRawDropDownGravity, ViewCompat.getLayoutDirection(mAnchorView));
        }
    }

    public final void setCallback(MenuPresenter.Callback callback)
    {
        mPresenterCallback = callback;
    }

    public final void setForceShowIcon(boolean flag)
    {
        mForceShowIcon = flag;
    }

    public final void setGravity(int i)
    {
        if (mRawDropDownGravity != i)
        {
            mRawDropDownGravity = i;
            mDropDownGravity = Gravity.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(mAnchorView));
        }
    }

    public final void setHorizontalOffset(int i)
    {
        mHasXOffset = true;
        mXOffset = i;
    }

    public final void setOnDismissListener(android.widget.PopupWindow.OnDismissListener ondismisslistener)
    {
        mOnDismissListener = ondismisslistener;
    }

    public final void setShowTitle(boolean flag)
    {
        mShowTitle = flag;
    }

    public final void setVerticalOffset(int i)
    {
        mHasYOffset = true;
        mYOffset = i;
    }

    public final void show()
    {
        if (!isShowing())
        {
            for (Iterator iterator = mPendingMenus.iterator(); iterator.hasNext(); showMenu((MenuBuilder)iterator.next())) { }
            mPendingMenus.clear();
            mShownAnchorView = mAnchorView;
            if (mShownAnchorView != null)
            {
                boolean flag;
                if (mTreeObserver == null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                mTreeObserver = mShownAnchorView.getViewTreeObserver();
                if (flag)
                {
                    mTreeObserver.addOnGlobalLayoutListener(mGlobalLayoutListener);
                }
                mShownAnchorView.addOnAttachStateChangeListener(mAttachStateChangeListener);
                return;
            }
        }
    }

    public final void updateMenuView(boolean flag)
    {
        Iterator iterator = mShowingMenus.iterator();
        while (iterator.hasNext()) 
        {
            Object obj = ((ListPopupWindow) (((CascadingMenuInfo)iterator.next()).window)).mDropDownList.getAdapter();
            if (obj instanceof HeaderViewListAdapter)
            {
                obj = (MenuAdapter)((HeaderViewListAdapter)obj).getWrappedAdapter();
            } else
            {
                obj = (MenuAdapter)obj;
            }
            ((MenuAdapter) (obj)).notifyDataSetChanged();
        }
    }


    private class _cls1
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final CascadingMenuPopup this$0;

        public final void onGlobalLayout()
        {
            if (isShowing() && mShowingMenus.size() > 0 && !((ListPopupWindow) (((CascadingMenuInfo)mShowingMenus.get(0)).window)).mModal)
            {
                View view = mShownAnchorView;
                if (view == null || !view.isShown())
                {
                    dismiss();
                } else
                {
                    Iterator iterator = mShowingMenus.iterator();
                    while (iterator.hasNext()) 
                    {
                        ((CascadingMenuInfo)iterator.next()).window.show();
                    }
                }
            }
        }

        _cls1()
        {
            this$0 = CascadingMenuPopup.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnAttachStateChangeListener
    {

        private final CascadingMenuPopup this$0;

        public final void onViewAttachedToWindow(View view)
        {
        }

        public final void onViewDetachedFromWindow(View view)
        {
            if (mTreeObserver != null)
            {
                if (!mTreeObserver.isAlive())
                {
                    mTreeObserver = view.getViewTreeObserver();
                }
                mTreeObserver.removeGlobalOnLayoutListener(mGlobalLayoutListener);
            }
            view.removeOnAttachStateChangeListener(this);
        }

        _cls2()
        {
            this$0 = CascadingMenuPopup.this;
            super();
        }
    }


    private class _cls3
        implements MenuItemHoverListener
    {

        public final CascadingMenuPopup this$0;

        public final void onItemHoverEnter(final MenuBuilder menu, final MenuItem item)
        {
            int i;
            int j;
            mSubMenuHoverHandler.removeCallbacksAndMessages(null);
            i = 0;
            j = mShowingMenus.size();
_L4:
label0:
            {
                if (i >= j)
                {
                    break MISSING_BLOCK_LABEL_158;
                }
                if (menu == ((CascadingMenuInfo)mShowingMenus.get(i)).menu)
                {
                    break label0;
                } else
                {
                    i++;
                    continue; /* Loop/switch isn't completed */
                }
            }
_L2:
            if (i == -1)
            {
                return;
            }
            i++;
            class _cls1
                implements Runnable
            {

                private final _cls3 this$1;
                private final MenuItem val$item;
                private final MenuBuilder val$menu;
                private final CascadingMenuInfo val$nextInfo;

                public final void run()
                {
                    if (nextInfo != null)
                    {
                        mShouldCloseImmediately = true;
                        nextInfo.menu.close(false);
                        mShouldCloseImmediately = false;
                    }
                    if (item.isEnabled() && item.hasSubMenu())
                    {
                        menu.performItemAction(item, null, 4);
                    }
                }

                _cls1()
                {
                    this$1 = _cls3.this;
                    nextInfo = cascadingmenuinfo;
                    item = menuitem;
                    menu = menubuilder;
                    super();
                }
            }

            final CascadingMenuInfo nextInfo;
            long l;
            if (i < mShowingMenus.size())
            {
                nextInfo = (CascadingMenuInfo)mShowingMenus.get(i);
            } else
            {
                nextInfo = null;
            }
            item = new _cls1();
            l = SystemClock.uptimeMillis();
            mSubMenuHoverHandler.postAtTime(item, menu, l + 200L);
            return;
            i = -1;
            if (true) goto _L2; else goto _L1
_L1:
            if (true) goto _L4; else goto _L3
_L3:
        }

        public final void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem)
        {
            mSubMenuHoverHandler.removeCallbacksAndMessages(menubuilder);
        }

        _cls3()
        {
            this$0 = CascadingMenuPopup.this;
            super();
        }
    }


    private class CascadingMenuInfo
    {

        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public CascadingMenuInfo(MenuPopupWindow menupopupwindow, MenuBuilder menubuilder, int i)
        {
            window = menupopupwindow;
            menu = menubuilder;
            position = i;
        }
    }

}
