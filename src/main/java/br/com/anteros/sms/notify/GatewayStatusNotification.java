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


package br.com.anteros.sms.notify;

import br.com.anteros.sms.AGateway;
import br.com.anteros.sms.AGateway.GatewayStatuses;

public class GatewayStatusNotification extends Notification
{
	private GatewayStatuses oldStatus, newStatus;

	public GatewayStatusNotification(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus)
	{
		super(gateway);
		setOldStatus(oldStatus);
		setNewStatus(newStatus);
	}

	public GatewayStatuses getOldStatus()
	{
		return this.oldStatus;
	}

	public void setOldStatus(GatewayStatuses oldStatus)
	{
		this.oldStatus = oldStatus;
	}

	public GatewayStatuses getNewStatus()
	{
		return this.newStatus;
	}

	public void setNewStatus(GatewayStatuses newStatus)
	{
		this.newStatus = newStatus;
	}
}
