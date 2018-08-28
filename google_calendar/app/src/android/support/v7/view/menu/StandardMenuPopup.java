// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.MenuPopupWindow;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v7.view.menu:
//            MenuPopup, MenuPresenter, MenuAdapter, MenuBuilder, 
//            MenuPopupHelper, SubMenuBuilder

final class StandardMenuPopup extends MenuPopup
    implements MenuPresenter, android.view.View.OnKeyListener, android.widget.AdapterView.OnItemClickListener, android.widget.PopupWindow.OnDismissListener
{

    private static final int ITEM_LAYOUT = 0x7f050013;
    private final MenuAdapter mAdapter;
    private View mAnchorView;
    private final android.view.View.OnAttachStateChangeListener mAttachStateChangeListener = new _cls2();
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity;
    public final android.view.ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new _cls1();
    private boolean mHasContentWidth;
    private final MenuBuilder mMenu;
    private android.widget.PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    public final MenuPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private boolean mShowTitle;
    public View mShownAnchorView;
    public ViewTreeObserver mTreeObserver;
    public boolean mWasDismissed;

    public StandardMenuPopup(Context context, MenuBuilder menubuilder, View view, int i, int j, boolean flag)
    {
        mDropDownGravity = 0;
        mContext = context;
        mMenu = menubuilder;
        mOverflowOnly = flag;
        mAdapter = new MenuAdapter(menubuilder, LayoutInflater.from(context), mOverflowOnly, ITEM_LAYOUT);
        mPopupStyleAttr = i;
        mPopupStyleRes = j;
        Resources resources = context.getResources();
        mPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(0x7f0e0017));
        mAnchorView = view;
        mPopup = new MenuPopupWindow(mContext, null, mPopupStyleAttr, mPopupStyleRes);
        menubuilder.mPresenters.add(new WeakReference(this));
        initForMenu(context, menubuilder);
        menubuilder.mIsActionItemsStale = true;
    }

    public final void addMenu(MenuBuilder menubuilder)
    {
    }

    public final void dismiss()
    {
        boolean flag;
        if (!mWasDismissed && ((ListPopupWindow) (mPopup)).mPopup.isShowing())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            mPopup.dismiss();
        }
    }

    public final boolean flagActionItems()
    {
        return false;
    }

    public final ListView getListView()
    {
        return ((ListPopupWindow) (mPopup)).mDropDownList;
    }

    public final boolean isShowing()
    {
        return !mWasDismissed && ((ListPopupWindow) (mPopup)).mPopup.isShowing();
    }

    public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if (menubuilder == mMenu)
        {
            dismiss();
            if (mPresenterCallback != null)
            {
                mPresenterCallback.onCloseMenu(menubuilder, flag);
                return;
            }
        }
    }

    public final void onDismiss()
    {
        mWasDismissed = true;
        mMenu.close();
        if (mTreeObserver != null)
        {
            if (!mTreeObserver.isAlive())
            {
                mTreeObserver = mShownAnchorView.getViewTreeObserver();
            }
            mTreeObserver.removeGlobalOnLayoutListener(mGlobalLayoutListener);
            mTreeObserver = null;
        }
        mShownAnchorView.removeOnAttachStateChangeListener(mAttachStateChangeListener);
        if (mOnDismissListener != null)
        {
            mOnDismissListener.onDismiss();
        }
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
        boolean flag2 = false;
        boolean flag1 = flag2;
        if (submenubuilder.hasVisibleItems())
        {
            MenuPopupHelper menupopuphelper = new MenuPopupHelper(mContext, submenubuilder, mShownAnchorView, mOverflowOnly, mPopupStyleAttr, mPopupStyleRes);
            Object obj = mPresenterCallback;
            menupopuphelper.mPresenterCallback = ((MenuPresenter.Callback) (obj));
            if (menupopuphelper.mPopup != null)
            {
                menupopuphelper.mPopup.setCallback(((MenuPresenter.Callback) (obj)));
            }
            flag1 = MenuPopup.shouldPreserveIconSpacing(submenubuilder);
            menupopuphelper.mForceShowIcon = flag1;
            if (menupopuphelper.mPopup != null)
            {
                menupopuphelper.mPopup.setForceShowIcon(flag1);
            }
            menupopuphelper.mOnDismissListener = mOnDismissListener;
            mOnDismissListener = null;
            mMenu.close(false);
            int k = ((ListPopupWindow) (mPopup)).mDropDownHorizontalOffset;
            obj = mPopup;
            int i;
            int j;
            boolean flag;
            if (!((ListPopupWindow) (obj)).mDropDownVerticalOffsetSet)
            {
                i = 0;
            } else
            {
                i = ((ListPopupWindow) (obj)).mDropDownVerticalOffset;
            }
            j = k;
            if ((Gravity.getAbsoluteGravity(mDropDownGravity, ViewCompat.getLayoutDirection(mAnchorView)) & 7) == 5)
            {
                j = k + mAnchorView.getWidth();
            }
            if (menupopuphelper.mPopup != null && menupopuphelper.mPopup.isShowing())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                i = 1;
            } else
            if (menupopuphelper.mAnchorView == null)
            {
                i = 0;
            } else
            {
                menupopuphelper.showPopup(j, i, true, true);
                i = 1;
            }
            flag1 = flag2;
            if (i != 0)
            {
                if (mPresenterCallback != null)
                {
                    mPresenterCallback.onOpenSubMenu(submenubuilder);
                }
                flag1 = true;
            }
        }
        return flag1;
    }

    public final void setAnchorView(View view)
    {
        mAnchorView = view;
    }

    public final void setCallback(MenuPresenter.Callback callback)
    {
        mPresenterCallback = callback;
    }

    public final void setForceShowIcon(boolean flag)
    {
        mAdapter.mForceShowIcon = flag;
    }

    public final void setGravity(int i)
    {
        mDropDownGravity = i;
    }

    public final void setHorizontalOffset(int i)
    {
        mPopup.mDropDownHorizontalOffset = i;
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
        MenuPopupWindow menupopupwindow = mPopup;
        menupopupwindow.mDropDownVerticalOffset = i;
        menupopupwindow.mDropDownVerticalOffsetSet = true;
    }

    public final void show()
    {
        boolean flag1 = true;
        boolean flag;
        if (!mWasDismissed && ((ListPopupWindow) (mPopup)).mPopup.isShowing())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            flag = flag1;
        } else
        if (mWasDismissed || mAnchorView == null)
        {
            flag = false;
        } else
        {
            mShownAnchorView = mAnchorView;
            ((ListPopupWindow) (mPopup)).mPopup.setOnDismissListener(this);
            mPopup.mItemClickListener = this;
            Object obj = mPopup;
            obj.mModal = true;
            ((ListPopupWindow) (obj)).mPopup.setFocusable(true);
            obj = mShownAnchorView;
            if (mTreeObserver == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mTreeObserver = ((View) (obj)).getViewTreeObserver();
            if (flag)
            {
                mTreeObserver.addOnGlobalLayoutListener(mGlobalLayoutListener);
            }
            ((View) (obj)).addOnAttachStateChangeListener(mAttachStateChangeListener);
            mPopup.mDropDownAnchorView = ((View) (obj));
            mPopup.mDropDownGravity = mDropDownGravity;
            if (!mHasContentWidth)
            {
                mContentWidth = measureIndividualMenuWidth(mAdapter, null, mContext, mPopupMaxWidth);
                mHasContentWidth = true;
            }
            mPopup.setContentWidth(mContentWidth);
            ((ListPopupWindow) (mPopup)).mPopup.setInputMethodMode(2);
            mPopup.mEpicenterBounds = super.mEpicenterBounds;
            mPopup.show();
            obj = ((ListPopupWindow) (mPopup)).mDropDownList;
            ((ListView) (obj)).setOnKeyListener(this);
            if (mShowTitle && mMenu.mHeaderTitle != null)
            {
                FrameLayout framelayout = (FrameLayout)LayoutInflater.from(mContext).inflate(0x7f050012, ((android.view.ViewGroup) (obj)), false);
                TextView textview = (TextView)framelayout.findViewById(0x1020016);
                if (textview != null)
                {
                    textview.setText(mMenu.mHeaderTitle);
                }
                framelayout.setEnabled(false);
                ((ListView) (obj)).addHeaderView(framelayout, null, false);
            }
            mPopup.setAdapter(mAdapter);
            mPopup.show();
            flag = flag1;
        }
        if (!flag)
        {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        } else
        {
            return;
        }
    }

    public final void updateMenuView(boolean flag)
    {
        mHasContentWidth = false;
        if (mAdapter != null)
        {
            mAdapter.notifyDataSetChanged();
        }
    }


    private class _cls1
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final StandardMenuPopup this$0;

        public final void onGlobalLayout()
        {
label0:
            {
                StandardMenuPopup standardmenupopup = StandardMenuPopup.this;
                boolean flag;
                if (!standardmenupopup.mWasDismissed && ((ListPopupWindow) (standardmenupopup.mPopup)).mPopup.isShowing())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && !((ListPopupWindow) (mPopup)).mModal)
                {
                    View view = mShownAnchorView;
                    if (view != null && view.isShown())
                    {
                        break label0;
                    }
                    dismiss();
                }
                return;
            }
            mPopup.show();
        }

        _cls1()
        {
            this$0 = StandardMenuPopup.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnAttachStateChangeListener
    {

        private final StandardMenuPopup this$0;

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
            this$0 = StandardMenuPopup.this;
            super();
        }
    }

}
