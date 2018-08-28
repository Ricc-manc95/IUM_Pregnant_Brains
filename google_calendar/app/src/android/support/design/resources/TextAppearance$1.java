// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.resources;

import android.graphics.Typeface;
import android.text.TextPaint;

// Referenced classes of package android.support.design.resources:
//            TextAppearance

final class tCallback extends android.support.v4.content.res.ontCallback
{

    private final TextAppearance this$0;
    private final android.support.v4.content.res.ontCallback val$callback;
    private final TextPaint val$textPaint;

    public final void onFontRetrievalFailed(int i)
    {
        createFallbackTypeface();
        fontResolved = true;
        val$callback.onFontRetrievalFailed(i);
    }

    public final void onFontRetrieved(Typeface typeface)
    {
        font = Typeface.create(typeface, textStyle);
        updateTextPaintMeasureState(val$textPaint, typeface);
        fontResolved = true;
        val$callback.onFontRetrieved(typeface);
    }

    tCallback()
    {
        this$0 = final_textappearance;
        val$textPaint = textpaint;
        val$callback = android.support.v4.content.res.ontCallback.this;
        super();
    }
}
