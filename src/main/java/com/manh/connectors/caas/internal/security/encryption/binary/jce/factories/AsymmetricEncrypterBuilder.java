/*
 * (c) 2003-2019 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.manh.connectors.caas.internal.security.encryption.binary.jce.factories;


import com.manh.connectors.caas.internal.security.encryption.binary.Encrypter;
import com.manh.connectors.caas.internal.security.encryption.binary.jce.JCEEncrypter;
import com.manh.connectors.caas.internal.security.encryption.binary.jce.algorithms.EncryptionAlgorithm;
import com.manh.connectors.caas.internal.security.encryption.binary.jce.algorithms.EncryptionMode;
import com.manh.connectors.caas.internal.security.encryption.binary.jce.algorithms.EncryptionPadding;
import com.manh.connectors.caas.internal.security.utils.keyfactories.AsymmetricEncryptionKeyFactory;

public class AsymmetricEncrypterBuilder extends EncrypterBuilder {

    @Override
	public Encrypter build() {

		return new JCEEncrypter(EncryptionAlgorithm.RSA, EncryptionMode.ECB, EncryptionPadding.PKCS1PADDING,
				new AsymmetricEncryptionKeyFactory(EncryptionAlgorithm.RSA.name(), key, usePublicKeyToEncrypt));
	}
}
