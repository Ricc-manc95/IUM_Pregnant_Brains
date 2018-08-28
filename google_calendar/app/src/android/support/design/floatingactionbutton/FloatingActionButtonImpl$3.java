// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.floatingactionbutton;

import android.support.design.internal.CircularBorderDrawable;
import android.support.design.internal.VisibilityAwareImageButton;
import android.support.design.shadow.ShadowDrawableWrapper;

// Referenced classes of package android.support.design.floatingactionbutton:
//            FloatingActionButtonImpl

final class this._cls0
    implements android.view.istener
{

    private final FloatingActionButtonImpl this$0;

    public final boolean onPreDraw()
    {
        FloatingActionButtonImpl floatingactionbuttonimpl = FloatingActionButtonImpl.this;
        float f = floatingactionbuttonimpl.view.getRotation();
        if (floatingactionbuttonimpl.rotation != f)
        {
            floatingactionbuttonimpl.rotation = f;
            if (floatingactionbuttonimpl.shadowDrawable != null)
            {
                ShadowDrawableWrapper shadowdrawablewrapper = floatingactionbuttonimpl.shadowDrawable;
                float f1 = -floatingactionbuttonimpl.rotation;
                if (shadowdrawablewrapper.rotation != f1)
                {
                    shadowdrawablewrapper.rotation = f1;
                    shadowdrawablewrapper.invalidateSelf();
                }
            }
            if (floatingactionbuttonimpl.borderDrawable != null)
            {
                CircularBorderDrawable circularborderdrawable = floatingactionbuttonimpl.borderDrawable;
                float f2 = -floatingactionbuttonimpl.rotation;
                if (f2 != circularborderdrawable.rotation)
                {
                    circularborderdrawable.rotation = f2;
                    circularborderdrawable.invalidateSelf();
                }
            }
        }
        return true;
    }

    ()
    {
        this$0 = FloatingActionButtonImpl.this;
        super();
    }
}
