// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            Android

final class UnsafeUtil
{
    static final class Android32MemoryAccessor extends MemoryAccessor
    {

        public final void copyMemory(byte abyte0[], long l, long l1, long l2)
        {
            Memory.pokeByteArray((int)(-1L & l1), abyte0, (int)l, (int)l2);
        }

        public final boolean getBoolean(Object obj, long l)
        {
            if (UnsafeUtil.IS_BIG_ENDIAN)
            {
                return UnsafeUtil.getBooleanBigEndian(obj, l);
            } else
            {
                return UnsafeUtil.getBooleanLittleEndian(obj, l);
            }
        }

        public final byte getByte(Object obj, long l)
        {
            if (UnsafeUtil.IS_BIG_ENDIAN)
            {
                return UnsafeUtil.getByteBigEndian(obj, l);
            } else
            {
                return UnsafeUtil.getByteLittleEndian(obj, l);
            }
        }

        public final double getDouble(Object obj, long l)
        {
            return Double.longBitsToDouble(super.unsafe.getLong(obj, l));
        }

        public final float getFloat(Object obj, long l)
        {
            return Float.intBitsToFloat(super.unsafe.getInt(obj, l));
        }

        public final void putBoolean(Object obj, long l, boolean flag)
        {
            if (UnsafeUtil.IS_BIG_ENDIAN)
            {
                UnsafeUtil.putBooleanBigEndian(obj, l, flag);
                return;
            } else
            {
                UnsafeUtil.putBooleanLittleEndian(obj, l, flag);
                return;
            }
        }

        public final void putByte(long l, byte byte0)
        {
            Memory.pokeByte((int)(-1L & l), byte0);
        }

        public final void putByte(Object obj, long l, byte byte0)
        {
            if (UnsafeUtil.IS_BIG_ENDIAN)
            {
                UnsafeUtil.putByteBigEndian(obj, l, byte0);
                return;
            } else
            {
                UnsafeUtil.putByteLittleEndian(obj, l, byte0);
                return;
            }
        }

        public final void putDouble(Object obj, long l, double d)
        {
            long l1 = Double.doubleToLongBits(d);
            super.unsafe.putLong(obj, l, l1);
        }

        public final void putFloat(Object obj, long l, float f)
        {
            int i = Float.floatToIntBits(f);
            super.unsafe.putInt(obj, l, i);
        }

        Android32MemoryAccessor(Unsafe unsafe)
        {
            super(unsafe);
        }
    }

    static final class Android64MemoryAccessor extends MemoryAccessor
    {

        public final void copyMemory(byte abyte0[], long l, long l1, long l2)
        {
            Memory.pokeByteArray(l1, abyte0, (int)l, (int)l2);
        }

        public final boolean getBoolean(Object obj, long l)
        {
            if (UnsafeUtil.IS_BIG_ENDIAN)
            {
                return UnsafeUtil.getBooleanBigEndian(obj, l);
            } else
            {
                return UnsafeUtil.getBooleanLittleEndian(obj, l);
            }
        }

        public final byte getByte(Object obj, long l)
        {
            if (UnsafeUtil.IS_BIG_ENDIAN)
            {
                return UnsafeUtil.getByteBigEndian(obj, l);
            } else
            {
                return UnsafeUtil.getByteLittleEndian(obj, l);
            }
        }

        public final double getDouble(Object obj, long l)
        {
            return Double.longBitsToDouble(super.unsafe.getLong(obj, l));
        }

        public final float getFloat(Object obj, long l)
        {
            return Float.intBitsToFloat(super.unsafe.getInt(obj, l));
        }

        public final void putBoolean(Object obj, long l, boolean flag)
        {
            if (UnsafeUtil.IS_BIG_ENDIAN)
            {
                UnsafeUtil.putBooleanBigEndian(obj, l, flag);
                return;
            } else
            {
                UnsafeUtil.putBooleanLittleEndian(obj, l, flag);
                return;
            }
        }

