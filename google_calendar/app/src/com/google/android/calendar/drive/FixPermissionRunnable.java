// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.content.Context;
import com.google.android.apiary.GoogleRequestInitializer;
import com.google.android.apps.calendar.util.conscrypt.ConscryptUtils;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FixPermissionsRequest;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.SortedMap;

// Referenced classes of package com.google.android.calendar.drive:
//            PotentialFix

public class FixPermissionRunnable
    implements Runnable
{

    private static final String LOG_TAG = com/google/android/calendar/drive/FixPermissionRunnable.getSimpleName();
    private final String account;
    private final Context context;
    private final PotentialFix fix;
    private final String role;

    public FixPermissionRunnable(Context context1, String s, PotentialFix potentialfix, String s1)
    {
        context = context1;
        account = s;
        fix = potentialfix;
        role = s1;
    }

    public void run()
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        ConscryptUtils.installSecurityProvider(context);
        obj = new GoogleRequestInitializer(context.getApplicationContext(), "oauth2:https://www.googleapis.com/auth/drive", LOG_TAG);
        obj.accountName = account;
        obj.authToken = null;
        obj1 = new Drive((com.google.api.services.drive.Drive.Builder)(new com.google.api.services.drive.Drive.Builder(new NetHttpTransport(), new AndroidJsonFactory(), ((com.google.api.client.http.HttpRequestInitializer) (obj)))).setApplicationName("Android Calendar"));
        obj = new FixPermissionsRequest();
        obj2 = fix.optionType;
        obj.recipientEmailAddresses = fix.fixableRecipientEmailAddresses;
        obj.fileIds = fix.fixableFileIds;
        obj.role = role;
        obj.fixOptionType = ((String) (obj2));
        obj2 = new com.google.api.services.drive.Drive.Files(((Drive) (obj1)));
        obj1 = new com.google.api.services.drive.Drive.Files.FixPermissions(((com.google.api.services.drive.Drive.Files) (obj2)), ((FixPermissionsRequest) (obj)));
        ((com.google.api.services.drive.Drive.Files) (obj2)).this$0.initialize(((AbstractGoogleClientRequest) (obj1)));
        obj2 = ((AbstractGoogleClientRequest) (obj1)).requestMethod;
        obj3 = ((AbstractGoogleClientRequest) (obj1)).getAbstractGoogleClient().requestFactory;
        obj4 = ((AbstractGoogleClientRequest) (obj1)).abstractGoogleClient;
        obj = String.valueOf(((AbstractGoogleClient) (obj4)).rootUrl);
        obj4 = String.valueOf(((AbstractGoogleClient) (obj4)).servicePath);
        if (((String) (obj4)).length() == 0) goto _L2; else goto _L1
_L1:
        obj = ((String) (obj)).concat(((String) (obj4)));
_L7:
        int i;
        obj = ((HttpRequestFactory) (obj3)).buildRequest(((String) (obj2)), new GenericUrl(UriTemplate.expand(((String) (obj)), ((AbstractGoogleClientRequest) (obj1)).uriTemplate, obj1, true)), ((AbstractGoogleClientRequest) (obj1)).httpContent);
        (new MethodOverride()).intercept(((HttpRequest) (obj)));
        obj.objectParser = ((AbstractGoogleClientRequest) (obj1)).getAbstractGoogleClient().getObjectParser();
        if (((AbstractGoogleClientRequest) (obj1)).httpContent == null && (((AbstractGoogleClientRequest) (obj1)).requestMethod.equals("POST") || ((AbstractGoogleClientRequest) (obj1)).requestMethod.equals("PUT") || ((AbstractGoogleClientRequest) (obj1)).requestMethod.equals("PATCH")))
        {
            obj.content = new EmptyContent();
        }
        ((HttpRequest) (obj)).headers.putAll(((AbstractGoogleClientRequest) (obj1)).requestHeaders);
        obj.encoding = new GZipEncoding();
        obj.responseInterceptor = new com.google.api.client.googleapis.services.AbstractGoogleClientRequest._cls1(((AbstractGoogleClientRequest) (obj1)), ((HttpRequest) (obj)).responseInterceptor, ((HttpRequest) (obj)));
        obj4 = ((HttpRequest) (obj)).execute();
        obj1 = ((AbstractGoogleClientRequest) (obj1)).responseClass;
        i = ((HttpResponse) (obj4)).statusCode;
        if (!((HttpResponse) (obj4)).request.requestMethod.equals("HEAD") && i / 100 != 1 && i != 204 && i != 304) goto _L4; else goto _L3
_L3:
        boolean flag;
        try
        {
            obj = ((HttpResponse) (obj4)).getContent();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            return;
        }
        if (obj == null) goto _L6; else goto _L5
_L5:
        ((InputStream) (obj)).close();
          goto _L6
_L2:
        obj = new String(((String) (obj)));
          goto _L7
_L15:
        obj2 = ((HttpResponse) (obj4)).request.objectParser;
        obj3 = ((HttpResponse) (obj4)).getContent();
        if (((HttpResponse) (obj4)).mediaType == null) goto _L9; else goto _L8
_L8:
        obj = (String)((HttpResponse) (obj4)).mediaType.parameters.get("charset".toLowerCase());
        if (obj != null) goto _L11; else goto _L10
_L10:
        obj = null;
          goto _L12
_L9:
        obj = Charsets.ISO_8859_1;
_L13:
        ((ObjectParser) (obj2)).parseAndClose(((InputStream) (obj3)), ((Charset) (obj)), ((Class) (obj1)));
        return;
_L11:
        obj = Charset.forName(((String) (obj)));
          goto _L12
_L17:
        obj = (String)((HttpResponse) (obj4)).mediaType.parameters.get("charset".toLowerCase());
label0:
        {
            if (obj != null)
            {
                break label0;
            }
            obj = null;
        }
          goto _L13
        obj = Charset.forName(((String) (obj)));
          goto _L13
_L6:
        flag = false;
_L16:
        if (flag) goto _L15; else goto _L14
_L14:
        return;
_L4:
        flag = true;
          goto _L16
          goto _L15
_L12:
        if (obj != null) goto _L17; else goto _L9
    }

}
