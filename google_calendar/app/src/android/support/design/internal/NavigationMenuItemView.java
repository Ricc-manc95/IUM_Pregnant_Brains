// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.TooltipCompatHandler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.TextView;

// Referenced classes of package android.support.design.internal:
//            ForegroundLinearLayout

public class NavigationMenuItemView extends ForegroundLinearLayout
    implements android.support.v7.view.menu.MenuView.ItemView
{

    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    private final AccessibilityDelegateCompat accessibilityDelegate;
    private FrameLayout actionArea;
    public boolean checkable;
    private final int iconSize;
    private MenuItemImpl itemData;
    private final CheckedTextView textView;

    public NavigationMenuItemView(Context context)
    {
        this(context, null);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        accessibilityDelegate = new _cls1();
        setOrientation(0);
        LayoutInflater.from(context).inflate(0x7f05004d, this, true);
        iconSize = context.getResources().getDimensionPixelSize(0x7f0e00fc);
        textView = (CheckedTextView)findViewById(0x7f100167);
        textView.setDuplicateParentStateEnabled(true);
        ViewCompat.setAccessibilityDelegate(textView, accessibilityDelegate);
    }

    public final MenuItemImpl getItemData()
    {
        return itemData;
    }

    public final void initialize(MenuItemImpl menuitemimpl, int i)
    {
        itemData = menuitemimpl;
        if (menuitemimpl.isVisible())
        {
            i = 0;
        } else
        {
            i = 8;
        }
        setVisibility(i);
        if (getBackground() == null)
        {
            TypedValue typedvalue = new TypedValue();
            Object obj;
            boolean flag;
            if (getContext().getTheme().resolveAttribute(0x7f0100c6, typedvalue, true))
            {
                obj = new StateListDrawable();
                ((StateListDrawable) (obj)).addState(CHECKED_STATE_SET, new ColorDrawable(typedvalue.data));
                ((StateListDrawable) (obj)).addState(EMPTY_STATE_SET, new ColorDrawable(0));
            } else
            {
                obj = null;
            }
            ViewCompat.setBackground(this, ((Drawable) (obj)));
        }
        flag = menuitemimpl.isCheckable();
        refreshDrawableState();
        if (checkable != flag)
        {
            checkable = flag;
            obj = textView;
            AccessibilityDelegateCompat.DEFAULT_DELEGATE.sendAccessibilityEvent(((View) (obj)), 2048);
        }
        flag = menuitemimpl.isChecked();
        refreshDrawableState();
        textView.setChecked(flag);
        setEnabled(menuitemimpl.isEnabled());
        obj = menuitemimpl.getTitle();
        textView.setText(((CharSequence) (obj)));
        obj = menuitemimpl.getIcon();
        if (obj != null)
        {
            ((Drawable) (obj)).setBounds(0, 0, iconSize, iconSize);
        }
        textView.setCompoundDrawablesRelative(((Drawable) (obj)), null, null, null);
        obj = menuitemimpl.getActionView();
        if (obj != null)
        {
            if (actionArea == null)
            {
                actionArea = (FrameLayout)((ViewStub)findViewById(0x7f100168)).inflate();
            }
            actionArea.removeAllViews();
            actionArea.addView(((View) (obj)));
        }
        setContentDescription(menuitemimpl.getContentDescription());
        menuitemimpl = menuitemimpl.getTooltipText();
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            setTooltipText(menuitemimpl);
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
            if (TextUtils.isEmpty(menuitemimpl))
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
                new TooltipCompatHandler(this, menuitemimpl);
            }
        }
        if (itemData.getTitle() == null && itemData.getIcon() == null && itemData.getActionView() != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            textView.setVisibility(8);
            if (actionArea != null)
            {
                menuitemimpl = (android.support.v7.widget.LinearLayoutCompat.LayoutParams)actionArea.getLayoutParams();
                menuitemimpl.width = -1;
                actionArea.setLayoutParams(menuitemimpl);
            }
        } else
        {
            textView.setVisibility(0);
            if (actionArea != null)
            {
                menuitemimpl = (android.support.v7.widget.LinearLayoutCompat.LayoutParams)actionArea.getLayoutParams();
                menuitemimpl.width = -2;
                actionArea.setLayoutParams(menuitemimpl);
                return;
            }
        }
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[] = super.onCreateDrawableState(i + 1);
        if (itemData != null && itemData.isCheckable() && itemData.isChecked())
        {
            mergeDrawableStates(ai, CHECKED_STATE_SET);
        }
        return ai;
    }

    public final boolean prefersCondensedTitle()
    {
        return false;
    }

    public void setHorizontalPadding(int i)
    {
        setPadding(i, 0, i, 0);
    }

    public void setIconPadding(int i)
    {
        textView.setCompoundDrawablePadding(i);
    }

    public void setTextAppearance(int i)
    {
        CheckedTextView checkedtextview = textView;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            checkedtextview.setTextAppearance(i);
            return;
        } else
        {
            checkedtextview.setTextAppearance(checkedtextview.getContext(), i);
            return;
        }
    }


    private class _cls1 extends AccessibilityDelegateCompat
    {

        private final NavigationMenuItemView this$0;

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            boolean flag = checkable;
            accessibilitynodeinfocompat.mInfo.setCheckable(flag);
        }

        _cls1()
        {
            this$0 = NavigationMenuItemView.this;
            super();
        }
    }

}