        public final void putByte(long l, byte byte0)
        {
            Memory.pokeByte(l, byte0);
        }

        public final void putByte(Object obj, long l, byte byte0)
        {
            if (UnsafeUtil.IS_BIG_ENDIAN)
            {
                UnsafeUtil.putByteBigEndian(obj, l, byte0);
                return;
            } else
            {
                UnsafeUtil.putByteLittleEndian(obj, l, byte0);
                return;
            }
        }

        public final void putDouble(Object obj, long l, double d)
        {
            long l1 = Double.doubleToLongBits(d);
            super.unsafe.putLong(obj, l, l1);
        }

        public final void putFloat(Object obj, long l, float f)
        {
            int i = Float.floatToIntBits(f);
            super.unsafe.putInt(obj, l, i);
        }

        Android64MemoryAccessor(Unsafe unsafe)
        {
            super(unsafe);
        }
    }

    static final class JvmMemoryAccessor extends MemoryAccessor
    {

        public final void copyMemory(byte abyte0[], long l, long l1, long l2)
        {
            unsafe.copyMemory(abyte0, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + l, null, l1, l2);
        }

        public final boolean getBoolean(Object obj, long l)
        {
            return unsafe.getBoolean(obj, l);
        }

        public final byte getByte(Object obj, long l)
        {
            return unsafe.getByte(obj, l);
        }

        public final double getDouble(Object obj, long l)
        {
            return unsafe.getDouble(obj, l);
        }

        public final float getFloat(Object obj, long l)
        {
            return unsafe.getFloat(obj, l);
        }

        public final void putBoolean(Object obj, long l, boolean flag)
        {
            unsafe.putBoolean(obj, l, flag);
        }

        public final void putByte(long l, byte byte0)
        {
            unsafe.putByte(l, byte0);
        }

        public final void putByte(Object obj, long l, byte byte0)
        {
            unsafe.putByte(obj, l, byte0);
        }

        public final void putDouble(Object obj, long l, double d)
        {
            unsafe.putDouble(obj, l, d);
        }

        public final void putFloat(Object obj, long l, float f)
        {
            unsafe.putFloat(obj, l, f);
        }

        JvmMemoryAccessor(Unsafe unsafe)
        {
            super(unsafe);
        }
    }

    static abstract class MemoryAccessor
    {

        public Unsafe unsafe;

        public abstract void copyMemory(byte abyte0[], long l, long l1, long l2);

        public abstract boolean getBoolean(Object obj, long l);

        public abstract byte getByte(Object obj, long l);

        public abstract double getDouble(Object obj, long l);

        public abstract float getFloat(Object obj, long l);

        public abstract void putBoolean(Object obj, long l, boolean flag);

        public abstract void putByte(long l, byte byte0);

        public abstract void putByte(Object obj, long l, byte byte0);

        public abstract void putDouble(Object obj, long l, double d);

        public abstract void putFloat(Object obj, long l, float f);

        MemoryAccessor(Unsafe unsafe1)
        {
            unsafe = unsafe1;
        }
    }


    public static final long BUFFER_ADDRESS_OFFSET;
    public static final long BYTE_ARRAY_BASE_OFFSET;
    public static final boolean HAS_UNSAFE_ARRAY_OPERATIONS;
    public static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS = supportsUnsafeByteBufferOperations();
    private static final boolean IS_ANDROID_32;
    private static final boolean IS_ANDROID_64;
    public static final boolean IS_BIG_ENDIAN;
    public static final MemoryAccessor MEMORY_ACCESSOR;
    private static final Class MEMORY_CLASS;
    private static final Unsafe UNSAFE;
    private static final Logger logger = Logger.getLogger(com/google/protobuf/UnsafeUtil.getName());

    private UnsafeUtil()
    {
    }

