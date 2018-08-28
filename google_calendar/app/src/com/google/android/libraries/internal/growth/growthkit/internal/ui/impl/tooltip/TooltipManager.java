// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.PopupWindow;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip:
//            Tooltip, ViewPosition, TooltipModel, ViewPositionTracker

public final class TooltipManager
    implements ViewPositionTracker.OnTrackingViewChangedListener, ViewPositionTracker.OnViewPositionChangedListener
{

    private final int anchorViewTrackerOffset[] = new int[2];
    public TooltipModel model;
    private final Rect positionRect = new Rect();
    public ViewPositionTracker targetViewTracker;
    public Tooltip tooltip;
    public View viewTreeRoot;

    public TooltipManager(TooltipModel tooltipmodel)
    {
        model = tooltipmodel;
    }

    private final Rect addAnchorViewTrackerOffset(Rect rect)
    {
        positionRect.set(rect);
        viewTreeRoot.getLocationInWindow(anchorViewTrackerOffset);
        positionRect.offset(anchorViewTrackerOffset[0], anchorViewTrackerOffset[1]);
        return positionRect;
    }

    final void hideShownTooltip()
    {
        Tooltip tooltip1 = tooltip;
        if (tooltip1 != null && tooltip1.tooltipView.isShown())
        {
            Tooltip.TooltipView tooltipview = tooltip1.tooltipView;
            if (tooltipview.popupWindow != null)
            {
                tooltipview.popupWindow.dismiss();
            }
            if (tooltip1 == tooltip)
            {
                tooltip = null;
                model = null;
            }
        }
    }

    public final void onTrackingViewChanged(View view)
    {
        if (view == null)
        {
            view = tooltip;
            if (view != null && ((Tooltip) (view)).tooltipView.isShown())
            {
                Tooltip.TooltipView tooltipview = ((Tooltip) (view)).tooltipView;
                if (tooltipview.popupWindow != null)
                {
                    tooltipview.popupWindow.dismiss();
                }
                if (view == tooltip)
                {
                    tooltip = null;
                    model = null;
                }
            }
        }
    }

    public final void onViewPositionChanged(ViewPosition viewposition)
    {
        if (tooltip != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = Math.max(viewposition.viewRect.left, viewposition.clipRect.left);
        if (Math.max(Math.min(viewposition.viewRect.right, viewposition.clipRect.right) - i, 0) <= 0) goto _L4; else goto _L3
_L3:
        i = Math.max(viewposition.viewRect.top, viewposition.clipRect.top);
        if (Math.max(Math.min(viewposition.viewRect.bottom, viewposition.clipRect.bottom) - i, 0) <= 0) goto _L4; else goto _L5
_L5:
        boolean flag = true;
_L7:
        if (flag)
        {
            Object obj = model;
            if (obj != null)
            {
                obj = ((TooltipModel) (obj)).targetView();
            } else
            {
                obj = null;
            }
            if (obj != null && ((View) (obj)).isShown())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                break; /* Loop/switch isn't completed */
            }
        }
        viewposition = tooltip;
        if (viewposition != null && ((Tooltip) (viewposition)).tooltipView.isShown())
        {
            obj = ((Tooltip) (viewposition)).tooltipView;
            if (((Tooltip.TooltipView) (obj)).popupWindow != null)
            {
                ((Tooltip.TooltipView) (obj)).popupWindow.dismiss();
            }
            if (viewposition == tooltip)
            {
                tooltip = null;
                model = null;
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
        if (tooltip.tooltipView.isShown()) goto _L9; else goto _L8
_L8:
        Rect rect;
        Tooltip tooltip2;
        TooltipModel.Alignment alignment;
        rect = addAnchorViewTrackerOffset(viewposition.viewRect);
        tooltip2 = tooltip;
        viewposition = tooltip2.placement;
        alignment = tooltip2.alignment;
        tooltip2.tooltipView.setAnchor(tooltip2.anchorView, rect, viewposition, alignment);
        if (viewposition == TooltipModel.Placement.ABOVE || viewposition == TooltipModel.Placement.BELOW)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag == 0) goto _L11; else goto _L10
_L10:
        Tooltip.TooltipView tooltipview = tooltip2.tooltipView;
        if (viewposition == TooltipModel.Placement.ABOVE || viewposition == TooltipModel.Placement.BELOW)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag == 0)
        {
            flag = true;
        } else
        {
            int k = tooltipview.getHeight();
            flag = k;
            if (k == 0)
            {
                flag = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                tooltipview.measure(flag, flag);
                flag = tooltipview.getMeasuredHeight();
            }
            k = tooltipview.getResources().getDisplayMetrics().heightPixels;
            if (viewposition == TooltipModel.Placement.ABOVE)
            {
                if (flag < rect.top)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            } else
            if (flag < k - rect.height() - rect.top)
            {
                flag = true;
            } else
            {
                flag = false;
            }
        }
        if (flag == 0)
        {
            if (viewposition == TooltipModel.Placement.ABOVE)
            {
                viewposition = TooltipModel.Placement.BELOW;
            } else
            {
                viewposition = TooltipModel.Placement.ABOVE;
            }
            tooltip2.tooltipView.setAnchor(tooltip2.anchorView, rect, viewposition, alignment);
        }
_L13:
        viewposition = tooltip2.tooltipView;
        ((Tooltip.TooltipView) (viewposition)).popupWindow.setClippingEnabled(false);
        ((Tooltip.TooltipView) (viewposition)).popupWindow.setAnimationStyle(0x1030002);
        ((Tooltip.TooltipView) (viewposition)).popupWindow.setTouchable(true);
        ((Tooltip.TooltipView) (viewposition)).popupWindow.setBackgroundDrawable(new BitmapDrawable(viewposition.getContext().getResources(), ""));
        ((Tooltip.TooltipView) (viewposition)).popupWindow.setOutsideTouchable(((Tooltip.TooltipView) (viewposition)).dismissWhenTouchedOutside);
        ((Tooltip.TooltipView) (viewposition)).popupWindow.setTouchInterceptor(new Tooltip.TooltipView..Lambda._cls0(viewposition));
        ((Tooltip.TooltipView) (viewposition)).popupWindow.showAtLocation(((Tooltip.TooltipView) (viewposition)).anchorView, 0, 0, 0);
        return;
_L11:
        Tooltip.TooltipView tooltipview1 = tooltip2.tooltipView;
        View view = tooltip2.anchorView;
        int j;
        if (viewposition == TooltipModel.Placement.ABOVE || viewposition == TooltipModel.Placement.BELOW)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            j = 1;
        } else
        {
            int i1 = Tooltip.toInternal$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5TQMIBR9DLO6OBRKDTNMOT39E0NL8RRFDHQ6IS2DDTI6AR14A1M62OR5DLIMST1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEP99HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0(viewposition, view);
            int l = tooltipview1.getWidth();
            j = l;
            if (l == 0)
            {
                j = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                tooltipview1.measure(j, j);
                j = tooltipview1.getMeasuredWidth();
            }
            l = tooltipview1.getResources().getDisplayMetrics().widthPixels;
            if (i1 == android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                if (j < rect.left)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
            } else
            if (j < l - rect.width() - rect.left)
            {
                j = 1;
            } else
            {
                j = 0;
            }
        }
        if (j == 0)
        {
            if (viewposition == TooltipModel.Placement.START)
            {
                viewposition = TooltipModel.Placement.END;
            } else
            {
                viewposition = TooltipModel.Placement.START;
            }
            tooltip2.tooltipView.setAnchor(tooltip2.anchorView, rect, viewposition, alignment);
        }
        continue; /* Loop/switch isn't completed */
_L9:
        Tooltip tooltip1 = tooltip;
        viewposition = addAnchorViewTrackerOffset(viewposition.viewRect);
        tooltip1.tooltipView.anchorRect.set(viewposition);
        tooltip1.tooltipView.requestLayout();
        return;
        if (true) goto _L13; else goto _L12
_L12:
        if (true) goto _L1; else goto _L14
_L14:
    }
}
