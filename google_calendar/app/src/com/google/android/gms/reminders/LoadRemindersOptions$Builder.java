// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import com.google.android.gms.reminders.model.TaskId;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders:
//            LoadRemindersOptions

public static final class zzcfZ
{

    public int zzbSV;
    public List zzcfN;
    public Long zzcfO;
    public Long zzcfP;
    private Long zzcfQ;
    private Long zzcfR;
    public boolean zzcfS;
    public int zzcfT;
    private boolean zzcfU;
    private boolean zzcfV;
    private int zzcfW;
    public List zzcfX;
    private Long zzcfY;
    private Long zzcfZ;
    public TaskId zzcga[];

    public final LoadRemindersOptions build()
    {
        if (zzcga == null)
        {
            return new LoadRemindersOptions(null, zzcfN, zzcfO, zzcfP, null, null, zzcfS, zzcfT, false, false, zzcfW, zzbSV, zzcfX, null, null);
        }
        ArrayList arraylist = new ArrayList();
        TaskId ataskid[] = zzcga;
        int j = ataskid.length;
        for (int i = 0; i < j; i++)
        {
            arraylist.add(ataskid[i].getClientAssignedId());
        }

        return new LoadRemindersOptions(arraylist, zzcfN, zzcfO, zzcfP, null, null, zzcfS, zzcfT, false, false, zzcfW, zzbSV, zzcfX, null, null);
    }

    public final transient zzcfX setLoadReminderType(int ai[])
    {
        if (ai == null)
        {
            throw new NullPointerException(String.valueOf(" The types should not be null"));
        }
        boolean flag;
        if (ai.length != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("The types should not be empty"));
        }
        zzcfW = 0;
        int j = ai.length;
        int i = 0;
        while (i < j) 
        {
            int k = ai[i];
            String s;
            boolean flag1;
            if (k == -1 || k == 0 || k == 1 || k == 2)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            s = (new StringBuilder(38)).append("Invalid load reminder type:").append(k).toString();
            if (!flag1)
            {
                throw new IllegalArgumentException(String.valueOf(s));
            }
            if (k == -1)
            {
                zzcfW = -1;
            } else
            {
                zzcfW = zzcfW | 1 << k;
            }
            i++;
        }
        return this;
    }

    public ()
    {
        zzcfO = null;
        zzcfP = null;
        zzcfQ = null;
        zzcfR = null;
        zzcfS = false;
        zzcfT = 0;
        zzcfU = false;
        zzcfV = false;
        zzcfW = -1;
        zzbSV = 0;
        zzcfX = null;
        zzcfY = null;
        zzcfZ = null;
    }
}
