// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

// Referenced classes of package android.support.v7.view.menu:
//            MenuPopup, CascadingMenuPopup, StandardMenuPopup, MenuBuilder

public class MenuPopupHelper
{

    public View mAnchorView;
    private final Context mContext;
    public int mDropDownGravity;
    public boolean mForceShowIcon;
    private final android.widget.PopupWindow.OnDismissListener mInternalOnDismissListener;
    private final MenuBuilder mMenu;
    public android.widget.PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    public MenuPopup mPopup;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    public MenuPresenter.Callback mPresenterCallback;

    public MenuPopupHelper(Context context, MenuBuilder menubuilder, View view, boolean flag, int i)
    {
        this(context, menubuilder, view, flag, i, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menubuilder, View view, boolean flag, int i, int j)
    {
        mDropDownGravity = 0x800003;
        mInternalOnDismissListener = new _cls1();
        mContext = context;
        mMenu = menubuilder;
        mAnchorView = view;
        mOverflowOnly = flag;
        mPopupStyleAttr = i;
        mPopupStyleRes = j;
    }

    public final void dismiss()
    {
        boolean flag;
        if (mPopup != null && mPopup.isShowing())
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

    public final MenuPopup getPopup()
    {
        if (mPopup == null)
        {
            Object obj = ((WindowManager)mContext.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            ((Display) (obj)).getRealSize(point);
            boolean flag;
            if (Math.min(point.x, point.y) >= mContext.getResources().getDimensionPixelSize(0x7f0e0016))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                obj = new CascadingMenuPopup(mContext, mAnchorView, mPopupStyleAttr, mPopupStyleRes, mOverflowOnly);
            } else
            {
                obj = new StandardMenuPopup(mContext, mMenu, mAnchorView, mPopupStyleAttr, mPopupStyleRes, mOverflowOnly);
            }
            ((MenuPopup) (obj)).addMenu(mMenu);
            ((MenuPopup) (obj)).setOnDismissListener(mInternalOnDismissListener);
            ((MenuPopup) (obj)).setAnchorView(mAnchorView);
            ((MenuPopup) (obj)).setCallback(mPresenterCallback);
            ((MenuPopup) (obj)).setForceShowIcon(mForceShowIcon);
            ((MenuPopup) (obj)).setGravity(mDropDownGravity);
            mPopup = ((MenuPopup) (obj));
        }
        return mPopup;
    }

    public void onDismiss()
    {
        mPopup = null;
        if (mOnDismissListener != null)
        {
            mOnDismissListener.onDismiss();
        }
    }

    public final void showPopup(int i, int j, boolean flag, boolean flag1)
    {
        MenuPopup menupopup = getPopup();
        menupopup.setShowTitle(flag1);
        if (flag)
        {
            int k = i;
            if ((Gravity.getAbsoluteGravity(mDropDownGravity, ViewCompat.getLayoutDirection(mAnchorView)) & 7) == 5)
            {
                k = i - mAnchorView.getWidth();
            }
            menupopup.setHorizontalOffset(k);
            menupopup.setVerticalOffset(j);
            i = (int)((mContext.getResources().getDisplayMetrics().density * 48F) / 2.0F);
            menupopup.mEpicenterBounds = new Rect(k - i, j - i, k + i, i + j);
        }
        menupopup.show();
    }

    public final boolean tryShow()
    {
        boolean flag;
        if (mPopup != null && mPopup.isShowing())
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
        if (mAnchorView == null)
        {
            return false;
        } else
        {
            showPopup(0, 0, false, false);
            return true;
        }
    }

    private class _cls1
        implements android.widget.PopupWindow.OnDismissListener
    {

        private final MenuPopupHelper this$0;

        public final void onDismiss()
        {
            MenuPopupHelper.this.onDismiss();
        }

        _cls1()
        {
            this$0 = MenuPopupHelper.this;
            super();
        }
    }

}
