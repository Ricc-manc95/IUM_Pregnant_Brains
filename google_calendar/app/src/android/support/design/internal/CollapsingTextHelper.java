// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.design.animation.AnimationUtils;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

public final class CollapsingTextHelper
{

    private static final Paint DEBUG_DRAW_PAINT = null;
    private static final boolean USE_SCALING_TEXTURE = false;
    public boolean boundsChanged;
    public final Rect collapsedBounds = new Rect();
    public float collapsedDrawX;
    public float collapsedDrawY;
    public int collapsedShadowColor;
    public float collapsedShadowDx;
    public float collapsedShadowDy;
    public float collapsedShadowRadius;
    public ColorStateList collapsedTextColor;
    public int collapsedTextGravity;
    public float collapsedTextSize;
    public Typeface collapsedTypeface;
    private final RectF currentBounds = new RectF();
    public float currentDrawX;
    public float currentDrawY;
    public float currentTextSize;
    private Typeface currentTypeface;
    public boolean drawTitle;
    public final Rect expandedBounds = new Rect();
    public float expandedDrawX;
    public float expandedDrawY;
    public float expandedFraction;
    public ColorStateList expandedTextColor;
    public int expandedTextGravity;
    public float expandedTextSize;
    public Bitmap expandedTitleTexture;
    public Typeface expandedTypeface;
    public boolean isRtl;
    public TimeInterpolator positionInterpolator;
    public float scale;
    public int state[];
    public CharSequence text;
    public final TextPaint textPaint = new TextPaint(129);
    public TimeInterpolator textSizeInterpolator;
    public CharSequence textToDraw;
    public float textureAscent;
    public float textureDescent;
    public Paint texturePaint;
    private final TextPaint tmpPaint;
    public boolean useTexture;
    public final View view;

    public CollapsingTextHelper(View view1)
    {
        expandedTextGravity = 16;
        collapsedTextGravity = 16;
        expandedTextSize = 15F;
        collapsedTextSize = 15F;
        view = view1;
        tmpPaint = new TextPaint(textPaint);
    }

    private static int blendColors(int i, int j, float f)
    {
        float f1 = 1.0F - f;
        float f2 = Color.alpha(i);
        float f3 = Color.alpha(j);
        float f4 = Color.red(i);
        float f5 = Color.red(j);
        float f6 = Color.green(i);
        float f7 = Color.green(j);
        float f8 = Color.blue(i);
        float f9 = Color.blue(j);
        return Color.argb((int)(f2 * f1 + f3 * f), (int)(f4 * f1 + f5 * f), (int)(f6 * f1 + f7 * f), (int)(f1 * f8 + f9 * f));
    }

    public static boolean rectEquals(Rect rect, int i, int j, int k, int l)
    {
        return rect.left == i && rect.top == j && rect.right == k && rect.bottom == l;
    }

    public final float calculateCollapsedTextWidth()
    {
        if (text == null)
        {
            return 0.0F;
        } else
        {
            TextPaint textpaint = tmpPaint;
            textpaint.setTextSize(collapsedTextSize);
            textpaint.setTypeface(collapsedTypeface);
            return tmpPaint.measureText(text, 0, text.length());
        }
    }

    public final boolean calculateIsRtl(CharSequence charsequence)
    {
        boolean flag = true;
        TextDirectionHeuristicCompat textdirectionheuristiccompat;
        if (ViewCompat.getLayoutDirection(view) != 1)
        {
            flag = false;
        }
        if (flag)
        {
            textdirectionheuristiccompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
        } else
        {
            textdirectionheuristiccompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        }
        return textdirectionheuristiccompat.isRtl(charsequence, 0, charsequence.length());
    }

