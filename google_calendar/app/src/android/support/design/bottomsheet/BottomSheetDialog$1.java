// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.bottomsheet;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;

// Referenced classes of package android.support.design.bottomsheet:
//            BottomSheetDialog

final class this._cls0
    implements android.view.
{

    private final BottomSheetDialog this$0;

    public final void onClick(View view)
    {
        if (cancelable && isShowing())
        {
            view = BottomSheetDialog.this;
            if (!((BottomSheetDialog) (view)).canceledOnTouchOutsideSet)
            {
                TypedArray typedarray = view.getContext().obtainStyledAttributes(new int[] {
                    0x101035b
                });
                view.canceledOnTouchOutside = typedarray.getBoolean(0, true);
                typedarray.recycle();
                view.canceledOnTouchOutsideSet = true;
            }
            if (((BottomSheetDialog) (view)).canceledOnTouchOutside)
            {
                cancel();
            }
        }
    }

    ()
    {
        this$0 = BottomSheetDialog.this;
        super();
    }
}
