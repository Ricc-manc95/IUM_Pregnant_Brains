// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray, ViewUtils

public class LinearLayoutCompat extends ViewGroup
{
    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public int gravity;
        public float weight;

        public LayoutParams(int i, int j)
        {
            super(i, j);
            gravity = -1;
            weight = 0.0F;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            gravity = -1;
            context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.LinearLayoutCompat_Layout);
            weight = context.getFloat(android.support.v7.appcompat.R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0F);
            gravity = context.getInt(android.support.v7.appcompat.R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            context.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            gravity = -1;
        }
    }


    public boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    public Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    public int mDividerWidth;
    public int mGravity;
    private int mMaxAscent[];
    private int mMaxDescent[];
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public LinearLayoutCompat(Context context)
    {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeset, int i)
    {
        boolean flag = true;
        super(context, attributeset, i);
        mBaselineAligned = true;
        mBaselineAlignedChildIndex = -1;
        mBaselineChildTop = 0;
        mGravity = 0x800033;
        context = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.LinearLayoutCompat, i, 0));
        i = android.support.v7.appcompat.R.styleable.LinearLayoutCompat_android_orientation;
        i = ((TintTypedArray) (context)).mWrapped.getInt(i, -1);
        if (i >= 0)
        {
            setOrientation(i);
        }
        i = android.support.v7.appcompat.R.styleable.LinearLayoutCompat_android_gravity;
        i = ((TintTypedArray) (context)).mWrapped.getInt(i, -1);
        if (i >= 0)
        {
            setGravity(i);
        }
        i = android.support.v7.appcompat.R.styleable.LinearLayoutCompat_android_baselineAligned;
        boolean flag1 = ((TintTypedArray) (context)).mWrapped.getBoolean(i, true);
        if (!flag1)
        {
            mBaselineAligned = flag1;
        }
        i = android.support.v7.appcompat.R.styleable.LinearLayoutCompat_android_weightSum;
        mWeightSum = ((TintTypedArray) (context)).mWrapped.getFloat(i, -1F);
        i = android.support.v7.appcompat.R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex;
        mBaselineAlignedChildIndex = ((TintTypedArray) (context)).mWrapped.getInt(i, -1);
        i = android.support.v7.appcompat.R.styleable.LinearLayoutCompat_measureWithLargestChild;
        mUseLargestChild = ((TintTypedArray) (context)).mWrapped.getBoolean(i, false);
        attributeset = context.getDrawable(android.support.v7.appcompat.R.styleable.LinearLayoutCompat_divider);
        if (attributeset != mDivider)
        {
            mDivider = attributeset;
            if (attributeset != null)
            {
                mDividerWidth = attributeset.getIntrinsicWidth();
                mDividerHeight = attributeset.getIntrinsicHeight();
            } else
            {
                mDividerWidth = 0;
                mDividerHeight = 0;
            }
            if (attributeset != null)
            {
                flag = false;
            }
            setWillNotDraw(flag);
            requestLayout();
        }
        i = android.support.v7.appcompat.R.styleable.LinearLayoutCompat_showDividers;
        mShowDividers = ((TintTypedArray) (context)).mWrapped.getInt(i, 0);
        i = android.support.v7.appcompat.R.styleable.LinearLayoutCompat_dividerPadding;
        mDividerPadding = ((TintTypedArray) (context)).mWrapped.getDimensionPixelSize(i, 0);
        ((TintTypedArray) (context)).mWrapped.recycle();
    }

    private final void drawHorizontalDivider(Canvas canvas, int i)
    {
        mDivider.setBounds(getPaddingLeft() + mDividerPadding, i, getWidth() - getPaddingRight() - mDividerPadding, mDividerHeight + i);
        mDivider.draw(canvas);
    }

    private final void drawVerticalDivider(Canvas canvas, int i)
    {
        mDivider.setBounds(i, getPaddingTop() + mDividerPadding, mDividerWidth + i, getHeight() - getPaddingBottom() - mDividerPadding);
        mDivider.draw(canvas);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        if (mOrientation == 0)
        {
            return new LayoutParams(-2, -2);
        }
        if (mOrientation == 1)
        {
            return new LayoutParams(-1, -2);
        } else
        {
            return null;
        }
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public int getBaseline()
    {
        int i = -1;
        if (mBaselineAlignedChildIndex >= 0) goto _L2; else goto _L1
_L1:
        i = super.getBaseline();
_L4:
        return i;
_L2:
        View view;
        int j;
        if (getChildCount() <= mBaselineAlignedChildIndex)
        {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        view = getChildAt(mBaselineAlignedChildIndex);
        j = view.getBaseline();
        if (j != -1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mBaselineAlignedChildIndex != 0)
        {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        if (true) goto _L4; else goto _L3
_L3:
        i = mBaselineChildTop;
        if (mOrientation != 1) goto _L6; else goto _L5
_L5:
        int k = mGravity & 0x70;
        if (k == 48) goto _L6; else goto _L7
_L7:
        k;
        JVM INSTR lookupswitch 2: default 132
    //                   16: 170
    //                   80: 147;
           goto _L6 _L8 _L9
_L6:
        return ((LayoutParams)view.getLayoutParams()).topMargin + i + j;
_L9:
        i = getBottom() - getTop() - getPaddingBottom() - mTotalLength;
        continue; /* Loop/switch isn't completed */
_L8:
        i += (getBottom() - getTop() - getPaddingTop() - getPaddingBottom() - mTotalLength) / 2;
        if (true) goto _L6; else goto _L10
_L10:
    }

    protected final boolean hasDividerBeforeChildAt(int i)
    {
        if (i != 0) goto _L2; else goto _L1
_L1:
        if ((mShowDividers & 1) == 0) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (i != getChildCount())
        {
            break; /* Loop/switch isn't completed */
        }
        if ((mShowDividers & 4) == 0)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
        if ((mShowDividers & 2) == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        i--;
        do
        {
            if (i < 0)
            {
                break MISSING_BLOCK_LABEL_75;
            }
            if (getChildAt(i).getVisibility() != 8)
            {
                break;
            }
            i--;
        } while (true);
        if (true) goto _L3; else goto _L6
_L6:
        return false;
        return false;
    }

    protected void onDraw(Canvas canvas)
    {
        if (mDivider != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (mOrientation != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        int i1 = getChildCount();
        for (int i = 0; i < i1; i++)
        {
            View view = getChildAt(i);
            if (view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(i))
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                drawHorizontalDivider(canvas, view.getTop() - layoutparams.topMargin - mDividerHeight);
            }
        }

        if (hasDividerBeforeChildAt(i1))
        {
            View view1 = getChildAt(i1 - 1);
            int j;
            if (view1 == null)
            {
                j = getHeight() - getPaddingBottom() - mDividerHeight;
            } else
            {
                LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
                j = view1.getBottom();
                j = layoutparams1.bottomMargin + j;
            }
            drawHorizontalDivider(canvas, j);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        int k1 = getChildCount();
        boolean flag = ViewUtils.isLayoutRtl(this);
        int k = 0;
        while (k < k1) 
        {
            View view2 = getChildAt(k);
            if (view2 != null && view2.getVisibility() != 8 && hasDividerBeforeChildAt(k))
            {
                LayoutParams layoutparams2 = (LayoutParams)view2.getLayoutParams();
                int j1;
                if (flag)
                {
                    j1 = view2.getRight();
                    j1 = layoutparams2.rightMargin + j1;
                } else
                {
                    j1 = view2.getLeft() - layoutparams2.leftMargin - mDividerWidth;
                }
                drawVerticalDivider(canvas, j1);
            }
            k++;
        }
        if (hasDividerBeforeChildAt(k1))
        {
            View view3 = getChildAt(k1 - 1);
            int l;
            if (view3 == null)
            {
                if (flag)
                {
                    l = getPaddingLeft();
                } else
                {
                    l = getWidth() - getPaddingRight() - mDividerWidth;
                }
            } else
            {
                LayoutParams layoutparams3 = (LayoutParams)view3.getLayoutParams();
                if (flag)
                {
                    l = view3.getLeft() - layoutparams3.leftMargin - mDividerWidth;
                } else
                {
                    l = view3.getRight();
                    l = layoutparams3.rightMargin + l;
                }
            }
            drawVerticalDivider(canvas, l);
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(android/support/v7/widget/LinearLayoutCompat.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/support/v7/widget/LinearLayoutCompat.getName());
    }

    public void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if (mOrientation != 1) goto _L2; else goto _L1
_L1:
        int i1;
        int k1;
        int i2;
        int j2;
        int l2;
        int i3;
        i1 = getPaddingLeft();
        k1 = k - i;
        i2 = getPaddingRight();
        j2 = getPaddingRight();
        l2 = getChildCount();
        i = mGravity;
        i3 = mGravity;
        i & 0x70;
        JVM INSTR lookupswitch 2: default 80
    //                   16: 135
    //                   80: 117;
           goto _L3 _L4 _L5
_L3:
        i = getPaddingTop();
_L29:
        k = 0;
_L8:
        View view;
        if (k >= l2)
        {
            break; /* Loop/switch isn't completed */
        }
        view = getChildAt(k);
          goto _L6
_L10:
        k++;
        if (true) goto _L8; else goto _L7
_L5:
        i = (getPaddingTop() + l) - j - mTotalLength;
        continue; /* Loop/switch isn't completed */
_L4:
        i = getPaddingTop() + (l - j - mTotalLength) / 2;
        continue; /* Loop/switch isn't completed */
_L6:
        if (view == null || view.getVisibility() == 8) goto _L10; else goto _L9
_L9:
        LayoutParams layoutparams;
        int j3;
        int k3;
        j3 = view.getMeasuredWidth();
        k3 = view.getMeasuredHeight();
        layoutparams = (LayoutParams)view.getLayoutParams();
        l = layoutparams.gravity;
        j = l;
        if (l < 0)
        {
            j = 0x800007 & i3;
        }
        Gravity.getAbsoluteGravity(j, ViewCompat.getLayoutDirection(this)) & 7;
        JVM INSTR lookupswitch 2: default 248
    //                   1: 315
    //                   5: 347;
           goto _L11 _L12 _L13
_L13:
        break MISSING_BLOCK_LABEL_347;
_L11:
        j = layoutparams.leftMargin + i1;
_L14:
        l = i;
        if (hasDividerBeforeChildAt(k))
        {
            l = i + mDividerHeight;
        }
        i = l + layoutparams.topMargin;
        view.layout(j, i, j3 + j, i + k3);
        i += layoutparams.bottomMargin + k3;
          goto _L10
_L12:
        j = ((k1 - i1 - j2 - j3) / 2 + i1 + layoutparams.leftMargin) - layoutparams.rightMargin;
          goto _L14
        j = k1 - i2 - j3 - layoutparams.rightMargin;
          goto _L14
_L2:
        int ai[];
        int ai1[];
        int l3;
        boolean flag1;
        flag = ViewUtils.isLayoutRtl(this);
        i2 = getPaddingTop();
        l2 = l - j;
        i3 = getPaddingBottom();
        j3 = getPaddingBottom();
        k3 = getChildCount();
        j = mGravity;
        l3 = mGravity;
        flag1 = mBaselineAligned;
        ai = mMaxAscent;
        ai1 = mMaxDescent;
        Gravity.getAbsoluteGravity(j & 0x800007, ViewCompat.getLayoutDirection(this));
        JVM INSTR lookupswitch 2: default 468
    //                   1: 548
    //                   5: 530;
           goto _L15 _L16 _L17
_L15:
        i = getPaddingLeft();
_L20:
        View view1;
        LayoutParams layoutparams1;
        int j1;
        int l1;
        int k2;
        int i4;
        int j4;
        int k4;
        if (flag)
        {
            l = k3 - 1;
            k = -1;
        } else
        {
            l = 0;
            k = 1;
        }
        j1 = 0;
        j = i;
_L19:
        if (j1 >= k3)
        {
            break; /* Loop/switch isn't completed */
        }
        k4 = l + k * j1;
        view1 = getChildAt(k4);
          goto _L18
_L22:
        j1++;
        if (true) goto _L19; else goto _L7
_L17:
        i = (getPaddingLeft() + k) - i - mTotalLength;
          goto _L20
_L16:
        i = getPaddingLeft() + (k - i - mTotalLength) / 2;
          goto _L20
_L18:
        if (view1 == null || view1.getVisibility() == 8) goto _L22; else goto _L21
_L21:
        i4 = view1.getMeasuredWidth();
        j4 = view1.getMeasuredHeight();
        i = -1;
        layoutparams1 = (LayoutParams)view1.getLayoutParams();
        l1 = i;
        if (flag1)
        {
            l1 = i;
            if (layoutparams1.height != -1)
            {
                l1 = view1.getBaseline();
            }
        }
        k2 = layoutparams1.gravity;
        i = k2;
        if (k2 < 0)
        {
            i = l3 & 0x70;
        }
        i & 0x70;
        JVM INSTR lookupswitch 3: default 692
    //                   16: 781
    //                   48: 748
    //                   80: 813;
           goto _L23 _L24 _L25 _L26
_L26:
        break MISSING_BLOCK_LABEL_813;
_L23:
        i = i2;
_L27:
        if (hasDividerBeforeChildAt(k4))
        {
            j = mDividerWidth + j;
        }
        j += layoutparams1.leftMargin;
        view1.layout(j, i, j + i4, i + j4);
        j += layoutparams1.rightMargin + i4;
          goto _L22
_L25:
        k2 = layoutparams1.topMargin + i2;
        i = k2;
        if (l1 != -1)
        {
            i = (ai[1] - l1) + k2;
        }
          goto _L27
_L24:
        i = ((l2 - i2 - j3 - j4) / 2 + i2 + layoutparams1.topMargin) - layoutparams1.bottomMargin;
          goto _L27
        k2 = l2 - i3 - j4 - layoutparams1.bottomMargin;
        i = k2;
        if (l1 != -1)
        {
            i = view1.getMeasuredHeight();
            i = k2 - (ai1[2] - (i - l1));
        }
          goto _L27
_L7:
        return;
        if (true) goto _L29; else goto _L28
_L28:
    }

    protected void onMeasure(int i, int j)
    {
        if (mOrientation != 1) goto _L2; else goto _L1
_L1:
        float f;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        boolean flag;
        int j2;
        int k2;
        int k6;
        int l6;
        int i7;
        int j7;
        boolean flag2;
        mTotalLength = 0;
        i1 = 0;
        k = 0;
        l = 0;
        j2 = 0;
        j1 = 1;
        f = 0.0F;
        k6 = getChildCount();
        l6 = android.view.View.MeasureSpec.getMode(i);
        i7 = android.view.View.MeasureSpec.getMode(j);
        flag = false;
        l1 = 0;
        j7 = mBaselineAlignedChildIndex;
        flag2 = mUseLargestChild;
        k1 = 0;
        k2 = 0;
_L7:
        if (k2 >= k6) goto _L4; else goto _L3
_L3:
        View view = getChildAt(k2);
        if (view != null) goto _L6; else goto _L5
_L5:
        int j3;
        int k4;
        int i5;
        int i6;
        mTotalLength = mTotalLength;
        i6 = i1;
        i5 = k;
        k4 = l;
        j3 = j2;
        i1 = j1;
        l = l1;
        k = k1;
_L12:
        k2++;
        k1 = k;
        l1 = l;
        j1 = i1;
        j2 = j3;
        l = k4;
        k = i5;
        i1 = i6;
          goto _L7
_L6:
        LayoutParams layoutparams;
        if (view.getVisibility() == 8)
        {
            break MISSING_BLOCK_LABEL_4042;
        }
        if (hasDividerBeforeChildAt(k2))
        {
            mTotalLength = mTotalLength + mDividerHeight;
        }
        layoutparams = (LayoutParams)view.getLayoutParams();
        f += layoutparams.weight;
        if (i7 != 0x40000000 || layoutparams.height != 0 || layoutparams.weight <= 0.0F) goto _L9; else goto _L8
_L8:
        l1 = mTotalLength;
        mTotalLength = Math.max(l1, layoutparams.topMargin + l1 + layoutparams.bottomMargin);
        l1 = 1;
_L11:
        if (j7 >= 0 && j7 == k2 + 1)
        {
            mBaselineChildTop = mTotalLength;
        }
        if (k2 < j7 && layoutparams.weight > 0.0F)
        {
            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
        }
        break; /* Loop/switch isn't completed */
_L9:
        k4 = 0x80000000;
        j3 = k4;
        if (layoutparams.height == 0)
        {
            j3 = k4;
            if (layoutparams.weight > 0.0F)
            {
                j3 = 0;
                layoutparams.height = -2;
            }
        }
        if (f == 0.0F)
        {
            k4 = mTotalLength;
        } else
        {
            k4 = 0;
        }
        measureChildWithMargins(view, i, 0, j, k4);
        if (j3 != 0x80000000)
        {
            layoutparams.height = j3;
        }
        j3 = view.getMeasuredHeight();
        k4 = mTotalLength;
        mTotalLength = Math.max(k4, k4 + j3 + layoutparams.topMargin + layoutparams.bottomMargin);
        if (flag2)
        {
            k1 = Math.max(j3, k1);
        }
        if (true) goto _L11; else goto _L10
_L10:
        j3 = 0;
        if (l6 != 0x40000000 && layoutparams.width == -1)
        {
            flag = true;
            j3 = 1;
        }
        k4 = layoutparams.leftMargin + layoutparams.rightMargin;
        i5 = view.getMeasuredWidth() + k4;
        i1 = Math.max(i1, i5);
        i6 = View.combineMeasuredStates(k, view.getMeasuredState());
        if (j1 != 0 && layoutparams.width == -1)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (layoutparams.weight > 0.0F)
        {
            if (j3 == 0)
            {
                k4 = i5;
            }
            j2 = Math.max(j2, k4);
            j1 = l;
            l = l1;
            l1 = i1;
            i1 = j2;
            j3 = k1;
            j2 = i6;
            k1 = j1;
            j1 = i1;
            i1 = k;
            k = j3;
        } else
        {
            if (j3 == 0)
            {
                k4 = i5;
            }
            j3 = Math.max(l, k4);
            j1 = k;
            l = l1;
            k = k1;
            l1 = i1;
            i1 = j1;
            j1 = j2;
            k1 = j3;
            j2 = i6;
        }
_L26:
        j3 = j1;
        k4 = k1;
        i5 = j2;
        i6 = l1;
          goto _L12
_L4:
        if (mTotalLength > 0 && hasDividerBeforeChildAt(k6))
        {
            mTotalLength = mTotalLength + mDividerHeight;
        }
        if (flag2 && (i7 == 0x80000000 || i7 == 0))
        {
            mTotalLength = 0;
            k2 = 0;
            while (k2 < k6) 
            {
                Object obj = getChildAt(k2);
                if (obj == null)
                {
                    mTotalLength = mTotalLength;
                } else
                if (((View) (obj)).getVisibility() != 8)
                {
                    obj = (LayoutParams)((View) (obj)).getLayoutParams();
                    int k3 = mTotalLength;
                    k4 = ((LayoutParams) (obj)).topMargin;
                    mTotalLength = Math.max(k3, ((LayoutParams) (obj)).bottomMargin + (k3 + k1 + k4));
                }
                k2++;
            }
        }
        mTotalLength = mTotalLength + (getPaddingTop() + getPaddingBottom());
        k4 = View.resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumHeight()), j, 0);
        k2 = (0xffffff & k4) - mTotalLength;
        if (l1 == 0 && (k2 == 0 || f <= 0.0F)) goto _L14; else goto _L13
_L13:
        if (mWeightSum > 0.0F)
        {
            f = mWeightSum;
        }
        mTotalLength = 0;
        j2 = 0;
        k1 = j1;
        j1 = i1;
        i1 = l;
        l = k1;
        k1 = k2;
        while (j2 < k6) 
        {
            View view1 = getChildAt(j2);
            if (view1.getVisibility() != 8)
            {
                LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
                float f1 = layoutparams1.weight;
                int ai[];
                int ai1[];
                Object obj1;
                LayoutParams layoutparams2;
                int i2;
                int i3;
                int i4;
                boolean flag1;
                int k5;
                int j6;
                int k7;
                int l7;
                boolean flag3;
                if (f1 > 0.0F)
                {
                    int l2 = (int)(((float)k1 * f1) / f);
                    int j5 = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutparams1.leftMargin + layoutparams1.rightMargin, layoutparams1.width);
                    if (layoutparams1.height != 0 || i7 != 0x40000000)
                    {
                        int l3 = l2 + view1.getMeasuredHeight();
                        l1 = l3;
                        if (l3 < 0)
                        {
                            l1 = 0;
                        }
                    } else
                    if (l2 > 0)
                    {
                        l1 = l2;
                    } else
                    {
                        l1 = 0;
                    }
                    view1.measure(j5, android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x40000000));
                    k = View.combineMeasuredStates(k, view1.getMeasuredState() & 0xffffff00);
                    l1 = k1 - l2;
                    k1 = k;
                    f -= f1;
                    k = l1;
                } else
                {
                    l1 = k;
                    k = k1;
                    k1 = l1;
                }
                l2 = layoutparams1.leftMargin + layoutparams1.rightMargin;
                i4 = view1.getMeasuredWidth() + l2;
                l1 = Math.max(j1, i4);
                if (l6 != 0x40000000 && layoutparams1.width == -1)
                {
                    j1 = 1;
                } else
                {
                    j1 = 0;
                }
                if (j1 != 0)
                {
                    j1 = l2;
                } else
                {
                    j1 = i4;
                }
                i1 = Math.max(i1, j1);
                if (l != 0 && layoutparams1.width == -1)
                {
                    l = 1;
                } else
                {
                    l = 0;
                }
                j1 = mTotalLength;
                l2 = view1.getMeasuredHeight();
                i4 = layoutparams1.topMargin;
                mTotalLength = Math.max(j1, layoutparams1.bottomMargin + (l2 + j1 + i4));
                j1 = l;
                l = l1;
            } else
            {
                l1 = l;
                l = j1;
                j1 = k;
                k = k1;
                k1 = j1;
                j1 = l1;
            }
            l2 = j2 + 1;
            j2 = k1;
            l1 = l;
            k1 = k;
            k = j2;
            l = j1;
            j1 = l1;
            j2 = l2;
        }
        mTotalLength = mTotalLength + (getPaddingTop() + getPaddingBottom());
        k1 = i1;
        i1 = j1;
        j1 = l;
        l = k1;
        if (j1 != 0 || l6 == 0x40000000)
        {
            l = i1;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(l + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, k), k4);
        if (flag)
        {
            k = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000);
            for (i = 0; i < k6; i++)
            {
                view1 = getChildAt(i);
                if (view1.getVisibility() != 8)
                {
                    layoutparams1 = (LayoutParams)view1.getLayoutParams();
                    if (layoutparams1.width == -1)
                    {
                        l = layoutparams1.height;
                        layoutparams1.height = view1.getMeasuredHeight();
                        measureChildWithMargins(view1, k, 0, j, 0);
                        layoutparams1.height = l;
                    }
                }
            }

        }
          goto _L15
_L2:
        mTotalLength = 0;
        j2 = 0;
        j1 = 0;
        i1 = 0;
        i2 = 0;
        k1 = 1;
        f = 0.0F;
        i7 = getChildCount();
        l7 = android.view.View.MeasureSpec.getMode(i);
        k7 = android.view.View.MeasureSpec.getMode(j);
        l1 = 0;
        l = 0;
        if (mMaxAscent == null || mMaxDescent == null)
        {
            mMaxAscent = new int[4];
            mMaxDescent = new int[4];
        }
        ai = mMaxAscent;
        ai1 = mMaxDescent;
        ai[3] = -1;
        ai[2] = -1;
        ai[1] = -1;
        ai[0] = -1;
        ai1[3] = -1;
        ai1[2] = -1;
        ai1[1] = -1;
        ai1[0] = -1;
        flag2 = mBaselineAligned;
        flag3 = mUseLargestChild;
        if (l7 == 0x40000000)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        k = 0;
        k4 = 0;
        if (k4 >= i7) goto _L17; else goto _L16
_L16:
        obj1 = getChildAt(k4);
        if (obj1 != null) goto _L19; else goto _L18
_L18:
        mTotalLength = mTotalLength;
        j6 = j2;
        k5 = j1;
        i3 = i1;
        j1 = i2;
        i1 = k1;
        j2 = k;
        k = l1;
_L21:
        k4++;
        l1 = k;
        k = j2;
        k1 = i1;
        i2 = j1;
        i1 = i3;
        j1 = k5;
        j2 = j6;
        break MISSING_BLOCK_LABEL_1797;
_L19:
        if (((View) (obj1)).getVisibility() == 8)
        {
            break; /* Loop/switch isn't completed */
        }
        if (hasDividerBeforeChildAt(k4))
        {
            mTotalLength = mTotalLength + mDividerWidth;
        }
        layoutparams2 = (LayoutParams)((View) (obj1)).getLayoutParams();
        f += layoutparams2.weight;
        if (l7 == 0x40000000 && layoutparams2.width == 0 && layoutparams2.weight > 0.0F)
        {
            if (flag1)
            {
                mTotalLength = mTotalLength + (layoutparams2.leftMargin + layoutparams2.rightMargin);
            } else
            {
                i3 = mTotalLength;
                mTotalLength = Math.max(i3, layoutparams2.leftMargin + i3 + layoutparams2.rightMargin);
            }
            if (flag2)
            {
                i3 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                ((View) (obj1)).measure(i3, i3);
            } else
            {
                l = 1;
            }
        } else
        {
            k5 = 0x80000000;
            i3 = k5;
            if (layoutparams2.width == 0)
            {
                i3 = k5;
                if (layoutparams2.weight > 0.0F)
                {
                    i3 = 0;
                    layoutparams2.width = -2;
                }
            }
            if (f == 0.0F)
            {
                k5 = mTotalLength;
            } else
            {
                k5 = 0;
            }
            measureChildWithMargins(((View) (obj1)), i, k5, j, 0);
            if (i3 != 0x80000000)
            {
                layoutparams2.width = i3;
            }
            i3 = ((View) (obj1)).getMeasuredWidth();
            if (flag1)
            {
                mTotalLength = mTotalLength + (layoutparams2.leftMargin + i3 + layoutparams2.rightMargin);
            } else
            {
                k5 = mTotalLength;
                mTotalLength = Math.max(k5, k5 + i3 + layoutparams2.leftMargin + layoutparams2.rightMargin);
            }
            if (flag3)
            {
                k = Math.max(i3, k);
            }
        }
        k5 = 0;
        if (k7 != 0x40000000 && layoutparams2.height == -1)
        {
            i3 = 1;
            k5 = 1;
        } else
        {
            i3 = l1;
        }
        j6 = layoutparams2.topMargin + layoutparams2.bottomMargin;
        k6 = ((View) (obj1)).getMeasuredHeight() + j6;
        l6 = View.combineMeasuredStates(j1, ((View) (obj1)).getMeasuredState());
        if (flag2)
        {
            l1 = ((View) (obj1)).getBaseline();
            if (l1 != -1)
            {
                if (layoutparams2.gravity < 0)
                {
                    j1 = mGravity;
                } else
                {
                    j1 = layoutparams2.gravity;
                }
                j1 = ((j1 & 0x70) >> 4 & -2) >> 1;
                ai[j1] = Math.max(ai[j1], l1);
                ai1[j1] = Math.max(ai1[j1], k6 - l1);
            }
        }
        l1 = Math.max(j2, k6);
        if (k1 != 0 && layoutparams2.height == -1)
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        if (layoutparams2.weight > 0.0F)
        {
            if (k5 == 0)
            {
                j6 = k6;
            }
            k1 = Math.max(i2, j6);
            j2 = j1;
            j1 = k1;
            i2 = l6;
            k1 = i1;
            i1 = j2;
        } else
        {
            if (k5 == 0)
            {
                j6 = k6;
            }
            k1 = Math.max(i1, j6);
            i1 = j1;
            j1 = i2;
            i2 = l6;
        }
_L25:
        j2 = k;
        k = i3;
        i3 = k1;
        k5 = i2;
        j6 = l1;
        if (true) goto _L21; else goto _L20
_L17:
        if (mTotalLength > 0 && hasDividerBeforeChildAt(i7))
        {
            mTotalLength = mTotalLength + mDividerWidth;
        }
        if (ai[1] != -1 || ai[0] != -1 || ai[2] != -1 || ai[3] != -1)
        {
            j2 = Math.max(j2, Math.max(ai[3], Math.max(ai[0], Math.max(ai[1], ai[2]))) + Math.max(ai1[3], Math.max(ai1[0], Math.max(ai1[1], ai1[2]))));
        }
        if (flag3 && (l7 == 0x80000000 || l7 == 0))
        {
            mTotalLength = 0;
            i3 = 0;
            while (i3 < i7) 
            {
                obj1 = getChildAt(i3);
                if (obj1 == null)
                {
                    mTotalLength = mTotalLength;
                } else
                if (((View) (obj1)).getVisibility() != 8)
                {
                    obj1 = (LayoutParams)((View) (obj1)).getLayoutParams();
                    if (flag1)
                    {
                        k4 = mTotalLength;
                        k5 = ((LayoutParams) (obj1)).leftMargin;
                        mTotalLength = ((LayoutParams) (obj1)).rightMargin + (k5 + k) + k4;
                    } else
                    {
                        k4 = mTotalLength;
                        k5 = ((LayoutParams) (obj1)).leftMargin;
                        mTotalLength = Math.max(k4, ((LayoutParams) (obj1)).rightMargin + (k4 + k + k5));
                    }
                }
                i3++;
            }
        }
        mTotalLength = mTotalLength + (getPaddingLeft() + getPaddingRight());
        j6 = View.resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumWidth()), i, 0);
        i3 = (0xffffff & j6) - mTotalLength;
        if (l == 0 && (i3 == 0 || f <= 0.0F)) goto _L23; else goto _L22
