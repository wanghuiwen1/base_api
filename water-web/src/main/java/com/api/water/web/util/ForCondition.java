package com.api.water.web.util;


import cn.hutool.core.util.ObjectUtil;
import org.hibernate.validator.internal.util.StringHelper;

import java.util.Date;

public class ForCondition {
    


    public static String  StringGenerator(String property){
        if(StringHelper.isNullOrEmptyString(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }

    }

    public static String  StringGenerator(String property,Boolean checkNull){
        if(!checkNull){
            return property;
        }
        if(StringHelper.isNullOrEmptyString(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }
    }

    public static Date  DateGenerator(Date property){
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }

    }

    public static Date  DateGenerator(Date property,Boolean checkNull){
        if(!checkNull){
            return property;
        }
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }
    }
    public static Long  LongGenerator(Long property){
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }

    }

    public static Long  LongGenerator(Long property,Boolean checkNull){
        if(!checkNull){
            return property;
        }
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }
    }
    public static Integer  IntegerGenerator(Integer property){
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }

    }

    public static Integer  IntegerGenerator(Integer property,Boolean checkNull){
        if(!checkNull){
            return property;
        }
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }
    }
    public static Byte  ByteGenerator(Byte property){
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }

    }

    public static Byte  ByteGenerator(Byte property,Boolean checkNull){
        if(!checkNull){
            return property;
        }
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }
    }
    public static Boolean  BooleanGenerator(Boolean property){
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }

    }

    public static Boolean  BooleanGenerator(Boolean property,Boolean checkNull){
        if(!checkNull){
            return property;
        }
        if(ObjectUtil.isNull(property)){
            throw new RuntimeException("请注意，传入的过滤参数为空，若想忽略此错误请在第二个参数传递false");
        }else{
            return property;
        }
    }
}
