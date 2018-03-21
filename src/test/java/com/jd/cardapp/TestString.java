package com.jd.cardapp;

import com.jd.cardapp.util.password.Secret;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestString {

    @Test
    public void test1()
    {
        Secret secret=new Secret();
        String s="111111";
        String d="123";
        System.out.println(Secret.enPass(s)+"   "+Secret.enPass(d));
        System.out.println(Secret.dePass(Secret.enPass(s))+"   "+Secret.dePass(Secret.enPass(d)) );
    }

    @Test
    public void enpass()
    {
        String s="asasbusj2lfk85hy";
        System.out.println(Secret.enPass(s));
    }

    @Test
    public void depass()
    {
        String s="fovtZnZPnPI";
        System.out.println(Secret.dePass(s));
    }

    @Test
    public void test2()
    {
        String a = "abc.jpg";
        System.out.println( a.substring(a.lastIndexOf(".")) );
    }

}
