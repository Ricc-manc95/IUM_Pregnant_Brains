// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.ContentUris;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.base.Splitter;
import java.util.Comparator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventKey, AutoValue_CpEventKey

public abstract class CpEventKey extends EventKey
    implements EventKey.Persisted
{

    public static final Comparator COMPARATOR;

    CpEventKey()
    {
    }

    static final int lambda$static$0$CpEventKey(CpEventKey cpeventkey, CpEventKey cpeventkey1)
    {
        long l;
        long l2;
        l = cpeventkey.localId();
        l2 = cpeventkey1.localId();
        if (l == l2) goto _L2; else goto _L1
_L1:
        if (l >= l2) goto _L4; else goto _L3
_L3:
        return -1;
_L4:
        return 1;
_L2:
        long l1 = cpeventkey.startMillis();
        long l3 = cpeventkey1.startMillis();
        if (l1 >= l3)
        {
            return l1 != l3 ? 1 : 0;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public static CpEventKey newInstance(long l)
    {
        return newInstance(false, 0L, l);
    }

    public static CpEventKey newInstance(long l, long l1)
    {
        return newInstance(true, l1, l);
    }

    static CpEventKey newInstance(String s)
    {
        s = SERIALIZED_DATA_SPLITTER.splitToList(s);
        if (s.size() == 2)
        {
            long l = Long.parseLong((String)s.get(0));
            return newInstance(true, Long.parseLong((String)s.get(1)), l);
        } else
        {
            return newInstance(false, 0L, Long.parseLong((String)s.get(0)));
        }
    }

    private static CpEventKey newInstance(boolean flag, long l, long l1)
    {
        boolean flag1;
        if (l1 > 0L)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException();
        } else
        {
            return new AutoValue_CpEventKey(flag, l, l1);
        }
    }

    public abstract boolean hasStartMillis();

    public abstract long localId();

    protected final void serializeInternal(StringBuilder stringbuilder)
    {
        stringbuilder.append(localId());
        if (hasStartMillis())
        {
            stringbuilder.append('|').append(startMillis());
        }
    }

    public abstract long startMillis();

    public Optional uri()
    {
        android.net.Uri uri1 = ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, localId());
        if (uri1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(uri1);
        }
    }

    static 
    {
        class .Lambda._cls0
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls0();

            public final int compare(Object obj, Object obj1)
            {
                return CpEventKey.lambda$static$0$CpEventKey((CpEventKey)obj, (CpEventKey)obj1);
            }


            private .Lambda._cls0()
            {
            }
        }

        COMPARATOR = .Lambda._cls0..instance;
    }
}
