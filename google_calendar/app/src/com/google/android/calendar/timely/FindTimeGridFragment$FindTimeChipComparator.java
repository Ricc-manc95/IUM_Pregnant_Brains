// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridFragment

final class this._cls0
    implements Comparator
{

    private final FindTimeGridFragment this$0;

    public final int compare(Object obj, Object obj1)
    {
        this._cls0 _lcls0 = (this._cls0)obj;
        obj1 = (this._cls0)obj1;
        if (_lcls0._fld0.partitionInfo.getStartTime() != ((this._cls0) (obj1))._fld0.partitionInfo.getStartTime())
        {
            return _lcls0._fld0.partitionInfo.getStartTime() - ((this._cls0) (obj1))._fld0.partitionInfo.getStartTime();
        }
        if (_lcls0._fld0 != ((this._cls0) (obj1))._fld0)
        {
            return _lcls0._fld0 - ((this._cls0) (obj1))._fld0;
        }
        obj = FindTimeGridFragment.this;
        if (((Fragment) (obj)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        if (RtlUtils.isLayoutDirectionRtl(((android.content.Context) (obj))))
        {
            return ((this._cls0) (obj1))._fld0.partitionInfo.getPartition() - _lcls0._fld0.partitionInfo.getPartition();
        } else
        {
            return _lcls0._fld0.partitionInfo.getPartition() - ((this._cls0) (obj1))._fld0.partitionInfo.getPartition();
        }
    }

    ()
    {
        this$0 = FindTimeGridFragment.this;
        super();
    }
}
