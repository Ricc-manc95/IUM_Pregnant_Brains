// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.Typeface;
import android.widget.TextView;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.widget:
//            AppCompatTextHelper

final class ontCallback extends android.support.v4.content.res.llback
{

    private final AppCompatTextHelper this$0;
    private final WeakReference val$textViewWeak;

    public final void onFontRetrievalFailed(int i)
    {
    }

    public final void onFontRetrieved(Typeface typeface)
    {
        AppCompatTextHelper appcompattexthelper = AppCompatTextHelper.this;
        Object obj = val$textViewWeak;
        if (appcompattexthelper.mAsyncFontPending)
        {
            appcompattexthelper.mFontTypeface = typeface;
            obj = (TextView)((WeakReference) (obj)).get();
            if (obj != null)
            {
                ((TextView) (obj)).setTypeface(typeface, appcompattexthelper.mStyle);
            }
        }
    }

    ontCallback()
    {
        this$0 = final_appcompattexthelper;
        val$textViewWeak = WeakReference.this;
        super();
    }
}