    private static Field bufferAddressField()
    {
        Field field1;
        boolean flag;
        if (Android.MEMORY_CLASS != null && !Android.IS_ROBOLECTRIC)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        field1 = field(java/nio/Buffer, "effectiveDirectAddress");
        if (field1 == null) goto _L2; else goto _L3
_L3:
        return field1;
_L2:
        Field field2;
        field2 = field(java/nio/Buffer, "address");
        if (field2 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        field1 = field2;
        if (field2.getType() == Long.TYPE) goto _L3; else goto _L4
_L4:
        return null;
    }

    private static boolean determineAndroidSupportByAddressSize(Class class1)
    {
        boolean flag;
        if (Android.MEMORY_CLASS != null && !Android.IS_ROBOLECTRIC)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return false;
        }
        try
        {
            Class class2 = MEMORY_CLASS;
            class2.getMethod("peekLong", new Class[] {
                class1, Boolean.TYPE
            });
            class2.getMethod("pokeLong", new Class[] {
                class1, Long.TYPE, Boolean.TYPE
            });
            class2.getMethod("pokeInt", new Class[] {
                class1, Integer.TYPE, Boolean.TYPE
            });
            class2.getMethod("peekInt", new Class[] {
                class1, Boolean.TYPE
            });
            class2.getMethod("pokeByte", new Class[] {
                class1, Byte.TYPE
            });
            class2.getMethod("peekByte", new Class[] {
                class1
            });
            class2.getMethod("pokeByteArray", new Class[] {
                class1, [B, Integer.TYPE, Integer.TYPE
            });
            class2.getMethod("peekByteArray", new Class[] {
                class1, [B, Integer.TYPE, Integer.TYPE
            });
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            return false;
        }
        return true;
    }

    private static Field field(Class class1, String s)
    {
        try
        {
            class1 = class1.getDeclaredField(s);
            class1.setAccessible(true);
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            return null;
        }
        return class1;
    }

    static boolean getBooleanBigEndian(Object obj, long l)
    {
        return getByteBigEndian(obj, l) != 0;
    }

    static boolean getBooleanLittleEndian(Object obj, long l)
    {
        return getByteLittleEndian(obj, l) != 0;
    }

    static byte getByteBigEndian(Object obj, long l)
    {
        return (byte)(MEMORY_ACCESSOR.unsafe.getInt(obj, -4L & l) >>> (int)(((-1L ^ l) & 3L) << 3));
    }

    static byte getByteLittleEndian(Object obj, long l)
    {
        return (byte)(MEMORY_ACCESSOR.unsafe.getInt(obj, -4L & l) >>> (int)((3L & l) << 3));
    }

    static Unsafe getUnsafe()
    {
        Unsafe unsafe;
        try
        {
            unsafe = (Unsafe)AccessController.doPrivileged(new _cls1());
        }
        catch (Throwable throwable)
        {
            return null;
        }
        return unsafe;
    }

    static void putBooleanBigEndian(Object obj, long l, boolean flag)
    {
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        putByteBigEndian(obj, l, (byte)i);
    }

    static void putBooleanLittleEndian(Object obj, long l, boolean flag)
    {
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        putByteLittleEndian(obj, l, (byte)i);
    }

    static void putByteBigEndian(Object obj, long l, byte byte0)
    {
        int i = MEMORY_ACCESSOR.unsafe.getInt(obj, l & -4L);
        int j = (~(int)l & 3) << 3;
        MEMORY_ACCESSOR.unsafe.putInt(obj, l & -4L, i & ~(255 << j) | (byte0 & 0xff) << j);
    }

    static void putByteLittleEndian(Object obj, long l, byte byte0)
    {
        int i = MEMORY_ACCESSOR.unsafe.getInt(obj, l & -4L);
        int j = ((int)l & 3) << 3;
        MEMORY_ACCESSOR.unsafe.putInt(obj, l & -4L, i & ~(255 << j) | (byte0 & 0xff) << j);
    }

    private static boolean supportsUnsafeArrayOperations()
    {
        if (UNSAFE == null)
        {
            return false;
        }
        Class class1;
        class1 = UNSAFE.getClass();
        class1.getMethod("objectFieldOffset", new Class[] {
            java/lang/reflect/Field
        });
        class1.getMethod("arrayBaseOffset", new Class[] {
            java/lang/Class
        });
        class1.getMethod("arrayIndexScale", new Class[] {
            java/lang/Class
        });
        class1.getMethod("getInt", new Class[] {
            java/lang/Object, Long.TYPE
        });
        class1.getMethod("putInt", new Class[] {
            java/lang/Object, Long.TYPE, Integer.TYPE
        });
        class1.getMethod("getLong", new Class[] {
            java/lang/Object, Long.TYPE
        });
        class1.getMethod("putLong", new Class[] {
            java/lang/Object, Long.TYPE, Long.TYPE
        });
        class1.getMethod("getObject", new Class[] {
            java/lang/Object, Long.TYPE
        });
        class1.getMethod("putObject", new Class[] {
            java/lang/Object, Long.TYPE, java/lang/Object
        });
        boolean flag;
        if (Android.MEMORY_CLASS != null && !Android.IS_ROBOLECTRIC)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        while (!flag) 
        {
            try
            {
                class1.getMethod("getByte", new Class[] {
                    java/lang/Object, Long.TYPE
                });
                class1.getMethod("putByte", new Class[] {
                    java/lang/Object, Long.TYPE, Byte.TYPE
                });
                class1.getMethod("getBoolean", new Class[] {
                    java/lang/Object, Long.TYPE
                });
                class1.getMethod("putBoolean", new Class[] {
                    java/lang/Object, Long.TYPE, Boolean.TYPE
                });
                class1.getMethod("getFloat", new Class[] {
                    java/lang/Object, Long.TYPE
                });
                class1.getMethod("putFloat", new Class[] {
                    java/lang/Object, Long.TYPE, Float.TYPE
                });
                class1.getMethod("getDouble", new Class[] {
                    java/lang/Object, Long.TYPE
                });
                class1.getMethod("putDouble", new Class[] {
                    java/lang/Object, Long.TYPE, Double.TYPE
                });
            }
            catch (Throwable throwable)
            {
                Logger logger1 = logger;
                Level level = Level.WARNING;
                String s = String.valueOf(throwable);
                logger1.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", (new StringBuilder(String.valueOf(s).length() + 71)).append("platform method missing - proto runtime falling back to safer methods: ").append(s).toString());
                return false;
            }
            return true;
        }
        return true;
    }

    private static boolean supportsUnsafeByteBufferOperations()
    {
        if (UNSAFE != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        Class class1;
        class1 = UNSAFE.getClass();
        class1.getMethod("objectFieldOffset", new Class[] {
            java/lang/reflect/Field
        });
        class1.getMethod("getLong", new Class[] {
            java/lang/Object, Long.TYPE
        });
        if (bufferAddressField() != null)
        {
            boolean flag;
            if (Android.MEMORY_CLASS != null && !Android.IS_ROBOLECTRIC)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            while (!flag) 
            {
                try
                {
                    class1.getMethod("getByte", new Class[] {
                        Long.TYPE
                    });
                    class1.getMethod("putByte", new Class[] {
                        Long.TYPE, Byte.TYPE
                    });
                    class1.getMethod("getInt", new Class[] {
                        Long.TYPE
                    });
                    class1.getMethod("putInt", new Class[] {
                        Long.TYPE, Integer.TYPE
                    });
                    class1.getMethod("getLong", new Class[] {
                        Long.TYPE
                    });
                    class1.getMethod("putLong", new Class[] {
                        Long.TYPE, Long.TYPE
                    });
                    class1.getMethod("copyMemory", new Class[] {
                        Long.TYPE, Long.TYPE, Long.TYPE
                    });
                    class1.getMethod("copyMemory", new Class[] {
                        java/lang/Object, Long.TYPE, java/lang/Object, Long.TYPE, Long.TYPE
                    });
                }
                catch (Throwable throwable)
                {
                    Logger logger1 = logger;
                    Level level = Level.WARNING;
                    String s = String.valueOf(throwable);
                    logger1.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", (new StringBuilder(String.valueOf(s).length() + 71)).append("platform method missing - proto runtime falling back to safer methods: ").append(s).toString());
                    return false;
                }
                return true;
            }
            return true;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    static 
    {
        Object obj1 = null;
        UNSAFE = getUnsafe();
        MEMORY_CLASS = Android.MEMORY_CLASS;
        IS_ANDROID_64 = determineAndroidSupportByAddressSize(Long.TYPE);
        IS_ANDROID_32 = determineAndroidSupportByAddressSize(Integer.TYPE);
        Object obj;
        Field field1;
        int i;
        long l;
        boolean flag;
        if (UNSAFE == null)
        {
            obj = null;
        } else
        {
            if (Android.MEMORY_CLASS != null && !Android.IS_ROBOLECTRIC)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                if (IS_ANDROID_64)
                {
                    obj = new Android64MemoryAccessor(UNSAFE);
                } else
                if (IS_ANDROID_32)
                {
                    obj = new Android32MemoryAccessor(UNSAFE);
                } else
                {
                    obj = null;
                }
            } else
            {
                obj = new JvmMemoryAccessor(UNSAFE);
            }
        }
        MEMORY_ACCESSOR = ((MemoryAccessor) (obj));
        HAS_UNSAFE_ARRAY_OPERATIONS = supportsUnsafeArrayOperations();
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            i = MEMORY_ACCESSOR.unsafe.arrayBaseOffset([B);
        } else
        {
            i = -1;
        }
        BYTE_ARRAY_BASE_OFFSET = i;
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayBaseOffset([Z);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayIndexScale([Z);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayBaseOffset([I);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayIndexScale([I);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayBaseOffset([J);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayIndexScale([J);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayBaseOffset([F);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayIndexScale([F);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayBaseOffset([D);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayIndexScale([D);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayBaseOffset([Ljava/lang/Object;);
        }
        if (HAS_UNSAFE_ARRAY_OPERATIONS)
        {
            MEMORY_ACCESSOR.unsafe.arrayIndexScale([Ljava/lang/Object;);
        }
        obj = bufferAddressField();
        if (obj == null || MEMORY_ACCESSOR == null)
        {
            l = -1L;
        } else
        {
            l = MEMORY_ACCESSOR.unsafe.objectFieldOffset(((Field) (obj)));
        }
        BUFFER_ADDRESS_OFFSET = l;
        field1 = field(java/lang/String, "value");
        obj = obj1;
        if (field1 != null)
        {
            obj = obj1;
            if (field1.getType() == [C)
            {
                obj = field1;
            }
        }
        if (obj != null && MEMORY_ACCESSOR != null)
        {
            MEMORY_ACCESSOR.unsafe.objectFieldOffset(((Field) (obj)));
        }
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        IS_BIG_ENDIAN = flag;
    }

    private class _cls1
        implements PrivilegedExceptionAction
    {

        public final Object run()
            throws Exception
        {
            Object obj1 = null;
            Field afield[] = sun/misc/Unsafe.getDeclaredFields();
            int j = afield.length;
            int i = 0;
            do
            {
label0:
                {
                    Object obj = obj1;
                    if (i < j)
                    {
                        obj = afield[i];
                        ((Field) (obj)).setAccessible(true);
                        obj = ((Field) (obj)).get(null);
                        if (!sun/misc/Unsafe.isInstance(obj))
                        {
                            break label0;
                        }
                        obj = (Unsafe)sun/misc/Unsafe.cast(obj);
                    }
                    return obj;
                }
                i++;
            } while (true);
        }

        _cls1()
        {
        }
    }

}
