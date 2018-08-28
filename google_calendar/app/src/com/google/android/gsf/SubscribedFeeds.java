// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gsf;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public final class SubscribedFeeds
{

    public static boolean manageSubscriptions(ContentResolver contentresolver, Account account, String s, String s1, Collection collection)
    {
        HashMap hashmap;
        Object obj;
        hashmap = new HashMap();
        obj = Feeds.CONTENT_URI;
        String s3 = account.name;
        String s5 = account.type;
        obj = contentresolver.query(((Uri) (obj)), new String[] {
            "_id", "feed"
        }, "_sync_account=? AND _sync_account_type=? AND authority=?", new String[] {
            s3, s5, s
        }, null);
        if (obj == null)
        {
            return false;
        }
_L2:
        String s4;
        long l;
        if (!((Cursor) (obj)).moveToNext())
        {
            break; /* Loop/switch isn't completed */
        }
        l = ((Cursor) (obj)).getLong(0);
        s4 = ((Cursor) (obj)).getString(1);
        if (hashmap.containsKey(s4))
        {
            contentresolver.delete(Feeds.CONTENT_URI, "_id=?", new String[] {
                Long.toString(l)
            });
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_151;
        contentresolver;
        ((Cursor) (obj)).close();
        throw contentresolver;
        hashmap.put(s4, Long.valueOf(l));
        if (true) goto _L2; else goto _L1
_L1:
        ((Cursor) (obj)).close();
        for (collection = collection.iterator(); collection.hasNext();)
        {
            String s2 = (String)collection.next();
            if (!hashmap.containsKey(s2))
            {
                ContentValues contentvalues = new ContentValues();
                contentvalues.put("_sync_account", account.name);
                contentvalues.put("_sync_account_type", account.type);
                contentvalues.put("feed", s2);
                contentvalues.put("service", s1);
                contentvalues.put("authority", s);
                try
                {
                    contentresolver.insert(Feeds.CONTENT_URI, contentvalues);
                }
                // Misplaced declaration of an exception variable
                catch (ContentResolver contentresolver)
                {
                    return false;
                }
            } else
            {
                hashmap.remove(s2);
            }
        }

        for (account = hashmap.entrySet().iterator(); account.hasNext();)
        {
            long l1 = ((Long)((java.util.Map.Entry)account.next()).getValue()).longValue();
            try
            {
                contentresolver.delete(ContentUris.withAppendedId(Feeds.CONTENT_URI, l1), null, null);
            }
            // Misplaced declaration of an exception variable
            catch (ContentResolver contentresolver)
            {
                return false;
            }
        }

        return true;
    }

    private class Feeds
        implements BaseColumns
    {

        public static final Uri CONTENT_URI = Uri.parse("content://subscribedfeeds/feeds");

        static 
        {
            Uri.parse("content://subscribedfeeds/deleted_feeds");
        }
    }

}
