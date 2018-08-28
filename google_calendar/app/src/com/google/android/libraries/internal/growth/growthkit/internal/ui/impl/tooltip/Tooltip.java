// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.PopupWindow;

final class Tooltip
{

    public final TooltipModel.Alignment alignment;
    public final View anchorView;
    public final TooltipModel.Placement placement;
    public final TooltipView tooltipView;

    Tooltip(View view, View view1, TooltipModel.Placement placement1, TooltipModel.Alignment alignment1)
    {
        if (view == null)
        {
            throw new NullPointerException();
        }
        if (view1 == null)
        {
            throw new NullPointerException();
        } else
        {
            anchorView = (View)view1;
            placement = placement1;
            alignment = alignment1;
            tooltipView = new TooltipView(view.getContext());
            view1 = tooltipView;
            view1.wrappedView = view;
            view1.popupWindow = new PopupWindow(view1);
            view1.addView(view);
            return;
        }
    }

    static int toInternal$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5TQMIBR9DLO6OBRKDTNMOT39E0NL8RRFDHQ6IS2DDTI6AR14A1M62OR5DLIMST1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEP99HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0(TooltipModel.Placement placement1, View view)
    {
        boolean flag = true;
        if (ViewCompat.getLayoutDirection(view) != 1)
        {
            flag = false;
        }
        switch (placement1.ordinal())
        {
        default:
            throw new IllegalArgumentException();

        case 2: // '\002'
            if (!flag)
            {
                return android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
            } else
            {
                return android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
            }

        case 0: // '\0'
            return android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;

        case 3: // '\003'
            if (!flag)
            {
                return android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
            } else
            {
                return android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
            }

        case 1: // '\001'
            return android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
        }
    }

    private class TooltipView extends ViewGroup
    {

        private int absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
        private TooltipModel.Alignment alignment;
        public final Rect anchorRect = new Rect();
        public View anchorView;
        private final int arrowBaseWidthPx;
        private final int arrowLengthPx;
        private final Path arrowPath = new Path();
        private final Paint backgroundPaint = new Paint();
        private final RectF containerBounds = new RectF();
        private final int containerCornerRadiusPx;
        private final int containerShadowOffsetPx;
        private final int containerShadowRadiusPx;
        private final int dimensionHelper[] = new int[2];
        public boolean dismissWhenTouchedOutside;
        private final Point displaySizeHelper = new Point();
        private boolean hasSetAnchor;
        private final int marginPx;
        private final int paddingPx;
        private TooltipModel.Placement placement;
        public PopupWindow popupWindow;
        private int popupWindowX;
        private int popupWindowY;
        private float suggestedMaxWidthPercentage;
        public android.view.View.OnClickListener targetViewOnClickListener;
        public android.view.View.OnClickListener userClickedListener;
        public View wrappedView;

        private final void drawArrow(Canvas canvas)
        {
            canvas.save();
            int i = absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
            if (i == android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 || i == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (!i) goto _L2; else goto _L1
_L1:
            canvas.translate(marginPx - popupWindowX, 0.0F);
_L4:
            canvas.drawPath(arrowPath, backgroundPaint);
            canvas.restore();
            return;
_L2:
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 || absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                canvas.translate(0.0F, marginPx - popupWindowY);
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        protected final void onDraw(Canvas canvas)
        {
            canvas.save();
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 || absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                drawArrow(canvas);
            }
            canvas.drawRoundRect(containerBounds, containerCornerRadiusPx, containerCornerRadiusPx, backgroundPaint);
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 || absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                drawArrow(canvas);
            }
            canvas.restore();
        }

