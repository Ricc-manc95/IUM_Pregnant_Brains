// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;


// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskImpl, TaskData

final class AutoValue_TaskImpl extends TaskImpl
{

    private final TaskData taskData;

    AutoValue_TaskImpl(TaskData taskdata)
    {
        taskData = taskdata;
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof TaskImpl)
        {
            obj = (TaskImpl)obj;
            return taskData.equals(((TaskImpl) (obj)).getTaskData());
        } else
        {
            return false;
        }
    }

    public final TaskData getTaskData()
    {
        return taskData;
    }

    public final int hashCode()
    {
        return 0xf4243 ^ taskData.hashCode();
    }

    public final TaskImpl.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        String s = String.valueOf(taskData);
        return (new StringBuilder(String.valueOf(s).length() + 19)).append("TaskImpl{taskData=").append(s).append("}").toString();
    }

    private class Builder extends TaskImpl.Builder
    {

        private TaskData taskData;

        public final TaskImpl build()
        {
            String s = "";
            if (taskData == null)
            {
                s = String.valueOf("").concat(" taskData");
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
                return new AutoValue_TaskImpl(taskData);
            }
        }

        public final TaskImpl.Builder setTaskData(TaskData taskdata)
        {
            if (taskdata == null)
            {
                throw new NullPointerException("Null taskData");
            } else
            {
                taskData = taskdata;
                return this;
            }
        }

        Builder()
        {
        }

        Builder(TaskImpl taskimpl)
        {
            taskData = taskimpl.getTaskData();
        }
    }

}
