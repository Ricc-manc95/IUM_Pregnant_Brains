// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.chip;

import android.graphics.Typeface;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.design.chip:
//            ChipDrawable

final class k extends android.support.v4.content.res..FontCallback
{

    private final ChipDrawable this$0;

    public final void onFontRetrievalFailed(int i)
    {
    }

    public final void onFontRetrieved(Typeface typeface)
    {
        textWidthDirty = true;
        typeface = (legate)_flddelegate.get();
        if (typeface != null)
        {
            typeface.onChipDrawableSizeChange();
        }
        invalidateSelf();
    }

    legate()
    {
        this$0 = ChipDrawable.this;
        super();
    }
}
