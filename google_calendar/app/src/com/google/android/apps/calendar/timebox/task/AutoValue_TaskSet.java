// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskSet

final class AutoValue_TaskSet extends TaskSet
{

    private final boolean done;
    private final ImmutableList items;

    AutoValue_TaskSet(boolean flag, ImmutableList immutablelist)
    {
        done = flag;
        items = immutablelist;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TaskSet)
            {
                if (done != ((TaskSet) (obj = (TaskSet)obj)).isDone() || !items.equals(((TaskSet) (obj)).getItems()))
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

    public final ImmutableList getItems()
    {
        return items;
    }

    public final int hashCode()
    {
        char c;
        if (done)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return (c ^ 0xf4243) * 0xf4243 ^ items.hashCode();
    }

    public final boolean isDone()
    {
        return done;
    }

    public final TaskSet.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        boolean flag = done;
        String s = String.valueOf(items);
        return (new StringBuilder(String.valueOf(s).length() + 27)).append("TaskSet{done=").append(flag).append(", items=").append(s).append("}").toString();
    }

    private class Builder extends TaskSet.Builder
    {

        private Boolean done;
        private ImmutableList items;
        private com.google.common.collect.ImmutableList.Builder itemsBuilder$;

        public final TaskSet build()
        {
            String s;
            if (itemsBuilder$ != null)
            {
                com.google.common.collect.ImmutableList.Builder builder = itemsBuilder$;
                builder.forceCopy = true;
                items = ImmutableList.asImmutableList(builder.contents, builder.size);
            } else
            if (items == null)
            {
                items = ImmutableList.of();
            }
            s = "";
            if (done == null)
            {
                s = String.valueOf("").concat(" done");
            }
            if (!s.isEmpty())
            {
                s = String.valueOf(s);
                if (s.length() != 0)
                {
                    s = "Missing required properties:".concat(s);
                } else
                {
                    s = new String("Missing required properties:");
                }
                throw new IllegalStateException(s);
            } else
            {
                return new AutoValue_TaskSet(done.booleanValue(), items);
            }
        }

        public final com.google.common.collect.ImmutableList.Builder itemsBuilder()
        {
            if (itemsBuilder$ == null)
            {
                if (items == null)
                {
                    itemsBuilder$ = ImmutableList.builder();
                } else
                {
                    itemsBuilder$ = ImmutableList.builder();
                    com.google.common.collect.ImmutableList.Builder builder = (com.google.common.collect.ImmutableList.Builder)itemsBuilder$.addAll(items);
                    items = null;
                }
            }
            return itemsBuilder$;
        }

        public final TaskSet.Builder setDone(boolean flag)
        {
            done = Boolean.valueOf(flag);
            return this;
        }

        public final TaskSet.Builder setItems(ImmutableList immutablelist)
        {
            if (immutablelist == null)
            {
                throw new NullPointerException("Null items");
            }
            if (itemsBuilder$ != null)
            {
                throw new IllegalStateException("Cannot set items after calling itemsBuilder()");
            } else
            {
                items = immutablelist;
                return this;
            }
        }

        public Builder()
        {
        }

        Builder(TaskSet taskset)
        {
            done = Boolean.valueOf(taskset.isDone());
            items = taskset.getItems();
        }
    }

}
