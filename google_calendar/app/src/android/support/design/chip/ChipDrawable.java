// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.design.resources.TextAppearance;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v4.text.BidiFormatter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public final class ChipDrawable extends Drawable
    implements android.graphics.drawable.Drawable.Callback, TintAwareDrawable
{

    private static final int DEFAULT_STATE[] = {
        0x101009e
    };
    private int alpha;
    public boolean checkable;
    public Drawable checkedIcon;
    public boolean checkedIconVisible;
    public ColorStateList chipBackgroundColor;
    public float chipCornerRadius;
    public float chipEndPadding;
    public Drawable chipIcon;
    public float chipIconSize;
    private ColorStateList chipIconTint;
    public boolean chipIconVisible;
    public float chipMinHeight;
    public final Paint chipPaint = new Paint(1);
    public float chipStartPadding;
    public ColorStateList chipStrokeColor;
    public float chipStrokeWidth;
    public Drawable closeIcon;
    public float closeIconEndPadding;
    public float closeIconSize;
    public float closeIconStartPadding;
    private int closeIconStateSet[];
    private ColorStateList closeIconTint;
    public boolean closeIconVisible;
    private ColorFilter colorFilter;
    public ColorStateList compatRippleColor;
    public final Context context;
    public boolean currentChecked;
    private int currentChipBackgroundColor;
    private int currentChipStrokeColor;
    private int currentCompatRippleColor;
    private int currentTextColor;
    private int currentTint;
    private final Paint debugPaint = null;
    public WeakReference _flddelegate;
    private final android.support.v4.content.res.ResourcesCompat.FontCallback fontCallback = new _cls1();
    private final android.graphics.Paint.FontMetrics fontMetrics = new android.graphics.Paint.FontMetrics();
    public float iconEndPadding;
    public float iconStartPadding;
    public int maxWidth;
    private final PointF pointF = new PointF();
    public CharSequence rawText;
    private final RectF rectF = new RectF();
    public ColorStateList rippleColor;
    public boolean shouldDrawText;
    public TextAppearance textAppearance;
    public float textEndPadding;
    private final TextPaint textPaint = new TextPaint(1);
    public float textStartPadding;
    private float textWidth;
    public boolean textWidthDirty;
    private ColorStateList tint;
    private PorterDuffColorFilter tintFilter;
    private android.graphics.PorterDuff.Mode tintMode;
    public android.text.TextUtils.TruncateAt truncateAt;
    private CharSequence unicodeWrappedText;
    public boolean useCompatRipple;

    ChipDrawable(Context context1)
    {
        alpha = 255;
        tintMode = android.graphics.PorterDuff.Mode.SRC_IN;
        _flddelegate = new WeakReference(null);
        textWidthDirty = true;
        context = context1;
        rawText = "";
        textPaint.density = context1.getResources().getDisplayMetrics().density;
        setState(DEFAULT_STATE);
        setCloseIconState(DEFAULT_STATE);
        shouldDrawText = true;
    }

    private final void applyChildDrawable(Drawable drawable)
    {
        if (drawable != null)
        {
            drawable.setCallback(this);
            DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == closeIcon)
            {
                if (drawable.isStateful())
                {
                    drawable.setState(closeIconStateSet);
                }
                drawable.setTintList(closeIconTint);
            } else
            if (drawable.isStateful())
            {
                drawable.setState(getState());
                return;
            }
        }
    }

    private final void calculateChipIconBounds(Rect rect, RectF rectf)
    {
label0:
        {
            boolean flag1 = true;
            rectf.setEmpty();
            float f;
            boolean flag;
            if (chipIconVisible && chipIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if (checkedIconVisible && checkedIcon != null && currentChecked)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            f = chipStartPadding + iconStartPadding;
            if (DrawableCompat.getLayoutDirection(this) == 0)
            {
                rectf.left = f + (float)rect.left;
                rectf.right = rectf.left + chipIconSize;
            } else
            {
                rectf.right = (float)rect.right - f;
                rectf.left = rectf.right - chipIconSize;
            }
            rectf.top = rect.exactCenterY() - chipIconSize / 2.0F;
            rectf.bottom = rectf.top + chipIconSize;
        }
    }

    private final float calculateCloseIconWidth()
    {
        boolean flag;
        if (closeIconVisible && closeIcon != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return closeIconStartPadding + closeIconSize + closeIconEndPadding;
        } else
        {
            return 0.0F;
        }
    }

    private final float getTextWidth()
    {
        if (!textWidthDirty)
        {
            return textWidth;
        }
        CharSequence charsequence = unicodeWrappedText;
        float f;
        if (charsequence == null)
        {
            f = 0.0F;
        } else
        {
            f = textPaint.measureText(charsequence, 0, charsequence.length());
        }
        textWidth = f;
        textWidthDirty = false;
        return textWidth;
    }

    static boolean isStateful(Drawable drawable)
    {
        return drawable != null && drawable.isStateful();
    }

    private final boolean onStateChange(int ai[], int ai1[])
    {
        int i;
        boolean flag1;
        boolean flag = true;
        flag1 = super.onStateChange(ai);
        float f;
        int ai2[];
        int j;
        boolean flag2;
        if (chipBackgroundColor != null)
        {
            i = chipBackgroundColor.getColorForState(ai, currentChipBackgroundColor);
        } else
        {
            i = 0;
        }
        if (currentChipBackgroundColor != i)
        {
            currentChipBackgroundColor = i;
            flag1 = true;
        }
        if (chipStrokeColor != null)
        {
            i = chipStrokeColor.getColorForState(ai, currentChipStrokeColor);
        } else
        {
            i = 0;
        }
        if (currentChipStrokeColor != i)
        {
            currentChipStrokeColor = i;
            flag1 = true;
        }
        if (compatRippleColor != null)
        {
            i = compatRippleColor.getColorForState(ai, currentCompatRippleColor);
        } else
        {
            i = 0;
        }
        flag2 = flag1;
        if (currentCompatRippleColor != i)
        {
            currentCompatRippleColor = i;
            flag2 = flag1;
            if (useCompatRipple)
            {
                flag2 = true;
            }
        }
        if (textAppearance != null && textAppearance.textColor != null)
        {
            i = textAppearance.textColor.getColorForState(ai, currentTextColor);
        } else
        {
            i = 0;
        }
        flag1 = flag2;
        if (currentTextColor != i)
        {
            currentTextColor = i;
            flag1 = true;
        }
        ai2 = getState();
        if (ai2 == null)
        {
            break MISSING_BLOCK_LABEL_570;
        }
        j = ai2.length;
        i = 0;
_L3:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_570;
        }
        if (ai2[i] != 0x10100a0) goto _L2; else goto _L1
_L1:
        i = 1;
_L4:
        boolean flag3;
        if (i != 0 && checkable)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (currentChecked != flag3 && checkedIcon != null)
        {
            f = calculateChipIconWidth();
            currentChecked = flag3;
            Object obj;
            android.graphics.PorterDuff.Mode mode;
            int k;
            if (f != calculateChipIconWidth())
            {
                i = 1;
                flag1 = true;
            } else
            {
                i = 0;
                flag1 = true;
            }
        } else
        {
            i = 0;
        }
        if (tint != null)
        {
            k = tint.getColorForState(ai, currentTint);
        } else
        {
            k = 0;
        }
        flag3 = flag1;
        if (currentTint != k)
        {
            currentTint = k;
            obj = tint;
            mode = tintMode;
            if (obj == null || mode == null)
            {
                obj = null;
            } else
            {
                obj = new PorterDuffColorFilter(((ColorStateList) (obj)).getColorForState(getState(), 0), mode);
            }
            tintFilter = ((PorterDuffColorFilter) (obj));
            flag3 = true;
        }
        obj = chipIcon;
        if (obj != null && ((Drawable) (obj)).isStateful())
        {
            k = 1;
        } else
        {
            k = 0;
        }
        flag1 = flag3;
        if (k != 0)
        {
            flag1 = flag3 | chipIcon.setState(ai);
        }
        obj = checkedIcon;
        if (obj != null && ((Drawable) (obj)).isStateful())
        {
            k = 1;
        } else
        {
            k = 0;
        }
        flag3 = flag1;
        if (k != 0)
        {
            flag3 = flag1 | checkedIcon.setState(ai);
        }
        ai = closeIcon;
        if (ai != null && ai.isStateful())
        {
            k = ((flag) ? 1 : 0);
        } else
        {
            k = 0;
        }
        flag1 = flag3;
        if (k != 0)
        {
            flag1 = flag3 | closeIcon.setState(ai1);
        }
        if (flag1)
        {
            invalidateSelf();
        }
        if (i != 0)
        {
            ai = (Delegate)_flddelegate.get();
            if (ai != null)
            {
                ai.onChipDrawableSizeChange();
            }
        }
        return flag1;
_L2:
        i++;
          goto _L3
        i = 0;
          goto _L4
    }

    final float calculateChipIconWidth()
    {
label0:
        {
            boolean flag1 = true;
            boolean flag;
            if (chipIconVisible && chipIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if (checkedIconVisible && checkedIcon != null && currentChecked)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            return iconStartPadding + chipIconSize + iconEndPadding;
        }
        return 0.0F;
    }

    public final void draw(Canvas canvas)
    {
        Rect rect = getBounds();
        if (!rect.isEmpty() && getAlpha() != 0)
        {
            float f;
            float f1;
            Object obj;
            int i;
            boolean flag;
            if (alpha < 255)
            {
                f = rect.left;
                f1 = rect.top;
                float f2 = rect.right;
                float f3 = rect.bottom;
                i = alpha;
                Paint paint;
                int j;
                if (android.os.Build.VERSION.SDK_INT > 21)
                {
                    i = canvas.saveLayerAlpha(f, f1, f2, f3, i);
                } else
                {
                    i = canvas.saveLayerAlpha(f, f1, f2, f3, i, 31);
                }
            } else
            {
                i = 0;
            }
            chipPaint.setColor(currentChipBackgroundColor);
            chipPaint.setStyle(android.graphics.Paint.Style.FILL);
            paint = chipPaint;
            if (colorFilter != null)
            {
                obj = colorFilter;
            } else
            {
                obj = tintFilter;
            }
            paint.setColorFilter(((ColorFilter) (obj)));
            rectF.set(rect);
            canvas.drawRoundRect(rectF, chipCornerRadius, chipCornerRadius, chipPaint);
            if (chipStrokeWidth > 0.0F)
            {
                chipPaint.setColor(currentChipStrokeColor);
                chipPaint.setStyle(android.graphics.Paint.Style.STROKE);
                paint = chipPaint;
                if (colorFilter != null)
                {
                    obj = colorFilter;
                } else
                {
                    obj = tintFilter;
                }
                paint.setColorFilter(((ColorFilter) (obj)));
                rectF.set((float)rect.left + chipStrokeWidth / 2.0F, (float)rect.top + chipStrokeWidth / 2.0F, (float)rect.right - chipStrokeWidth / 2.0F, (float)rect.bottom - chipStrokeWidth / 2.0F);
                f = chipCornerRadius - chipStrokeWidth / 2.0F;
                canvas.drawRoundRect(rectF, f, f, chipPaint);
            }
            chipPaint.setColor(currentCompatRippleColor);
            chipPaint.setStyle(android.graphics.Paint.Style.FILL);
            rectF.set(rect);
            canvas.drawRoundRect(rectF, chipCornerRadius, chipCornerRadius, chipPaint);
            if (chipIconVisible && chipIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                calculateChipIconBounds(rect, rectF);
                f = rectF.left;
                f1 = rectF.top;
                canvas.translate(f, f1);
                chipIcon.setBounds(0, 0, (int)rectF.width(), (int)rectF.height());
                chipIcon.draw(canvas);
                canvas.translate(-f, -f1);
            }
            if (checkedIconVisible && checkedIcon != null && currentChecked)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                calculateChipIconBounds(rect, rectF);
                f = rectF.left;
                f1 = rectF.top;
                canvas.translate(f, f1);
                checkedIcon.setBounds(0, 0, (int)rectF.width(), (int)rectF.height());
                checkedIcon.draw(canvas);
                canvas.translate(-f, -f1);
            }
            if (shouldDrawText && unicodeWrappedText != null)
            {
                Object obj1 = pointF;
                ((PointF) (obj1)).set(0.0F, 0.0F);
                obj = android.graphics.Paint.Align.LEFT;
                if (unicodeWrappedText != null)
                {
                    f = chipStartPadding + calculateChipIconWidth() + textStartPadding;
                    if (DrawableCompat.getLayoutDirection(this) == 0)
                    {
                        obj1.x = f + (float)rect.left;
                        obj = android.graphics.Paint.Align.LEFT;
                    } else
                    {
                        obj1.x = (float)rect.right - f;
                        obj = android.graphics.Paint.Align.RIGHT;
                    }
                    f = rect.centerY();
                    textPaint.getFontMetrics(fontMetrics);
                    obj1.y = f - (fontMetrics.descent + fontMetrics.ascent) / 2.0F;
                }
                obj1 = rectF;
                ((RectF) (obj1)).setEmpty();
                if (unicodeWrappedText != null)
                {
                    f = chipStartPadding + calculateChipIconWidth() + textStartPadding;
                    f1 = chipEndPadding + calculateCloseIconWidth() + textEndPadding;
                    if (DrawableCompat.getLayoutDirection(this) == 0)
                    {
                        obj1.left = f + (float)rect.left;
                        obj1.right = (float)rect.right - f1;
                    } else
                    {
                        obj1.left = f1 + (float)rect.left;
                        obj1.right = (float)rect.right - f;
                    }
                    obj1.top = rect.top;
                    obj1.bottom = rect.bottom;
                }
                if (textAppearance != null)
                {
                    textPaint.drawableState = getState();
                    textAppearance.updateDrawState(context, textPaint, fontCallback);
                }
                textPaint.setTextAlign(((android.graphics.Paint.Align) (obj)));
                if (Math.round(getTextWidth()) > Math.round(rectF.width()))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                j = 0;
                if (flag)
                {
                    j = canvas.save();
                    canvas.clipRect(rectF);
                }
                obj1 = unicodeWrappedText;
                obj = obj1;
                if (flag)
                {
                    obj = obj1;
                    if (truncateAt != null)
                    {
                        obj = TextUtils.ellipsize(unicodeWrappedText, textPaint, rectF.width(), truncateAt);
                    }
                }
                canvas.drawText(((CharSequence) (obj)), 0, ((CharSequence) (obj)).length(), pointF.x, pointF.y, textPaint);
                if (flag)
                {
                    canvas.restoreToCount(j);
                }
            }
            if (closeIconVisible && closeIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                obj = rectF;
                ((RectF) (obj)).setEmpty();
                if (closeIconVisible && closeIcon != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    f = chipEndPadding + closeIconEndPadding;
                    if (DrawableCompat.getLayoutDirection(this) == 0)
                    {
                        obj.right = (float)rect.right - f;
                        obj.left = ((RectF) (obj)).right - closeIconSize;
                    } else
                    {
                        obj.left = f + (float)rect.left;
                        obj.right = ((RectF) (obj)).left + closeIconSize;
                    }
                    obj.top = rect.exactCenterY() - closeIconSize / 2.0F;
                    obj.bottom = ((RectF) (obj)).top + closeIconSize;
                }
                f = rectF.left;
                f1 = rectF.top;
                canvas.translate(f, f1);
                closeIcon.setBounds(0, 0, (int)rectF.width(), (int)rectF.height());
                closeIcon.draw(canvas);
                canvas.translate(-f, -f1);
            }
            if (alpha < 255)
            {
                canvas.restoreToCount(i);
                return;
            }
        }
    }

    public final int getAlpha()
    {
        return alpha;
    }

    public final Drawable getChipIcon()
    {
        if (chipIcon != null)
        {
            Drawable drawable1 = chipIcon;
            Drawable drawable = drawable1;
            if (drawable1 instanceof WrappedDrawable)
            {
                drawable = ((WrappedDrawable)drawable1).getWrappedDrawable();
            }
            return drawable;
        } else
        {
            return null;
        }
    }

    public final Drawable getCloseIcon()
    {
        if (closeIcon != null)
        {
            Drawable drawable1 = closeIcon;
            Drawable drawable = drawable1;
            if (drawable1 instanceof WrappedDrawable)
            {
                drawable = ((WrappedDrawable)drawable1).getWrappedDrawable();
            }
            return drawable;
        } else
        {
            return null;
        }
    }

    public final ColorFilter getColorFilter()
    {
        return colorFilter;
    }

    public final int getIntrinsicHeight()
    {
        return (int)chipMinHeight;
    }

    public final int getIntrinsicWidth()
    {
        float f;
        boolean flag;
        float f1 = 0.0F;
        float f2 = chipStartPadding;
        if (chipIconVisible && chipIcon != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            float f3;
            float f4;
            float f5;
            float f6;
            if (checkedIconVisible && checkedIcon != null && currentChecked)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_176;
            }
        }
        f = iconStartPadding + chipIconSize + iconEndPadding;
