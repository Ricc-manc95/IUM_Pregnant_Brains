// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import android.content.Context;
import com.google.android.gms.common.api.PendingResult;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsClientBase, SettingsApiStore, SettingsModifications

public final class SettingsClientFutureImpl extends SettingsClientBase
{

    public SettingsClientFutureImpl(SettingsApiStore settingsapistore)
    {
        super(settingsapistore);
    }

    public final volatile void initialize(Context context)
    {
        super.initialize(context);
    }

    public final PendingResult list()
    {
        return new List();
    }

    public final PendingResult read(Account account)
    {
        return new Read(account);
    }

    public final PendingResult update(SettingsModifications settingsmodifications)
    {
        return new Update(settingsmodifications);
    }

    private class List extends FuturePendingResult
    {

        private final SettingsClientFutureImpl this$0;

        protected final Result calculateResult()
            throws Exception
        {
            int j = 0;
            Settings asettings[] = api.list();
            boolean flag;
            int i;
            if (asettings != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                i = 0;
            } else
            {
                i = 13;
            }
            if (flag)
            {
                j = asettings.length;
            }
            return new SettingsClientBase.Result(i, j, null, asettings);
        }

        protected final Result createFailedResult(Throwable throwable)
        {
            return new SettingsClientBase.Result(throwable);
        }

        List()
        {
            this$0 = SettingsClientFutureImpl.this;
            super();
            CalendarExecutor.API.execute(super.future);
        }
    }


    private class Read extends FuturePendingResult
    {

        private final Account account;
        private final SettingsClientFutureImpl this$0;

        protected final Result calculateResult()
            throws Exception
        {
            int j = 0;
            Object obj = SettingsClientFutureImpl.this;
            Account account1 = account;
            obj = ((SettingsClientBase) (obj)).api.read(account1);
            int i;
            if (obj != null)
            {
                i = 0;
            } else
            {
                i = 13;
            }
            if (obj != null)
            {
                j = 1;
            }
            return new SettingsClientBase.Result(i, j, ((Settings) (obj)), null);
        }

        protected final Result createFailedResult(Throwable throwable)
        {
            return new SettingsClientBase.Result(throwable);
        }

        Read(Account account1)
        {
            this$0 = SettingsClientFutureImpl.this;
            super();
            account = account1;
            CalendarExecutor.API.execute(super.future);
        }
    }


    private class Update extends FuturePendingResult
    {

        private final SettingsModifications settings;
        private final SettingsClientFutureImpl this$0;

        protected final Result calculateResult()
            throws Exception
        {
            boolean flag = true;
            SettingsClientFutureImpl settingsclientfutureimpl = SettingsClientFutureImpl.this;
            SettingsModifications settingsmodifications = settings;
            if (settingsmodifications.getOriginal() == null)
            {
                return new SettingsClientBase.Result(13, 0, null, null);
            }
            int i;
            if (((SettingsClientBase) (settingsclientfutureimpl)).api.update(settingsmodifications) != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            return new SettingsClientBase.Result(0, i, null, null);
        }

        protected final Result createFailedResult(Throwable throwable)
        {
            return new SettingsClientBase.Result(throwable);
        }

        Update(SettingsModifications settingsmodifications)
        {
            this$0 = SettingsClientFutureImpl.this;
            super();
            settings = settingsmodifications;
            CalendarExecutor.API.execute(super.future);
        }
    }

}
