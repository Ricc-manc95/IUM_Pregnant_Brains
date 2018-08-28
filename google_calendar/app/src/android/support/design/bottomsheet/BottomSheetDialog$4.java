// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.bottomsheet;


// Referenced classes of package android.support.design.bottomsheet:
//            BottomSheetDialog

final class BottomSheetCallback extends BottomSheetCallback
{

    private final BottomSheetDialog this$0;

    public final void onSlide$51662RJ4E9NMIP1FEPKMATPFAPKMATPR8OKLC___0()
    {
    }

    public final void onStateChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR94KLC___0(int i)
    {
        if (i == 5)
        {
            cancel();
        }
    }

    BottomSheetCallback()
    {
        this$0 = BottomSheetDialog.this;
        super();
    }
}