_L1:
        f3 = textStartPadding;
        f4 = getTextWidth();
        f5 = textEndPadding;
        if (closeIconVisible && closeIcon != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            f1 = closeIconStartPadding;
            f6 = closeIconSize;
            f1 = closeIconEndPadding + (f1 + f6);
        }
        return Math.min(Math.round(f5 + (f + f2 + f3 + f4) + f1 + chipEndPadding), maxWidth);
        f = 0.0F;
          goto _L1
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final void getOutline(Outline outline)
    {
        Rect rect = getBounds();
        if (!rect.isEmpty())
        {
            outline.setRoundRect(rect, chipCornerRadius);
        } else
        {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), chipCornerRadius);
        }
        outline.setAlpha((float)getAlpha() / 255F);
    }

    public final void invalidateDrawable(Drawable drawable)
    {
        drawable = getCallback();
        if (drawable != null)
        {
            drawable.invalidateDrawable(this);
        }
    }

    public final boolean isStateful()
    {
label0:
        {
label1:
            {
                boolean flag1 = false;
                ColorStateList colorstatelist = chipBackgroundColor;
                boolean flag;
                if (colorstatelist != null && colorstatelist.isStateful())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label1;
                }
                colorstatelist = chipStrokeColor;
                if (colorstatelist != null && colorstatelist.isStateful())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label1;
                }
                if (useCompatRipple)
                {
                    Object obj = compatRippleColor;
                    if (obj != null && ((ColorStateList) (obj)).isStateful())
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        break label1;
                    }
                }
                obj = textAppearance;
                if (obj != null && ((TextAppearance) (obj)).textColor != null && ((TextAppearance) (obj)).textColor.isStateful())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    if (checkedIconVisible && checkedIcon != null && checkable)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        obj = chipIcon;
                        if (obj != null && ((Drawable) (obj)).isStateful())
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (!flag)
                        {
                            obj = checkedIcon;
                            if (obj != null && ((Drawable) (obj)).isStateful())
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (!flag)
                            {
                                obj = tint;
                                if (obj != null && ((ColorStateList) (obj)).isStateful())
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (!flag)
                                {
                                    break label0;
                                }
                            }
                        }
                    }
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public final boolean onLayoutDirectionChanged(int i)
    {
        boolean flag1 = false;
        boolean flag3 = super.onLayoutDirectionChanged(i);
        boolean flag;
        boolean flag2;
        if (chipIconVisible && chipIcon != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag2 = flag3;
        if (flag)
        {
            flag2 = flag3 | chipIcon.setLayoutDirection(i);
        }
        if (checkedIconVisible && checkedIcon != null && currentChecked)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag3 = flag2;
        if (flag)
        {
            flag3 = flag2 | checkedIcon.setLayoutDirection(i);
        }
        flag = flag1;
        if (closeIconVisible)
        {
            flag = flag1;
            if (closeIcon != null)
            {
                flag = true;
            }
        }
        flag2 = flag3;
        if (flag)
        {
            flag2 = flag3 | closeIcon.setLayoutDirection(i);
        }
        if (flag2)
        {
            invalidateSelf();
        }
        return true;
    }

    protected final boolean onLevelChange(int i)
    {
        boolean flag1 = true;
        boolean flag3 = super.onLevelChange(i);
        boolean flag;
        boolean flag2;
        if (chipIconVisible && chipIcon != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag2 = flag3;
        if (flag)
        {
            flag2 = flag3 | chipIcon.setLevel(i);
        }
        if (checkedIconVisible && checkedIcon != null && currentChecked)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag3 = flag2;
        if (flag)
        {
            flag3 = flag2 | checkedIcon.setLevel(i);
        }
        if (closeIconVisible && closeIcon != null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        flag2 = flag3;
        if (flag)
        {
            flag2 = flag3 | closeIcon.setLevel(i);
        }
        if (flag2)
        {
            invalidateSelf();
        }
        return flag2;
    }

    protected final boolean onStateChange(int ai[])
    {
        return onStateChange(ai, closeIconStateSet);
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        drawable = getCallback();
        if (drawable != null)
        {
            drawable.scheduleDrawable(this, runnable, l);
        }
    }

    public final void setAlpha(int i)
    {
        if (alpha != i)
        {
            alpha = i;
            invalidateSelf();
        }
    }

    public final void setCheckable(boolean flag)
    {
        if (checkable != flag)
        {
            checkable = flag;
            float f = calculateChipIconWidth();
            if (!flag && currentChecked)
            {
                currentChecked = false;
            }
            float f1 = calculateChipIconWidth();
            invalidateSelf();
            if (f != f1)
            {
                Delegate delegate1 = (Delegate)_flddelegate.get();
                if (delegate1 != null)
                {
                    delegate1.onChipDrawableSizeChange();
                }
            }
        }
    }

    public final void setCheckedIcon(Drawable drawable)
    {
        if (checkedIcon != drawable)
        {
            float f = calculateChipIconWidth();
            checkedIcon = drawable;
            float f1 = calculateChipIconWidth();
            drawable = checkedIcon;
            if (drawable != null)
            {
                drawable.setCallback(null);
            }
            applyChildDrawable(checkedIcon);
            invalidateSelf();
            if (f != f1)
            {
                drawable = (Delegate)_flddelegate.get();
                if (drawable != null)
                {
                    drawable.onChipDrawableSizeChange();
                }
            }
        }
    }

    public final void setCheckedIconVisible(boolean flag)
    {
        boolean flag3 = true;
        if (checkedIconVisible == flag) goto _L2; else goto _L1
_L1:
        Delegate delegate1;
        boolean flag1;
        boolean flag2;
        if (checkedIconVisible && checkedIcon != null && currentChecked)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        checkedIconVisible = flag;
        if (checkedIconVisible && checkedIcon != null && currentChecked)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag1 != flag2)
        {
            flag1 = flag3;
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L2; else goto _L3
_L3:
        if (!flag2) goto _L5; else goto _L4
_L4:
        applyChildDrawable(checkedIcon);
_L7:
        invalidateSelf();
        delegate1 = (Delegate)_flddelegate.get();
        if (delegate1 != null)
        {
            delegate1.onChipDrawableSizeChange();
        }
_L2:
        return;
_L5:
        Drawable drawable = checkedIcon;
        if (drawable != null)
        {
            drawable.setCallback(null);
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void setChipIcon(Drawable drawable)
    {
        Drawable drawable1 = getChipIcon();
        if (drawable1 != drawable)
        {
            float f = calculateChipIconWidth();
            float f1;
            boolean flag;
            if (drawable != null)
            {
                Object obj = drawable;
                if (android.os.Build.VERSION.SDK_INT < 23)
                {
                    obj = drawable;
                    if (!(drawable instanceof TintAwareDrawable))
                    {
                        obj = new WrappedDrawableApi21(drawable);
                    }
                }
                drawable = ((Drawable) (obj)).mutate();
            } else
            {
                drawable = null;
            }
            chipIcon = drawable;
            f1 = calculateChipIconWidth();
            if (drawable1 != null)
            {
                drawable1.setCallback(null);
            }
            if (chipIconVisible && chipIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                applyChildDrawable(chipIcon);
            }
            invalidateSelf();
            if (f != f1)
            {
                drawable = (Delegate)_flddelegate.get();
                if (drawable != null)
                {
                    drawable.onChipDrawableSizeChange();
                }
            }
        }
    }

    public final void setChipIconSize(float f)
    {
        if (chipIconSize != f)
        {
            float f1 = calculateChipIconWidth();
            chipIconSize = f;
            f = calculateChipIconWidth();
            invalidateSelf();
            if (f1 != f)
            {
                Delegate delegate1 = (Delegate)_flddelegate.get();
                if (delegate1 != null)
                {
                    delegate1.onChipDrawableSizeChange();
                }
            }
        }
    }

    public final void setChipIconTint(ColorStateList colorstatelist)
    {
        if (chipIconTint != colorstatelist)
        {
            chipIconTint = colorstatelist;
            boolean flag;
            if (chipIconVisible && chipIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                chipIcon.setTintList(colorstatelist);
            }
            onStateChange(getState());
        }
    }

    public final void setChipIconVisible(boolean flag)
    {
        boolean flag3 = true;
        if (chipIconVisible == flag) goto _L2; else goto _L1
_L1:
        Delegate delegate1;
        boolean flag1;
        boolean flag2;
        if (chipIconVisible && chipIcon != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        chipIconVisible = flag;
        if (chipIconVisible && chipIcon != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag1 != flag2)
        {
            flag1 = flag3;
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L2; else goto _L3
_L3:
        if (!flag2) goto _L5; else goto _L4
_L4:
        applyChildDrawable(chipIcon);
_L7:
        invalidateSelf();
        delegate1 = (Delegate)_flddelegate.get();
        if (delegate1 != null)
        {
            delegate1.onChipDrawableSizeChange();
        }
_L2:
        return;
_L5:
        Drawable drawable = chipIcon;
        if (drawable != null)
        {
            drawable.setCallback(null);
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void setCloseIcon(Drawable drawable)
    {
        Drawable drawable1 = getCloseIcon();
        if (drawable1 != drawable)
        {
            float f = calculateCloseIconWidth();
            float f1;
            boolean flag;
            if (drawable != null)
            {
                Object obj = drawable;
                if (android.os.Build.VERSION.SDK_INT < 23)
                {
                    obj = drawable;
                    if (!(drawable instanceof TintAwareDrawable))
                    {
                        obj = new WrappedDrawableApi21(drawable);
                    }
                }
                drawable = ((Drawable) (obj)).mutate();
            } else
            {
                drawable = null;
            }
            closeIcon = drawable;
            f1 = calculateCloseIconWidth();
            if (drawable1 != null)
            {
                drawable1.setCallback(null);
            }
            if (closeIconVisible && closeIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                applyChildDrawable(closeIcon);
            }
            invalidateSelf();
            if (f != f1)
            {
                drawable = (Delegate)_flddelegate.get();
                if (drawable != null)
                {
                    drawable.onChipDrawableSizeChange();
                }
            }
        }
    }

    public final boolean setCloseIconState(int ai[])
    {
        boolean flag2 = false;
        boolean flag1 = flag2;
        if (!Arrays.equals(closeIconStateSet, ai))
        {
            closeIconStateSet = ai;
            boolean flag;
            if (closeIconVisible && closeIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            flag1 = flag2;
            if (flag)
            {
                flag1 = onStateChange(getState(), ai);
            }
        }
        return flag1;
    }

    public final void setCloseIconTint(ColorStateList colorstatelist)
    {
        if (closeIconTint != colorstatelist)
        {
            closeIconTint = colorstatelist;
            boolean flag;
            if (closeIconVisible && closeIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                closeIcon.setTintList(colorstatelist);
            }
            onStateChange(getState());
        }
    }

    public final void setCloseIconVisible(boolean flag)
    {
        boolean flag3 = true;
        if (closeIconVisible == flag) goto _L2; else goto _L1
_L1:
        Delegate delegate1;
        boolean flag1;
        boolean flag2;
        if (closeIconVisible && closeIcon != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        closeIconVisible = flag;
        if (closeIconVisible && closeIcon != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag1 != flag2)
        {
            flag1 = flag3;
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L2; else goto _L3
_L3:
        if (!flag2) goto _L5; else goto _L4
_L4:
        applyChildDrawable(closeIcon);
_L7:
        invalidateSelf();
        delegate1 = (Delegate)_flddelegate.get();
        if (delegate1 != null)
        {
            delegate1.onChipDrawableSizeChange();
        }
_L2:
        return;
_L5:
        Drawable drawable = closeIcon;
        if (drawable != null)
        {
            drawable.setCallback(null);
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        if (colorFilter != colorfilter)
        {
            colorFilter = colorfilter;
            invalidateSelf();
        }
    }

    public final void setIconEndPadding(float f)
    {
        if (iconEndPadding != f)
        {
            float f1 = calculateChipIconWidth();
            iconEndPadding = f;
            f = calculateChipIconWidth();
            invalidateSelf();
            if (f1 != f)
            {
                Delegate delegate1 = (Delegate)_flddelegate.get();
                if (delegate1 != null)
                {
                    delegate1.onChipDrawableSizeChange();
                }
            }
        }
    }

    public final void setIconStartPadding(float f)
    {
        if (iconStartPadding != f)
        {
            float f1 = calculateChipIconWidth();
            iconStartPadding = f;
            f = calculateChipIconWidth();
            invalidateSelf();
            if (f1 != f)
            {
                Delegate delegate1 = (Delegate)_flddelegate.get();
                if (delegate1 != null)
                {
                    delegate1.onChipDrawableSizeChange();
                }
            }
        }
    }

    public final void setText(CharSequence charsequence)
    {
        Object obj = charsequence;
        if (charsequence == null)
        {
            obj = "";
        }
        if (rawText != obj)
        {
            rawText = ((CharSequence) (obj));
            charsequence = BidiFormatter.getInstance();
            unicodeWrappedText = charsequence.unicodeWrap(((CharSequence) (obj)), ((BidiFormatter) (charsequence)).mDefaultTextDirectionHeuristicCompat, true);
            textWidthDirty = true;
            invalidateSelf();
            charsequence = (Delegate)_flddelegate.get();
            if (charsequence != null)
            {
                charsequence.onChipDrawableSizeChange();
            }
        }
    }

    public final void setTextAppearance(TextAppearance textappearance)
    {
        if (textAppearance != textappearance)
        {
            textAppearance = textappearance;
            if (textappearance != null)
            {
                textappearance.updateMeasureState(context, textPaint, fontCallback);
                textWidthDirty = true;
            }
            onStateChange(getState());
            textappearance = (Delegate)_flddelegate.get();
            if (textappearance != null)
            {
                textappearance.onChipDrawableSizeChange();
            }
        }
    }

    public final void setTintList(ColorStateList colorstatelist)
    {
        if (tint != colorstatelist)
        {
            tint = colorstatelist;
            onStateChange(getState());
        }
    }

    public final void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (tintMode != mode)
        {
            tintMode = mode;
            ColorStateList colorstatelist = tint;
            if (colorstatelist == null || mode == null)
            {
                mode = null;
            } else
            {
                mode = new PorterDuffColorFilter(colorstatelist.getColorForState(getState(), 0), mode);
            }
            tintFilter = mode;
            invalidateSelf();
        }
    }

    public final boolean setVisible(boolean flag, boolean flag1)
    {
        boolean flag3 = true;
        boolean flag5 = super.setVisible(flag, flag1);
        boolean flag2;
        boolean flag4;
        if (chipIconVisible && chipIcon != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag4 = flag5;
        if (flag2)
        {
            flag4 = flag5 | chipIcon.setVisible(flag, flag1);
        }
        if (checkedIconVisible && checkedIcon != null && currentChecked)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag5 = flag4;
        if (flag2)
        {
            flag5 = flag4 | checkedIcon.setVisible(flag, flag1);
        }
        if (closeIconVisible && closeIcon != null)
        {
            flag2 = flag3;
        } else
        {
            flag2 = false;
        }
        flag4 = flag5;
        if (flag2)
        {
            flag4 = flag5 | closeIcon.setVisible(flag, flag1);
        }
        if (flag4)
        {
            invalidateSelf();
        }
        return flag4;
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        drawable = getCallback();
        if (drawable != null)
        {
            drawable.unscheduleDrawable(this, runnable);
        }
    }


    private class _cls1 extends android.support.v4.content.res.ResourcesCompat.FontCallback
    {

        private final ChipDrawable this$0;

        public final void onFontRetrievalFailed(int i)
        {
        }

        public final void onFontRetrieved(Typeface typeface)
        {
            textWidthDirty = true;
            typeface = (Delegate)_flddelegate.get();
            if (typeface != null)
            {
                typeface.onChipDrawableSizeChange();
            }
            invalidateSelf();
        }

        _cls1()
        {
            this$0 = ChipDrawable.this;
            super();
        }
    }


    private class Delegate
    {

        public abstract void onChipDrawableSizeChange();
    }

}
