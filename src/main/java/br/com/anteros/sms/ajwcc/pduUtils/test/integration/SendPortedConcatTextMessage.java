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

import br.com.anteros.sms.*;

public class SendPortedConcatTextMessage extends AbstractTester
{
	@Override
	public void test() throws Exception
	{
		OutboundMessage msg;
		// long message
		msg = new OutboundMessage(MODEM_NUMBER, "3 8 Thank you for using this service.  Your transaction has been logged as TXN 7 abcdef 7 Thank you for using this service.  Your transaction has been logged as TXN 7 abcdefz");
		// port of j2me app
		msg.setSrcPort(0);
		msg.setDstPort(4501);
		Service.getInstance().sendMessage(msg);
		System.out.println(msg);
		System.out.println("Now Sleeping - Hit <enter> to terminate.");
		System.in.read();
		Service.getInstance().stopService();
	}

	public static void main(String args[])
	{
		SendPortedConcatTextMessage app = new SendPortedConcatTextMessage();
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
