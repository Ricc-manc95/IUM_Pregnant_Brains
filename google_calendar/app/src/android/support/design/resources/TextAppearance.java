// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.util.TypedValue;

// Referenced classes of package android.support.design.resources:
//            MaterialResources

public final class TextAppearance
{

    public Typeface font;
    private final String fontFamily;
    private final int fontFamilyResourceId;
    public boolean fontResolved;
    private final ColorStateList shadowColor;
    private final float shadowDx;
    private final float shadowDy;
    private final float shadowRadius;
    public final ColorStateList textColor;
    private final float textSize;
    public final int textStyle;
    private final int typeface;

    public TextAppearance(Context context, int i)
    {
        fontResolved = false;
        TypedArray typedarray = context.obtainStyledAttributes(i, R.styleable.TextAppearance);
        textSize = typedarray.getDimension(R.styleable.TextAppearance_android_textSize, 0.0F);
        textColor = MaterialResources.getColorStateList(context, typedarray, R.styleable.TextAppearance_android_textColor);
        MaterialResources.getColorStateList(context, typedarray, R.styleable.TextAppearance_android_textColorHint);
        MaterialResources.getColorStateList(context, typedarray, R.styleable.TextAppearance_android_textColorLink);
        textStyle = typedarray.getInt(R.styleable.TextAppearance_android_textStyle, 0);
        typeface = typedarray.getInt(R.styleable.TextAppearance_android_typeface, 1);
        i = R.styleable.TextAppearance_fontFamily;
        int j = R.styleable.TextAppearance_android_fontFamily;
        if (!typedarray.hasValue(i))
        {
            i = j;
        }
        fontFamilyResourceId = typedarray.getResourceId(i, 0);
        fontFamily = typedarray.getString(i);
        typedarray.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        shadowColor = MaterialResources.getColorStateList(context, typedarray, R.styleable.TextAppearance_android_shadowColor);
        shadowDx = typedarray.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0F);
        shadowDy = typedarray.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0F);
        shadowRadius = typedarray.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0F);
        typedarray.recycle();
    }

    final void createFallbackTypeface()
    {
        if (font == null)
        {
            font = Typeface.create(fontFamily, textStyle);
        }
        if (font != null) goto _L2; else goto _L1
_L1:
        typeface;
        JVM INSTR tableswitch 1 3: default 60
    //                   1 90
    //                   2 100
    //                   3 110;
           goto _L3 _L4 _L5 _L6
_L3:
        font = Typeface.DEFAULT;
_L8:
        if (font != null)
        {
            font = Typeface.create(font, textStyle);
        }
_L2:
        return;
_L4:
        font = Typeface.SANS_SERIF;
        continue; /* Loop/switch isn't completed */
_L5:
        font = Typeface.SERIF;
        continue; /* Loop/switch isn't completed */
_L6:
        font = Typeface.MONOSPACE;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void updateDrawState(Context context, TextPaint textpaint, android.support.v4.content.res.ResourcesCompat.FontCallback fontcallback)
    {
        updateMeasureState(context, textpaint, fontcallback);
        float f;
        float f1;
        float f2;
        int i;
        if (textColor != null)
        {
            i = textColor.getColorForState(textpaint.drawableState, textColor.getDefaultColor());
        } else
        {
            i = 0xff000000;
        }
        textpaint.setColor(i);
        f = shadowRadius;
        f1 = shadowDx;
        f2 = shadowDy;
        if (shadowColor != null)
        {
            i = shadowColor.getColorForState(textpaint.drawableState, shadowColor.getDefaultColor());
        } else
        {
            i = 0;
        }
        textpaint.setShadowLayer(f, f1, f2, i);
    }

    public final void updateMeasureState(Context context, final TextPaint textPaint, final android.support.v4.content.res.ResourcesCompat.FontCallback callback)
    {
        Object obj = null;
        if (!fontResolved) goto _L2; else goto _L1
_L1:
        updateTextPaintMeasureState(textPaint, font);
_L4:
        if (!fontResolved)
        {
            updateTextPaintMeasureState(textPaint, font);
        }
        return;
_L2:
        createFallbackTypeface();
        if (context.isRestricted())
        {
            fontResolved = true;
            updateTextPaintMeasureState(textPaint, font);
            continue; /* Loop/switch isn't completed */
        }
        int i;
        i = fontFamilyResourceId;
        callback = new _cls1();
        if (callback != null)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        throw new NullPointerException();
        if (!context.isRestricted())
        {
            break MISSING_BLOCK_LABEL_136;
        }
        context = obj;
        if (false)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        context = new Handler(Looper.getMainLooper());
        context.post(new android.support.v4.content.res.ResourcesCompat.FontCallback._cls2(callback, -4));
        continue; /* Loop/switch isn't completed */
        try
        {
            ResourcesCompat.loadFont(context, i, new TypedValue(), 0, callback, null, false);
        }
        // Misplaced declaration of an exception variable
        catch (Context context) { }
        // Misplaced declaration of an exception variable
        catch (Context context) { }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            context = String.valueOf(fontFamily);
            if (context.length() != 0)
            {
                "Error loading font ".concat(context);
            } else
            {
                new String("Error loading font ");
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void updateTextPaintMeasureState(TextPaint textpaint, Typeface typeface1)
    {
        textpaint.setTypeface(typeface1);
        int i = textStyle;
        i = ~typeface1.getStyle() & i;
        float f;
        boolean flag;
        if ((i & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        textpaint.setFakeBoldText(flag);
        if ((i & 2) != 0)
        {
            f = -0.25F;
        } else
        {
            f = 0.0F;
        }
        textpaint.setTextSkewX(f);
        textpaint.setTextSize(textSize);
    }

    private class _cls1 extends android.support.v4.content.res.ResourcesCompat.FontCallback
    {

        private final TextAppearance this$0;
        private final android.support.v4.content.res.ResourcesCompat.FontCallback val$callback;
        private final TextPaint val$textPaint;

        public final void onFontRetrievalFailed(int i)
        {
            createFallbackTypeface();
            fontResolved = true;
            callback.onFontRetrievalFailed(i);
        }

        public final void onFontRetrieved(Typeface typeface1)
        {
            font = Typeface.create(typeface1, textStyle);
            updateTextPaintMeasureState(textPaint, typeface1);
            fontResolved = true;
            callback.onFontRetrieved(typeface1);
        }

        _cls1()
        {
            this$0 = TextAppearance.this;
            textPaint = textpaint;
            callback = fontcallback;
            super();
        }
    }

}
