// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;

// Referenced classes of package android.support.v7.preference:
//            SeekBarPreference

final class this._cls0
    implements android.view.kBarPreference._cls2
{

    private final SeekBarPreference this$0;

    public final boolean onKey(View view, int i, KeyEvent keyevent)
    {
        while (keyevent.getAction() != 0 || !mAdjustable && (i == 21 || i == 22) || i == 23 || i == 66) 
        {
            return false;
        }
        if (mSeekBar == null)
        {
            Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
            return false;
        } else
        {
            return mSeekBar.onKeyDown(i, keyevent);
        }
    }

    ()
    {
        this$0 = SeekBarPreference.this;
        super();
    }
}
