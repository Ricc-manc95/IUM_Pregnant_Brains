// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.squareup.okhttp;

import java.util.List;

public final class Headers
{

    public final String namesAndValues[];

    Headers(Builder builder)
    {
        namesAndValues = (String[])builder.namesAndValues.toArray(new String[builder.namesAndValues.size()]);
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        int j = namesAndValues.length / 2;
        int i = 0;
        while (i < j) 
        {
            int k = i << 1;
            String s;
            StringBuilder stringbuilder1;
            if (k < 0 || k >= namesAndValues.length)
            {
                s = null;
            } else
            {
                s = namesAndValues[k];
            }
            stringbuilder1 = stringbuilder.append(s).append(": ");
            k = (i << 1) + 1;
            if (k < 0 || k >= namesAndValues.length)
            {
                s = null;
            } else
            {
                s = namesAndValues[k];
            }
            stringbuilder1.append(s).append("\n");
            i++;
        }
        return stringbuilder.toString();
    }

    private class Builder
    {

        public final List namesAndValues = new ArrayList(20);

        public Builder()
        {
        }
    }

}
