// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.google.android.calendar.common.fragment.FragmentRunnable;

// Referenced classes of package com.google.android.calendar.drive:
//            CheckPermissionsResults, CheckPermissionProgressFragment

final class val.data extends FragmentRunnable
{

    private final CheckPermissionProgressFragment this$0;
    private final CheckPermissionsResults val$data;

    public final void go()
    {
        dismiss();
        Object obj = mTarget;
        if (obj instanceof stener)
        {
            obj = (stener)obj;
        } else
        {
            obj = null;
        }
        if (val$data == null)
        {
            ((stener) (obj)).onPermissionsCheckFinished();
            return;
        }
        if ("NONE_FIXABLE".equals(val$data.fixabilitySummary))
        {
            ((stener) (obj)).showFilesNotSharedDialog(numFiles);
            return;
        } else
        {
            ((stener) (obj)).showFixPermissionsDialog(null, val$data.potentialFixes, numFiles, accountName);
            return;
        }
    }

    stener(Fragment fragment, CheckPermissionsResults checkpermissionsresults)
    {
        this$0 = final_checkpermissionprogressfragment;
        val$data = checkpermissionsresults;
        super(fragment);
    }
}
