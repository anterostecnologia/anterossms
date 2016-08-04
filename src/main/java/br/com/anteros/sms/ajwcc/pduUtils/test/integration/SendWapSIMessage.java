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

package br.com.anteros.sms.ajwcc.pduUtils.test.integration;

import java.net.*;
import br.com.anteros.sms.*;

public class SendWapSIMessage extends AbstractTester
{
	@Override
	public void test() throws Exception
	{
		// send out a WAP SI message.
		OutboundWapSIMessage wapMsg = new OutboundWapSIMessage(MODEM_NUMBER, new URL("https://mail.google.com/"), "Visit GMail now!");
		Service.getInstance().sendMessage(wapMsg);
		System.out.println(wapMsg);
		System.out.println("Now Sleeping - Hit <enter> to terminate.");
		System.in.read();
		Service.getInstance().stopService();
	}

	public static void main(String args[])
	{
		SendWapSIMessage app = new SendWapSIMessage();
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
