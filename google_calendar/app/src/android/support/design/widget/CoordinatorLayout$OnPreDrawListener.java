// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;


// Referenced classes of package android.support.design.widget:
//            CoordinatorLayout

final class this._cls0
    implements android.view.nPreDrawListener
{

    private final CoordinatorLayout this$0;

    public final boolean onPreDraw()
    {
        onChildViewsChanged(0);
        return true;
    }

    ()
    {
        this$0 = CoordinatorLayout.this;
        super();
    }
}
