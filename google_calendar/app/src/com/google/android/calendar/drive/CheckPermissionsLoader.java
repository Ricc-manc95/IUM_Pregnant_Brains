// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.GoogleRequestInitializer;
import com.google.android.apps.calendar.util.conscrypt.ConscryptUtils;
import com.google.android.calendar.common.asynctaskloader.MailAsyncTaskLoader;
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
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.CheckPermissionsRequest;
import com.google.api.services.drive.model.CheckPermissionsResponse;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;

// Referenced classes of package com.google.android.calendar.drive:
//            PotentialFix, CheckPermissionsResults

public class CheckPermissionsLoader extends MailAsyncTaskLoader
{

    private static final String LOG_TAG = com/google/android/calendar/drive/CheckPermissionsLoader.getSimpleName();
    private final String account;
    private final ArrayList fileIds;
    private final ArrayList recipients;

    public CheckPermissionsLoader(Context context, Bundle bundle)
    {
        super(context);
        account = bundle.getString("account");
        recipients = bundle.getStringArrayList("recipients");
        fileIds = bundle.getStringArrayList("fileIds");
    }

    private final CheckPermissionsResults loadInBackground()
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        ConscryptUtils.installSecurityProvider(super.mContext.getApplicationContext());
        obj = new GoogleRequestInitializer(super.mContext.getApplicationContext(), "oauth2:https://www.googleapis.com/auth/drive", LOG_TAG);
        obj.accountName = account;
        obj.authToken = null;
        obj1 = new Drive((com.google.api.services.drive.Drive.Builder)(new com.google.api.services.drive.Drive.Builder(new NetHttpTransport(), new AndroidJsonFactory(), ((com.google.api.client.http.HttpRequestInitializer) (obj)))).setApplicationName("Android Calendar"));
        obj = new CheckPermissionsRequest();
        obj.recipientEmailAddresses = recipients;
        obj.fileIds = fileIds;
        obj.role = "READER";
        LogUtils.v(LOG_TAG, "check permissions request: %s", new Object[] {
            ((GenericJson) (obj)).toPrettyString()
        });
        obj2 = new com.google.api.services.drive.Drive.Files(((Drive) (obj1)));
        obj1 = new com.google.api.services.drive.Drive.Files.CheckPermissions(((com.google.api.services.drive.Drive.Files) (obj2)), ((CheckPermissionsRequest) (obj)));
        ((com.google.api.services.drive.Drive.Files) (obj2)).this$0.initialize(((AbstractGoogleClientRequest) (obj1)));
        obj2 = ((AbstractGoogleClientRequest) (obj1)).requestMethod;
        obj3 = ((AbstractGoogleClientRequest) (obj1)).getAbstractGoogleClient().requestFactory;
        obj4 = ((AbstractGoogleClientRequest) (obj1)).abstractGoogleClient;
        obj = String.valueOf(((AbstractGoogleClient) (obj4)).rootUrl);
        obj4 = String.valueOf(((AbstractGoogleClient) (obj4)).servicePath);
        if (((String) (obj4)).length() == 0) goto _L2; else goto _L1
_L1:
        obj = ((String) (obj)).concat(((String) (obj4)));
_L11:
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
        obj = ((HttpResponse) (obj4)).getContent();
        if (obj == null) goto _L6; else goto _L5
_L5:
        ((InputStream) (obj)).close();
          goto _L6
_L17:
        obj = (CheckPermissionsResponse)obj;
        LogUtils.i(LOG_TAG, "check permissions response: %s", new Object[] {
            ((GenericJson) (obj)).toPrettyString()
        });
        if (obj == null) goto _L8; else goto _L7
_L7:
        obj2 = ((CheckPermissionsResponse) (obj)).fixOptions;
        obj1 = new ArrayList();
        if (obj2 == null) goto _L10; else goto _L9
_L9:
        obj2 = ((List) (obj2)).iterator();
        do
        {
            if (!((Iterator) (obj2)).hasNext())
            {
                break;
            }
            obj3 = (com.google.api.services.drive.model.CheckPermissionsResponse.FixOptions)((Iterator) (obj2)).next();
            if (PotentialFix.isSupportedFixOption(((com.google.api.services.drive.model.CheckPermissionsResponse.FixOptions) (obj3)).optionType))
            {
                ((ArrayList) (obj1)).add(new PotentialFix(((com.google.api.services.drive.model.CheckPermissionsResponse.FixOptions) (obj3))));
            }
        } while (true);
          goto _L10
_L2:
        obj = new String(((String) (obj)));
          goto _L11
_L20:
        obj2 = ((HttpResponse) (obj4)).request.objectParser;
        obj3 = ((HttpResponse) (obj4)).getContent();
        if (((HttpResponse) (obj4)).mediaType == null) goto _L13; else goto _L12
_L12:
        obj = (String)((HttpResponse) (obj4)).mediaType.parameters.get("charset".toLowerCase());
        if (obj != null) goto _L15; else goto _L14
_L14:
        obj = null;
          goto _L16
_L13:
        obj = Charsets.ISO_8859_1;
_L18:
        obj = ((ObjectParser) (obj2)).parseAndClose(((InputStream) (obj3)), ((Charset) (obj)), ((Class) (obj1)));
          goto _L17
_L15:
        obj = Charset.forName(((String) (obj)));
          goto _L16
_L22:
        obj = (String)((HttpResponse) (obj4)).mediaType.parameters.get("charset".toLowerCase());
label0:
        {
            if (obj != null)
            {
                break label0;
            }
            obj = null;
        }
          goto _L18
        obj = Charset.forName(((String) (obj)));
          goto _L18
_L10:
        obj = new CheckPermissionsResults(((CheckPermissionsResponse) (obj)).fixabilitySummaryState, ((ArrayList) (obj1)));
        return ((CheckPermissionsResults) (obj));
_L6:
        boolean flag = false;
_L21:
        if (flag) goto _L20; else goto _L19
_L19:
        obj = null;
          goto _L17
        obj;
_L8:
        return null;
_L4:
        flag = true;
          goto _L21
_L16:
        if (obj != null) goto _L22; else goto _L13
    }

    public final volatile Object loadInBackground()
    {
        return loadInBackground();
    }

    protected final volatile void onDiscardResult(Object obj)
    {
    }

}
