// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.accounts.Account;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.widget.Filter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.android.ex.chips:
//            RecipientEntry, BaseRecipientAdapter, ChipsUtil

final class this._cls0 extends Filter
{

    private final BaseRecipientAdapter this$0;

    public final CharSequence convertResultToString(Object obj)
    {
        Object obj1 = (RecipientEntry)obj;
        obj = ((RecipientEntry) (obj1)).displayName;
        obj1 = ((RecipientEntry) (obj1)).destination;
        if (TextUtils.isEmpty(((CharSequence) (obj))) || TextUtils.equals(((CharSequence) (obj)), ((CharSequence) (obj1))))
        {
            return ((CharSequence) (obj1));
        } else
        {
            return (new Rfc822Token(((String) (obj)), ((String) (obj1)), null)).toString();
        }
    }

    protected final android.widget.ilter performFiltering(CharSequence charsequence)
    {
        Object obj;
        Object obj1;
        obj = null;
        obj1 = new android.widget.yEntry();
        if (TextUtils.isEmpty(charsequence))
        {
            tempEntries = null;
            return ((android.widget.ies) (obj1));
        }
        android.content.Context context = BaseRecipientAdapter.this.context;
        BaseRecipientAdapter baserecipientadapter = BaseRecipientAdapter.this;
        if (!ChipsUtil.hasPermissions(context, null))
        {
            tempEntries = null;
            return ((android.widget.ies) (obj1));
        }
        Object obj2;
        int i;
        boolean flag;
        obj2 = BaseRecipientAdapter.this;
        i = mPreferredMaxResultCount;
        flag = ChipsUtil.hasPermissions(((BaseRecipientAdapter) (obj2)).context, null);
        if (flag) goto _L2; else goto _L1
_L1:
        charsequence = ((CharSequence) (obj));
_L6:
        if (charsequence == null) goto _L4; else goto _L3
_L3:
        HashSet hashset;
        obj = new LinkedHashMap();
        obj2 = new ArrayList();
        hashset = new HashSet();
        for (; charsequence.moveToNext(); BaseRecipientAdapter.putOneEntry(new (charsequence, null), true, ((LinkedHashMap) (obj)), ((List) (obj2)), hashset)) { }
          goto _L5
        obj1;
        obj = charsequence;
        charsequence = ((CharSequence) (obj1));
_L7:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw charsequence;
_L2:
        charsequence = ((BaseRecipientAdapter) (obj2)).queryMode..buildUpon().rUri(charsequence.toString()).rUri("limit", String.valueOf(i + 5));
        if (true)
        {
            break MISSING_BLOCK_LABEL_219;
        }
        charsequence.rUri("directory", String.valueOf(null));
        if (((BaseRecipientAdapter) (obj2)).account != null)
        {
            charsequence.nt("name_for_primary_account", ((BaseRecipientAdapter) (obj2)).account.name);
            charsequence.nt("type_for_primary_account", ((BaseRecipientAdapter) (obj2)).account.type);
        }
        System.currentTimeMillis();
        charsequence = ((BaseRecipientAdapter) (obj2)).contentResolver.query(charsequence.ntResolver(), ((BaseRecipientAdapter) (obj2)).queryMode.r, null, null, null);
        System.currentTimeMillis();
          goto _L6
_L5:
        List list = constructEntryList(((LinkedHashMap) (obj)), ((List) (obj2)));
        obj1.tEntryList = new esult(list, ((LinkedHashMap) (obj)), ((List) (obj2)), hashset, searchOtherDirectories(hashset));
        obj1.herDirectories = list.size();
_L4:
        if (charsequence != null)
        {
            charsequence.close();
        }
        return ((android.widget.herDirectories) (obj1));
        charsequence;
        obj = null;
          goto _L7
    }

    protected final void publishResults(CharSequence charsequence, android.widget.ilter ilter)
    {
        mCurrentConstraint = charsequence;
        tempEntries = null;
        if (ilter.ies != null)
        {
            ilter = (esult)ilter.ilterResult;
            entryMap = ((esult) (ilter)).entryMap;
            nonAggregatedEntries = ((esult) (ilter)).nonAggregatedEntries;
            existingDestinations = ((esult) (ilter)).existingDestinations;
            BaseRecipientAdapter baserecipientadapter = BaseRecipientAdapter.this;
            int j = ((esult) (ilter)).entries.size();
            List list;
            int i;
            if (((esult) (ilter)).paramsList == null)
            {
                i = 0;
            } else
            {
                i = ((esult) (ilter)).paramsList.size();
            }
            if (j == 0 && i > 1)
            {
                baserecipientadapter.tempEntries = baserecipientadapter.entries;
            }
            baserecipientadapter = BaseRecipientAdapter.this;
            list = ((esult) (ilter)).entries;
            baserecipientadapter.entries = list;
            baserecipientadapter.entriesUpdatedObserver.onChanged(list);
            baserecipientadapter.notifyDataSetChanged();
            if (((esult) (ilter)).paramsList != null)
            {
                i = mPreferredMaxResultCount;
                int k = ((esult) (ilter)).existingDestinations.size();
                startSearchOtherDirectories(charsequence, ((esult) (ilter)).paramsList, i - k);
            }
            return;
        } else
        {
            charsequence = BaseRecipientAdapter.this;
            ilter = Collections.emptyList();
            charsequence.entries = ilter;
            ((BaseRecipientAdapter) (charsequence)).entriesUpdatedObserver.onChanged(ilter);
            charsequence.notifyDataSetChanged();
            return;
        }
    }

    Observer()
    {
        this$0 = BaseRecipientAdapter.this;
        super();
    }
}
