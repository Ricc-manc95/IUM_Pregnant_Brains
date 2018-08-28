// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Base64;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class FontResourcesParserCompat
{

    public static List readCerts(Resources resources, int i)
    {
        ArrayList arraylist = null;
        Object obj = null;
        if (i != 0)
        {
            TypedArray typedarray = resources.obtainTypedArray(i);
            arraylist = obj;
            if (typedarray.length() > 0)
            {
                arraylist = new ArrayList();
                boolean flag;
                if (typedarray.getResourceId(0, 0) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    for (i = 0; i < typedarray.length(); i++)
                    {
                        arraylist.add(toByteArrayList(resources.getStringArray(typedarray.getResourceId(i, 0))));
                    }

                } else
                {
                    arraylist.add(toByteArrayList(resources.getStringArray(i)));
                }
            }
            typedarray.recycle();
        }
        if (arraylist != null)
        {
            return arraylist;
        } else
        {
            return Collections.emptyList();
        }
    }

    static void skip(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i = 1;
        do
        {
            if (i <= 0)
            {
                break;
            }
            switch (xmlpullparser.next())
            {
            case 2: // '\002'
                i++;
                break;

            case 3: // '\003'
                i--;
                break;
            }
        } while (true);
    }

    private static List toByteArrayList(String as[])
    {
        ArrayList arraylist = new ArrayList();
        int j = as.length;
        for (int i = 0; i < j; i++)
        {
            arraylist.add(Base64.decode(as[i], 0));
        }

        return arraylist;
    }
}
