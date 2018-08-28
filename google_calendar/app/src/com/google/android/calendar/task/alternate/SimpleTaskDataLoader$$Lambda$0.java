// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import android.accounts.Account;
import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.task.TaskConnection;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.task.alternate:
//            SimpleTaskDataLoader

final class arg._cls3
    implements AsyncFunction
{

    private final SimpleTaskDataLoader arg$1;
    private final int arg$2;
    private final int arg$3;

    public final ListenableFuture apply(Object obj)
    {
        SimpleTaskDataLoader simpletaskdataloader = arg$1;
        int i = arg$2;
        int j = arg$3;
        ImmutableMap immutablemap = (ImmutableMap)obj;
        long l = TimeBoxUtil.julianDayToMs(simpletaskdataloader.timeZone, i);
        long l1 = TimeBoxUtil.julianDayToMs(simpletaskdataloader.timeZone, j + 1);
        ArrayList arraylist = new ArrayList();
        Account aaccount[] = AccountsUtil.getGoogleAccounts(simpletaskdataloader.context);
        int k = aaccount.length;
        i = 0;
        while (i < k) 
        {
            Account account = aaccount[i];
            obj = (Settings)immutablemap.get(account);
            boolean flag;
            if (obj instanceof GoogleSettings)
            {
                obj = (GoogleSettings)obj;
            } else
            {
                obj = null;
            }
            if (obj != null && ((GoogleSettings) (obj)).areTasksVisible())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                arraylist.add((FluentFuture)AbstractTransformFuture.create(simpletaskdataloader.taskConnection.loadTasksAsync(simpletaskdataloader.context, account.name, l, l1), new <init>(simpletaskdataloader, account, ((GoogleSettings) (obj))), com.google.common.util.concurrent.STANCE));
            }
            i++;
        }
        obj = new com.google.common.util.concurrent.it>(ImmutableList.copyOf(arraylist), true);
        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
        }
        return (FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), .instance, com.google.common.util.concurrent.STANCE);
    }

    (SimpleTaskDataLoader simpletaskdataloader, int i, int j)
    {
        arg$1 = simpletaskdataloader;
        arg$2 = i;
        arg$3 = j;
    }
}
