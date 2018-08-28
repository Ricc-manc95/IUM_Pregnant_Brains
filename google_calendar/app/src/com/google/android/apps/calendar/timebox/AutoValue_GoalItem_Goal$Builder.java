// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


// Referenced classes of package com.google.android.apps.calendar.timebox:
//            AutoValue_GoalItem_Goal

public final class done extends done
{

    private Boolean done;
    private String habitId;
    private Integer type;

    public final done build()
    {
        String s = "";
        if (habitId == null)
        {
            s = String.valueOf("").concat(" habitId");
        }
        String s2 = s;
        if (done == null)
        {
            s2 = String.valueOf(s).concat(" done");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_GoalItem_Goal(habitId, type, done.booleanValue());
        }
    }

    public final done setDone(boolean flag)
    {
        done = Boolean.valueOf(flag);
        return this;
    }

    public final done setHabitId(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null habitId");
        } else
        {
            habitId = s;
            return this;
        }
    }

    public final habitId setType(Integer integer)
    {
        type = integer;
        return this;
    }

    public ()
    {
    }

    ( )
    {
        habitId = .habitId();
        type = .type();
        done = Boolean.valueOf(.done());
    }
}
