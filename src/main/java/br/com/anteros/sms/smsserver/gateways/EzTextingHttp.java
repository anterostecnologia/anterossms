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


package br.com.anteros.sms.smsserver.gateways;

import java.util.Properties;

/**
 * <b>SMSServer Application Gateway.</b>
 */
public class EzTextingHttp extends AGateway
{
	public EzTextingHttp(String myGatewayId, Properties myProps, br.com.anteros.sms.smsserver.SMSServer myServer)
	{
		super(myGatewayId, myProps, myServer);
		setDescription("Default EzTexting Gateway.");
	}

	@Override
	public void create() throws Exception
	{
		String propName;
		propName = getGatewayId() + ".";
		setGateway(new br.com.anteros.sms.http.EzTextingHTTPGateway(getGatewayId(), getProperties().getProperty(propName + "username"), getProperties().getProperty(propName + "password")));
		if (getProperties().getProperty(propName + "outbound").equalsIgnoreCase("yes")) getGateway().setOutbound(true);
		else if (getProperties().getProperty(propName + "outbound").equalsIgnoreCase("no")) getGateway().setOutbound(false);
		else throw new Exception("Incorrect parameter: " + propName + "outbound");
	}
}