    public final void calculateOffsets(float f)
    {
        Object obj = currentBounds;
        float f4 = expandedBounds.left;
        float f2 = collapsedBounds.left;
        Object obj1 = positionInterpolator;
        float f1;
        int i;
        if (obj1 != null)
        {
            f1 = ((TimeInterpolator) (obj1)).getInterpolation(f);
        } else
        {
            f1 = f;
        }
        obj.left = AnimationUtils.lerp(f4, f2, f1);
        obj = currentBounds;
        f4 = expandedDrawY;
        f2 = collapsedDrawY;
        obj1 = positionInterpolator;
        if (obj1 != null)
        {
            f1 = ((TimeInterpolator) (obj1)).getInterpolation(f);
        } else
        {
            f1 = f;
        }
        obj.top = AnimationUtils.lerp(f4, f2, f1);
        obj1 = currentBounds;
        f4 = expandedBounds.right;
        f2 = collapsedBounds.right;
        obj = positionInterpolator;
        if (obj != null)
        {
            f1 = ((TimeInterpolator) (obj)).getInterpolation(f);
        } else
        {
            f1 = f;
        }
        obj1.right = AnimationUtils.lerp(f4, f2, f1);
        obj = currentBounds;
        f4 = expandedBounds.bottom;
        f2 = collapsedBounds.bottom;
        obj1 = positionInterpolator;
        if (obj1 != null)
        {
            f1 = ((TimeInterpolator) (obj1)).getInterpolation(f);
        } else
        {
            f1 = f;
        }
        obj.bottom = AnimationUtils.lerp(f4, f2, f1);
        f4 = expandedDrawX;
        f2 = collapsedDrawX;
        obj = positionInterpolator;
        if (obj != null)
        {
            f1 = ((TimeInterpolator) (obj)).getInterpolation(f);
        } else
        {
            f1 = f;
        }
        currentDrawX = AnimationUtils.lerp(f4, f2, f1);
        f2 = expandedDrawY;
        f4 = collapsedDrawY;
        obj = positionInterpolator;
        if (obj != null)
        {
            f1 = ((TimeInterpolator) (obj)).getInterpolation(f);
        } else
        {
            f1 = f;
        }
        currentDrawY = AnimationUtils.lerp(f2, f4, f1);
        f4 = expandedTextSize;
        f2 = collapsedTextSize;
        obj = textSizeInterpolator;
        if (obj != null)
        {
            f1 = ((TimeInterpolator) (obj)).getInterpolation(f);
        } else
        {
            f1 = f;
        }
        calculateUsingTextSize(AnimationUtils.lerp(f4, f2, f1));
        useTexture = false;
        if (useTexture && expandedTitleTexture == null && !expandedBounds.isEmpty() && !TextUtils.isEmpty(textToDraw))
        {
            calculateOffsets(0.0F);
            textureAscent = textPaint.ascent();
            textureDescent = textPaint.descent();
            i = Math.round(textPaint.measureText(textToDraw, 0, textToDraw.length()));
            j = Math.round(textureDescent - textureAscent);
            if (i > 0 && j > 0)
            {
                expandedTitleTexture = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(expandedTitleTexture)).drawText(textToDraw, 0, textToDraw.length(), 0.0F, (float)j - textPaint.descent(), textPaint);
                if (texturePaint == null)
                {
                    texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(view);
        if (collapsedTextColor != expandedTextColor)
        {
            obj = textPaint;
            float f3;
            float f5;
            int j;
            if (state != null)
            {
                i = expandedTextColor.getColorForState(state, 0);
            } else
            {
                i = expandedTextColor.getDefaultColor();
            }
            if (state != null)
            {
                j = collapsedTextColor.getColorForState(state, 0);
            } else
            {
                j = collapsedTextColor.getDefaultColor();
            }
            i = blendColors(i, j, f);
        } else
        {
            obj = textPaint;
            if (state != null)
            {
                i = collapsedTextColor.getColorForState(state, 0);
            } else
            {
                i = collapsedTextColor.getDefaultColor();
            }
        }
        ((TextPaint) (obj)).setColor(i);
        obj = textPaint;
        f1 = collapsedShadowRadius;
        if (false)
        {
            throw new NullPointerException();
        }
        f1 = AnimationUtils.lerp(0.0F, f1, f);
        f3 = collapsedShadowDx;
        if (false)
        {
            throw new NullPointerException();
        }
        f5 = AnimationUtils.lerp(0.0F, f3, f);
        f3 = collapsedShadowDy;
        if (false)
        {
            throw new NullPointerException();
        } else
        {
            ((TextPaint) (obj)).setShadowLayer(f1, f5, AnimationUtils.lerp(0.0F, f3, f), blendColors(0, collapsedShadowColor, f));
            ViewCompat.postInvalidateOnAnimation(view);
            return;
        }
    }

    public final void calculateUsingTextSize(float f)
    {
        boolean flag2 = true;
        if (text != null)
        {
            float f2 = collapsedBounds.width();
            float f3 = expandedBounds.width();
            boolean flag;
            if (Math.abs(f - collapsedTextSize) < 0.001F)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                float f1 = collapsedTextSize;
                scale = 1.0F;
                Object obj;
                boolean flag1;
                if (currentTypeface != collapsedTypeface)
                {
                    currentTypeface = collapsedTypeface;
                    flag = true;
                    f = f2;
                } else
                {
                    flag = false;
                    f = f2;
                }
            } else
            {
                f1 = expandedTextSize;
                if (currentTypeface != expandedTypeface)
                {
                    currentTypeface = expandedTypeface;
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (Math.abs(f - expandedTextSize) < 0.001F)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    scale = 1.0F;
                } else
                {
                    scale = f / expandedTextSize;
                }
                f = collapsedTextSize / expandedTextSize;
                if (f3 * f > f2)
                {
                    f = Math.min(f2 / f, f3);
                } else
                {
                    f = f3;
                }
            }
            flag1 = flag;
            if (f > 0.0F)
            {
                if (currentTextSize != f1 || boundsChanged || flag)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                currentTextSize = f1;
                boundsChanged = false;
                flag1 = flag;
            }
            if (textToDraw == null || flag1)
            {
                textPaint.setTextSize(currentTextSize);
                textPaint.setTypeface(currentTypeface);
                obj = textPaint;
                if (scale == 1.0F)
                {
                    flag2 = false;
                }
                ((TextPaint) (obj)).setLinearText(flag2);
                obj = TextUtils.ellipsize(text, textPaint, f, android.text.TextUtils.TruncateAt.END);
                if (!TextUtils.equals(((CharSequence) (obj)), textToDraw))
                {
                    textToDraw = ((CharSequence) (obj));
                    isRtl = calculateIsRtl(textToDraw);
                    return;
                }
            }
        }
    }

    public final float getCollapsedTextHeight()
    {
        TextPaint textpaint = tmpPaint;
        textpaint.setTextSize(collapsedTextSize);
        textpaint.setTypeface(collapsedTypeface);
        return -tmpPaint.ascent();
    }

    public final void onBoundsChanged()
    {
        boolean flag;
        if (collapsedBounds.width() > 0 && collapsedBounds.height() > 0 && expandedBounds.width() > 0 && expandedBounds.height() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        drawTitle = flag;
    }

    public final Typeface readFontFamilyTypeface(int i)
    {
        TypedArray typedarray = view.getContext().obtainStyledAttributes(i, new int[] {
            0x10103ac
        });
        Object obj = typedarray.getString(0);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        obj = Typeface.create(((String) (obj)), 0);
        typedarray.recycle();
        return ((Typeface) (obj));
        typedarray.recycle();
        return null;
        Exception exception;
        exception;
        typedarray.recycle();
        throw exception;
    }

    public final void recalculate()
    {
        boolean flag = true;
        if (view.getHeight() <= 0 || view.getWidth() <= 0) goto _L2; else goto _L1
_L1:
        float f;
        int i;
        float f1 = currentTextSize;
        calculateUsingTextSize(collapsedTextSize);
        int l;
        if (textToDraw != null)
        {
            f = textPaint.measureText(textToDraw, 0, textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = collapsedTextGravity;
        if (isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(l, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 116
    //                   48: 472
    //                   80: 457;
           goto _L3 _L4 _L5
_L3:
        collapsedDrawY = ((textPaint.descent() - textPaint.ascent()) / 2.0F - textPaint.descent()) + (float)collapsedBounds.centerY();
_L15:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 184
    //                   1: 495
    //                   5: 514;
           goto _L6 _L7 _L8
_L6:
        collapsedDrawX = collapsedBounds.left;
_L16:
        calculateUsingTextSize(expandedTextSize);
        if (textToDraw != null)
        {
            f = textPaint.measureText(textToDraw, 0, textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = expandedTextGravity;
        if (isRtl)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(l, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 288
    //                   48: 556
    //                   80: 541;
           goto _L9 _L10 _L11
_L9:
        expandedDrawY = ((textPaint.descent() - textPaint.ascent()) / 2.0F - textPaint.descent()) + (float)expandedBounds.centerY();
_L17:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 356
    //                   1: 579
    //                   5: 598;
           goto _L12 _L13 _L14
_L12:
        expandedDrawX = expandedBounds.left;
_L18:
        if (expandedTitleTexture != null)
        {
            expandedTitleTexture.recycle();
            expandedTitleTexture = null;
        }
        calculateUsingTextSize(f1);
        useTexture = false;
        if (useTexture && expandedTitleTexture == null && !expandedBounds.isEmpty() && !TextUtils.isEmpty(textToDraw))
        {
            calculateOffsets(0.0F);
            textureAscent = textPaint.ascent();
            textureDescent = textPaint.descent();
            int j = Math.round(textPaint.measureText(textToDraw, 0, textToDraw.length()));
            int k = Math.round(textureDescent - textureAscent);
            if (j > 0 && k > 0)
            {
                expandedTitleTexture = Bitmap.createBitmap(j, k, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(expandedTitleTexture)).drawText(textToDraw, 0, textToDraw.length(), 0.0F, (float)k - textPaint.descent(), textPaint);
                if (texturePaint == null)
                {
                    texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(view);
        calculateOffsets(expandedFraction);
_L2:
        return;
_L5:
        collapsedDrawY = collapsedBounds.bottom;
          goto _L15
_L4:
        collapsedDrawY = (float)collapsedBounds.top - textPaint.ascent();
          goto _L15
_L7:
        collapsedDrawX = (float)collapsedBounds.centerX() - f / 2.0F;
          goto _L16
_L8:
        collapsedDrawX = (float)collapsedBounds.right - f;
          goto _L16
_L11:
        expandedDrawY = expandedBounds.bottom;
          goto _L17
_L10:
        expandedDrawY = (float)expandedBounds.top - textPaint.ascent();
          goto _L17
_L13:
        expandedDrawX = (float)expandedBounds.centerX() - f / 2.0F;
          goto _L18
_L14:
        expandedDrawX = (float)expandedBounds.right - f;
          goto _L18
    }

    public final void setCollapsedTextColor(ColorStateList colorstatelist)
    {
        boolean flag = true;
        if (collapsedTextColor == colorstatelist) goto _L2; else goto _L1
_L1:
        collapsedTextColor = colorstatelist;
        if (view.getHeight() <= 0 || view.getWidth() <= 0) goto _L2; else goto _L3
_L3:
        float f;
        int i;
        float f1 = currentTextSize;
        calculateUsingTextSize(collapsedTextSize);
        int l;
        if (textToDraw != null)
        {
            f = textPaint.measureText(textToDraw, 0, textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = collapsedTextGravity;
        if (isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(l, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 136
    //                   48: 505
    //                   80: 490;
           goto _L4 _L5 _L6
_L4:
        collapsedDrawY = ((textPaint.descent() - textPaint.ascent()) / 2.0F - textPaint.descent()) + (float)collapsedBounds.centerY();
_L16:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 208
    //                   1: 528
    //                   5: 547;
           goto _L7 _L8 _L9
_L7:
        collapsedDrawX = collapsedBounds.left;
_L17:
        calculateUsingTextSize(expandedTextSize);
        if (textToDraw != null)
        {
            f = textPaint.measureText(textToDraw, 0, textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = expandedTextGravity;
        if (isRtl)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(l, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 316
    //                   48: 590
    //                   80: 575;
           goto _L10 _L11 _L12
_L10:
        expandedDrawY = ((textPaint.descent() - textPaint.ascent()) / 2.0F - textPaint.descent()) + (float)expandedBounds.centerY();
_L18:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 388
    //                   1: 613
    //                   5: 632;
           goto _L13 _L14 _L15
_L13:
        expandedDrawX = expandedBounds.left;
_L19:
        if (expandedTitleTexture != null)
        {
            expandedTitleTexture.recycle();
            expandedTitleTexture = null;
        }
        calculateUsingTextSize(f1);
        useTexture = false;
        if (useTexture && expandedTitleTexture == null && !expandedBounds.isEmpty() && !TextUtils.isEmpty(textToDraw))
        {
            calculateOffsets(0.0F);
            textureAscent = textPaint.ascent();
            textureDescent = textPaint.descent();
            int j = Math.round(textPaint.measureText(textToDraw, 0, textToDraw.length()));
            int k = Math.round(textureDescent - textureAscent);
            if (j > 0 && k > 0)
            {
                expandedTitleTexture = Bitmap.createBitmap(j, k, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(expandedTitleTexture)).drawText(textToDraw, 0, textToDraw.length(), 0.0F, (float)k - textPaint.descent(), textPaint);
                if (texturePaint == null)
                {
                    texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(view);
        calculateOffsets(expandedFraction);
_L2:
        return;
_L6:
        collapsedDrawY = collapsedBounds.bottom;
          goto _L16
_L5:
        collapsedDrawY = (float)collapsedBounds.top - textPaint.ascent();
          goto _L16
_L8:
        collapsedDrawX = (float)collapsedBounds.centerX() - f / 2.0F;
          goto _L17
_L9:
        collapsedDrawX = (float)collapsedBounds.right - f;
          goto _L17
_L12:
        expandedDrawY = expandedBounds.bottom;
          goto _L18
_L11:
        expandedDrawY = (float)expandedBounds.top - textPaint.ascent();
          goto _L18
_L14:
        expandedDrawX = (float)expandedBounds.centerX() - f / 2.0F;
          goto _L19
_L15:
        expandedDrawX = (float)expandedBounds.right - f;
          goto _L19
    }

    public final void setCollapsedTextGravity(int i)
    {
        boolean flag = true;
        if (collapsedTextGravity == i) goto _L2; else goto _L1
_L1:
        collapsedTextGravity = i;
        if (view.getHeight() <= 0 || view.getWidth() <= 0) goto _L2; else goto _L3
_L3:
        float f;
        float f1 = currentTextSize;
        calculateUsingTextSize(collapsedTextSize);
        int k;
        if (textToDraw != null)
        {
            f = textPaint.measureText(textToDraw, 0, textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        k = collapsedTextGravity;
        if (isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(k, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 132
    //                   48: 488
    //                   80: 473;
           goto _L4 _L5 _L6
_L4:
        collapsedDrawY = ((textPaint.descent() - textPaint.ascent()) / 2.0F - textPaint.descent()) + (float)collapsedBounds.centerY();
_L16:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 200
    //                   1: 511
    //                   5: 530;
           goto _L7 _L8 _L9
_L7:
        collapsedDrawX = collapsedBounds.left;
_L17:
        calculateUsingTextSize(expandedTextSize);
        if (textToDraw != null)
        {
            f = textPaint.measureText(textToDraw, 0, textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        k = expandedTextGravity;
        if (isRtl)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(k, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 304
    //                   48: 572
    //                   80: 557;
           goto _L10 _L11 _L12
_L10:
        expandedDrawY = ((textPaint.descent() - textPaint.ascent()) / 2.0F - textPaint.descent()) + (float)expandedBounds.centerY();
_L18:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 372
    //                   1: 595
    //                   5: 614;
           goto _L13 _L14 _L15
_L13:
        expandedDrawX = expandedBounds.left;
_L19:
        if (expandedTitleTexture != null)
        {
            expandedTitleTexture.recycle();
            expandedTitleTexture = null;
        }
        calculateUsingTextSize(f1);
        useTexture = false;
        if (useTexture && expandedTitleTexture == null && !expandedBounds.isEmpty() && !TextUtils.isEmpty(textToDraw))
        {
            calculateOffsets(0.0F);
            textureAscent = textPaint.ascent();
            textureDescent = textPaint.descent();
            i = Math.round(textPaint.measureText(textToDraw, 0, textToDraw.length()));
            int j = Math.round(textureDescent - textureAscent);
            if (i > 0 && j > 0)
            {
                expandedTitleTexture = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(expandedTitleTexture)).drawText(textToDraw, 0, textToDraw.length(), 0.0F, (float)j - textPaint.descent(), textPaint);
                if (texturePaint == null)
                {
                    texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(view);
        calculateOffsets(expandedFraction);
_L2:
        return;
_L6:
        collapsedDrawY = collapsedBounds.bottom;
          goto _L16
_L5:
        collapsedDrawY = (float)collapsedBounds.top - textPaint.ascent();
          goto _L16
_L8:
        collapsedDrawX = (float)collapsedBounds.centerX() - f / 2.0F;
          goto _L17
_L9:
        collapsedDrawX = (float)collapsedBounds.right - f;
          goto _L17
_L12:
        expandedDrawY = expandedBounds.bottom;
          goto _L18
_L11:
        expandedDrawY = (float)expandedBounds.top - textPaint.ascent();
          goto _L18
_L14:
        expandedDrawX = (float)expandedBounds.centerX() - f / 2.0F;
          goto _L19
_L15:
        expandedDrawX = (float)expandedBounds.right - f;
          goto _L19
    }

    public final void setExpandedTextColor(ColorStateList colorstatelist)
    {
        boolean flag = true;
        if (expandedTextColor == colorstatelist) goto _L2; else goto _L1
_L1:
        expandedTextColor = colorstatelist;
        if (view.getHeight() <= 0 || view.getWidth() <= 0) goto _L2; else goto _L3
_L3:
        float f;
        int i;
        float f1 = currentTextSize;
        calculateUsingTextSize(collapsedTextSize);
        int l;
        if (textToDraw != null)
        {
            f = textPaint.measureText(textToDraw, 0, textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = collapsedTextGravity;
        if (isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(l, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 136
    //                   48: 505
    //                   80: 490;
           goto _L4 _L5 _L6
_L4:
        collapsedDrawY = ((textPaint.descent() - textPaint.ascent()) / 2.0F - textPaint.descent()) + (float)collapsedBounds.centerY();
_L16:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 208
    //                   1: 528
    //                   5: 547;
           goto _L7 _L8 _L9
_L7:
        collapsedDrawX = collapsedBounds.left;
_L17:
        calculateUsingTextSize(expandedTextSize);
        if (textToDraw != null)
        {
            f = textPaint.measureText(textToDraw, 0, textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = expandedTextGravity;
        if (isRtl)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(l, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 316
    //                   48: 590
    //                   80: 575;
           goto _L10 _L11 _L12
_L10:
        expandedDrawY = ((textPaint.descent() - textPaint.ascent()) / 2.0F - textPaint.descent()) + (float)expandedBounds.centerY();
_L18:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 388
    //                   1: 613
    //                   5: 632;
           goto _L13 _L14 _L15
_L13:
        expandedDrawX = expandedBounds.left;
_L19:
        if (expandedTitleTexture != null)
        {
            expandedTitleTexture.recycle();
            expandedTitleTexture = null;
        }
        calculateUsingTextSize(f1);
        useTexture = false;
        if (useTexture && expandedTitleTexture == null && !expandedBounds.isEmpty() && !TextUtils.isEmpty(textToDraw))
        {
            calculateOffsets(0.0F);
            textureAscent = textPaint.ascent();
            textureDescent = textPaint.descent();
            int j = Math.round(textPaint.measureText(textToDraw, 0, textToDraw.length()));
            int k = Math.round(textureDescent - textureAscent);
            if (j > 0 && k > 0)
            {
                expandedTitleTexture = Bitmap.createBitmap(j, k, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(expandedTitleTexture)).drawText(textToDraw, 0, textToDraw.length(), 0.0F, (float)k - textPaint.descent(), textPaint);
                if (texturePaint == null)
                {
                    texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(view);
        calculateOffsets(expandedFraction);
_L2:
        return;
_L6:
        collapsedDrawY = collapsedBounds.bottom;
          goto _L16
_L5:
        collapsedDrawY = (float)collapsedBounds.top - textPaint.ascent();
          goto _L16
_L8:
        collapsedDrawX = (float)collapsedBounds.centerX() - f / 2.0F;
          goto _L17
_L9:
        collapsedDrawX = (float)collapsedBounds.right - f;
          goto _L17
_L12:
        expandedDrawY = expandedBounds.bottom;
          goto _L18
_L11:
        expandedDrawY = (float)expandedBounds.top - textPaint.ascent();
          goto _L18
_L14:
        expandedDrawX = (float)expandedBounds.centerX() - f / 2.0F;
          goto _L19
_L15:
        expandedDrawX = (float)expandedBounds.right - f;
          goto _L19
    }

    public final void setExpansionFraction(float f)
    {
        if (f >= 0.0F) goto _L2; else goto _L1
_L1:
        float f1 = 0.0F;
_L4:
        if (f1 != expandedFraction)
        {
            expandedFraction = f1;
            calculateOffsets(expandedFraction);
        }
        return;
_L2:
        f1 = f;
        if (f > 1.0F)
        {
            f1 = 1.0F;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

}
