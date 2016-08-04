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
// Gateway used: Kannel (http://www.kannel.org)
// Please look the KannelHTTPGateway documentation for details.

package br.com.anteros.sms.examples.kannel;

import br.com.anteros.sms.AGateway;
import br.com.anteros.sms.IGatewayStatusNotification;
import br.com.anteros.sms.Library;
import br.com.anteros.sms.OutboundMessage;
import br.com.anteros.sms.Service;
import br.com.anteros.sms.AGateway.GatewayStatuses;
import br.com.anteros.sms.http.KannelHTTPGateway;

/**
 * @author Bassam Al-Sarori
 */
public class SendMessage
{
	public void doIt() throws Exception
	{
		GatewayStatusNotification statusNotification = new GatewayStatusNotification();
		OutboundMessage msg;
		System.out.println("Example: Send message through Kannel HTTP Interface.");
		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		KannelHTTPGateway gateway = new KannelHTTPGateway("mysmsc", "http://localhost:13013/cgi-bin/sendsms", "simple", "elpmis");
		// Uncomment in order gateway to start and stop SMSC automatically on Kannel
		//gateway.setAutoStartSmsc(true);
		//gateway.setAutoStopSmsc(true);
		// Set Kannel's Admin URL and password to be used starting, stopping and checking SMSC status   
		gateway.setAdminUrl("http://localhost:13000");
		gateway.setAdminPassword("bar");
		gateway.setOutbound(true);
		Service.getInstance().addGateway(gateway);
		Service.getInstance().setGatewayStatusNotification(statusNotification);
		Service.getInstance().startService();
		// Send a message.
		msg = new OutboundMessage("+967712831950", "Hello from SMSLib (Kannel handler)");
		//msg.setEncoding(MessageEncodings.ENCUCS2);
		Service.getInstance().sendMessage(msg);
		System.out.println(msg);
		System.out.println("Now Sleeping - Hit <enter> to terminate.");
		System.in.read();
		Service.getInstance().stopService();
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

	public class GatewayStatusNotification implements IGatewayStatusNotification
	{
		public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus)
		{
			System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
		}
	}
}
