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

package br.com.anteros.sms;

import br.com.anteros.sms.AGateway.GatewayStatuses;

/**
 * Interface of the callback class used by SMSLib. SMSLib will call this method
 * whenever a gateway changes status.
 * 
 * @see Service#setGatewayStatusNotification(IGatewayStatusNotification)
 */
public interface IGatewayStatusNotification
{
	/**
	 * This method will be called by SMSLib whenever a gateway changes its
	 * status.
	 * 
	 * @param gateway
	 *            The Gateway involved in the status change.
	 * @param oldStatus
	 *            The old status.
	 * @param newStatus
	 *            The new status.
	 */
	void process(final AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus);
}