        protected final void onLayout(boolean flag, int i, int j, int k, int l)
        {
            int i1;
            int j1;
            int k1;
            int l1;
            Object obj = wrappedView;
            int i2 = paddingPx;
            int j2;
            int k2;
            int l2;
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                i1 = arrowLengthPx;
            } else
            {
                i1 = 0;
            }
            j2 = paddingPx;
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                j1 = arrowLengthPx;
            } else
            {
                j1 = 0;
            }
            k2 = paddingPx;
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                k1 = arrowLengthPx;
            } else
            {
                k1 = 0;
            }
            l2 = paddingPx;
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                l1 = arrowLengthPx;
            } else
            {
                l1 = 0;
            }
            ((View) (obj)).layout(i2 + i1, j2 + j1, k - i - k2 - k1, l - j - l2 - l1);
            obj = displaySizeHelper;
            ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay().getSize(((Point) (obj)));
            l1 = displaySizeHelper.x;
            i1 = displaySizeHelper.y;
            j1 = getMeasuredWidth();
            k1 = getMeasuredHeight();
            j = 0;
            i = 0;
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                i = -k1 - marginPx;
            } else
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                i = anchorRect.height() + marginPx;
            } else
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                j = -j1 - marginPx;
                i = (anchorRect.height() - k1) / 2;
            } else
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                i = anchorRect.width();
                j = marginPx + i;
                i = (anchorRect.height() - k1) / 2;
            }
            if (ViewCompat.getLayoutDirection(this) == 1)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            l = absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
            if (l == android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 || l == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            if (l == 0) goto _L2; else goto _L1
_L1:
            j = i + anchorRect.top;
            alignment.ordinal();
            JVM INSTR tableswitch 0 2: default 296
        //                       0 442
        //                       1 595
        //                       2 619;
               goto _L3 _L4 _L5 _L6
_L3:
            throw new IllegalStateException();
_L4:
            if (k != 0)
            {
                i = (anchorRect.left + anchorRect.width()) - j1;
            } else
            {
                i = anchorRect.left;
            }
_L11:
            k = marginPx;
            popupWindowX = Math.min(l1 - marginPx - j1, Math.max(k, i));
            i = marginPx;
            popupWindowY = Math.min(i1 - marginPx - k1, Math.max(i, j));
            popupWindow.update(popupWindowX, popupWindowY, j1, k1, true);
            alignment.ordinal();
            JVM INSTR tableswitch 0 2: default 576
        //                       0 684
        //                       1 835
        //                       2 848;
               goto _L7 _L8 _L9 _L10
_L7:
            throw new IllegalStateException();
_L5:
            i = anchorRect.left + (anchorRect.width() - j1) / 2;
              goto _L11
_L6:
            if (k != 0)
            {
                i = anchorRect.left;
            } else
            {
                i = (anchorRect.left + anchorRect.width()) - j1;
            }
              goto _L11
_L2:
            k = j + anchorRect.left;
            j = i + anchorRect.top;
            i = k;
              goto _L11
_L8:
            i = arrowBaseWidthPx / 2 + (marginPx << 1);
_L17:
            j = i;
            if (ViewCompat.getLayoutDirection(this) == 1)
            {
                j = anchorRect.width() - i;
            }
            i = j + anchorRect.left;
            arrowPath.reset();
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 != android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0) goto _L13; else goto _L12
_L12:
            arrowPath.moveTo(i - marginPx - arrowBaseWidthPx / 2, containerBounds.bottom);
            arrowPath.rLineTo(arrowBaseWidthPx, 0.0F);
            arrowPath.rLineTo(-arrowBaseWidthPx / 2, arrowLengthPx);
            arrowPath.rLineTo(-arrowBaseWidthPx / 2, -arrowLengthPx);
            arrowPath.close();
_L15:
            return;
_L9:
            i = anchorRect.width() / 2;
            continue; /* Loop/switch isn't completed */
_L10:
            i = anchorRect.width() - arrowBaseWidthPx / 2 - (marginPx << 1);
            continue; /* Loop/switch isn't completed */
