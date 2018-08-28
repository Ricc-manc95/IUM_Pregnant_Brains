// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


// Referenced classes of package android.support.v7.widget:
//            DropDownListView

final class this._cls0
    implements Runnable
{

    public final DropDownListView this$0;

    public final void run()
    {
        mResolveHoverRunnable = null;
        drawableStateChanged();
    }

    ()
    {
        this$0 = DropDownListView.this;
        super();
    }
}
