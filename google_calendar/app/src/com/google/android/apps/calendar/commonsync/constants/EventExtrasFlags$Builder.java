// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.commonsync.constants;

import android.util.Log;

// Referenced classes of package com.google.android.apps.calendar.commonsync.constants:
//            EventExtrasFlags

public static final class flags
{

    public int flags;

    public final flags setConferenceData(boolean flag)
    {
        if (flag)
        {
            flags = 0x20 | flags;
            return this;
        } else
        {
            flags = flags & 0xffffffdf;
            return this;
        }
    }

    public final flags setEndTimeUnspecified(boolean flag)
    {
        if (flag)
        {
            flags = 4 | flags;
            return this;
        } else
        {
            flags = flags & -5;
            return this;
        }
    }

    public final flags setEveryoneDeclined(boolean flag)
    {
        if (flag)
        {
            flags = 0x800 | flags;
            return this;
        } else
        {
            flags = flags & 0xfffff7ff;
            return this;
        }
    }

    public final flags setEveryoneDeclinedDismissed(boolean flag)
    {
        if (flag)
        {
            flags = 0x2000 | flags;
            return this;
        } else
        {
            flags = flags & 0xffffdfff;
            return this;
        }
    }

    public final flags setHasTimeProposals(boolean flag)
    {
        if (flag)
        {
            flags = 0x4000 | flags;
            return this;
        } else
        {
            flags = flags & 0xffffbfff;
            return this;
        }
    }

    public final flags setImageAvailable(boolean flag)
    {
        if (flag)
        {
            flags = 2 | flags;
            return this;
        } else
        {
            flags = flags & -3;
            return this;
        }
    }

    public final flags setOoo(boolean flag)
    {
        if (flag)
        {
            flags = 0x1000 | flags;
            return this;
        } else
        {
            flags = flags & 0xffffefff;
            return this;
        }
    }

    public final flags setParticipantStatus(boolean flag)
    {
        if (flag)
        {
            flags = 0x400 | flags;
            return this;
        } else
        {
            flags = flags & 0xfffffbff;
            return this;
        }
    }

    public final flags setSmartMailAvailable(boolean flag)
    {
        if (flag)
        {
            flags = 1 | flags;
            return this;
        } else
        {
            flags = flags & -2;
            return this;
        }
    }

    public final flags setStructuredLocation(boolean flag)
    {
        if (flag)
        {
            flags = 0x10 | flags;
            return this;
        } else
        {
            flags = flags & 0xffffffef;
            return this;
        }
    }

    ()
    {
        flags = 0;
    }

    public flags(int i)
    {
        flags = i;
    }

    flags(String s)
    {
        flags = 0;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        flags = Integer.decode(s).intValue();
        return;
        s;
        Log.w(EventExtrasFlags.TAG, "Unable to decode event extras flags.", s);
        return;
    }
}
