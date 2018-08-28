// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Handler;

// Referenced classes of package android.support.v7.preference:
//            PreferenceGroup, CollapsiblePreferenceGroupController, PreferenceGroupAdapter, Preference

final class val.group
    implements val.group
{

    private final CollapsiblePreferenceGroupController this$0;
    private final PreferenceGroup val$group;

    public final boolean onPreferenceClick(Preference preference)
    {
        val$group.setInitialExpandedChildrenCount(0x7fffffff);
        preference = mPreferenceGroupAdapter;
        ((PreferenceGroupAdapter) (preference)).mHandler.removeCallbacks(((PreferenceGroupAdapter) (preference)).mSyncRunnable);
        ((PreferenceGroupAdapter) (preference)).mHandler.post(((PreferenceGroupAdapter) (preference)).mSyncRunnable);
        return true;
    }

    ()
    {
        this$0 = final_collapsiblepreferencegroupcontroller;
        val$group = PreferenceGroup.this;
        super();
    }
}
