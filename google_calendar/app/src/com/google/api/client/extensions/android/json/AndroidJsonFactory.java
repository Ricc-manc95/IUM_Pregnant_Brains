// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.extensions.android.json;

import android.util.JsonReader;
import android.util.JsonWriter;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.Charsets;
import com.google.common.base.Strings;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.Charset;

// Referenced classes of package com.google.api.client.extensions.android.json:
//            AndroidJsonGenerator, AndroidJsonParser

public final class AndroidJsonFactory extends JsonFactory
{

    public AndroidJsonFactory()
    {
        boolean flag;
        int i;
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = android.os.Build.VERSION.SDK_INT;
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("running on Android SDK level %s but requires minimum %s", new Object[] {
                Integer.valueOf(i), Integer.valueOf(11)
            }));
        } else
        {
            return;
        }
    }

    public final JsonGenerator createJsonGenerator(OutputStream outputstream, Charset charset)
    {
        return new AndroidJsonGenerator(this, new JsonWriter(new OutputStreamWriter(outputstream, charset)));
    }

    public final JsonParser createJsonParser(InputStream inputstream)
    {
        return new AndroidJsonParser(this, new JsonReader(new InputStreamReader(inputstream, Charsets.UTF_8)));
    }

    public final JsonParser createJsonParser(InputStream inputstream, Charset charset)
    {
        if (charset == null)
        {
            return createJsonParser(inputstream);
        } else
        {
            return new AndroidJsonParser(this, new JsonReader(new InputStreamReader(inputstream, charset)));
        }
    }

    public final JsonParser createJsonParser(String s)
    {
        return new AndroidJsonParser(this, new JsonReader(new StringReader(s)));
    }
}
