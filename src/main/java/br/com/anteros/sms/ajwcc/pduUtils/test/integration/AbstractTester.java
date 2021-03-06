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

package br.com.anteros.sms.ajwcc.pduUtils.test.integration;

import br.com.anteros.sms.*;
import br.com.anteros.sms.Message.*;
import br.com.anteros.sms.modem.*;

public abstract class AbstractTester
{
	protected static final String MODEM_NUMBER = "xxxx";

	protected static final String PHONE_NUMBER = "xxxx";

	protected void initModem() throws Exception
	{
		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		SerialModemGateway gateway = new SerialModemGateway("modem.com1", "COM10", 115200, "Wavecom", "");
		gateway.setSimPin("0000");
		gateway.setOutbound(true);
		OutboundNotification outboundNotification = new OutboundNotification();
		Service.getInstance().setOutboundMessageNotification(outboundNotification);
		gateway.setInbound(true);
		InboundNotification inboundNotification = new InboundNotification();
		Service.getInstance().setInboundMessageNotification(inboundNotification);
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
	}

	protected abstract void test() throws Exception;

	public class InboundNotification implements IInboundMessageNotification
	{
		public void process(AGateway gateway, MessageTypes msgType, InboundMessage msg)
		{
			if (msgType == MessageTypes.INBOUND)
			{
				System.out.println(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
			}
			else if (msgType == MessageTypes.STATUSREPORT)
			{
				System.out.println(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());
			}
			System.out.println(msg);
			try
			{
				// Uncomment following line if you wish to delete the message upon arrival.
				Service.getInstance().deleteMessage(msg);
			}
			catch (Exception e)
			{
				System.out.println("Oops!!! Something gone bad...");
				e.printStackTrace();
			}
		}
	}

	public class CallNotification implements ICallNotification
	{
		public void process(AGateway gateway, String callerId)
		{
			System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
		}
	}

	public class OutboundNotification implements IOutboundMessageNotification
	{
		public void process(AGateway gateway, OutboundMessage msg)
		{
			System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
			System.out.println(msg);
		}
	}
}
