// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.accounts.Account;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Filter;
import java.util.ArrayList;

// Referenced classes of package com.android.ex.chips:
//            BaseRecipientAdapter, ChipsUtil

public final class params extends Filter
{

    private int limit;
    private final arams params;
    private final BaseRecipientAdapter this$0;

    private final int getLimit()
    {
        this;
        JVM INSTR monitorenter ;
        int i = limit;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    protected final android.widget.ilter performFiltering(CharSequence charsequence)
    {
        Object obj;
        obj = new android.widget.ner();
        obj.ner = null;
        obj.ner = 0;
        if (TextUtils.isEmpty(charsequence)) goto _L2; else goto _L1
_L1:
        Object obj1 = new ArrayList();
        BaseRecipientAdapter baserecipientadapter;
        Long long1;
        int i;
        boolean flag;
        baserecipientadapter = BaseRecipientAdapter.this;
        i = getLimit();
        long1 = Long.valueOf(params.directoryId);
        flag = ChipsUtil.hasPermissions(baserecipientadapter.context, null);
        if (flag) goto _L4; else goto _L3
_L3:
        charsequence = null;
_L9:
        if (charsequence == null) goto _L6; else goto _L5
_L5:
        for (; charsequence.moveToNext(); ((ArrayList) (obj1)).add(new init>(charsequence, Long.valueOf(params.directoryId)))) { }
          goto _L6
        obj1;
        obj = charsequence;
        charsequence = ((CharSequence) (obj1));
_L7:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw charsequence;
_L4:
        charsequence = baserecipientadapter.queryMode.directoryId.buildUpon().ri(charsequence.toString()).ri("limit", String.valueOf(i + 5));
        if (long1 == null)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        charsequence.ri("directory", String.valueOf(long1));
        if (baserecipientadapter.account != null)
        {
            charsequence.("name_for_primary_account", baserecipientadapter.account.name);
            charsequence.("type_for_primary_account", baserecipientadapter.account.type);
        }
        System.currentTimeMillis();
        charsequence = baserecipientadapter.contentResolver.query(charsequence.Resolver(), baserecipientadapter.queryMode.directoryId, null, null, null);
        System.currentTimeMillis();
        continue; /* Loop/switch isn't completed */
        charsequence;
        obj = null;
        if (true) goto _L7; else goto _L6
_L6:
        if (charsequence != null)
        {
            charsequence.close();
        }
        if (!((ArrayList) (obj1)).isEmpty())
        {
            obj.olver = obj1;
            obj.olver = ((ArrayList) (obj1)).size();
        }
_L2:
        return ((android.widget.olver) (obj));
        if (true) goto _L9; else goto _L8
_L8:
    }

    protected final void publishResults(CharSequence charsequence, android.widget.ilter ilter)
    {
        delayedMessageHandler.removeMessages(1);
        if (TextUtils.equals(charsequence, mCurrentConstraint))
        {
            if (ilter.nstraint > 0)
            {
                charsequence = (ArrayList)(ArrayList)ilter.nstraint;
                int j = charsequence.size();
                int i = 0;
                while (i < j) 
                {
                    arams.directoryId directoryid = (nt)charsequence.get(i);
                    BaseRecipientAdapter baserecipientadapter = BaseRecipientAdapter.this;
                    boolean flag;
                    if (params.directoryId == 0L)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    baserecipientadapter.putOneEntry(directoryid, flag);
                    i++;
                }
            }
            charsequence = BaseRecipientAdapter.this;
            charsequence.remainingDirectoryCount = ((BaseRecipientAdapter) (charsequence)).remainingDirectoryCount - 1;
            if (remainingDirectoryCount > 0)
            {
                charsequence = delayedMessageHandler;
                charsequence.sendMessageDelayed(charsequence.obtainMessage(1, 0, 0, null), 1000L);
            }
            if (ilter.obtainMessage > 0 || remainingDirectoryCount == 0)
            {
                tempEntries = null;
            }
        }
        charsequence = BaseRecipientAdapter.this;
        ilter = constructEntryList();
        charsequence.entries = ilter;
        ((BaseRecipientAdapter) (charsequence)).entriesUpdatedObserver.onChanged(ilter);
        charsequence.notifyDataSetChanged();
    }

    public final void setLimit(int i)
    {
        this;
        JVM INSTR monitorenter ;
        limit = i;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public arams(arams arams)
    {
        this$0 = BaseRecipientAdapter.this;
        super();
        params = arams;
    }
}
