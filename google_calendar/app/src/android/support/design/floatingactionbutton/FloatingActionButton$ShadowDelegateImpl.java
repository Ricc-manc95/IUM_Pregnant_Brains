// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.floatingactionbutton;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.design.shadow.ShadowViewDelegate;

// Referenced classes of package android.support.design.floatingactionbutton:
//            FloatingActionButton

final class this._cls0
    implements ShadowViewDelegate
{

    private final FloatingActionButton this$0;

    public final float getRadius()
    {
        FloatingActionButton floatingactionbutton = FloatingActionButton.this;
        return (float)floatingactionbutton.getSizeDimension(floatingactionbutton.size) / 2.0F;
    }

    public final boolean isCompatPaddingEnabled()
    {
        return compatPadding;
    }

    public final void setBackgroundDrawable(Drawable drawable)
    {
        FloatingActionButton.access$101(FloatingActionButton.this, drawable);
    }

    public final void setShadowPadding(int i, int j, int k, int l)
    {
        shadowPadding.set(i, j, k, l);
        setPadding(imagePadding + i, imagePadding + j, imagePadding + k, imagePadding + l);
    }

    Y()
    {
        this$0 = FloatingActionButton.this;
        super();
    }
}
