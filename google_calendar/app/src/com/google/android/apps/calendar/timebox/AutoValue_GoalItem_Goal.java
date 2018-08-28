// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;


final class AutoValue_GoalItem_Goal extends GoalItem.Goal
{

    private final boolean done;
    private final String habitId;
    private final Integer type;

    AutoValue_GoalItem_Goal(String s, Integer integer, boolean flag)
    {
        habitId = s;
        type = integer;
        done = flag;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof GoalItem.Goal)
            {
                if (!habitId.equals(((GoalItem.Goal) (obj = (GoalItem.Goal)obj)).getHabitId()) || (type != null ? !type.equals(((GoalItem.Goal) (obj)).getType()) : ((GoalItem.Goal) (obj)).getType() != null) || done != ((GoalItem.Goal) (obj)).isDone())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final String getHabitId()
    {
        return habitId;
    }

    public final Integer getType()
    {
        return type;
    }

    public final int hashCode()
    {
        int j = habitId.hashCode();
        int i;
        char c;
        if (type == null)
        {
            i = 0;
        } else
        {
            i = type.hashCode();
        }
        if (done)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ (i ^ (j ^ 0xf4243) * 0xf4243) * 0xf4243;
    }

    public final boolean isDone()
    {
        return done;
    }

    public final GoalItem.Goal.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        String s = habitId;
        String s1 = String.valueOf(type);
        boolean flag = done;
        return (new StringBuilder(String.valueOf(s).length() + 33 + String.valueOf(s1).length())).append("Goal{habitId=").append(s).append(", type=").append(s1).append(", done=").append(flag).append("}").toString();
    }

    private class Builder extends GoalItem.Goal.Builder
    {

        private Boolean done;
        private String habitId;
        private Integer type;

        public final GoalItem.Goal build()
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

        public final GoalItem.Goal.Builder setDone(boolean flag)
        {
            done = Boolean.valueOf(flag);
            return this;
        }

        public final GoalItem.Goal.Builder setHabitId(String s)
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

        public final GoalItem.Goal.Builder setType(Integer integer)
        {
            type = integer;
            return this;
        }

        public Builder()
        {
        }

        Builder(GoalItem.Goal goal)
        {
            habitId = goal.getHabitId();
            type = goal.getType();
            done = Boolean.valueOf(goal.isDone());
        }
    }

}