_L13:
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                arrowPath.moveTo((i - marginPx) + arrowBaseWidthPx / 2, containerBounds.top);
                arrowPath.rLineTo(-arrowBaseWidthPx, 0.0F);
                arrowPath.rLineTo(arrowBaseWidthPx / 2, -arrowLengthPx);
                arrowPath.rLineTo(arrowBaseWidthPx / 2, arrowLengthPx);
                arrowPath.close();
                return;
            }
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
            {
                arrowPath.moveTo(containerBounds.right, (anchorRect.centerY() - arrowBaseWidthPx) + marginPx / 2);
                arrowPath.rLineTo(arrowLengthPx, arrowBaseWidthPx / 2);
                arrowPath.rLineTo(-arrowLengthPx, arrowBaseWidthPx / 2);
                arrowPath.rLineTo(0.0F, -arrowBaseWidthPx);
                arrowPath.close();
                return;
            }
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 != android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0) goto _L15; else goto _L14
_L14:
            arrowPath.moveTo(containerBounds.left, (anchorRect.centerY() - arrowBaseWidthPx) + marginPx / 2);
            arrowPath.rLineTo(0.0F, arrowBaseWidthPx);
            arrowPath.rLineTo(-arrowLengthPx, -arrowBaseWidthPx / 2);
            arrowPath.rLineTo(arrowLengthPx, -arrowBaseWidthPx / 2);
            arrowPath.close();
            return;
            if (true) goto _L17; else goto _L16
_L16:
        }

        protected final void onMeasure(int i, int j)
        {
            float f1;
            int ai[];
            boolean flag;
            f1 = 0.0F;
            flag = false;
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.NOT_SET$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 && hasSetAnchor)
            {
                absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 = Tooltip.toInternal$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5TQMIBR9DLO6OBRKDTNMOT39E0NL8RRFDHQ6IS2DDTI6AR14A1M62OR5DLIMST1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEP99HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0(placement, this);
            }
            ai = dimensionHelper;
            Point point = displaySizeHelper;
            ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
            j = displaySizeHelper.x;
            i = displaySizeHelper.y;
            absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 - 1;
            JVM INSTR tableswitch 0 3: default 120
        //                       0 128
        //                       1 550
        //                       2 585
        //                       3 610;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            throw new IllegalStateException();
_L2:
            j -= marginPx << 1;
            i = anchorRect.top - marginPx;
_L8:
            int k;
            int l;
