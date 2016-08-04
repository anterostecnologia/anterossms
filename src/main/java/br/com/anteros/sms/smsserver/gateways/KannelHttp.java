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
import br.com.anteros.sms.http.KannelHTTPGateway;

/**
 * <b>SMSServer Application Gateway.</b>
 */
public class KannelHttp extends AGateway
{
	public KannelHttp(String myGatewayId, Properties myProps, br.com.anteros.sms.smsserver.SMSServer myServer)
	{
		super(myGatewayId, myProps, myServer);
		setDescription(myGatewayId + " Kannel Gateway.");
	}

	@Override
	public void create() throws Exception
	{
		String propName;
		propName = getGatewayId() + ".";
		Properties properties = getProperties();
		KannelHTTPGateway gateway = new br.com.anteros.sms.http.KannelHTTPGateway(getGatewayId(), properties.getProperty(propName + "url"), properties.getProperty(propName + "username"), properties.getProperty(propName + "password"));
		setGateway(gateway);
		gateway.setAdminUrl(properties.getProperty(propName + "adminurl"));
		gateway.setAdminPassword(properties.getProperty(propName + "adminpassword"));
		gateway.setStatusPassword(properties.getProperty(propName + "statuspassword"));
		if ("yes".equalsIgnoreCase(properties.getProperty(propName + "outbound"))) gateway.setOutbound(true);
		else if ("no".equalsIgnoreCase(properties.getProperty(propName + "outbound"))) gateway.setOutbound(false);
		else throw new Exception("Incorrect parameter: " + propName + "outbound");
		if ("yes".equalsIgnoreCase(properties.getProperty(propName + "autostartsmsc"))) gateway.setAutoStartSmsc(true);
		if ("yes".equalsIgnoreCase(properties.getProperty(propName + "autostopsmsc"))) gateway.setAutoStopSmsc(true);
	}
}
