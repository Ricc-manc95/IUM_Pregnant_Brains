// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apiary;

import android.content.ContentProviderClient;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import java.util.ArrayList;

// Referenced classes of package com.google.android.apiary:
//            ParseException

public final class ProviderHelper
{

    public static ContentProviderResult[] applyBatchProvider(ContentProviderClient contentproviderclient, ArrayList arraylist)
        throws RemoteException, OperationApplicationException, ParseException
    {
        try
        {
            contentproviderclient = contentproviderclient.applyBatch(arraylist);
        }
        // Misplaced declaration of an exception variable
        catch (ContentProviderClient contentproviderclient)
        {
            throw new ParseException(contentproviderclient);
        }
        return contentproviderclient;
    }

    public static Uri insertProvider(ContentProviderClient contentproviderclient, Uri uri, ContentValues contentvalues)
        throws RemoteException, ParseException
    {
        try
        {
            contentproviderclient = contentproviderclient.insert(uri, contentvalues);
        }
        // Misplaced declaration of an exception variable
        catch (ContentProviderClient contentproviderclient)
        {
            throw new ParseException(contentproviderclient);
        }
        return contentproviderclient;
    }

    public static Cursor queryProvider(ContentProviderClient contentproviderclient, Uri uri, String as[], String s, String as1[], String s1)
        throws RemoteException, ParseException
    {
        try
        {
            contentproviderclient = contentproviderclient.query(uri, as, s, as1, s1);
        }
        // Misplaced declaration of an exception variable
        catch (ContentProviderClient contentproviderclient)
        {
            throw new ParseException(contentproviderclient);
        }
        return contentproviderclient;
    }

    public static int updateProvider(ContentProviderClient contentproviderclient, Uri uri, ContentValues contentvalues, String s, String as[])
        throws RemoteException, ParseException
    {
        int i;
        try
        {
            i = contentproviderclient.update(uri, contentvalues, s, as);
        }
        // Misplaced declaration of an exception variable
        catch (ContentProviderClient contentproviderclient)
        {
            throw new ParseException(contentproviderclient);
        }
        return i;
    }
}
