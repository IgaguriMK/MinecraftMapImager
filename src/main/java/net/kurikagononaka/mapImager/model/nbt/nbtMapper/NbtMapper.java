/*
 *  Main Author: Igaguri
 *  Copyright: 2017 Igaguri
 *  License: MIT LICENSE
 *           See README in repository.
 */

package net.kurikagononaka.mapImager.model.nbt.nbtMapper;

import com.flowpowered.nbt.*;

import java.lang.reflect.Field;


public class NbtMapper {
    public static Object parse(Class targetClass, Tag<?> tag) {

        try {
            Object obj = targetClass.newInstance();

            for (Field field : obj.getClass().getFields()) {
                NbtValue nbtValue = field.getAnnotation(NbtValue.class);

                if (nbtValue == null) continue;

                String pathStr = nbtValue.value();
                Tag<?> valueTag = find(pathStr, tag);

                if (valueTag == null) continue;

                setValue(obj, field, valueTag);
            }

            return obj;

        } catch (InstantiationException e) {
            throw new MappingFailException("Failed mapping value: Can't instantiate target class(" + targetClass.getName() + ").", e);
        } catch (IllegalAccessException e) {
            throw new MappingFailException("Failed mapping value: Can't instantiate target class(" + targetClass.getName() + ").", e);
        }
    }

    private static void setValue(Object obj, Field field, Tag<?> tag) {


        if (field.getType().equals(Integer.TYPE)) {
            setIntValue(obj, field, tag);
            return;
        }

        if (field.getType().isArray()) {
            setArrayValue(obj, field, tag);
            return;
        }

        return;
    }

    private static void setIntValue(Object obj, Field field, Tag<?> tag) {
        try {
            if (tag instanceof IntTag) {
                IntTag intTag = (IntTag) tag;
                field.setInt(obj, intTag.getValue().intValue());
                return;
            }

            if (tag instanceof ShortTag) {
                ShortTag shortTag = (ShortTag) tag;
                field.setInt(obj, shortTag.getValue().intValue());
                return;
            }

            if (tag instanceof ByteTag) {
                ByteTag byteTag = (ByteTag) tag;
                field.setInt(obj, byteTag.getValue().intValue());
                return;
            }

        } catch (IllegalAccessException e) {
            throw new MappingFailException("Failed mapping value: Can't access target field(" + field.getName() + ").", e);
        }

        throw new MappingFailException("Failed mapping value: No compatible tag found, found " + tag.getClass().getName() + ".");
    }

    private static void setArrayValue(Object obj, Field field, Tag<?> tag) {
        if (field.getType().getComponentType().equals(Byte.TYPE)) {
            setByteArrayValue(obj, field, tag);
            return;
        }
    }

    private static void setByteArrayValue(Object obj, Field field, Tag<?> tag) {
        try {
            if (tag instanceof ByteArrayTag) {
                ByteArrayTag byteArrayTag = (ByteArrayTag) tag;
                field.set(obj, byteArrayTag.getValue());
                return;
            }

        } catch (IllegalAccessException e) {
            throw new MappingFailException("Failed mapping value: Can't access target field(" + field.getName() + ").", e);
        }

        throw new MappingFailException("Failed mapping value: No compatible tag found, found " + tag.getClass().getName() + ".");
    }


    public static Tag<?> find(String pathString, Tag<?> tag) {
        Path path = Path.parse(pathString);

        return find(path.getChild(), tag);
    }

    public static Tag<?> find(Path path, Tag<?> tag) {

        if (path == null) {
            return tag;
        }

        try {
            CompoundTag compoundTag = (CompoundTag) tag;
            CompoundMap compoundMap = compoundTag.getValue();

            return find(path.getChild(), compoundMap.get(path.getName()));

        } catch (ClassCastException e) {
            throw new MappingFailException("Failed find child tag: tas isn't CompoundTag, it is " + tag.getClass().getName() + ".", e);
        }
    }

    public static class MappingFailException extends RuntimeException {
        public MappingFailException() {
        }

        public MappingFailException(String message) {
            super(message);
        }

        public MappingFailException(String message, Throwable cause) {
            super(message, cause);
        }

        public MappingFailException(Throwable cause) {
            super(cause);
        }
    }
}
