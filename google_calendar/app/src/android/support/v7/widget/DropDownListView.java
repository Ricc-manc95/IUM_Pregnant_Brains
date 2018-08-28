// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;

class DropDownListView extends ListView
{

    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private Field mIsChildViewEnabled;
    public boolean mListSelectionHidden;
    private int mMotionPosition;
    public ResolveHoverRunnable mResolveHoverRunnable;
    private ListViewAutoScrollHelper mScrollHelper;
    private int mSelectionBottomPadding;
    private int mSelectionLeftPadding;
    private int mSelectionRightPadding;
    private int mSelectionTopPadding;
    private GateKeeperDrawable mSelector;
    private final Rect mSelectorRect = new Rect();

    DropDownListView(Context context, boolean flag)
    {
        super(context, null, 0x7f0100b8);
        mSelectionLeftPadding = 0;
        mSelectionTopPadding = 0;
        mSelectionRightPadding = 0;
        mSelectionBottomPadding = 0;
        mHijackFocus = flag;
        setCacheColorHint(0);
        try
        {
            mIsChildViewEnabled = android/widget/AbsListView.getDeclaredField("mIsChildViewEnabled");
            mIsChildViewEnabled.setAccessible(true);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            ThrowableExtension.STRATEGY.printStackTrace(context);
        }
    }

