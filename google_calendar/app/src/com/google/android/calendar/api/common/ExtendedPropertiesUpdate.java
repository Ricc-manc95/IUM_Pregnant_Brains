// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import android.accounts.Account;
import android.content.ContentProviderOperation;
import com.google.calendar.v2a.android.util.provider.Selection;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.common:
//            ExtendedPropertiesUtils

public final class ExtendedPropertiesUpdate
{

    public final List deletes = new ArrayList();
    public final List updates = new ArrayList();

    public ExtendedPropertiesUpdate()
    {
    }

    public static ContentProviderOperation createDeleteOperation(String s, Account account, long l)
    {
        Object obj = Selection.where("event_id").this$0;
        obj.argsCount = ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).argsCount + 1;
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append("=?");
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append(" AND ");
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append("name");
        obj = (new com.google.calendar.v2a.android.util.provider.Selection.Builder.ColumnExpression(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)))).this$0;
        obj.argsCount = ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).argsCount + 1;
        ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.append("=?");
        obj = new Selection(((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).filterString.toString(), ((com.google.calendar.v2a.android.util.provider.Selection.Builder) (obj)).argsCount);
        String s1 = Long.toString(l);
        account = ContentProviderOperation.newDelete(ExtendedPropertiesUtils.getExtendedPropertyUri(account));
        account.withSelection(((Selection) (obj)).filterString, new String[] {
            s1, s
        });
        return account.build();
    }
}
