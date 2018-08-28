// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;


// Referenced classes of package org.ccil.cowan.tagsoup:
//            AttributesImpl, Schema

public final class ElementType
{

    public AttributesImpl theAtts;
    public int theFlags;
    public String theLocalName;
    public int theMemberOf;
    public int theModel;
    public String theName;
    public String theNamespace;
    public ElementType theParent;
    private Schema theSchema;

    public ElementType(String s, int i, int j, int k, Schema schema)
    {
        theName = s;
        theModel = i;
        theMemberOf = j;
        theFlags = k;
        theAtts = new AttributesImpl();
        theSchema = schema;
        theNamespace = namespace(s, false);
        i = s.indexOf(':');
        if (i != -1)
        {
            s = s.substring(i + 1).intern();
        }
        theLocalName = s;
    }

    private final String namespace(String s, boolean flag)
    {
        int i = s.indexOf(':');
        if (i == -1)
        {
            if (flag)
            {
                return "";
            } else
            {
                return theSchema.theURI;
            }
        }
        s = s.substring(0, i);
        if (s.equals("xml"))
        {
            return "http://www.w3.org/XML/1998/namespace";
        } else
        {
            return ("urn:x-prefix:" + s).intern();
        }
    }

    private static String normalize(String s)
    {
        if (s != null)
        {
            String s1 = s.trim();
            s = s1;
            if (s1.indexOf("  ") != -1)
            {
                int j = s1.length();
                s = new StringBuffer(j);
                int i = 0;
                boolean flag = false;
                while (i < j) 
                {
                    char c = s1.charAt(i);
                    if (c == ' ')
                    {
                        if (!flag)
                        {
                            s.append(c);
                        }
                        flag = true;
                    } else
                    {
                        s.append(c);
                        flag = false;
                    }
                    i++;
                }
                return s.toString();
            }
        }
        return s;
    }

    public final void setAttribute(AttributesImpl attributesimpl, String s, String s1, String s2)
    {
        String s3;
        String s6;
        int i;
label0:
        {
            String s4;
label1:
            {
                if (s.equals("xmlns") || s.startsWith("xmlns:"))
                {
                    return;
                }
                s6 = namespace(s, true);
                i = s.indexOf(':');
                int j;
                if (i == -1)
                {
                    s3 = s;
                } else
                {
                    s3 = s.substring(i + 1).intern();
                }
                i = attributesimpl.getIndex(s);
                if (i != -1)
                {
                    break label0;
                }
                s4 = s.intern();
                s = s1;
                if (s1 == null)
                {
                    s = "CDATA";
                }
                s1 = s2;
                if (!s.equals("CDATA"))
                {
                    s1 = normalize(s2);
                }
                j = attributesimpl.length + 1;
                if (j <= 0)
                {
                    break label1;
                }
                if (attributesimpl.data != null && attributesimpl.data.length != 0)
                {
                    if (attributesimpl.data.length >= j * 5)
                    {
                        break label1;
                    }
                    i = attributesimpl.data.length;
                }
                for (i = 25; i < j * 5; i <<= 1) { }
                s2 = new String[i];
                if (attributesimpl.length > 0)
                {
                    System.arraycopy(attributesimpl.data, 0, s2, 0, attributesimpl.length * 5);
                }
                attributesimpl.data = s2;
            }
            attributesimpl.data[attributesimpl.length * 5] = s6;
            attributesimpl.data[attributesimpl.length * 5 + 1] = s3;
            attributesimpl.data[attributesimpl.length * 5 + 2] = s4;
            attributesimpl.data[attributesimpl.length * 5 + 3] = s;
            attributesimpl.data[attributesimpl.length * 5 + 4] = s1;
            attributesimpl.length = attributesimpl.length + 1;
            return;
        }
        String s5 = s1;
        if (s1 == null)
        {
            s5 = attributesimpl.getType(i);
        }
        s1 = s2;
        if (!s5.equals("CDATA"))
        {
            s1 = normalize(s2);
        }
        if (i >= 0 && i < attributesimpl.length)
        {
            attributesimpl.data[i * 5] = s6;
            attributesimpl.data[i * 5 + 1] = s3;
            attributesimpl.data[i * 5 + 2] = s;
            attributesimpl.data[i * 5 + 3] = s5;
            attributesimpl.data[i * 5 + 4] = s1;
            return;
        } else
        {
            throw new ArrayIndexOutOfBoundsException("Attempt to modify attribute at illegal index: " + i);
        }
    }
}
