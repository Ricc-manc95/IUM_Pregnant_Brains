// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.v4.app:
//            NavUtils

public final class TaskStackBuilder
    implements Iterable
{

    public final ArrayList mIntents = new ArrayList();
    public final Context mSourceContext;

    public TaskStackBuilder(Context context)
    {
        mSourceContext = context;
    }

    public final TaskStackBuilder addParentStack(ComponentName componentname)
    {
        int i = mIntents.size();
        try
        {
            componentname = NavUtils.getParentActivityIntent(mSourceContext, componentname);
        }
        // Misplaced declaration of an exception variable
        catch (ComponentName componentname)
        {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(componentname);
        }
        if (componentname == null)
        {
            break; /* Loop/switch isn't completed */
        }
        mIntents.add(i, componentname);
        componentname = NavUtils.getParentActivityIntent(mSourceContext, componentname.getComponent());
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_17;
_L1:
        return this;
    }

    public final Iterator iterator()
    {
        return mIntents.iterator();
    }
}