    private final void positionSelectorCompat(int i, View view)
    {
        Rect rect = mSelectorRect;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left = rect.left - mSelectionLeftPadding;
        rect.top = rect.top - mSelectionTopPadding;
        rect.right = rect.right + mSelectionRightPadding;
        rect.bottom = rect.bottom + mSelectionBottomPadding;
        boolean flag;
        flag = mIsChildViewEnabled.getBoolean(this);
        if (view.isEnabled() == flag)
        {
            break MISSING_BLOCK_LABEL_128;
        }
        view = mIsChildViewEnabled;
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        try
        {
            view.set(this, Boolean.valueOf(flag));
        }
        // Misplaced declaration of an exception variable
        catch (View view)
        {
            ThrowableExtension.STRATEGY.printStackTrace(view);
            return;
        }
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_128;
        }
        refreshDrawableState();
    }

    private final void updateSelectorStateCompat()
    {
        Drawable drawable = getSelector();
        if (drawable != null && mDrawsInPressedState && isPressed())
        {
            drawable.setState(getDrawableState());
        }
    }

    protected void dispatchDraw(Canvas canvas)
    {
        if (!mSelectorRect.isEmpty())
        {
            Drawable drawable = getSelector();
            if (drawable != null)
            {
                drawable.setBounds(mSelectorRect);
                drawable.draw(canvas);
            }
        }
        super.dispatchDraw(canvas);
    }

    protected void drawableStateChanged()
    {
        if (mResolveHoverRunnable != null)
        {
            return;
        }
        super.drawableStateChanged();
        if (mSelector != null)
        {
            mSelector.mEnabled = true;
        }
        updateSelectorStateCompat();
    }

    public boolean hasFocus()
    {
        return mHijackFocus || super.hasFocus();
    }

    public boolean hasWindowFocus()
    {
        return mHijackFocus || super.hasWindowFocus();
    }

    public boolean isFocused()
    {
        return mHijackFocus || super.isFocused();
    }

    public boolean isInTouchMode()
    {
        return mHijackFocus && mListSelectionHidden || super.isInTouchMode();
    }

    public int measureHeightOfChildrenCompat(int i, int j, int k, int l, int i1)
    {
        Object obj;
        ListAdapter listadapter;
        int j1;
        j = getListPaddingTop();
        k = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        j1 = getDividerHeight();
        obj = getDivider();
        listadapter = getAdapter();
        if (listadapter != null) goto _L2; else goto _L1
_L1:
        j += k;
_L4:
        return j;
_L2:
        k += j;
        int l1;
        int j2;
        if (j1 <= 0 || obj == null)
        {
            j1 = 0;
        }
        j = 0;
        obj = null;
        l1 = 0;
        j2 = listadapter.getCount();
        for (int k1 = 0; k1 < j2;)
        {
            int i2 = listadapter.getItemViewType(k1);
            if (i2 != l1)
            {
                obj = null;
                l1 = i2;
            }
            View view = listadapter.getView(k1, ((View) (obj)), this);
            android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
            obj = layoutparams;
            if (layoutparams == null)
            {
                obj = generateDefaultLayoutParams();
                view.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            }
            if (((android.view.ViewGroup.LayoutParams) (obj)).height > 0)
            {
                i2 = android.view.View.MeasureSpec.makeMeasureSpec(((android.view.ViewGroup.LayoutParams) (obj)).height, 0x40000000);
            } else
            {
                i2 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, i2);
            view.forceLayout();
            if (k1 > 0)
            {
                k += j1;
            }
            k = view.getMeasuredHeight() + k;
            if (k >= l)
            {
                if (i1 < 0 || k1 <= i1 || j <= 0 || k == l)
                {
                    return l;
                }
                continue; /* Loop/switch isn't completed */
            }
            if (i1 >= 0 && k1 >= i1)
            {
                j = k;
            }
            k1++;
            obj = view;
        }

        return k;
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void onDetachedFromWindow()
    {
        mResolveHoverRunnable = null;
        super.onDetachedFromWindow();
    }

    public boolean onForwardedEvent(MotionEvent motionevent, int i)
    {
        int j = motionevent.getActionMasked();
        j;
        JVM INSTR tableswitch 1 3: default 36
    //                   1 144
    //                   2 533
    //                   3 136;
           goto _L1 _L2 _L3 _L4
_L3:
        break MISSING_BLOCK_LABEL_533;
_L1:
        boolean flag;
        i = 0;
        flag = true;
_L7:
        if (!flag || i != 0)
        {
            mDrawsInPressedState = false;
            setPressed(false);
            drawableStateChanged();
            View view = getChildAt(mMotionPosition - getFirstVisiblePosition());
            if (view != null)
            {
                view.setPressed(false);
            }
        }
        if (!flag) goto _L6; else goto _L5
_L5:
        if (mScrollHelper == null)
        {
            mScrollHelper = new ListViewAutoScrollHelper(this);
        }
        mScrollHelper.setEnabled(true);
        mScrollHelper.onTouch(this, motionevent);
_L9:
        return flag;
_L4:
        i = 0;
        flag = false;
          goto _L7
_L2:
        flag = false;
_L10:
        int k;
        int l;
        k = motionevent.findPointerIndex(i);
        if (k < 0)
        {
            i = 0;
            flag = false;
        } else
        {
label0:
            {
                i = (int)motionevent.getX(k);
                l = (int)motionevent.getY(k);
                k = pointToPosition(i, l);
                if (k != -1)
                {
                    break label0;
                }
                i = 1;
            }
        }
          goto _L7
        View view1 = getChildAt(k - getFirstVisiblePosition());
        float f = i;
        float f1 = l;
        mDrawsInPressedState = true;
        drawableHotspotChanged(f, f1);
        if (!isPressed())
        {
            setPressed(true);
        }
        layoutChildren();
        if (mMotionPosition != -1)
        {
            View view2 = getChildAt(mMotionPosition - getFirstVisiblePosition());
            if (view2 != null && view2 != view1 && view2.isPressed())
            {
                view2.setPressed(false);
            }
        }
        mMotionPosition = k;
        view1.drawableHotspotChanged(f - (float)view1.getLeft(), f1 - (float)view1.getTop());
        if (!view1.isPressed())
        {
            view1.setPressed(true);
        }
        Drawable drawable = getSelector();
        if (drawable != null && k != -1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            drawable.setVisible(false, false);
        }
        positionSelectorCompat(k, view1);
        if (i != 0)
        {
            Rect rect = mSelectorRect;
            float f2 = rect.exactCenterX();
            float f3 = rect.exactCenterY();
            if (getVisibility() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            drawable.setVisible(flag, false);
            drawable.setHotspot(f2, f3);
        }
        drawable = getSelector();
        if (drawable != null && k != -1)
        {
            drawable.setHotspot(f, f1);
        }
        if (mSelector != null)
        {
            mSelector.mEnabled = false;
        }
        refreshDrawableState();
        if (j == 1)
        {
            performItemClick(view1, k, getItemIdAtPosition(k));
        }
          goto _L1
_L6:
        if (mScrollHelper == null) goto _L9; else goto _L8
_L8:
        mScrollHelper.setEnabled(false);
        return flag;
        flag = true;
          goto _L10
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT < 26)
        {
            flag = super.onHoverEvent(motionevent);
        } else
        {
            int i = motionevent.getActionMasked();
            if (i == 10 && mResolveHoverRunnable == null)
            {
                mResolveHoverRunnable = new ResolveHoverRunnable();
                ResolveHoverRunnable resolvehoverrunnable = mResolveHoverRunnable;
                resolvehoverrunnable._fld0.post(resolvehoverrunnable);
            }
            boolean flag1 = super.onHoverEvent(motionevent);
            if (i == 9 || i == 7)
            {
                int j = pointToPosition((int)motionevent.getX(), (int)motionevent.getY());
                flag = flag1;
                if (j != -1)
                {
                    flag = flag1;
                    if (j != getSelectedItemPosition())
                    {
                        motionevent = getChildAt(j - getFirstVisiblePosition());
                        if (motionevent.isEnabled())
                        {
                            setSelectionFromTop(j, motionevent.getTop() - getTop());
                        }
                        updateSelectorStateCompat();
                        return flag1;
                    }
                }
            } else
            {
                setSelection(-1);
                return flag1;
            }
        }
        return flag;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 0 0: default 24
    //                   0 59;
           goto _L1 _L2
_L1:
        if (mResolveHoverRunnable != null)
        {
            ResolveHoverRunnable resolvehoverrunnable = mResolveHoverRunnable;
            resolvehoverrunnable._fld0.mResolveHoverRunnable = null;
            resolvehoverrunnable._fld0.removeCallbacks(resolvehoverrunnable);
        }
        return super.onTouchEvent(motionevent);
_L2:
        mMotionPosition = pointToPosition((int)motionevent.getX(), (int)motionevent.getY());
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void setSelector(Drawable drawable)
    {
        Object obj;
        if (drawable != null)
        {
            obj = new GateKeeperDrawable(drawable);
        } else
        {
            obj = null;
        }
        mSelector = ((GateKeeperDrawable) (obj));
        super.setSelector(mSelector);
        obj = new Rect();
        if (drawable != null)
        {
            drawable.getPadding(((Rect) (obj)));
        }
        mSelectionLeftPadding = ((Rect) (obj)).left;
        mSelectionTopPadding = ((Rect) (obj)).top;
        mSelectionRightPadding = ((Rect) (obj)).right;
        mSelectionBottomPadding = ((Rect) (obj)).bottom;
    }

    private class GateKeeperDrawable extends DrawableWrapper
    {

        public boolean mEnabled;

        public final void draw(Canvas canvas)
        {
            if (mEnabled)
            {
                super.draw(canvas);
            }
        }

        public final void setHotspot(float f, float f1)
        {
            if (mEnabled)
            {
                super.setHotspot(f, f1);
            }
        }

        public final void setHotspotBounds(int i, int j, int k, int l)
        {
            if (mEnabled)
            {
                super.setHotspotBounds(i, j, k, l);
            }
        }

        public final boolean setState(int ai[])
        {
            if (mEnabled)
            {
                return super.setState(ai);
            } else
            {
                return false;
            }
        }

        public final boolean setVisible(boolean flag, boolean flag1)
        {
            if (mEnabled)
            {
                return super.setVisible(flag, flag1);
            } else
            {
                return false;
            }
        }

        GateKeeperDrawable(Drawable drawable)
        {
            super(drawable);
            mEnabled = true;
        }
    }


    private class ResolveHoverRunnable
        implements Runnable
    {

        public final DropDownListView this$0;

        public final void run()
        {
            mResolveHoverRunnable = null;
            drawableStateChanged();
        }

        ResolveHoverRunnable()
        {
            this$0 = DropDownListView.this;
            super();
        }
    }

}
