// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.util.ArraySet;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Iterator;
import java.util.Set;

public class ButtonPaneLayout extends ViewGroup
{
    final class DelegatingTouchDelegate extends TouchDelegate
    {

        public TouchDelegate parentDelegate;
        private final ButtonPaneLayout this$0;
        public final Set touchDelegates = new ArraySet();

        public final boolean onTouchEvent(MotionEvent motionevent)
        {
label0:
            {
                if (parentDelegate != null && parentDelegate.onTouchEvent(motionevent))
                {
                    return true;
                }
                motionevent.offsetLocation(-getLeft(), -getTop());
                if (touchDelegates.isEmpty())
                {
                    break label0;
                }
                Iterator iterator = touchDelegates.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                } while (!((TouchDelegate)iterator.next()).onTouchEvent(motionevent));
                return true;
            }
            return false;
        }

        DelegatingTouchDelegate()
        {
            this$0 = ButtonPaneLayout.this;
            super(new Rect(), ButtonPaneLayout.this);
        }
    }


    private final int buttonHeightPx;
    private SparseIntArray buttonWidths;
    private boolean customTextAlignment;
    private final boolean fillWidthWhenVertical;
    private boolean forceVerticalLayout;
    private final int horizontalBottomPaddingPx;
    private final int horizontalEndPaddingPx;
    private int horizontalSpacing;
    private final int horizontalStartPaddingPx;
    private final int horizontalTopPaddingPx;
    private final int minButtonWidthPx;
    private final int minTouchDimenPx;
    private boolean paddingNeverUpdated;
    private boolean stackVertically;
    private DelegatingTouchDelegate touchDelegate;
    private final int verticalBottomPaddingPx;
    private final int verticalEndPaddingPx;
    private int verticalSpacing;
    private final int verticalStartPaddingPx;
    private final int verticalTopPaddingPx;

    public ButtonPaneLayout(Context context)
    {
        this(context, null);
    }

    public ButtonPaneLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ButtonPaneLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        paddingNeverUpdated = true;
        buttonWidths = new SparseIntArray();
        context = context.getTheme().obtainStyledAttributes(attributeset, R.styleable.ButtonPaneLayout, i, 0x7f14016e);
        fillWidthWhenVertical = context.getBoolean(R.styleable.ButtonPaneLayout_fillWidthWhenVertical, true);
        customTextAlignment = context.getBoolean(R.styleable.ButtonPaneLayout_customTextAlignment, false);
        forceVerticalLayout = context.getBoolean(R.styleable.ButtonPaneLayout_forceVerticalLayout, false);
        minButtonWidthPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_minChildWidth, 0);
        buttonHeightPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_buttonHeight, 0);
        horizontalSpacing = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_horizontalSpacing, 0);
        i = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_horizontalPadding, 0x80000000);
        if (i == 0x80000000)
        {
            horizontalStartPaddingPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_horizontalPaddingStart, 0);
            horizontalTopPaddingPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_horizontalPaddingTop, 0);
            horizontalEndPaddingPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_horizontalPaddingEnd, 0);
            horizontalBottomPaddingPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_horizontalPaddingBottom, 0);
        } else
        {
            horizontalStartPaddingPx = i;
            horizontalTopPaddingPx = i;
            horizontalEndPaddingPx = i;
            horizontalBottomPaddingPx = i;
        }
        verticalSpacing = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_verticalSpacing, 0);
        i = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_verticalPadding, 0x80000000);
        if (i == 0x80000000)
        {
            verticalStartPaddingPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_verticalPaddingStart, 0);
            verticalTopPaddingPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_verticalPaddingTop, 0);
            verticalEndPaddingPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_verticalPaddingEnd, 0);
            verticalBottomPaddingPx = context.getDimensionPixelSize(R.styleable.ButtonPaneLayout_verticalPaddingBottom, 0);
        } else
        {
            verticalStartPaddingPx = i;
            verticalTopPaddingPx = i;
            verticalEndPaddingPx = i;
            verticalBottomPaddingPx = i;
        }
        context.recycle();
        minTouchDimenPx = getResources().getDimensionPixelSize(0x7f0e02ce);
    }

    private final void addTouchDelegate(View view, Rect rect)
    {
        if (touchDelegate == null)
        {
            touchDelegate = new DelegatingTouchDelegate();
        }
        DelegatingTouchDelegate delegatingtouchdelegate = touchDelegate;
        view = new TouchDelegate(rect, view);
        delegatingtouchdelegate.touchDelegates.add(view);
        if (getParent() instanceof View)
        {
            view = (View)getParent();
            rect = view.getTouchDelegate();
            if (rect != touchDelegate)
            {
                touchDelegate.parentDelegate = rect;
                view.setTouchDelegate(touchDelegate);
            }
        }
    }

    private static int adjustDimenForMode(int i, int j, int k)
    {
        int l = j;
        switch (android.view.View.MeasureSpec.getMode(k))
        {
        default:
            l = i;
            // fall through

        case 1073741824: 
            return l;

        case -2147483648: 
            return Math.min(j, i);
        }
    }

    private final Rect getTouchRect(int i, int j, int k, int l)
    {
        int j1 = l - j;
        int k1 = k - i;
        if (j1 > minTouchDimenPx && k1 > minTouchDimenPx)
        {
            return null;
        }
        int i1 = i;
        if (k1 < minTouchDimenPx)
        {
            i1 = i - (minTouchDimenPx - k1) / 2;
            k = i1 + minTouchDimenPx;
        }
        i = j;
        if (j1 < minTouchDimenPx)
        {
            i = j - (minTouchDimenPx - j1) / 2;
            l = i + minTouchDimenPx;
        }
        return new Rect(i1, i, k, l);
    }

    private final void updatePadding()
    {
        boolean flag = false;
        paddingNeverUpdated = false;
        if (stackVertically || forceVerticalLayout)
        {
            flag = true;
        }
        if (flag)
        {
            ViewCompat.setPaddingRelative(this, verticalStartPaddingPx, verticalTopPaddingPx, verticalEndPaddingPx, verticalBottomPaddingPx);
            return;
        } else
        {
            ViewCompat.setPaddingRelative(this, horizontalStartPaddingPx, horizontalTopPaddingPx, horizontalEndPaddingPx, horizontalBottomPaddingPx);
            return;
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        if (stackVertically || forceVerticalLayout)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 != 0)
        {
            int i2 = k - i;
            if (ViewCompat.getLayoutDirection(this) == 1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0 || fillWidthWhenVertical)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            i = getPaddingLeft();
            l = l - j - getPaddingBottom();
            if (touchDelegate != null)
            {
                touchDelegate.touchDelegates.clear();
            }
            j = 0;
            while (j < getChildCount()) 
            {
                View view = getChildAt(j);
                int k1 = l;
                i1 = i;
                if (view.getVisibility() != 8)
                {
                    if (k == 0)
                    {
                        i = i2 - getPaddingRight() - view.getMeasuredWidth();
                    }
                    Rect rect;
                    if (fillWidthWhenVertical)
                    {
                        i1 = i2 - getPaddingLeft() - getPaddingRight();
                    } else
                    {
                        i1 = view.getMeasuredWidth();
                    }
                    k1 = l - view.getMeasuredHeight();
                    i1 += i;
                    view.layout(i, k1, i1, l);
                    rect = getTouchRect(i, k1, i1, l);
                    if (rect != null)
                    {
                        if (rect.bottom - rect.top > (l - k1) + verticalSpacing)
                        {
                            i1 = verticalSpacing / 2;
                            rect.top = k1 - i1;
                            rect.bottom = l + i1;
                        }
                        addTouchDelegate(view, rect);
                    }
                    k1 -= verticalSpacing;
                    i1 = i;
                }
                j++;
                l = k1;
                i = i1;
            }
        } else
        {
            int j2;
            if (ViewCompat.getLayoutDirection(this) == 1)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            if (l != 0)
            {
                i = getPaddingLeft();
            } else
            {
                i = k - i - getPaddingRight();
            }
            j2 = getPaddingTop();
            if (touchDelegate != null)
            {
                touchDelegate.touchDelegates.clear();
            }
            k = getChildCount() - 1;
            while (k >= 0) 
            {
                View view1 = getChildAt(k);
                if (view1.getVisibility() != 8)
                {
                    int l1 = Math.max(minButtonWidthPx, view1.getMeasuredWidth());
                    Rect rect1;
                    int j1;
                    if (l != 0)
                    {
                        j1 = i + l1;
                        j = l1 + horizontalSpacing + i;
                        l1 = i;
                        i = j;
                    } else
                    {
                        j = i - (l1 + horizontalSpacing);
                        l1 = i - l1;
                        j1 = i;
                        i = j;
                    }
                    j = view1.getMeasuredHeight() + j2;
                    view1.layout(l1, j2, j1, j);
                    rect1 = getTouchRect(l1, j2, j1, j);
                    j = i;
                    if (rect1 != null)
                    {
                        if (rect1.right - rect1.left > (j1 - l1) + horizontalSpacing)
                        {
                            j = horizontalSpacing / 2;
                            rect1.left = l1 - j;
                            rect1.right = j1 + j;
                        }
                        addTouchDelegate(view1, rect1);
                        j = i;
                    }
                } else
                {
                    j = i;
                }
                k--;
                i = j;
            }
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int i1;
        int j2;
        int k2;
        int k3;
        int l3;
        super.onMeasure(i, j);
        l3 = android.view.View.MeasureSpec.makeMeasureSpec(android.view.View.MeasureSpec.getSize(i), 0);
        int i4 = android.view.View.MeasureSpec.makeMeasureSpec(buttonHeightPx, 0x40000000);
        k = 0;
        for (int l = 0; l < getChildCount();)
        {
            int j1 = k;
            if (getChildAt(l).getVisibility() != 8)
            {
                j1 = k + 1;
            }
            l++;
            k = j1;
        }

        buttonWidths.clear();
        int k4 = getMeasuredWidth() - horizontalStartPaddingPx - horizontalEndPaddingPx;
        boolean flag;
        boolean flag1;
        if (k > 0)
        {
            k = (k4 - (k - 1) * horizontalSpacing) / k;
        } else
        {
            k = 0;
        }
        i1 = 0;
        if (stackVertically || forceVerticalLayout)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (k < minButtonWidthPx)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        stackVertically = flag1;
        k2 = 0;
        j2 = 0;
        for (k = i1; j2 < getChildCount(); k = i1)
        {
            View view = getChildAt(j2);
            int l2 = k2;
            i1 = k;
            if (view.getVisibility() != 8)
            {
                view.measure(l3, i4);
                i1 = k;
                if (k < view.getMeasuredWidth())
                {
                    i1 = view.getMeasuredWidth();
                }
                buttonWidths.append(j2, view.getMeasuredWidth());
                l2 = k2 + (view.getMeasuredWidth() + horizontalSpacing);
            }
            j2++;
            k2 = l2;
        }

        if (k4 < k2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        stackVertically = flag1;
        if (stackVertically || forceVerticalLayout)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (flag != i1 || paddingNeverUpdated)
        {
            updatePadding();
        }
        if (stackVertically || forceVerticalLayout)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 == 0)
        {
            k = getPaddingLeft();
            l3 = getPaddingRight();
            int j4 = horizontalSpacing;
            int k1 = getPaddingTop();
            j2 = getPaddingBottom();
            k2 = buttonHeightPx;
            int i3 = android.view.View.MeasureSpec.makeMeasureSpec(buttonHeightPx, 0x40000000);
            i1 = 0;
            k = (k + l3) - j4;
            for (; i1 < getChildCount(); i1++)
            {
                View view1 = getChildAt(i1);
                if (view1.getVisibility() == 8)
                {
                    continue;
                }
                l3 = android.view.View.MeasureSpec.makeMeasureSpec(buttonWidths.get(i1), 0x80000000);
                if (!customTextAlignment && (view1 instanceof TextView))
                {
                    ((TextView)view1).setGravity(17);
                }
                view1.measure(l3, i3);
                k += view1.getMeasuredWidth() + horizontalSpacing;
            }

            setMeasuredDimension(adjustDimenForMode(k, getMeasuredWidth(), i), adjustDimenForMode(k1 + j2 + k2, getMeasuredHeight(), j));
            return;
        }
        if (fillWidthWhenVertical)
        {
            j2 = android.view.View.MeasureSpec.makeMeasureSpec(adjustDimenForMode(k, getMeasuredWidth(), i), 0x40000000);
            k2 = android.view.View.MeasureSpec.makeMeasureSpec(buttonHeightPx, 0x40000000);
            i1 = getPaddingTop();
            int j3 = getPaddingBottom();
            l3 = verticalSpacing;
            int l1 = 0;
            i1 = (i1 + j3) - l3;
            for (; l1 < getChildCount(); l1++)
            {
                View view2 = getChildAt(l1);
                if (view2.getVisibility() == 8)
                {
                    continue;
                }
                if (!customTextAlignment && (view2 instanceof TextView))
                {
                    ((TextView)view2).setGravity(0x800015);
                }
                view2.measure(j2, k2);
                i1 += view2.getMeasuredHeight() + verticalSpacing;
            }

            setMeasuredDimension(adjustDimenForMode(getPaddingLeft() + k + getPaddingRight(), getMeasuredWidth(), i), adjustDimenForMode(i1, getMeasuredHeight(), j));
            return;
        }
        k2 = getPaddingLeft() + getPaddingRight();
        k3 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - k2, 0x80000000);
        l3 = android.view.View.MeasureSpec.makeMeasureSpec(buttonHeightPx, 0x40000000);
        i1 = (getPaddingTop() + getPaddingBottom()) - verticalSpacing;
        k = 0;
        j2 = 0;
_L2:
        int i2;
        if (j2 >= getChildCount())
        {
            break; /* Loop/switch isn't completed */
        }
        View view3 = getChildAt(j2);
        i2 = i1;
        if (view3.getVisibility() == 8)
        {
            break MISSING_BLOCK_LABEL_966;
        }
        if (!customTextAlignment && (view3 instanceof TextView))
        {
            ((TextView)view3).setGravity(0x800015);
        }
        view3.measure(k3, l3);
        i1 += view3.getMeasuredHeight() + verticalSpacing;
        i2 = i1;
        if (view3.getMeasuredWidth() <= k)
        {
            break MISSING_BLOCK_LABEL_966;
        }
        i2 = view3.getMeasuredWidth();
        k = i1;
        i1 = i2;
_L3:
        j2++;
        i2 = i1;
        i1 = k;
        k = i2;
        if (true) goto _L2; else goto _L1
_L1:
        setMeasuredDimension(adjustDimenForMode(k + k2, getMeasuredWidth(), i), adjustDimenForMode(i1, getMeasuredHeight(), j));
        return;
        i1 = k;
        k = i2;
          goto _L3
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        updatePadding();
    }

    public void setHorizontalSpacing(int i)
    {
        horizontalSpacing = i;
        invalidate();
    }

    public void setVerticalSpacing(int i)
    {
        verticalSpacing = i;
        invalidate();
    }
}
