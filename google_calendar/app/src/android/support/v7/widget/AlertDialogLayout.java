// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.v7.widget:
//            LinearLayoutCompat

public class AlertDialogLayout extends LinearLayoutCompat
{

    public AlertDialogLayout(Context context)
    {
        super(context);
    }

    public AlertDialogLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private static int resolveMinimumHeight(View view)
    {
        do
        {
            int i = ViewCompat.getMinimumHeight(view);
            if (i > 0)
            {
                return i;
            }
            if (!(view instanceof ViewGroup))
            {
                break;
            }
            view = (ViewGroup)view;
            if (view.getChildCount() != 1)
            {
                break;
            }
            view = view.getChildAt(0);
        } while (true);
        return 0;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        j1 = getPaddingLeft();
        k1 = k - i;
        l1 = getPaddingRight();
        i2 = getPaddingRight();
        i = getMeasuredHeight();
        j2 = getChildCount();
        k2 = super.mGravity;
        k2 & 0x70;
        JVM INSTR lookupswitch 2: default 72
    //                   16: 300
    //                   80: 285;
           goto _L1 _L2 _L3
_L1:
        i = getPaddingTop();
_L11:
        LinearLayoutCompat.LayoutParams layoutparams;
        int l2;
        Object obj = super.mDivider;
        int i1;
        int i3;
        if (obj == null)
        {
            k = 0;
        } else
        {
            k = ((Drawable) (obj)).getIntrinsicHeight();
        }
_L10:
        for (l = 0; l >= j2;)
        {
            break MISSING_BLOCK_LABEL_381;
        }

        obj = getChildAt(l);
        j = i;
        if (obj == null) goto _L5; else goto _L4
_L4:
        j = i;
        if (((View) (obj)).getVisibility() == 8) goto _L5; else goto _L6
_L6:
        l2 = ((View) (obj)).getMeasuredWidth();
        i3 = ((View) (obj)).getMeasuredHeight();
        layoutparams = (LinearLayoutCompat.LayoutParams)((View) (obj)).getLayoutParams();
        i1 = layoutparams.gravity;
        j = i1;
        if (i1 < 0)
        {
            j = k2 & 0x800007;
        }
        Gravity.getAbsoluteGravity(j, ViewCompat.getLayoutDirection(this)) & 7;
        JVM INSTR lookupswitch 2: default 212
    //                   1: 331
    //                   5: 363;
           goto _L7 _L8 _L9
_L7:
        j = layoutparams.leftMargin + j1;
_L12:
        i1 = i;
        if (hasDividerBeforeChildAt(l))
        {
            i1 = i + k;
        }
        i = i1 + layoutparams.topMargin;
        ((View) (obj)).layout(j, i, l2 + j, i + i3);
        j = i + (layoutparams.bottomMargin + i3);
_L5:
        l++;
        i = j;
          goto _L10
_L3:
        i = (getPaddingTop() + l) - j - i;
          goto _L11
_L2:
        k = getPaddingTop();
        i = (l - j - i) / 2 + k;
          goto _L11
_L8:
        j = ((k1 - j1 - i2 - l2) / 2 + j1 + layoutparams.leftMargin) - layoutparams.rightMargin;
          goto _L12
_L9:
        j = k1 - l1 - l2 - layoutparams.rightMargin;
          goto _L12
          goto _L10
    }

