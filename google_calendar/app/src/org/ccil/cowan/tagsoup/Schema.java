// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;

import java.util.HashMap;

// Referenced classes of package org.ccil.cowan.tagsoup:
//            ElementType

public class Schema
{

    public HashMap theElementTypes;
    public HashMap theEntities;
    public String thePrefix;
    public ElementType theRoot;
    public String theURI;

    public Schema()
    {
        theEntities = new HashMap();
        theElementTypes = new HashMap();
        theURI = "";
        thePrefix = "";
        theRoot = null;
    }

    public final void attribute(String s, String s1, String s2, String s3)
    {
        ElementType elementtype = (ElementType)theElementTypes.get(s.toLowerCase());
        if (elementtype == null)
        {
            throw new Error("Attribute " + s1 + " specified for unknown element type " + s);
        } else
        {
            elementtype.setAttribute(elementtype.theAtts, s1, s2, s3);
            return;
        }
    }

    public final void elementType(String s, int i, int j, int k)
    {
        ElementType elementtype = new ElementType(s, i, j, k, this);
        theElementTypes.put(s.toLowerCase(), elementtype);
        if (j == 0x80000000)
        {
            theRoot = elementtype;
        }
    }

    public final void entity(String s, int i)
    {
        theEntities.put(s, new Integer(i));
    }

    public final void parent(String s, String s1)
    {
        ElementType elementtype = (ElementType)theElementTypes.get(s.toLowerCase());
        ElementType elementtype1 = (ElementType)theElementTypes.get(s1.toLowerCase());
        if (elementtype == null)
        {
            throw new Error("No child " + s + " for parent " + s1);
        }
        if (elementtype1 == null)
        {
            throw new Error("No parent " + s1 + " for child " + s);
        } else
        {
            elementtype.theParent = elementtype1;
            return;
        }
    }

    public final void setPrefix(String s)
    {
        thePrefix = s;
    }

    public final void setURI(String s)
    {
        theURI = s;
    }
}
