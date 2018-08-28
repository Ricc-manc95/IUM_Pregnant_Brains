// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            ReadOnlyEventPermissionsImpl

public final class HabitInstanceEventPermissionsImpl extends ReadOnlyEventPermissionsImpl
{

    public HabitInstanceEventPermissionsImpl()
    {
    }

    public final boolean canDelete()
    {
        return true;
    }

    public final boolean canModifyStartTime()
    {
        return true;
    }

    public final List getAllowedModificationScopes()
    {
        return Collections.singletonList(Integer.valueOf(0));
    }

    public final boolean isReadOnly()
    {
        return false;
    }
}
