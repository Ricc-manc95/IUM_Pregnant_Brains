// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.android.ex.chips:
//            DefaultPhotoManager, Queries, RecipientEntry, PhotoManager, 
//            ChipsUtil

public class BaseRecipientAdapter extends BaseAdapter
    implements Filterable, PhotoManager.PhotoManagerCallback
{

    public Account account;
    public final ContentResolver contentResolver;
    public final Context context;
    public final DelayedMessageHandler delayedMessageHandler;
    public List entries;
    public EntriesUpdatedObserver entriesUpdatedObserver;
    public LinkedHashMap entryMap;
    public Set existingDestinations;
    public CharSequence mCurrentConstraint;
    public int mPreferredMaxResultCount;
    public List nonAggregatedEntries;
    public PhotoManager photoManager;
    public final Queries.Query queryMode;
    public int remainingDirectoryCount;
    public List tempEntries;

    public BaseRecipientAdapter(Context context1, int i)
    {
        this(context1, i, 0);
    }

    private BaseRecipientAdapter(Context context1, int i, int j)
    {
        delayedMessageHandler = new DelayedMessageHandler();
        context = context1;
        contentResolver = context1.getContentResolver();
        mPreferredMaxResultCount = i;
        photoManager = new DefaultPhotoManager(contentResolver);
        queryMode = Queries.EMAIL;
    }

    static void putOneEntry(TemporaryEntry temporaryentry, boolean flag, LinkedHashMap linkedhashmap, List list, Set set)
    {
        if (set.contains(temporaryentry.destination))
        {
            return;
        }
        set.add(temporaryentry.destination);
        if (!flag)
        {
            list.add(RecipientEntry.constructTopLevelEntry(temporaryentry.displayName, temporaryentry.displayNameSource, temporaryentry.destination, temporaryentry.destinationType, temporaryentry.destinationLabel, temporaryentry.contactId, temporaryentry.directoryId, temporaryentry.dataId, temporaryentry.thumbnailUriString, true, temporaryentry.lookupKey));
            return;
        }
        if (linkedhashmap.containsKey(Long.valueOf(temporaryentry.contactId)))
        {
            set = (List)linkedhashmap.get(Long.valueOf(temporaryentry.contactId));
            linkedhashmap = temporaryentry.displayName;
            int i = temporaryentry.displayNameSource;
            list = temporaryentry.destination;
            int j = temporaryentry.destinationType;
            String s = temporaryentry.destinationLabel;
            long l = temporaryentry.contactId;
            Long long1 = temporaryentry.directoryId;
            long l1 = temporaryentry.dataId;
            String s2 = temporaryentry.thumbnailUriString;
            String s1 = temporaryentry.lookupKey;
            if (i > 20)
            {
                temporaryentry = linkedhashmap;
            } else
            {
                temporaryentry = list;
            }
            if (s2 != null)
            {
                linkedhashmap = Uri.parse(s2);
            } else
            {
                linkedhashmap = null;
            }
            set.add(new RecipientEntry(0, temporaryentry, list, j, s, l, long1, l1, linkedhashmap, false, true, s1, null));
            return;
        } else
        {
            list = new ArrayList();
            list.add(RecipientEntry.constructTopLevelEntry(temporaryentry.displayName, temporaryentry.displayNameSource, temporaryentry.destination, temporaryentry.destinationType, temporaryentry.destinationLabel, temporaryentry.contactId, temporaryentry.directoryId, temporaryentry.dataId, temporaryentry.thumbnailUriString, true, temporaryentry.lookupKey));
            linkedhashmap.put(Long.valueOf(temporaryentry.contactId), list);
            return;
        }
    }

    private static List setupOtherDirectories(Context context1, Cursor cursor, Account account1)
    {
        PackageManager packagemanager = context1.getPackageManager();
        ArrayList arraylist = new ArrayList();
        context1 = null;
        do
        {
            if (!cursor.moveToNext())
            {
                break;
            }
            long l = cursor.getLong(0);
            if (l != 1L)
            {
                DirectorySearchParams directorysearchparams = new DirectorySearchParams();
                String s = cursor.getString(4);
                int i = cursor.getInt(5);
                directorysearchparams.directoryId = l;
                directorysearchparams.displayName = cursor.getString(3);
                directorysearchparams.accountName = cursor.getString(1);
                directorysearchparams.accountType = cursor.getString(2);
                if (s != null && i != 0)
                {
                    try
                    {
                        directorysearchparams.directoryType = packagemanager.getResourcesForApplication(s).getString(i);
                        if (directorysearchparams.directoryType == null)
                        {
                            Log.e("chips", (new StringBuilder(String.valueOf(s).length() + 43)).append("Cannot resolve directory name: ").append(i).append("@").append(s).toString());
                        }
                    }
                    catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
                    {
                        Log.e("chips", (new StringBuilder(String.valueOf(s).length() + 43)).append("Cannot resolve directory name: ").append(i).append("@").append(s).toString(), namenotfoundexception);
                    }
                }
                if (context1 == null && account1 != null && account1.name.equals(directorysearchparams.accountName) && account1.type.equals(directorysearchparams.accountType))
                {
                    context1 = directorysearchparams;
                } else
                {
                    arraylist.add(directorysearchparams);
                }
            }
        } while (true);
        if (context1 != null)
        {
            arraylist.add(1, context1);
        }
        return arraylist;
    }

    public List constructEntryList()
    {
        return constructEntryList(entryMap, nonAggregatedEntries);
    }

    final List constructEntryList(LinkedHashMap linkedhashmap, List list)
    {
        ArrayList arraylist;
        int k;
label0:
        {
            arraylist = new ArrayList();
            linkedhashmap = linkedhashmap.entrySet().iterator();
            int i = 0;
            while (linkedhashmap.hasNext()) 
            {
                List list1 = (List)((java.util.Map.Entry)linkedhashmap.next()).getValue();
                int l = list1.size();
                for (int j = 0; j < l; j++)
                {
                    RecipientEntry recipiententry = (RecipientEntry)list1.get(j);
                    arraylist.add(recipiententry);
                    photoManager.populatePhotoBytesAsync(recipiententry, this);
                    i++;
                }

                k = i;
                if (i > mPreferredMaxResultCount)
                {
                    break label0;
                }
            }
            k = i;
        }
        if (k <= mPreferredMaxResultCount)
        {
            linkedhashmap = list.iterator();
            do
            {
                if (!linkedhashmap.hasNext())
                {
                    break;
                }
                list = (RecipientEntry)linkedhashmap.next();
                if (k > mPreferredMaxResultCount)
                {
                    break;
                }
                arraylist.add(list);
                photoManager.populatePhotoBytesAsync(list, this);
                k++;
            } while (true);
        }
        return arraylist;
    }

    public int getCount()
    {
        List list;
        if (tempEntries != null)
        {
            list = tempEntries;
        } else
        {
            list = entries;
        }
        if (list != null)
        {
            return list.size();
        } else
        {
            return 0;
        }
    }

    public Filter getFilter()
    {
        return new DefaultFilter();
    }

    public Object getItem(int i)
    {
        List list;
        if (tempEntries != null)
        {
            list = tempEntries;
        } else
        {
            list = entries;
        }
        return (RecipientEntry)list.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public int getItemViewType(int i)
    {
        List list;
        if (tempEntries != null)
        {
            list = tempEntries;
        } else
        {
            list = entries;
        }
        return ((RecipientEntry)list.get(i)).entryType;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if (tempEntries != null)
        {
            view = tempEntries;
        } else
        {
            view = entries;
        }
        view.get(i);
        if (mCurrentConstraint != null)
        {
            mCurrentConstraint.toString();
        }
        throw new NoSuchMethodError();
    }

    public int getViewTypeCount()
    {
        return 2;
    }

    public boolean isEnabled(int i)
    {
        Object obj;
        if (tempEntries != null)
        {
            obj = tempEntries;
        } else
        {
            obj = entries;
        }
        obj = (RecipientEntry)((List) (obj)).get(i);
        return ((RecipientEntry) (obj)).entryType == 0 || ((RecipientEntry) (obj)).entryType == 1;
    }

    public final void onPhotoBytesAsyncLoadFailed()
    {
    }

    public final void onPhotoBytesAsynchronouslyPopulated()
    {
        notifyDataSetChanged();
    }

    public final void onPhotoBytesPopulated()
    {
    }

    public void putOneEntry(TemporaryEntry temporaryentry, boolean flag)
    {
        putOneEntry(temporaryentry, flag, entryMap, nonAggregatedEntries, existingDestinations);
    }

    public final List searchOtherDirectories(Set set)
    {
        List list = null;
        if (ChipsUtil.hasPermissions(context, null)) goto _L2; else goto _L1
_L1:
        Log.e("chips", "Not searching other directories because we don't have required permissions.");
        set = null;
_L4:
        return set;
_L2:
        if (mPreferredMaxResultCount - set.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        set = list;
        Cursor cursor = contentResolver.query(DirectoryListQuery.URI, DirectoryListQuery.PROJECTION, null, null, null);
        set = cursor;
        list = setupOtherDirectories(context, cursor, account);
        set = list;
        if (cursor == null) goto _L4; else goto _L3
_L3:
        cursor.close();
        return list;
        Exception exception;
        exception;
        if (set != null)
        {
            set.close();
        }
        throw exception;
        return null;
    }

    public final void startSearchOtherDirectories(CharSequence charsequence, List list, int i)
    {
        int k = list.size();
        for (int j = 1; j < k; j++)
        {
            DirectorySearchParams directorysearchparams = (DirectorySearchParams)list.get(j);
            if (directorysearchparams.filter == null)
            {
                directorysearchparams.filter = new DirectoryFilter(directorysearchparams);
            }
            directorysearchparams.filter.setLimit(i);
            directorysearchparams.filter.filter(charsequence);
        }

        remainingDirectoryCount = k - 1;
        charsequence = delayedMessageHandler;
        charsequence.sendMessageDelayed(charsequence.obtainMessage(1, 0, 0, null), 1000L);
    }

    private class DelayedMessageHandler extends Handler
    {

        private final BaseRecipientAdapter this$0;

        public final void handleMessage(Message message)
        {
            if (remainingDirectoryCount > 0)
            {
                message = BaseRecipientAdapter.this;
                List list = constructEntryList();
                message.entries = list;
                ((BaseRecipientAdapter) (message)).entriesUpdatedObserver.onChanged(list);
                message.notifyDataSetChanged();
            }
        }

        DelayedMessageHandler()
        {
            this$0 = BaseRecipientAdapter.this;
            super();
        }

        private class EntriesUpdatedObserver
        {

            public abstract void onChanged(List list);
        }

    }


    private class TemporaryEntry
    {

        public final long contactId;
        public final long dataId;
        public final String destination;
        public final String destinationLabel;
        public final int destinationType;
        public final Long directoryId;
        public final String displayName;
        public final int displayNameSource;
        public final String lookupKey;
        public final String thumbnailUriString;

        public TemporaryEntry(Cursor cursor, Long long1)
        {
            displayName = cursor.getString(0);
            destination = cursor.getString(1);
            destinationType = cursor.getInt(2);
            destinationLabel = cursor.getString(3);
            contactId = cursor.getLong(4);
            directoryId = long1;
            dataId = cursor.getLong(5);
            thumbnailUriString = cursor.getString(6);
            displayNameSource = cursor.getInt(7);
            lookupKey = cursor.getString(8);
        }
    }


    private class DirectorySearchParams
    {

        public String accountName;
        public String accountType;
        public long directoryId;
        public String directoryType;
        public String displayName;
        public DirectoryFilter filter;

        public DirectorySearchParams()
        {
        }
    }


    private class DefaultFilter extends Filter
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

        protected final android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
        {
            Object obj;
            Object obj1;
            obj = null;
            obj1 = new android.widget.Filter.FilterResults();
            if (TextUtils.isEmpty(charsequence))
            {
                tempEntries = null;
                return ((android.widget.Filter.FilterResults) (obj1));
            }
            Context context1 = context;
            BaseRecipientAdapter baserecipientadapter = BaseRecipientAdapter.this;
            if (!ChipsUtil.hasPermissions(context1, null))
            {
                tempEntries = null;
                return ((android.widget.Filter.FilterResults) (obj1));
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
            for (; charsequence.moveToNext(); BaseRecipientAdapter.putOneEntry(new TemporaryEntry(charsequence, null), true, ((LinkedHashMap) (obj)), ((List) (obj2)), hashset)) { }
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
            charsequence = ((BaseRecipientAdapter) (obj2)).queryMode.contentFilterUri.buildUpon().appendPath(charsequence.toString()).appendQueryParameter("limit", String.valueOf(i + 5));
            if (true)
            {
                break MISSING_BLOCK_LABEL_219;
            }
            charsequence.appendQueryParameter("directory", String.valueOf(null));
            if (((BaseRecipientAdapter) (obj2)).account != null)
            {
                charsequence.appendQueryParameter("name_for_primary_account", ((BaseRecipientAdapter) (obj2)).account.name);
                charsequence.appendQueryParameter("type_for_primary_account", ((BaseRecipientAdapter) (obj2)).account.type);
            }
            System.currentTimeMillis();
            charsequence = ((BaseRecipientAdapter) (obj2)).contentResolver.query(charsequence.build(), ((BaseRecipientAdapter) (obj2)).queryMode.projection, null, null, null);
            System.currentTimeMillis();
              goto _L6
_L5:
            List list = constructEntryList(((LinkedHashMap) (obj)), ((List) (obj2)));
            obj1.values = new DefaultFilterResult(list, ((LinkedHashMap) (obj)), ((List) (obj2)), hashset, searchOtherDirectories(hashset));
            obj1.count = list.size();
_L4:
            if (charsequence != null)
            {
                charsequence.close();
            }
            return ((android.widget.Filter.FilterResults) (obj1));
            charsequence;
            obj = null;
              goto _L7
        }

        protected final void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
        {
            mCurrentConstraint = charsequence;
            tempEntries = null;
            if (filterresults.values != null)
            {
                filterresults = (DefaultFilterResult)filterresults.values;
                entryMap = ((DefaultFilterResult) (filterresults)).entryMap;
                nonAggregatedEntries = ((DefaultFilterResult) (filterresults)).nonAggregatedEntries;
                existingDestinations = ((DefaultFilterResult) (filterresults)).existingDestinations;
                BaseRecipientAdapter baserecipientadapter = BaseRecipientAdapter.this;
                int j = ((DefaultFilterResult) (filterresults)).entries.size();
                List list;
                int i;
                if (((DefaultFilterResult) (filterresults)).paramsList == null)
                {
                    i = 0;
                } else
                {
                    i = ((DefaultFilterResult) (filterresults)).paramsList.size();
                }
                if (j == 0 && i > 1)
                {
                    baserecipientadapter.tempEntries = baserecipientadapter.entries;
                }
                baserecipientadapter = BaseRecipientAdapter.this;
                list = ((DefaultFilterResult) (filterresults)).entries;
                baserecipientadapter.entries = list;
                baserecipientadapter.entriesUpdatedObserver.onChanged(list);
                baserecipientadapter.notifyDataSetChanged();
                if (((DefaultFilterResult) (filterresults)).paramsList != null)
                {
                    i = mPreferredMaxResultCount;
                    int k = ((DefaultFilterResult) (filterresults)).existingDestinations.size();
                    startSearchOtherDirectories(charsequence, ((DefaultFilterResult) (filterresults)).paramsList, i - k);
                }
                return;
            } else
            {
                charsequence = BaseRecipientAdapter.this;
                filterresults = Collections.emptyList();
                charsequence.entries = filterresults;
                ((BaseRecipientAdapter) (charsequence)).entriesUpdatedObserver.onChanged(filterresults);
                charsequence.notifyDataSetChanged();
                return;
            }
        }

        DefaultFilter()
        {
            this$0 = BaseRecipientAdapter.this;
            super();
        }

        private class DefaultFilterResult
        {

            public final List entries;
            public final LinkedHashMap entryMap;
            public final Set existingDestinations;
            public final List nonAggregatedEntries;
            public final List paramsList;

            public DefaultFilterResult(List list, LinkedHashMap linkedhashmap, List list1, Set set, List list2)
            {
                entries = list;
                entryMap = linkedhashmap;
                nonAggregatedEntries = list1;
                existingDestinations = set;
                paramsList = list2;
            }
        }

    }


    private class DirectoryListQuery
    {

        public static final String PROJECTION[] = {
            "_id", "accountName", "accountType", "displayName", "packageName", "typeResourceId"
        };
        public static final Uri URI;

        static 
        {
            URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directories");
        }
    }


    private class DirectoryFilter extends Filter
    {

        private int limit;
        private final DirectorySearchParams params;
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

        protected final android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
        {
            Object obj;
            obj = new android.widget.Filter.FilterResults();
            obj.values = null;
            obj.count = 0;
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
            for (; charsequence.moveToNext(); ((ArrayList) (obj1)).add(new TemporaryEntry(charsequence, Long.valueOf(params.directoryId)))) { }
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
            charsequence = baserecipientadapter.queryMode.contentFilterUri.buildUpon().appendPath(charsequence.toString()).appendQueryParameter("limit", String.valueOf(i + 5));
            if (long1 == null)
            {
                break MISSING_BLOCK_LABEL_181;
            }
            charsequence.appendQueryParameter("directory", String.valueOf(long1));
            if (baserecipientadapter.account != null)
            {
                charsequence.appendQueryParameter("name_for_primary_account", baserecipientadapter.account.name);
                charsequence.appendQueryParameter("type_for_primary_account", baserecipientadapter.account.type);
            }
            System.currentTimeMillis();
            charsequence = baserecipientadapter.contentResolver.query(charsequence.build(), baserecipientadapter.queryMode.projection, null, null, null);
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
                obj.values = obj1;
                obj.count = ((ArrayList) (obj1)).size();
            }
_L2:
            return ((android.widget.Filter.FilterResults) (obj));
            if (true) goto _L9; else goto _L8
_L8:
        }

        protected final void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
        {
            delayedMessageHandler.removeMessages(1);
            if (TextUtils.equals(charsequence, mCurrentConstraint))
            {
                if (filterresults.count > 0)
                {
                    charsequence = (ArrayList)(ArrayList)filterresults.values;
                    int j = charsequence.size();
                    int i = 0;
                    while (i < j) 
                    {
                        TemporaryEntry temporaryentry = (TemporaryEntry)charsequence.get(i);
                        BaseRecipientAdapter baserecipientadapter = BaseRecipientAdapter.this;
                        boolean flag;
                        if (params.directoryId == 0L)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        baserecipientadapter.putOneEntry(temporaryentry, flag);
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
                if (filterresults.count > 0 || remainingDirectoryCount == 0)
                {
                    tempEntries = null;
                }
            }
            charsequence = BaseRecipientAdapter.this;
            filterresults = constructEntryList();
            charsequence.entries = filterresults;
            ((BaseRecipientAdapter) (charsequence)).entriesUpdatedObserver.onChanged(filterresults);
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

        public DirectoryFilter(DirectorySearchParams directorysearchparams)
        {
            this$0 = BaseRecipientAdapter.this;
            super();
            params = directorysearchparams;
        }
    }

}
