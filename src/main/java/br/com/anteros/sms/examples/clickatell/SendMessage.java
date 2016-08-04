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

package br.com.anteros.sms.examples.clickatell;

import br.com.anteros.sms.AGateway;
import br.com.anteros.sms.IOutboundMessageNotification;
import br.com.anteros.sms.Library;
import br.com.anteros.sms.OutboundMessage;
import br.com.anteros.sms.Service;
import br.com.anteros.sms.http.ClickatellHTTPGateway;

public class SendMessage
{
	public void doIt() throws Exception
	{
		OutboundMessage msg;
		OutboundNotification outboundNotification = new OutboundNotification();
		System.out.println("Example: Send message from Clickatell HTTP Interface.");
		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		ClickatellHTTPGateway gateway = new ClickatellHTTPGateway("clickatell.http.1", "api_id", "username", "password");
		gateway.setOutbound(true);
		Service.getInstance().setOutboundMessageNotification(outboundNotification);
		// Do we need secure (https) communication?
		// True uses "https", false uses "http" - default is false.
		gateway.setSecure(true);
		Service.getInstance().addGateway(gateway);
		Service.getInstance().startService();
		// Create a message.
		msg = new OutboundMessage("+306948494037", "Hello from SMSLib (Clickatell handler)");
		//msg.setFrom("SMSLIB.ORG");
		// Ask for coverage.
		System.out.println("Is recipient's network covered? : " + gateway.queryCoverage(msg));
		// Send the message.
		Service.getInstance().sendMessage(msg);
		System.out.println(msg);
		// Now query the service to find out our credit balance.
		System.out.println("Remaining credit: " + gateway.queryBalance());
		// Send some messages in async mode...
		// After this, your IOutboundMessageNotification method will be called
		// for each message sent out.
		//msg = new OutboundMessage("+30...", "Max");
		//srv.queueMessage(msg, "clickatell.http.1", AbstractGateway.Priority.HIGH);
		//msg = new OutboundMessage("+30...", "Min");
		//srv.queueMessage(msg, "clickatell.http.1", AbstractGateway.Priority.LOW);
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
