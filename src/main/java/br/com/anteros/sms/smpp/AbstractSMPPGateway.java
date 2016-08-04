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


package br.com.anteros.sms.smpp;

import java.io.IOException;
import java.util.Collection;
import br.com.anteros.sms.AGateway;
import br.com.anteros.sms.GatewayException;
import br.com.anteros.sms.InboundMessage;
import br.com.anteros.sms.TimeoutException;
import br.com.anteros.sms.InboundMessage.MessageClasses;

/**
 * SMPP Gateways' base class.
 * 
 * @author Bassam Al-Sarori
 */
public abstract class AbstractSMPPGateway extends AGateway
{
	protected String host;

	protected int port;

	protected BindAttributes bindAttributes;

	protected Address sourceAddress = new Address();

	protected Address destinationAddress = new Address();

	protected int enquireLink = -1;

	/**
	 * @param id
	 *            gateway ID
	 * @param host
	 *            SMPP host
	 * @param port
	 *            SMPP port
	 * @param bindAttributes
	 *            SMPP bind attributes
	 */
	public AbstractSMPPGateway(String id, String host, int port, BindAttributes bindAttributes)
	{
		super(id);
		this.host = host;
		this.port = port;
		this.bindAttributes = bindAttributes;
	}

	/* (non-Javadoc)
	 * @see br.com.anteros.sms.AGateway#getQueueSchedulingInterval()
	 */
	@Override
	public int getQueueSchedulingInterval()
	{
		return 500;
	}

	public String getHost()
	{
		return host;
	}

	public int getPort()
	{
		return port;
	}

	public BindAttributes getBindAttributes()
	{
		return bindAttributes;
	}

	public int getEnquireLink()
	{
		return enquireLink;
	}

	public void setEnquireLink(int enquireLink)
	{
		this.enquireLink = enquireLink;
	}

	public Address getSourceAddress()
	{
		return sourceAddress;
	}

	public void setSourceAddress(Address sourceAddress)
	{
		this.sourceAddress = sourceAddress;
	}

	public Address getDestinationAddress()
	{
		return destinationAddress;
	}

	public void setDestinationAddress(Address destinationAddress)
	{
		this.destinationAddress = destinationAddress;
	}

	@Override
	public void readMessages(Collection<InboundMessage> msgList, MessageClasses msgClass) throws TimeoutException, GatewayException, IOException, InterruptedException
	{
		// A dummy implementation
		// A temporarily fix for issue 354  
	}
}
