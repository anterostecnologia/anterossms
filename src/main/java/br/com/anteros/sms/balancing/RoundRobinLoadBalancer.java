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
 * RoundRobinLoadBalancer is forwarding messages via each gateway in turns. This
 * is the default SMSLib load balancer.
 */
public final class RoundRobinLoadBalancer extends LoadBalancer
{
	private int currentGateway;

	public RoundRobinLoadBalancer()
	{
		this.currentGateway = 0;
	}

	/**
	 * This Load Balancing implementation returns every other available gateway
	 * on each invocation.
	 */
	@Override
	public AGateway balance(OutboundMessage msg, Collection<AGateway> candidates)
	{
		int currentIndex;
		ArrayList<AGateway> c = new ArrayList<AGateway>(candidates);
		synchronized (this)
		{
			if (this.currentGateway >= c.size()) this.currentGateway = 0;
			currentIndex = this.currentGateway++;
		}
		return (c.get(currentIndex));
	}
}
