// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import android.util.Base64;
import java.util.List;

public final class FontRequest
{

    public final List mCertificates;
    private final int mCertificatesArray;
    public final String mIdentifier;
    public final String mProviderAuthority;
    public final String mProviderPackage;
    public final String mQuery;

    public FontRequest(String s, String s1, String s2, List list)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        mProviderAuthority = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        mProviderPackage = (String)s1;
        if (s2 == null)
        {
            throw new NullPointerException();
        }
        mQuery = (String)s2;
        if (list == null)
        {
            throw new NullPointerException();
        } else
        {
            mCertificates = (List)list;
            mCertificatesArray = 0;
            mIdentifier = (new StringBuilder(mProviderAuthority)).append("-").append(mProviderPackage).append("-").append(mQuery).toString();
            return;
        }
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((new StringBuilder("FontRequest {mProviderAuthority: ")).append(mProviderAuthority).append(", mProviderPackage: ").append(mProviderPackage).append(", mQuery: ").append(mQuery).append(", mCertificates:").toString());
        for (int i = 0; i < mCertificates.size(); i++)
        {
            stringbuilder.append(" [");
            List list = (List)mCertificates.get(i);
            for (int j = 0; j < list.size(); j++)
            {
                stringbuilder.append(" \"");
                stringbuilder.append(Base64.encodeToString((byte[])list.get(j), 0));
                stringbuilder.append("\"");
            }

            stringbuilder.append(" ]");
        }

        stringbuilder.append("}");
        stringbuilder.append((new StringBuilder("mCertificatesArray: ")).append(0).toString());
        return stringbuilder.toString();
    }
}
