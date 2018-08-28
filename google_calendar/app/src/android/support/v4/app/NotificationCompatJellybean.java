// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.os.Bundle;

// Referenced classes of package android.support.v4.app:
//            RemoteInput

final class NotificationCompatJellybean
{

    private static final Object sExtrasLock = new Object();

    static Bundle getBundleForAction(NotificationCompat.Action action)
    {
        Bundle bundle1 = new Bundle();
        bundle1.putInt("icon", action.icon);
        bundle1.putCharSequence("title", action.title);
        bundle1.putParcelable("actionIntent", action.actionIntent);
        Bundle bundle;
        if (action.mExtras != null)
        {
            bundle = new Bundle(action.mExtras);
        } else
        {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.mAllowGeneratedReplies);
        bundle1.putBundle("extras", bundle);
        bundle1.putParcelableArray("remoteInputs", toBundleArray(action.mRemoteInputs));
        bundle1.putBoolean("showsUserInterface", action.mShowsUserInterface);
        bundle1.putInt("semanticAction", action.mSemanticAction);
        return bundle1;
    }

    private static Bundle[] toBundleArray(RemoteInput aremoteinput[])
    {
        Bundle abundle[];
        if (aremoteinput == null)
        {
            abundle = null;
        } else
        {
            abundle = new Bundle[aremoteinput.length];
            if (aremoteinput.length < 0)
            {
                aremoteinput = aremoteinput[0];
                new Bundle();
                throw new NoSuchMethodError();
            }
        }
        return abundle;
    }

    static 
    {
        new Object();
    }
}
