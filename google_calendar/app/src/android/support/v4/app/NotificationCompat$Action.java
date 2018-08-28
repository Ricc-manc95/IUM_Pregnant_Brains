// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

// Referenced classes of package android.support.v4.app:
//            RemoteInput

public final class mShowsUserInterface
{

    public PendingIntent actionIntent;
    public int icon;
    public boolean mAllowGeneratedReplies;
    public final RemoteInput mDataOnlyRemoteInputs[];
    public final Bundle mExtras;
    public final RemoteInput mRemoteInputs[];
    public final int mSemanticAction;
    public boolean mShowsUserInterface;
    public CharSequence title;

    public (int i, CharSequence charsequence, PendingIntent pendingintent)
    {
        this(i, charsequence, pendingintent, new Bundle(), null, null, true, 0, true);
    }

    private <init>(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInput aremoteinput[], RemoteInput aremoteinput1[], boolean flag, 
            int j, boolean flag1)
    {
        mShowsUserInterface = true;
        icon = i;
        aremoteinput = charsequence;
        if (charsequence != null)
        {
            aremoteinput = charsequence;
            if (charsequence.length() > 5120)
            {
                aremoteinput = charsequence.subSequence(0, 5120);
            }
        }
        title = aremoteinput;
        actionIntent = pendingintent;
        mExtras = bundle;
        mRemoteInputs = null;
        mDataOnlyRemoteInputs = null;
        mAllowGeneratedReplies = true;
        mSemanticAction = 0;
        mShowsUserInterface = true;
    }
}
