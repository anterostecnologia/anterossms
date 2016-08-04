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
/**
 * 
 */

package br.com.anteros.sms.routing;

import java.util.ArrayList;
import java.util.Collection;
import br.com.anteros.sms.AGateway;
import br.com.anteros.sms.OutboundMessage;
import br.com.anteros.sms.AGateway.GatewayStatuses;

/**
 * Base class for all possible Router implementations.
 * 
 * @author Bassam Al-Sarori
 */
public abstract class ARouter
{
	/**
	 * Performs basic routing. Selects gateways that are able to outbound
	 * messages and are started. If gatewayId is specified in <code>msg</code>
	 * then only gateways with matching ids are selected. Before returning, the
	 * message and selected gateways are passed to <code>customRoute</code>
	 * method.
	 * 
	 * @param msg
	 *            message to be routed
	 * @param gateways
	 *            a collection of gateways that will used for selecting
	 *            appropriate gateways for routing.
	 * @return a collection of gateways that this <code>msg</code> should be
	 *         routed through
	 */
	public Collection<AGateway> route(OutboundMessage msg, Collection<AGateway> gateways)
	{
		ArrayList<AGateway> candidates = new ArrayList<AGateway>();
		for (AGateway gtw : gateways)
			if ((gtw.isOutbound()) && (gtw.getStatus() == GatewayStatuses.STARTED))
			{
				if (msg.getGatewayId().equalsIgnoreCase("*")) candidates.add(gtw);
				else if (msg.getGatewayId().equalsIgnoreCase(gtw.getGatewayId())) candidates.add(gtw);
			}
		return customRoute(msg, candidates);
	}

	/**
	 * Performs custom routing.
	 * 
	 * @param msg
	 *            message to be routed
	 * @param gateways
	 *            a collection of gateways that will used for selecting
	 *            appropriate gateways for routing.
	 * @return a collection of gateways that this <code>msg</code> should be
	 *         routed through
	 */
	public abstract Collection<AGateway> customRoute(OutboundMessage msg, Collection<AGateway> gateways);
}
