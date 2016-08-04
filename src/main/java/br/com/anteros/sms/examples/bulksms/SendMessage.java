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
// Bulk Operator used: BULKSMS (http://www.bulksms.com)
// Please look the BulkSmsHTTPGateway documentation for details.

package br.com.anteros.sms.examples.bulksms;

import br.com.anteros.sms.Library;
import br.com.anteros.sms.OutboundMessage;
import br.com.anteros.sms.Service;
import br.com.anteros.sms.http.BulkSmsHTTPGateway;

public class SendMessage
{
	public void doIt() throws Exception
	{
		try
		{
			OutboundMessage msg;
			System.out.println("Example: Send message from BulkSMS HTTP Interface.");
			System.out.println(Library.getLibraryDescription());
			System.out.println("Version: " + Library.getLibraryVersion());
			BulkSmsHTTPGateway gateway = new BulkSmsHTTPGateway("bulksms.http.1", "username", "password");
			gateway.setOutbound(true);
			Service.getInstance().addGateway(gateway);
			Service.getInstance().startService();
			// Query the service to find out our credit balance.
			System.out.println("Remaining credit: " + gateway.queryBalance());
			// Send a message synchronously.
			msg = new OutboundMessage("+30...", "Hello from SMSLib (BULKSMS handler)");
			Service.getInstance().sendMessage(msg);
			System.out.println(msg);
			System.out.println("Now Sleeping - Hit <enter> to terminate.");
			System.in.read();
			Service.getInstance().stopService();
		}
		catch (Exception e)
		{
			System.out.print(e);
			e.printStackTrace();
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
