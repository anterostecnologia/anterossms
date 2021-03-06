/*******************************************************************************
 * Copyright 2016 Anteros Tecnologia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package br.com.anteros.sms;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import br.com.anteros.sms.ajwcc.pduUtils.gsm3040.*;
import br.com.anteros.sms.crypto.AKey;
import br.com.anteros.sms.crypto.KeyManager;

/**
 * Class representing an inbound encrypted sms message.
 */
public class InboundEncryptedMessage extends InboundBinaryMessage
{
	private static final long serialVersionUID = 2L;

	public InboundEncryptedMessage(SmsDeliveryPdu pdu, int memIndex, String memLocation)
	{
		super(pdu, memIndex, memLocation);
	}

	public String getDecryptedText() throws SMSLibException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException
	{
		KeyManager km = KeyManager.getInstance();
		if (km.getKey(getOriginator()) != null) setDataBytes(km.decrypt(getOriginator(), getDataBytes()));
		else throw new SMSLibException("Message is not encrypted, have you defined the key in KeyManager?");
		return AKey.asString(getDataBytes());
	}
}