_L22:
label0:
        {
            if (mWeightSum > 0.0F)
            {
                f = mWeightSum;
            }
            ai[3] = -1;
            ai[2] = -1;
            ai[1] = -1;
            ai[0] = -1;
            ai1[3] = -1;
            ai1[2] = -1;
            ai1[1] = -1;
            ai1[0] = -1;
            mTotalLength = 0;
            j2 = 0;
            l = k1;
            k1 = -1;
            k = j1;
            j1 = k1;
            k1 = i3;
            while (j2 < i7) 
            {
                obj1 = getChildAt(j2);
                if (obj1 != null && ((View) (obj1)).getVisibility() != 8)
                {
                    layoutparams2 = (LayoutParams)((View) (obj1)).getLayoutParams();
                    f1 = layoutparams2.weight;
                    if (f1 > 0.0F)
                    {
                        i3 = (int)(((float)k1 * f1) / f);
                        k5 = getChildMeasureSpec(j, getPaddingTop() + getPaddingBottom() + layoutparams2.topMargin + layoutparams2.bottomMargin, layoutparams2.height);
                        if (layoutparams2.width != 0 || l7 != 0x40000000)
                        {
                            k4 = i3 + ((View) (obj1)).getMeasuredWidth();
                            i2 = k4;
                            if (k4 < 0)
                            {
                                i2 = 0;
                            }
                        } else
                        if (i3 > 0)
                        {
                            i2 = i3;
                        } else
                        {
                            i2 = 0;
                        }
                        ((View) (obj1)).measure(android.view.View.MeasureSpec.makeMeasureSpec(i2, 0x40000000), k5);
                        k = View.combineMeasuredStates(k, ((View) (obj1)).getMeasuredState() & 0xff000000);
                        f -= f1;
                        i2 = k1 - i3;
                        k1 = k;
                        k = i2;
                    } else
                    {
                        i2 = k;
                        k = k1;
                        k1 = i2;
                    }
                    if (flag1)
                    {
                        mTotalLength = mTotalLength + (((View) (obj1)).getMeasuredWidth() + layoutparams2.leftMargin + layoutparams2.rightMargin);
                    } else
                    {
                        i2 = mTotalLength;
                        mTotalLength = Math.max(i2, ((View) (obj1)).getMeasuredWidth() + i2 + layoutparams2.leftMargin + layoutparams2.rightMargin);
                    }
                    if (k7 != 0x40000000 && layoutparams2.height == -1)
                    {
                        i2 = 1;
                    } else
                    {
                        i2 = 0;
                    }
                    k5 = layoutparams2.topMargin + layoutparams2.bottomMargin;
                    k4 = ((View) (obj1)).getMeasuredHeight() + k5;
                    i3 = Math.max(j1, k4);
                    if (i2 != 0)
                    {
                        j1 = k5;
                    } else
                    {
                        j1 = k4;
                    }
                    j1 = Math.max(i1, j1);
                    if (l != 0 && layoutparams2.height == -1)
                    {
                        l = 1;
                    } else
                    {
                        l = 0;
                    }
                    if (flag2)
                    {
                        i2 = ((View) (obj1)).getBaseline();
                        if (i2 != -1)
                        {
                            if (layoutparams2.gravity < 0)
                            {
                                i1 = mGravity;
                            } else
                            {
                                i1 = layoutparams2.gravity;
                            }
                            i1 = ((i1 & 0x70) >> 4 & -2) >> 1;
                            ai[i1] = Math.max(ai[i1], i2);
                            ai1[i1] = Math.max(ai1[i1], k4 - i2);
                        }
                    }
                    i2 = k1;
                    k1 = l;
                    i1 = i3;
                    l = i2;
                } else
                {
                    i3 = l;
                    l = k;
                    i2 = j1;
                    k = k1;
                    k1 = i3;
                    j1 = i1;
                    i1 = i2;
                }
                i3 = j2 + 1;
                i2 = k1;
                j2 = i1;
                k1 = k;
                k = l;
                l = i2;
                i1 = j1;
                j1 = j2;
                j2 = i3;
            }
            mTotalLength = mTotalLength + (getPaddingLeft() + getPaddingRight());
            if (ai[1] == -1 && ai[0] == -1 && ai[2] == -1)
            {
                k1 = j1;
                if (ai[3] == -1)
                {
                    break label0;
                }
            }
            k1 = Math.max(j1, Math.max(ai[3], Math.max(ai[0], Math.max(ai[1], ai[2]))) + Math.max(ai1[3], Math.max(ai1[0], Math.max(ai1[1], ai1[2]))));
        }
        j1 = k;
        i2 = l;
        l = k1;
        k = i1;
