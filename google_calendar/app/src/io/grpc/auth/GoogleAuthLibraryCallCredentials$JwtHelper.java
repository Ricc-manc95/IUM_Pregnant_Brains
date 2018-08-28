// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.auth;

import com.google.auth.Credentials;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.auth:
//            GoogleAuthLibraryCallCredentials

static final class jwtConstructor
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

    public (Class class1, ClassLoader classloader)
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
