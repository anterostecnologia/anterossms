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
//
// Bulk Operator used: Clickatell (http://www.clickatell.com)
// Please look the ClickatellHTTPGateway documentation for details.

package br.com.anteros.sms.ajwcc.pduUtils.test.integration;

import br.com.anteros.sms.*;
import br.com.anteros.sms.http.*;

public class ClickatellSendMessage extends AbstractTester
{
	@Override
	public void test() throws Exception
	{
		OutboundMessage msg;
		OutboundNotification outboundNotification = new OutboundNotification();
		System.out.println("Example: Send message from Clickatell HTTP Interface.");
		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		ClickatellHTTPGateway gateway = new ClickatellHTTPGateway("clickatell.http.1", " 2982992", "tdelenikas", "AFghjkr3");
		gateway.setOutbound(true);
		Service.getInstance().setOutboundMessageNotification(outboundNotification);
		// Do we need secure (https) communication?
		// True uses "https", false uses "http" - default is false.
		gateway.setSecure(true);
		Service.getInstance().addGateway(gateway);
		gateway.startGateway();
		// Create a message.
		msg = new OutboundMessage("xxxx", "Hello from SMSLib (Clickatell handler)");
		msg.setFrom("SMSLIB.ORG");
		// Ask for coverage.
		System.out.println("Is recipient's network covered? : " + gateway.queryCoverage(msg));
		// Send the message.
		gateway.sendMessage(msg);
		System.out.println(msg);
		System.out.println(msg.getPduUserDataHeader());
		// Now query the service to find out our credit balance.
		System.out.println("Remaining credit: " + gateway.queryBalance());
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
		ClickatellSendMessage app = new ClickatellSendMessage();
		try
		{
			app.initModem();
			app.test();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
