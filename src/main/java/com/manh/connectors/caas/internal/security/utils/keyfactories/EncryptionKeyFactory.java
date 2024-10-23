/*
 * (c) 2003-2019 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.manh.connectors.caas.internal.security.utils.keyfactories;

import java.security.Key;

public interface EncryptionKeyFactory {

    Key buildEncryptionKey();
    Key buildDecryptionKey();
    
    String getPlainKey();
}
