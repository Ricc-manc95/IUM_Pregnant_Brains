// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.commonsync.constants;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class EventExtrasFlags
{
    public static final class Builder
    {

        public int flags;

        public final Builder setConferenceData(boolean flag)
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

        public final Builder setEndTimeUnspecified(boolean flag)
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

        public final Builder setEveryoneDeclined(boolean flag)
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

        public final Builder setEveryoneDeclinedDismissed(boolean flag)
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

        public final Builder setHasTimeProposals(boolean flag)
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

        public final Builder setImageAvailable(boolean flag)
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

        public final Builder setOoo(boolean flag)
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

        public final Builder setParticipantStatus(boolean flag)
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

        public final Builder setSmartMailAvailable(boolean flag)
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

        public final Builder setStructuredLocation(boolean flag)
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

        Builder()
        {
            flags = 0;
        }

        public Builder(int i)
        {
            flags = i;
        }

        Builder(String s)
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


    public static final String TAG = com/google/android/apps/calendar/commonsync/constants/EventExtrasFlags.getSimpleName();
    public final int flags;

    public EventExtrasFlags(int i)
    {
        flags = i;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static Builder builder(ContentValues contentvalues, String s)
    {
        contentvalues = contentvalues.getAsInteger(s);
        if (contentvalues == null)
        {
            return new Builder();
        } else
        {
            return new Builder(contentvalues.intValue());
        }
    }

    public static EventExtrasFlags fromCursor(Cursor cursor, int i)
    {
        return new EventExtrasFlags((new Builder(cursor.getString(i))).flags);
    }

    public static EventExtrasFlags fromExisting(int i)
    {
        return new EventExtrasFlags((new Builder(i)).flags);
    }

    public static EventExtrasFlags fromExisting(ContentValues contentvalues, String s)
    {
        contentvalues = contentvalues.getAsInteger(s);
        if (contentvalues == null)
        {
            contentvalues = new Builder();
        } else
        {
            contentvalues = new Builder(contentvalues.intValue());
        }
        return new EventExtrasFlags(((Builder) (contentvalues)).flags);
    }

}