_L24:
        if (i2 != 0 || k7 == 0x40000000)
        {
            k = l;
        }
        setMeasuredDimension(0xff000000 & j1 | j6, View.resolveSizeAndState(Math.max(k + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), j, j1 << 16));
        if (l1 != 0)
        {
            k = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0x40000000);
            for (j = 0; j < i7; j++)
            {
                ai = getChildAt(j);
                if (ai.getVisibility() != 8)
                {
                    ai1 = (LayoutParams)ai.getLayoutParams();
                    if (((LayoutParams) (ai1)).height == -1)
                    {
                        l = ((LayoutParams) (ai1)).width;
                        ai1.width = ai.getMeasuredWidth();
                        measureChildWithMargins(ai, i, 0, k, 0);
                        ai1.width = l;
                    }
                }
            }

        }
          goto _L15
_L23:
        i1 = Math.max(i1, i2);
        if (flag3 && l7 != 0x40000000)
        {
            for (l = 0; l < i7; l++)
            {
                ai = getChildAt(l);
                if (ai != null && ai.getVisibility() != 8 && ((LayoutParams)ai.getLayoutParams()).weight > 0.0F)
                {
                    ai.measure(android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(ai.getMeasuredHeight(), 0x40000000));
                }
            }

        }
        k = i1;
        l = j2;
        i2 = k1;
        continue; /* Loop/switch isn't completed */
