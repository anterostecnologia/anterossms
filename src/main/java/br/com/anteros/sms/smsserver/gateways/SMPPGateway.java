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

import java.lang.reflect.Constructor;
import java.util.Properties;
import br.com.anteros.sms.smpp.AbstractSMPPGateway;
import br.com.anteros.sms.smpp.Address;
import br.com.anteros.sms.smpp.BindAttributes;
import br.com.anteros.sms.smpp.Address.NumberingPlanIndicator;
import br.com.anteros.sms.smpp.Address.TypeOfNumber;
import br.com.anteros.sms.smpp.BindAttributes.BindType;

/**
 * <b>SMSServer Application Gateway.</b>
 * 
 * @author Bassam Al-Sarori
 */
public class SMPPGateway extends AGateway
{
	public SMPPGateway(String myGatewayId, Properties myProps, br.com.anteros.sms.smsserver.SMSServer myServer)
	{
		super(myGatewayId, myProps, myServer);
		setDescription(myGatewayId + " SMPP Gateway.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void create() throws Exception
	{
		String implClass = getProperty("impl");
		Class<AbstractSMPPGateway> clazz = (Class<AbstractSMPPGateway>) Class.forName(implClass);
		Class<?>[] classArgs = new Class[] { String.class, String.class, int.class, BindAttributes.class };
		Constructor<AbstractSMPPGateway> con = clazz.getConstructor(classArgs);
		String host = getProperty("host");
		Integer port = Integer.parseInt(getProperty("port"));
		Object args[] = new Object[] { getGatewayId(), host, port, getBindAttributes() };
		AbstractSMPPGateway gateway = con.newInstance(args);
		String enquireLink = getProperty("enquirelink");
		if (enquireLink != null && !enquireLink.isEmpty())
		{
			gateway.setEnquireLink(Integer.parseInt(enquireLink));
		}
		String ton = getProperty("sourceton");
		TypeOfNumber typeOfNumber = (ton == null) ? TypeOfNumber.UNKNOWN : TypeOfNumber.valueOf(Byte.parseByte(ton));
		String npi = getProperty("sourcenpi");
		NumberingPlanIndicator numberingPlanIndicator = (npi == null) ? NumberingPlanIndicator.UNKNOWN : NumberingPlanIndicator.valueOf(Byte.parseByte(npi));
		gateway.setSourceAddress(new Address(typeOfNumber, numberingPlanIndicator));
		ton = getProperty("destton");
		typeOfNumber = (ton == null) ? TypeOfNumber.UNKNOWN : TypeOfNumber.valueOf(Byte.parseByte(ton));
		npi = getProperty("destnpi");
		numberingPlanIndicator = (npi == null) ? NumberingPlanIndicator.UNKNOWN : NumberingPlanIndicator.valueOf(Byte.parseByte(npi));
		gateway.setDestinationAddress(new Address(typeOfNumber, numberingPlanIndicator));
		setGateway(gateway);
	}

	private BindAttributes getBindAttributes()
	{
		String systemId = getProperty("systemid");
		String password = getProperty("password");
		String systemType = getProperty("systemtype");
		BindType bindType = BindType.getByShortName(getProperty("bindtype"));
		String ton = getProperty("bindton");
		TypeOfNumber typeOfNumber = (ton == null) ? TypeOfNumber.UNKNOWN : TypeOfNumber.valueOf(Byte.parseByte(ton));
		String npi = getProperty("bindnpi");
		NumberingPlanIndicator numberingPlanIndicator = (npi == null) ? NumberingPlanIndicator.UNKNOWN : NumberingPlanIndicator.valueOf(Byte.parseByte(npi));
		return new BindAttributes(systemId, password, systemType, bindType, new Address(typeOfNumber, numberingPlanIndicator));
	}

	private String getProperty(String name)
	{
		String propertyValue = getProperties().getProperty(getGatewayId() + "." + name);
		if (propertyValue != null) return propertyValue.trim();
		else return propertyValue;
	}
}
