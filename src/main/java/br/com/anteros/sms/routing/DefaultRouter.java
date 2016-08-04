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

import java.util.Collection;
import br.com.anteros.sms.AGateway;
import br.com.anteros.sms.OutboundMessage;

/**
 * Default Router implementation which actually doesn't perform any custom
 * routing, instead relies on basic routing of ARouter.
 * 
 * @author Bassam Al-Sarori
 */
public class DefaultRouter extends ARouter
{
	/* (non-Javadoc)
	 * @see br.com.anteros.sms.routing.ARouter#route(br.com.anteros.sms.OutboundMessage, java.util.Collection)
	 */
	@Override
	public Collection<AGateway> customRoute(OutboundMessage msg, Collection<AGateway> gateways)
	{
		return gateways;
	}
}