_L15:
        return;
        if (true) goto _L24; else goto _L20
_L20:
        k5 = i1;
        i3 = l1;
        l1 = j2;
        j2 = j1;
        i1 = k1;
        j1 = i2;
        k1 = k5;
        i2 = j2;
          goto _L25
_L14:
        l1 = Math.max(l, j2);
        if (flag2 && i7 != 0x40000000)
        {
            for (l = 0; l < k6; l++)
            {
                view1 = getChildAt(l);
                if (view1 != null && view1.getVisibility() != 8 && ((LayoutParams)view1.getLayoutParams()).weight > 0.0F)
                {
                    view1.measure(android.view.View.MeasureSpec.makeMeasureSpec(view1.getMeasuredWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(k1, 0x40000000));
                }
            }

        }
        l = l1;
        if (true)
        {
            break MISSING_BLOCK_LABEL_1391;
        }
        int j4 = k1;
        int l4 = l1;
        k1 = l;
        l1 = i1;
        int l5 = k;
        k = j4;
        l = l4;
        i1 = j1;
        j1 = j2;
        j2 = l5;
          goto _L26
    }

    public void setBaselineAlignedChildIndex(int i)
    {
        if (i < 0 || i >= getChildCount())
        {
            throw new IllegalArgumentException((new StringBuilder("base aligned child index out of range (0, ")).append(getChildCount()).append(")").toString());
        } else
        {
            mBaselineAlignedChildIndex = i;
            return;
        }
    }

    public void setDividerPadding(int i)
    {
        mDividerPadding = i;
    }

    public void setGravity(int i)
    {
        if (mGravity != i)
        {
            if ((0x800007 & i) == 0)
            {
                i = 0x800003 | i;
            }
            int j = i;
            if ((i & 0x70) == 0)
            {
                j = i | 0x30;
            }
            mGravity = j;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i)
    {
        i &= 0x800007;
        if ((mGravity & 0x800007) != i)
        {
            mGravity = i | mGravity & 0xff7ffff8;
            requestLayout();
        }
    }

    public void setOrientation(int i)
    {
        if (mOrientation != i)
        {
            mOrientation = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i)
    {
        if (i != mShowDividers)
        {
            requestLayout();
        }
        mShowDividers = i;
    }

    public void setVerticalGravity(int i)
    {
        i &= 0x70;
        if ((mGravity & 0x70) != i)
        {
            mGravity = i | mGravity & 0xffffff8f;
            requestLayout();
        }
    }

    public void setWeightSum(float f)
    {
        mWeightSum = Math.max(0.0F, f);
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }
}
