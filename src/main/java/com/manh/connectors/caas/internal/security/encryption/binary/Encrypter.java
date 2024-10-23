/*
 * (c) 2003-2019 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.manh.connectors.caas.internal.security.encryption.binary;



import java.io.InputStream;
import java.io.OutputStream;

import com.manh.connectors.caas.internal.security.encryption.MuleEncryptionException;


public interface Encrypter {
    byte[] encrypt(byte[] data) throws MuleEncryptionException;

    void encrypt(InputStream in, OutputStream out) throws MuleEncryptionException;

    byte[] decrypt(byte[] data) throws MuleEncryptionException;

    void decrypt(InputStream in, OutputStream out) throws MuleEncryptionException;
}
