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
// SendMessage.java - Sample application.
//
// This application shows you the basic procedure for sending messages.
// You will find how to send synchronous and asynchronous messages.
//
// For asynchronous dispatch, the example application sets a callback
// notification, to see what's happened with messages.

package br.com.anteros.sms.examples.modem;

import br.com.anteros.sms.AGateway;
import br.com.anteros.sms.IOutboundMessageNotification;
import br.com.anteros.sms.Library;
import br.com.anteros.sms.OutboundMessage;
import br.com.anteros.sms.Service;
import br.com.anteros.sms.modem.SerialModemGateway;

public class SendMessage
{
	public void doIt() throws Exception
	{
		OutboundNotification outboundNotification = new OutboundNotification();
		System.out.println("Example: Send message from a serial gsm modem.");
		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		SerialModemGateway gateway = new SerialModemGateway("modem.com1", "COM4", 115200, "Huawei", "");
		gateway.setInbound(true);
		gateway.setOutbound(true);
		gateway.setSimPin("0000");
		// Explicit SMSC address set is required for some modems.
		// Below is for VODAFONE GREECE - be sure to set your own!
		gateway.setSmscNumber("+306942190000");
		Service.getInstance().setOutboundMessageNotification(outboundNotification);
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
		// Send a message synchronously.
		OutboundMessage msg = new OutboundMessage("306974000000", "Hello from SMSLib!");
		Service.getInstance().sendMessage(msg);
		System.out.println(msg);
		// Or, send out a WAP SI message.
		//OutboundWapSIMessage wapMsg = new OutboundWapSIMessage("306974000000",  new URL("http://www.smslib.org/"), "Visit SMSLib now!");
		//Service.getInstance().sendMessage(wapMsg);
		//System.out.println(wapMsg);
		// You can also queue some asynchronous messages to see how the callbacks
		// are called...
		//msg = new OutboundMessage("309999999999", "Wrong number!");
		//srv.queueMessage(msg, gateway.getGatewayId());
		//msg = new OutboundMessage("308888888888", "Wrong number!");
		//srv.queueMessage(msg, gateway.getGatewayId());
		System.out.println("Now Sleeping - Hit <enter> to terminate.");
		System.in.read();
		Service.getInstance().stopService();
	}

	public class OutboundNotification implements IOutboundMessageNotification
	{
		public void process(AGateway gateway, OutboundMessage msg)
		{
			System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
			System.out.println(msg);
		}
	}

	public static void main(String args[])
	{
		SendMessage app = new SendMessage();
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
