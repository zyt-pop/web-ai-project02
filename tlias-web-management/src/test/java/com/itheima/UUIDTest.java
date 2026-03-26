package com.itheima;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {
    @Test
    public void testUuid(){
        String str="012345678";
        String new_str = str.substring(1,8);
        System.out.println(new_str);

//        for (int i = 0; i < 10; i++) {
//            System.out.println(UUID.randomUUID().toString());
//        }
    }
}
