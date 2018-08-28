// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.licenses;

import android.content.Context;
import android.content.res.Resources;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.jar.JarFile;

// Referenced classes of package com.google.android.libraries.social.licenses:
//            License

public final class Licenses
{

    static ArrayList getLicenseListFromMetadata(String s, String s1)
    {
        String as[] = s.split("\n");
        ArrayList arraylist = new ArrayList(as.length);
        int k = as.length;
        for (int i = 0; i < k; i++)
        {
            String s2 = as[i];
            int l = s2.indexOf(' ');
            String as1[] = s2.substring(0, l).split(":");
            boolean flag;
            if (as1.length == 2 && l > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            s = String.valueOf(s2);
            if (s.length() != 0)
            {
                s = "Invalid license meta-data line:\n".concat(s);
            } else
            {
                s = new String("Invalid license meta-data line:\n");
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf(s));
            }
            long l1 = Long.parseLong(as1[0]);
            int j = Integer.parseInt(as1[1]);
            arraylist.add(new License(s2.substring(l + 1), l1, j, s1));
        }

        Collections.sort(arraylist);
        return arraylist;
    }

    private static String getTextFromInputStream(InputStream inputstream, long l, int i)
    {
        byte abyte0[] = new byte[1024];
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        int j;
        try
        {
            inputstream.skip(l);
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            throw new RuntimeException("Failed to read license or metadata text.", inputstream);
        }
        if (i <= 0)
        {
            i = 0x7fffffff;
        }
        if (i <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        j = inputstream.read(abyte0, 0, Math.min(i, 1024));
        if (j == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        bytearrayoutputstream.write(abyte0, 0, j);
        i -= j;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_26;
_L1:
        inputstream.close();
        try
        {
            inputstream = bytearrayoutputstream.toString("UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream)
        {
            throw new RuntimeException("Unsupported encoding UTF8. This should always be supported.", inputstream);
        }
        return inputstream;
    }

    static String getTextFromJar(String s, String s1, long l, int i)
    {
        JarFile jarfile = new JarFile(s1);
        s1 = jarfile;
        s = jarfile.getJarEntry(s);
        if (s == null)
        {
            try
            {
                jarfile.close();
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return null;
            }
            return null;
        }
        s1 = jarfile;
        s = getTextFromInputStream(jarfile.getInputStream(s), l, i);
        try
        {
            jarfile.close();
        }
        // Misplaced declaration of an exception variable
        catch (String s1)
        {
            return s;
        }
        return s;
        s;
        s1 = null;
_L4:
        throw new RuntimeException("Failed to read license text.", s);
        s;
_L2:
        if (s1 != null)
        {
            try
            {
                s1.close();
            }
            // Misplaced declaration of an exception variable
            catch (String s1) { }
        }
        throw s;
        s;
        s1 = null;
        if (true) goto _L2; else goto _L1
_L1:
        s;
        s1 = jarfile;
        if (true) goto _L4; else goto _L3
_L3:
    }

    static String getTextFromResource(Context context, String s, long l, int i)
    {
        context = context.getApplicationContext().getResources();
        return getTextFromInputStream(context.openRawResource(context.getIdentifier(s, "raw", context.getResourcePackageName(0x7f100015))), l, i);
    }
}
