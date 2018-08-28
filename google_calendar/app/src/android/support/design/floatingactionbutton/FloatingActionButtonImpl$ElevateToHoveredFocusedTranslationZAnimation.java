// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.floatingactionbutton;


// Referenced classes of package android.support.design.floatingactionbutton:
//            FloatingActionButtonImpl

final class this._cls0 extends this._cls0
{

    private final FloatingActionButtonImpl this$0;

    protected final float getTargetShadowSize()
    {
        return elevation + hoveredFocusedTranslationZ;
    }

    ()
    {
        this$0 = FloatingActionButtonImpl.this;
        super(FloatingActionButtonImpl.this, (byte)0);
    }
}
