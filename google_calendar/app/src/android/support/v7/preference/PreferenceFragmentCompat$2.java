// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.support.v7.widget.RecyclerView;

// Referenced classes of package android.support.v7.preference:
//            PreferenceFragmentCompat

final class this._cls0
    implements Runnable
{

    private final PreferenceFragmentCompat this$0;

    public final void run()
    {
        mList.focusableViewAvailable(mList);
    }

    ()
    {
        this$0 = PreferenceFragmentCompat.this;
        super();
    }
}
