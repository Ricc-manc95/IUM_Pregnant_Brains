// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.google.android.calendar.utils.ColorUtils;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipViewModel

final class SolidChipBackgroundDrawable extends Drawable
{

    private float adjustedCornerRadius;
    private float adjustedOuterCornerRadius;
    private final Paint borderPaint = new Paint();
    private int cornerRadius;
    private final float dragHandlePadding;
    private final Paint dragHandlePaint = new Paint();
    private final float dragHandleSpacing;
    private boolean dragHandles;
    private final Paint fillPaint = new Paint();
    private float halfBorderWidth;
    private final Paint outerBorderPaint;
    private final RectF recycleRect = new RectF();

    SolidChipBackgroundDrawable(Resources resources)
    {
        halfBorderWidth = 0.5F * resources.getDimension(0x7f0e0086);
        dragHandlePaint.setStrokeWidth(resources.getDimension(0x7f0e0091));
        dragHandlePaint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        dragHandlePaint.setAntiAlias(true);
        dragHandleSpacing = resources.getDimension(0x7f0e0090);
        dragHandlePadding = resources.getDimension(0x7f0e008f);
        fillPaint.setAntiAlias(true);
        fillPaint.setStyle(android.graphics.Paint.Style.FILL);
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(android.graphics.Paint.Style.STROKE);
        borderPaint.setStrokeWidth(2.0F * halfBorderWidth);
        outerBorderPaint = new Paint(borderPaint);
    }

    final void configure(ChipViewModel chipviewmodel)
    {
        if (chipviewmodel != null)
        {
            int i;
            if (chipviewmodel.getColorStyle() == ChipViewModel.ColorStyle.LIGHT)
            {
                i = ColorUtils.blend(chipviewmodel.getBackgroundColor(), -1, 0.88F);
            } else
            {
                i = chipviewmodel.getBackgroundColor();
            }
            setColor(i, chipviewmodel.getBorderColor(), chipviewmodel.getOuterBorderColor());
            dragHandles = chipviewmodel.getDragHandles().booleanValue();
            dragHandlePaint.setColor(chipviewmodel.getBorderColor());
            cornerRadius = chipviewmodel.getCornerRadius();
            if (chipviewmodel.getBorderWidth() != null)
            {
                halfBorderWidth = 0.5F * chipviewmodel.getBorderWidth().floatValue();
                borderPaint.setStrokeWidth(chipviewmodel.getBorderWidth().floatValue());
            }
            adjustedCornerRadius = (float)cornerRadius - halfBorderWidth;
            adjustedOuterCornerRadius = (float)cornerRadius + halfBorderWidth;
        }
    }

    public final void draw(Canvas canvas)
    {
        recycleRect.set(getBounds());
        canvas.drawRoundRect(recycleRect, cornerRadius, cornerRadius, fillPaint);
        if (borderPaint.getAlpha() > 0)
        {
            recycleRect.inset(halfBorderWidth, halfBorderWidth);
            canvas.drawRoundRect(recycleRect, adjustedCornerRadius, adjustedCornerRadius, borderPaint);
            recycleRect.inset(-halfBorderWidth, -halfBorderWidth);
        }
        if (outerBorderPaint.getAlpha() > 0)
        {
            recycleRect.inset(-halfBorderWidth, -halfBorderWidth);
            canvas.drawRoundRect(recycleRect, adjustedOuterCornerRadius, adjustedOuterCornerRadius, outerBorderPaint);
        }
        if (dragHandles)
        {
            for (int i = 1; i <= 2; i++)
            {
                float f = dragHandlePadding;
                float f1 = dragHandleSpacing;
                float f2 = i;
                float f3 = dragHandleSpacing;
                canvas.drawLine(f, f2 * f1, (float)i * f3, dragHandlePadding, dragHandlePaint);
                canvas.drawLine(recycleRect.right - dragHandlePadding, recycleRect.bottom - dragHandleSpacing * (float)i, recycleRect.right - dragHandleSpacing * (float)i, recycleRect.bottom - dragHandlePadding, dragHandlePaint);
            }

        }
    }

    public final int getAlpha()
    {
        return fillPaint.getAlpha();
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final void getOutline(Outline outline)
    {
        outline.setRoundRect(getBounds(), cornerRadius);
    }

    public final void setAlpha(int i)
    {
        if (i != getAlpha())
        {
            fillPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    final void setColor(int i, int j, int k)
    {
        if (i != fillPaint.getColor() || j != borderPaint.getColor() || k != outerBorderPaint.getColor())
        {
            fillPaint.setColor(i);
            borderPaint.setColor(j);
            outerBorderPaint.setColor(k);
            invalidateSelf();
        }
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
    }
}
