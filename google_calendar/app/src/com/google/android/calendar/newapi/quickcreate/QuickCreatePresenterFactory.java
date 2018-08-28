// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.accounts.Account;
import android.support.v7.widget.RecyclerView;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.executors.ThrottlingExecutor;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.net.taskassist.TaskAssistService;
import com.google.android.calendar.newapi.common.ShinobiEditText;
import com.google.android.calendar.newapi.quickcreate.annotation.Annotator;
import com.google.android.calendar.newapi.quickcreate.annotation.RequestFactory;
import com.google.android.calendar.newapi.quickcreate.annotation.TaskAssistServiceFactory;
import com.google.android.calendar.newapi.quickcreate.suggestion.SuggestionAdapter;
import com.google.android.calendar.newapi.quickcreate.text.TextPresenter;
import com.google.common.collect.ImmutableList;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            QuickCreateSession, QuickCreatePresenter, ResultCreator

public final class QuickCreatePresenterFactory
{

    public QuickCreatePresenterFactory()
    {
    }

    public static QuickCreatePresenter createPresenter(RecyclerView recyclerview, ShinobiEditText shinobiedittext, QuickCreateSession quickcreatesession, ResultCreator resultcreator, boolean flag, Account account, ImmutableList immutablelist)
    {
        android.content.Context context = recyclerview.getContext();
        shinobiedittext = new TextPresenter(shinobiedittext, immutablelist);
        account = new SuggestionAdapter(flag, account.name);
        recyclerview.setAdapter(account);
        new TaskAssistServiceFactory();
        recyclerview = new Annotator(new TaskAssistService(context, quickcreatesession.getAccount().name, ExperimentalOptions.isTaskAssistStagingEnabled(context)), RequestFactory.create(context, quickcreatesession.getType()), new ThrottlingExecutor(CalendarExecutor.MAIN, 200L), quickcreatesession.getId());
        quickcreatesession = new QuickCreatePresenter(recyclerview, account, shinobiedittext, resultcreator);
        recyclerview.listener = quickcreatesession;
        account.listener = quickcreatesession;
        shinobiedittext.listener = quickcreatesession;
        shinobiedittext.updateTextListener();
        return quickcreatesession;
    }
}
