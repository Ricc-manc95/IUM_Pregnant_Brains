// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;


final class Queries
{

    public static final Query EMAIL;
    public static final Query PHONE;

    static 
    {
        Uri uri = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI;
        Uri uri1 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        PHONE = new _cls1(new String[] {
            "display_name", "data1", "data2", "data3", "contact_id", "_id", "photo_thumb_uri", "display_name_source", "lookup", "mimetype"
        }, uri, uri1);
        uri = android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI;
        uri1 = android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        EMAIL = new _cls2(new String[] {
            "display_name", "data1", "data2", "data3", "contact_id", "_id", "photo_thumb_uri", "display_name_source", "lookup", "mimetype"
        }, uri, uri1);
    }

    private class _cls1 extends Query
    {
        private class Query
        {

            public final Uri contentFilterUri;
            public final String projection[];

            public Query(String as[], Uri uri, Uri uri1)
            {
                projection = as;
                contentFilterUri = uri;
            }
        }


        _cls1(String as[], Uri uri, Uri uri1)
        {
            super(as, uri, uri1);
        }
    }


    private class _cls2 extends Query
    {

        _cls2(String as[], Uri uri, Uri uri1)
        {
            super(as, uri, uri1);
        }
    }

}
