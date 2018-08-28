// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.auth;

import com.google.auth.Credentials;
import com.google.common.io.BaseEncoding;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.StatusException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.PrivateKey;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class GoogleAuthLibraryCallCredentials
    implements CallCredentials
{
    static final class JwtHelper
    {

        private final Method getClientEmail;
        private final Method getClientId;
        private final Method getPrivateKey;
        private final Method getPrivateKeyId;
        private final Method getScopes;
        private final Constructor jwtConstructor;
        private final Class serviceAccountClass;

        public final Credentials tryServiceAccountToJwt(Credentials credentials)
        {
            if (serviceAccountClass.isInstance(credentials)) goto _L2; else goto _L1
_L1:
            return credentials;
_L2:
            Object obj = serviceAccountClass;
            Object obj1;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            int i;
            try
            {
                obj = ((Class) (obj)).cast(credentials);
            }
            catch (InvocationTargetException invocationtargetexception)
            {
                obj = credentials;
                credentials = invocationtargetexception;
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Object obj2)
            {
                obj = credentials;
                credentials = ((Credentials) (obj2));
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                obj = (Credentials)obj;
            }
            catch (InvocationTargetException invocationtargetexception1)
            {
                obj = credentials;
                credentials = invocationtargetexception1;
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Object obj2)
            {
                obj = credentials;
                credentials = ((Credentials) (obj2));
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                credentials = getScopes;
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                credentials = ((Credentials) (credentials.invoke(obj, new Object[0])));
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                credentials = (Collection)credentials;
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                i = credentials.size();
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            credentials = ((Credentials) (obj));
            if (i != 0) goto _L1; else goto _L3
_L3:
            try
            {
                credentials = jwtConstructor;
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                obj1 = getClientId;
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                obj1 = ((Method) (obj1)).invoke(obj, new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                obj3 = getClientEmail;
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                obj3 = ((Method) (obj3)).invoke(obj, new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                obj4 = getPrivateKey;
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                obj4 = ((Method) (obj4)).invoke(obj, new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                obj5 = getPrivateKeyId;
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                obj5 = ((Method) (obj5)).invoke(obj, new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                credentials = ((Credentials) (credentials.newInstance(new Object[] {
                    obj1, obj3, obj4, obj5
                })));
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch (Credentials credentials)
            {
                continue; /* Loop/switch isn't completed */
            }
            credentials = (Credentials)credentials;
            return credentials;
            obj2;
            obj = credentials;
            credentials = ((Credentials) (obj2));
_L5:
            GoogleAuthLibraryCallCredentials.log.logp(Level.WARNING, "io.grpc.auth.GoogleAuthLibraryCallCredentials$JwtHelper", "tryServiceAccountToJwt", "Failed converting service account credential to JWT. This is unexpected", credentials);
            return ((Credentials) (obj));
            credentials;
            continue; /* Loop/switch isn't completed */
            obj2;
            obj = credentials;
            credentials = ((Credentials) (obj2));
            continue; /* Loop/switch isn't completed */
            credentials;
            continue; /* Loop/switch isn't completed */
            obj2;
            obj = credentials;
            credentials = ((Credentials) (obj2));
            continue; /* Loop/switch isn't completed */
            credentials;
            if (true) goto _L5; else goto _L4
_L4:
        }

        public JwtHelper(Class class1, ClassLoader classloader)
            throws ClassNotFoundException, NoSuchMethodException
        {
            serviceAccountClass = class1.asSubclass(com/google/auth/Credentials);
            getScopes = serviceAccountClass.getMethod("getScopes", new Class[0]);
            getClientId = serviceAccountClass.getMethod("getClientId", new Class[0]);
            getClientEmail = serviceAccountClass.getMethod("getClientEmail", new Class[0]);
            getPrivateKey = serviceAccountClass.getMethod("getPrivateKey", new Class[0]);
            getPrivateKeyId = serviceAccountClass.getMethod("getPrivateKeyId", new Class[0]);
            jwtConstructor = Class.forName("com.google.auth.oauth2.ServiceAccountJwtAccessCredentials", false, classloader).asSubclass(com/google/auth/Credentials).getConstructor(new Class[] {
                java/lang/String, java/lang/String, java/security/PrivateKey, java/lang/String
            });
        }
    }


    private static final Class googleCredentialsClass = loadGoogleCredentialsClass();
    private static final JwtHelper jwtHelper = createJwtHelperOrNull(io/grpc/auth/GoogleAuthLibraryCallCredentials.getClassLoader());
    public static final Logger log = Logger.getLogger(io/grpc/auth/GoogleAuthLibraryCallCredentials.getName());
    private final Credentials creds;
    public Metadata lastHeaders;
    public Map lastMetadata;
    private final boolean requirePrivacy;

    public GoogleAuthLibraryCallCredentials(Credentials credentials)
    {
        this(credentials, jwtHelper);
    }

    private GoogleAuthLibraryCallCredentials(Credentials credentials, JwtHelper jwthelper)
    {
        if (credentials == null)
        {
            throw new NullPointerException(String.valueOf("creds"));
        }
        boolean flag = false;
        if (googleCredentialsClass != null)
        {
            flag = googleCredentialsClass.isInstance(credentials);
        }
        Credentials credentials1 = credentials;
        if (jwthelper != null)
        {
            credentials1 = jwthelper.tryServiceAccountToJwt(credentials);
        }
        requirePrivacy = flag;
        creds = credentials1;
    }

    private static JwtHelper createJwtHelperOrNull(ClassLoader classloader)
    {
        Class class1;
        try
        {
            class1 = Class.forName("com.google.auth.oauth2.ServiceAccountCredentials", false, classloader);
        }
        // Misplaced declaration of an exception variable
        catch (ClassLoader classloader)
        {
            return null;
        }
        classloader = new JwtHelper(class1, classloader);
        return classloader;
        classloader;
_L2:
        log.logp(Level.WARNING, "io.grpc.auth.GoogleAuthLibraryCallCredentials", "createJwtHelperOrNull", "Failed to create JWT helper. This is unexpected", classloader);
        return null;
        classloader;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static Class loadGoogleCredentialsClass()
    {
        Class class1;
        try
        {
            class1 = Class.forName("com.google.auth.oauth2.GoogleCredentials");
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            log.logp(Level.FINE, "io.grpc.auth.GoogleAuthLibraryCallCredentials", "loadGoogleCredentialsClass", "Failed to load GoogleCredentials", classnotfoundexception);
            return null;
        }
        return class1.asSubclass(com/google/auth/Credentials);
    }

    private static URI removePort(URI uri)
        throws StatusException
    {
        try
        {
            uri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), -1, uri.getPath(), uri.getQuery(), uri.getFragment());
        }
        // Misplaced declaration of an exception variable
        catch (URI uri)
        {
            throw new StatusException(Status.UNAUTHENTICATED.withDescription("Unable to construct service URI after removing port").withCause(uri));
        }
        return uri;
    }

    private static URI serviceUri(String s, MethodDescriptor methoddescriptor)
        throws StatusException
    {
        if (s == null)
        {
            throw new StatusException(Status.UNAUTHENTICATED.withDescription("Channel has no authority"));
        }
        methoddescriptor = String.valueOf(MethodDescriptor.extractFullServiceName(methoddescriptor.fullMethodName));
        if (methoddescriptor.length() != 0)
        {
            methoddescriptor = "/".concat(methoddescriptor);
        } else
        {
            methoddescriptor = new String("/");
        }
        try
        {
            methoddescriptor = new URI("https", s, methoddescriptor, null, null);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new StatusException(Status.UNAUTHENTICATED.withDescription("Unable to construct service URI for auth").withCause(s));
        }
        s = methoddescriptor;
        if (methoddescriptor.getPort() == 443)
        {
            s = removePort(methoddescriptor);
        }
        return s;
    }

    static Metadata toHeaders(Map map)
    {
        Metadata metadata = new Metadata();
        if (map != null)
        {
            for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
            {
                Object obj = (String)iterator.next();
                if (((String) (obj)).endsWith("-bin"))
                {
                    io.grpc.Metadata.Key key = io.grpc.Metadata.Key.of(((String) (obj)), Metadata.BINARY_BYTE_MARSHALLER);
                    obj = ((List)map.get(obj)).iterator();
                    while (((Iterator) (obj)).hasNext()) 
                    {
                        String s = (String)((Iterator) (obj)).next();
                        metadata.put(key, BaseEncoding.BASE64.decode(s));
                    }
                } else
                {
                    io.grpc.Metadata.Key key1 = io.grpc.Metadata.Key.of(((String) (obj)), Metadata.ASCII_STRING_MARSHALLER);
                    obj = ((List)map.get(obj)).iterator();
                    while (((Iterator) (obj)).hasNext()) 
                    {
                        metadata.put(key1, (String)((Iterator) (obj)).next());
                    }
                }
            }

        }
        return metadata;
    }

    public final void applyRequestMetadata(MethodDescriptor methoddescriptor, Attributes attributes, Executor executor, final io.grpc.CallCredentials.MetadataApplier applier)
    {
        Object obj = ATTR_SECURITY_LEVEL;
        SecurityLevel securitylevel = (SecurityLevel)attributes.data.get(obj);
        obj = securitylevel;
        if (securitylevel == null)
        {
            obj = SecurityLevel.NONE;
        }
        if (requirePrivacy && obj != SecurityLevel.PRIVACY_AND_INTEGRITY)
        {
            methoddescriptor = Status.UNAUTHENTICATED;
            attributes = String.valueOf(obj);
            applier.fail(methoddescriptor.withDescription((new StringBuilder(String.valueOf(attributes).length() + 96)).append("Credentials require channel with PRIVACY_AND_INTEGRITY security level. Observed security level: ").append(attributes).toString()));
            return;
        }
        obj = ATTR_AUTHORITY;
        attributes = (String)attributes.data.get(obj);
        if (attributes == null)
        {
            throw new NullPointerException(String.valueOf("authority"));
        }
        attributes = (String)attributes;
        try
        {
            methoddescriptor = serviceUri(attributes, methoddescriptor);
        }
        // Misplaced declaration of an exception variable
        catch (MethodDescriptor methoddescriptor)
        {
            applier.fail(((StatusException) (methoddescriptor)).status);
            return;
        }
        creds.getRequestMetadata(methoddescriptor, executor, new _cls1());
    }


    private class _cls1
        implements RequestMetadataCallback
    {

        private final GoogleAuthLibraryCallCredentials this$0;
        private final io.grpc.CallCredentials.MetadataApplier val$applier;

        public final void onFailure(Throwable throwable)
        {
            if (throwable instanceof IOException)
            {
                applier.fail(Status.UNAVAILABLE.withDescription("Credentials failed to obtain metadata").withCause(throwable));
                return;
            } else
            {
                applier.fail(Status.UNAUTHENTICATED.withDescription("Failed computing credential metadata").withCause(throwable));
                return;
            }
        }

        public final void onSuccess(Map map)
        {
            synchronized (GoogleAuthLibraryCallCredentials.this)
            {
                if (lastMetadata == null || lastMetadata != map)
                {
                    lastHeaders = GoogleAuthLibraryCallCredentials.toHeaders(map);
                    lastMetadata = map;
                }
                map = lastHeaders;
            }
            applier.apply(map);
            return;
            map;
            googleauthlibrarycallcredentials;
            JVM INSTR monitorexit ;
            try
            {
                throw map;
            }
            // Misplaced declaration of an exception variable
            catch (Map map)
            {
                applier.fail(Status.UNAUTHENTICATED.withDescription("Failed to convert credential metadata").withCause(map));
            }
            return;
        }

        _cls1()
        {
            this$0 = GoogleAuthLibraryCallCredentials.this;
            applier = metadataapplier;
            super();
        }
    }

}
