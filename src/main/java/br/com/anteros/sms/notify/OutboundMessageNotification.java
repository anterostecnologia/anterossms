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
import br.com.anteros.sms.OutboundMessage;

public class OutboundMessageNotification extends Notification
{
	private OutboundMessage msg;

	public OutboundMessageNotification(AGateway gateway, OutboundMessage msg)
	{
		super(gateway);
		setMsg(msg);
	}

	public OutboundMessage getMsg()
	{
		return this.msg;
	}

	public void setMsg(OutboundMessage msg)
	{
		this.msg = msg;
	}
}
