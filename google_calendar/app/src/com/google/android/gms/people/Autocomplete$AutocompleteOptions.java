// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people;


public final class Builder.zzbSL
{

    public final String account;
    public final int autocompleteType;
    public final String directoryAccountType;
    public final boolean isDirectorySearch = false;
    public final int numberOfResults;
    public final String pageId = null;
    public final int searchOptions = 0;
    public final boolean useAndroidContactFallback;

    public final String toString()
    {
        boolean flag = true;
        boolean flag1 = false;
        Object aobj[] = new Object[16];
        aobj[0] = "isDirectorySearch";
        aobj[1] = Boolean.valueOf(false);
        aobj[2] = "directoryAccountType";
        aobj[3] = directoryAccountType;
        aobj[4] = "account";
        aobj[5] = account;
        aobj[6] = "pageId";
        aobj[7] = null;
        aobj[8] = "autocompleteType";
        aobj[9] = Integer.valueOf(autocompleteType);
        aobj[10] = "searchOptions";
        aobj[11] = Integer.valueOf(0);
        aobj[12] = "numberOfResults";
        aobj[13] = Integer.valueOf(numberOfResults);
        aobj[14] = "useAndroidContactFallback";
        aobj[15] = Boolean.valueOf(useAndroidContactFallback);
        StringBuilder stringbuilder = new StringBuilder();
        if (aobj.length % 2 != 0)
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        String s = "";
        for (int i = ((flag1) ? 1 : 0); i < aobj.length; i += 2)
        {
            stringbuilder.append(s);
            stringbuilder.append(aobj[i]);
            stringbuilder.append("=");
            stringbuilder.append(aobj[i + 1]);
            s = ", ";
        }

        return stringbuilder.toString();
    }

    public Builder.zzbSL(Builder builder)
    {
        class Builder
        {

            public String mAccount;
            public String zzbSI;
            public int zzbSJ;
            public int zzbSK;
            public boolean zzbSL;

            public Builder()
            {
                zzbSI = "com.google";
                zzbSJ = 0;
                zzbSK = 10;
                zzbSL = true;
            }
        }

        directoryAccountType = builder.zzbSI;
        account = builder.mAccount;
        autocompleteType = builder.zzbSJ;
        numberOfResults = builder.zzbSK;
        useAndroidContactFallback = builder.zzbSL;
    }
}
