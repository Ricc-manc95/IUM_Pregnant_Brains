// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ForwardingListener;
import android.support.v7.widget.TooltipCompatHandler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

// Referenced classes of package android.support.v7.view.menu:
//            MenuItemImpl, ShowableListMenu

public class ActionMenuItemView extends AppCompatTextView
    implements MenuView.ItemView, android.support.v7.widget.ActionMenuView.ActionMenuChildView, android.view.View.OnClickListener
{
    final class ActionMenuItemForwardingListener extends ForwardingListener
    {

        private final ActionMenuItemView this$0;

        public final ShowableListMenu getPopup()
        {
            if (mPopupCallback != null)
            {
                return mPopupCallback.getPopup();
            } else
            {
                return null;
            }
        }

        protected final boolean onForwardingStarted()
        {
            boolean flag1 = false;
            boolean flag = flag1;
            if (mItemInvoker != null)
            {
                flag = flag1;
                if (mItemInvoker.invokeItem(mItemData))
                {
                    ShowableListMenu showablelistmenu = getPopup();
                    flag = flag1;
                    if (showablelistmenu != null)
                    {
                        flag = flag1;
                        if (showablelistmenu.isShowing())
                        {
                            flag = true;
                        }
                    }
                }
            }
            return flag;
        }

        public ActionMenuItemForwardingListener()
        {
            this$0 = ActionMenuItemView.this;
            super(ActionMenuItemView.this);
        }
    }

    public static abstract class PopupCallback
    {

        public abstract ShowableListMenu getPopup();

        public PopupCallback()
        {
        }
    }


    private boolean mAllowTextWithIcon;
    private ForwardingListener mForwardingListener;
    private Drawable mIcon;
    public MenuItemImpl mItemData;
    public MenuBuilder.ItemInvoker mItemInvoker;
    private int mMaxIconSize;
    private int mMinWidth;
    public PopupCallback mPopupCallback;
    private int mSavedPaddingLeft;
    private CharSequence mTitle;

    public ActionMenuItemView(Context context)
    {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        Resources resources = context.getResources();
        mAllowTextWithIcon = shouldAllowTextWithIcon();
        context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ActionMenuItemView, i, 0);
        mMinWidth = context.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.ActionMenuItemView_android_minWidth, 0);
        context.recycle();
        mMaxIconSize = (int)(resources.getDisplayMetrics().density * 32F + 0.5F);
        setOnClickListener(this);
        mSavedPaddingLeft = -1;
        setSaveEnabled(false);
    }

    private final boolean shouldAllowTextWithIcon()
    {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int j = configuration.screenHeightDp;
        return i >= 480 || i >= 640 && j >= 480 || configuration.orientation == 2;
    }

    private final void updateTextButtonVisibility()
    {
        boolean flag2 = true;
        CharSequence charsequence;
        boolean flag;
        boolean flag1;
        if (!TextUtils.isEmpty(mTitle))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag1 = flag2;
        if (mIcon != null)
        {
            if ((mItemData.mShowAsAction & 4) == 4 && mAllowTextWithIcon)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
        }
        flag = flag1 & flag;
        if (flag)
        {
            charsequence = mTitle;
        } else
        {
            charsequence = null;
        }
        setText(charsequence);
        charsequence = mItemData.getContentDescription();
        if (TextUtils.isEmpty(charsequence))
        {
            if (flag)
            {
                charsequence = null;
            } else
            {
                charsequence = mItemData.getTitle();
            }
            setContentDescription(charsequence);
        } else
        {
            setContentDescription(charsequence);
        }
        charsequence = mItemData.getTooltipText();
        if (TextUtils.isEmpty(charsequence))
        {
            if (flag)
            {
                charsequence = null;
            } else
            {
                charsequence = mItemData.getTitle();
            }
            if (android.os.Build.VERSION.SDK_INT >= 26)
            {
                setTooltipText(charsequence);
                return;
            }
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
            if (TextUtils.isEmpty(charsequence))
            {
                if (TooltipCompatHandler.sActiveHandler != null && TooltipCompatHandler.sActiveHandler.mAnchor == this)
                {
                    TooltipCompatHandler.sActiveHandler.hide();
                }
                setOnLongClickListener(null);
                setLongClickable(false);
                setOnHoverListener(null);
                return;
            } else
            {
                new TooltipCompatHandler(this, charsequence);
                return;
            }
        }
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            setTooltipText(charsequence);
            return;
        }
        if (TooltipCompatHandler.sPendingHandler != null && TooltipCompatHandler.sPendingHandler.mAnchor == this)
        {
            if (TooltipCompatHandler.sPendingHandler != null)
            {
                TooltipCompatHandler tooltipcompathandler2 = TooltipCompatHandler.sPendingHandler;
                tooltipcompathandler2.mAnchor.removeCallbacks(tooltipcompathandler2.mShowRunnable);
            }
            TooltipCompatHandler.sPendingHandler = null;
            if (false)
            {
                TooltipCompatHandler tooltipcompathandler3 = TooltipCompatHandler.sPendingHandler;
                tooltipcompathandler3.mAnchor.postDelayed(tooltipcompathandler3.mShowRunnable, ViewConfiguration.getLongPressTimeout());
            }
        }
        if (TextUtils.isEmpty(charsequence))
        {
            if (TooltipCompatHandler.sActiveHandler != null && TooltipCompatHandler.sActiveHandler.mAnchor == this)
            {
                TooltipCompatHandler.sActiveHandler.hide();
            }
            setOnLongClickListener(null);
            setLongClickable(false);
            setOnHoverListener(null);
            return;
        } else
        {
            new TooltipCompatHandler(this, charsequence);
            return;
        }
    }

    public final MenuItemImpl getItemData()
    {
        return mItemData;
    }

    public final void initialize(MenuItemImpl menuitemimpl, int i)
    {
        mItemData = menuitemimpl;
        Object obj = menuitemimpl.getIcon();
        mIcon = ((Drawable) (obj));
        if (obj != null)
        {
            int l = ((Drawable) (obj)).getIntrinsicWidth();
            int k = ((Drawable) (obj)).getIntrinsicHeight();
            int j = k;
            i = l;
            if (l > mMaxIconSize)
            {
                float f = (float)mMaxIconSize / (float)l;
                i = mMaxIconSize;
                j = (int)((float)k * f);
            }
            l = j;
            k = i;
            if (j > mMaxIconSize)
            {
                float f1 = (float)mMaxIconSize / (float)j;
                l = mMaxIconSize;
                k = (int)((float)i * f1);
            }
            ((Drawable) (obj)).setBounds(0, 0, k, l);
        }
        setCompoundDrawables(((Drawable) (obj)), null, null, null);
        updateTextButtonVisibility();
        if (this != null && prefersCondensedTitle())
        {
            obj = menuitemimpl.getTitleCondensed();
        } else
        {
            obj = menuitemimpl.getTitle();
        }
        mTitle = ((CharSequence) (obj));
        updateTextButtonVisibility();
        setId(menuitemimpl.getItemId());
        if (menuitemimpl.isVisible())
        {
            i = 0;
        } else
        {
            i = 8;
        }
        setVisibility(i);
        setEnabled(menuitemimpl.isEnabled());
        if (menuitemimpl.hasSubMenu() && mForwardingListener == null)
        {
            mForwardingListener = new ActionMenuItemForwardingListener();
        }
    }

    public final boolean needsDividerAfter()
    {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean needsDividerBefore()
    {
        boolean flag;
        if (!TextUtils.isEmpty(getText()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && mItemData.getIcon() == null;
    }

    public void onClick(View view)
    {
        if (mItemInvoker != null)
        {
            mItemInvoker.invokeItem(mItemData);
        }
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mAllowTextWithIcon = shouldAllowTextWithIcon();
        updateTextButtonVisibility();
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag;
        int k;
        int l;
        if (!TextUtils.isEmpty(getText()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && mSavedPaddingLeft >= 0)
        {
            super.setPadding(mSavedPaddingLeft, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, j);
        k = android.view.View.MeasureSpec.getMode(i);
        i = android.view.View.MeasureSpec.getSize(i);
        l = getMeasuredWidth();
        if (k == 0x80000000)
        {
            i = Math.min(i, mMinWidth);
        } else
        {
            i = mMinWidth;
        }
        if (k != 0x40000000 && mMinWidth > 0 && l < i)
        {
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000), j);
        }
        if (!flag && mIcon != null)
        {
            super.setPadding((getMeasuredWidth() - mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        super.onRestoreInstanceState(null);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (mItemData.hasSubMenu() && mForwardingListener != null && mForwardingListener.onTouch(this, motionevent))
        {
            return true;
        } else
        {
            return super.onTouchEvent(motionevent);
        }
    }

    public final boolean prefersCondensedTitle()
    {
        return true;
    }

    public void setPadding(int i, int j, int k, int l)
    {
        mSavedPaddingLeft = i;
        super.setPadding(i, j, k, l);
    }
}
