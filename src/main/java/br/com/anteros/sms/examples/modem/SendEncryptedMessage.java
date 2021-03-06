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
// SendEncryptedMessage.java - Sample application.

package br.com.anteros.sms.examples.modem;

import javax.crypto.spec.SecretKeySpec;
import br.com.anteros.sms.Library;
import br.com.anteros.sms.OutboundEncryptedMessage;
import br.com.anteros.sms.Service;
import br.com.anteros.sms.crypto.AESKey;
import br.com.anteros.sms.modem.SerialModemGateway;

public class SendEncryptedMessage
{
	public void doIt() throws Exception
	{
		System.out.println("Example: Send an encrypted message from a serial gsm modem.");
		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		SerialModemGateway gateway = new SerialModemGateway("modem.com1", "COM5", 57600, "Nokia", "E60");
		gateway.setInbound(true);
		gateway.setOutbound(true);
		gateway.setSimPin("0000");
		Service.getInstance().addGateway(gateway);
		Service.getInstance().startService();
		System.out.println();
		System.out.println("Modem Information:");
		System.out.println("  Manufacturer: " + gateway.getManufacturer());
		System.out.println("  Model: " + gateway.getModel());
		System.out.println("  Serial No: " + gateway.getSerialNo());
		System.out.println("  SIM IMSI: " + gateway.getImsi());
		System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
		System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
		System.out.println();
		// Create a new AES Key with a known key value. 
		// Register it in KeyManager in order to keep it active. SMSLib will then automatically
		// encrypt / decrypt all messages send to / received from this number.
		Service.getInstance().getKeyManager().registerKey("+306948494037", new AESKey(new SecretKeySpec("0011223344556677".getBytes(), "AES")));
		OutboundEncryptedMessage msg = new OutboundEncryptedMessage("+306948494037", "Hello (encrypted) from SMSLib!".getBytes());
		Service.getInstance().sendMessage(msg);
		System.out.println(msg);
		System.out.println("Now Sleeping - Hit <enter> to terminate.");
		System.in.read();
		Service.getInstance().stopService();
	}

	public static void main(String args[])
	{
		SendEncryptedMessage app = new SendEncryptedMessage();
		try
		{
			app.doIt();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
