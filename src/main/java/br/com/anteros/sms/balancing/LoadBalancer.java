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


package br.com.anteros.sms.balancing;

import java.util.ArrayList;
import java.util.Collection;
import br.com.anteros.sms.AGateway;
import br.com.anteros.sms.OutboundMessage;

/**
 * Load Balancing base class. Implements default trivial Load Balancing - just
 * picking first available Gateway to send message. Create subclasses to
 * implement custom functionality.
 * 
 * @author Tomek Cejner
 */
public class LoadBalancer
{
	public LoadBalancer()
	{
	}

	/**
	 * Core of Load Balancing. Default is trivial selection of first candidate.
	 * 
	 * @param msg
	 *            Message to be sent.
	 * @param candidates
	 *            List of candidate gateways to choose from
	 */
	public AGateway balance(OutboundMessage msg, Collection<AGateway> candidates)
	{
		return new ArrayList<AGateway>(candidates).get(0);
	}
}