    protected void onMeasure(int i, int j)
    {
        Object obj;
        Object obj1;
        Object obj3;
        int k;
        int k2;
        obj = null;
        obj1 = null;
        k2 = getChildCount();
        k = 0;
        obj3 = null;
_L2:
        Object obj2;
        int l;
        if (k >= k2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj2 = getChildAt(k);
        if (((View) (obj2)).getVisibility() == 8)
        {
            break MISSING_BLOCK_LABEL_720;
        }
        l = ((View) (obj2)).getId();
        if (l == 0x7f1000e0)
        {
            obj = obj1;
            obj1 = obj2;
        } else
        {
label0:
            {
                if (l != 0x7f1000d5)
                {
                    break label0;
                }
                obj1 = obj;
                obj = obj2;
            }
        }
_L7:
        k++;
        obj2 = obj1;
        obj1 = obj;
        obj = obj2;
        if (true) goto _L2; else goto _L1
        if (l != 0x7f1000d8 && l != 0x7f1000de) goto _L4; else goto _L3
_L3:
        if (obj3 == null) goto _L6; else goto _L5
_L5:
        k = 0;
_L8:
        if (k == 0)
        {
            super.onMeasure(i, j);
        }
        return;
_L6:
        View view1 = ((View) (obj));
        obj3 = obj2;
        obj = obj1;
        obj1 = view1;
          goto _L7
_L4:
        k = 0;
          goto _L8
_L1:
        int i3 = android.view.View.MeasureSpec.getMode(j);
        int j2 = android.view.View.MeasureSpec.getSize(j);
        int l2 = android.view.View.MeasureSpec.getMode(i);
        int j1 = 0;
        k = getPaddingTop();
        k = getPaddingBottom() + k;
        int i1 = k;
        if (obj != null)
        {
            ((View) (obj)).measure(i, 0);
            i1 = k + ((View) (obj)).getMeasuredHeight();
            j1 = View.combineMeasuredStates(0, ((View) (obj)).getMeasuredState());
        }
        k = 0;
        int k1;
        int i2;
        if (obj1 != null)
        {
            ((View) (obj1)).measure(i, 0);
            k = resolveMinimumHeight(((View) (obj1)));
            k1 = ((View) (obj1)).getMeasuredHeight();
            i1 += k;
            j1 = View.combineMeasuredStates(j1, ((View) (obj1)).getMeasuredState());
            k1 -= k;
        } else
        {
            k1 = 0;
        }
        if (obj3 != null)
        {
            int l1;
            int j3;
            if (i3 == 0)
            {
                l1 = 0;
            } else
            {
                l1 = android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, j2 - i1), i3);
            }
            ((View) (obj3)).measure(i, l1);
            i2 = ((View) (obj3)).getMeasuredHeight();
            i1 += i2;
            j1 = View.combineMeasuredStates(j1, ((View) (obj3)).getMeasuredState());
        } else
        {
            i2 = 0;
        }
        j2 -= i1;
        if (obj1 != null)
        {
            j3 = Math.min(j2, k1);
            k1 = j2;
            l1 = k;
            if (j3 > 0)
            {
                k1 = j2 - j3;
                l1 = k + j3;
            }
            ((View) (obj1)).measure(i, android.view.View.MeasureSpec.makeMeasureSpec(l1, 0x40000000));
            l1 = ((View) (obj1)).getMeasuredHeight();
            j1 = View.combineMeasuredStates(j1, ((View) (obj1)).getMeasuredState());
            k = l1 + (i1 - k);
            j2 = k1;
            i1 = j1;
        } else
        {
            k = i1;
            i1 = j1;
        }
        if (obj3 != null && j2 > 0)
        {
            ((View) (obj3)).measure(i, android.view.View.MeasureSpec.makeMeasureSpec(j2 + i2, i3));
            j1 = ((View) (obj3)).getMeasuredHeight();
            i1 = View.combineMeasuredStates(i1, ((View) (obj3)).getMeasuredState());
            k = (k - i2) + j1;
        }
        k1 = 0;
        for (j1 = 0; j1 < k2;)
        {
            obj = getChildAt(j1);
            l1 = k1;
            if (((View) (obj)).getVisibility() != 8)
            {
                l1 = Math.max(k1, ((View) (obj)).getMeasuredWidth());
            }
            j1++;
            k1 = l1;
        }

        setMeasuredDimension(View.resolveSizeAndState(k1 + (getPaddingLeft() + getPaddingRight()), i, i1), View.resolveSizeAndState(k, j, 0));
        if (l2 != 0x40000000)
        {
            i1 = android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000);
            for (k = 0; k < k2; k++)
            {
                obj = getChildAt(k);
                if (((View) (obj)).getVisibility() == 8)
                {
                    continue;
                }
                obj1 = (LinearLayoutCompat.LayoutParams)((View) (obj)).getLayoutParams();
                if (((LinearLayoutCompat.LayoutParams) (obj1)).width == -1)
                {
                    j1 = ((LinearLayoutCompat.LayoutParams) (obj1)).height;
                    obj1.height = ((View) (obj)).getMeasuredHeight();
                    measureChildWithMargins(((View) (obj)), i1, 0, j, 0);
                    obj1.height = j1;
                }
            }

        }
        k = 1;
          goto _L8
        View view = ((View) (obj));
        obj = obj1;
        obj1 = view;
          goto _L7
    }
}
