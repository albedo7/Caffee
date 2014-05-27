package com.caffee.utils;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.AssertJUnit.assertTrue;

public class CryptoUtilsTest {
    private List<Object> objects = new ArrayList<>();
    private Map<String, String> encrypted = new HashMap<>();
    private Set<String> decrypted = new HashSet<>();

    @BeforeTest
    public void setUp() {
        objects.add(new Integer(123).toString());
        objects.add(new String("avFRDDDaa"));
        objects.add(new Long(149521).toString());

        String salt;
        String encrypt;
        for (Object o : objects) {
            salt = CryptoUtils.doSalt();
            encrypt = CryptoUtils.crypt(o, salt);
            encrypted.put(encrypt, salt);
        }
    }

    @Test
    public void testDecrypt() {
        for (String s : encrypted.keySet()) {
            decrypted.add(CryptoUtils.decrypt(s, encrypted.get(s)));
        }
        for (String s : decrypted) {
            assertTrue(objects.contains(s));
        }
    }
}