label0:
            {
                ai[0] = j;
                ai[1] = i;
                j = dimensionHelper[0];
                i = dimensionHelper[1];
                j = j - (paddingPx << 1) - containerShadowOffsetPx;
                i = i - (paddingPx << 1) - containerShadowOffsetPx;
                k = absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
                float f;
                float f2;
                Object obj;
                int i1;
                if (k == android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 || k == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0)
                {
                    k = arrowLengthPx;
                    i -= k;
                } else
                if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 || absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
                {
                    j -= arrowLengthPx;
                }
                obj = displaySizeHelper;
                ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay().getSize(((Point) (obj)));
                k = Math.min((int)((float)displaySizeHelper.x * suggestedMaxWidthPercentage), j);
                wrappedView.measure(android.view.View.MeasureSpec.makeMeasureSpec(k, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(i, 0));
                if (wrappedView.getMeasuredHeight() > i)
                {
                    wrappedView.measure(android.view.View.MeasureSpec.makeMeasureSpec(j, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(i, 0x80000000));
                }
                l = wrappedView.getMeasuredWidth();
                i1 = paddingPx;
                j = wrappedView.getMeasuredHeight();
                k = paddingPx;
                obj = containerBounds;
                if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
                {
                    f = arrowLengthPx;
                } else
                {
                    f = 0.0F;
                }
                if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
                {
                    f1 = arrowLengthPx;
                }
                if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
                {
                    i = arrowLengthPx;
                } else
                {
                    i = 0;
                }
                f2 = i + (l + (i1 << 1));
                if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
                {
                    i = arrowLengthPx;
                } else
                {
                    i = 0;
                }
                ((RectF) (obj)).set(f, f1, f2, i + (j + (k << 1)));
                i = (int)containerBounds.width();
                k = containerShadowOffsetPx + i;
                l = (int)containerBounds.height() + containerShadowOffsetPx;
                j = absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
                if (j != android.support.v4.content.ModernAsyncTask.Status.ABOVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
                {
                    i = ((flag) ? 1 : 0);
                    if (j != android.support.v4.content.ModernAsyncTask.Status.BELOW$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0)
                    {
                        break label0;
                    }
                }
                i = 1;
            }
            if (i == 0) goto _L7; else goto _L6
_L6:
            i = l + arrowLengthPx;
            j = k;
_L11:
            setMeasuredDimension(j, i);
            return;
_L3:
            j -= marginPx << 1;
            i = i - anchorRect.top - anchorRect.height() - marginPx;
              goto _L8
_L4:
            j = anchorRect.left - marginPx;
            i -= marginPx << 1;
              goto _L8
_L5:
            j = j - anchorRect.left - anchorRect.width() - marginPx;
            i -= marginPx << 1;
              goto _L8
_L7:
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 == android.support.v4.content.ModernAsyncTask.Status.TO_LEFT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0) goto _L10; else goto _L9
_L9:
            i = l;
            j = k;
            if (absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 != android.support.v4.content.ModernAsyncTask.Status.TO_RIGHT_OF$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0) goto _L11; else goto _L10
_L10:
            j = k + arrowLengthPx;
            i = l;
              goto _L11
        }

        public final void setAnchor(View view, Rect rect, TooltipModel.Placement placement1, TooltipModel.Alignment alignment1)
        {
            anchorView = view;
            anchorRect.set(rect);
            placement = placement1;
            absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 = android.support.v4.content.ModernAsyncTask.Status.NOT_SET$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
            alignment = alignment1;
            hasSetAnchor = true;
        }

        public final void setContainerBackgroundColor(int i)
        {
            backgroundPaint.setColor(i);
            setLayerType(1, backgroundPaint);
        }

        public final void setSuggestedMaxWidthPercentage(float f)
        {
            suggestedMaxWidthPercentage = f;
            if (isShown())
            {
                requestLayout();
            }
        }

        TooltipView(Context context)
        {
            super(context);
            absolutePlacement$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0 = android.support.v4.content.ModernAsyncTask.Status.NOT_SET$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TKMST35E9N62R1FELKIUQBDE1M2UT3FDTM78QBG5TA6URRCEHKN0921C9PMUR3LEHIL0R31CDIMQPBEEGTG____0;
            suggestedMaxWidthPercentage = 1.0F;
            popupWindowX = 0;
            popupWindowY = 0;
            setWillNotDraw(false);
            DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
            context = context.obtainStyledAttributes(R.styleable.GrowthKitTooltip);
            paddingPx = context.getDimensionPixelSize(R.styleable.GrowthKitTooltip_containerPadding, (int)((double)((float)9 * displaymetrics.density) + 0.5D));
            marginPx = context.getDimensionPixelSize(R.styleable.GrowthKitTooltip_containerMargin, (int)((double)((float)8 * displaymetrics.density) + 0.5D));
            containerShadowOffsetPx = context.getDimensionPixelSize(R.styleable.GrowthKitTooltip_containerShadowOffset, (int)((double)((float)1 * displaymetrics.density) + 0.5D));
            containerShadowRadiusPx = context.getDimensionPixelSize(R.styleable.GrowthKitTooltip_containerShadowRadius, (int)((double)((float)1 * displaymetrics.density) + 0.5D));
            arrowLengthPx = context.getDimensionPixelSize(R.styleable.GrowthKitTooltip_arrowLength, (int)((double)((float)10 * displaymetrics.density) + 0.5D));
            arrowBaseWidthPx = context.getDimensionPixelSize(R.styleable.GrowthKitTooltip_arrowBaseWidth, (int)((double)((float)20 * displaymetrics.density) + 0.5D));
            int i = R.styleable.GrowthKitTooltip_containerCornerRadius;
            float f = 4;
            containerCornerRadiusPx = context.getDimensionPixelSize(i, (int)((double)(displaymetrics.density * f) + 0.5D));
            i = context.getColor(R.styleable.GrowthKitTooltip_containerBackgroundColor, 0xff4285f4);
            int j = context.getColor(R.styleable.GrowthKitTooltip_containerShadowColor, 0x40000000);
            context.recycle();
            backgroundPaint.setStyle(android.graphics.Paint.Style.FILL);
            backgroundPaint.setShadowLayer(containerShadowRadiusPx, containerShadowOffsetPx, containerShadowOffsetPx, j);
            setContainerBackgroundColor(i);
            dismissWhenTouchedOutside = true;
        }
    }

}
