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

package br.com.anteros.sms.queues;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import br.com.anteros.sms.OutboundMessage;

class DelayedOutboundMessage implements Delayed
{
	OutboundMessage msg;

	Date at;

	public DelayedOutboundMessage(OutboundMessage msg, Date at)
	{
		setMsg(msg);
		setAt(at);
	}

	public int compareTo(java.util.concurrent.Delayed object)
	{
		if (getAt().getTime() < ((DelayedOutboundMessage) object).getAt().getTime()) return -1;
		if (getAt().getTime() > ((DelayedOutboundMessage) object).getAt().getTime()) return 1;
		return 0;
	}

	public long getDelay(TimeUnit unit)
	{
		long n = getAt().getTime() - System.currentTimeMillis();
		return unit.convert(n, TimeUnit.MILLISECONDS);
	}

	public OutboundMessage getMsg()
	{
		return this.msg;
	}

	public void setMsg(OutboundMessage msg)
	{
		this.msg = msg;
	}

	public Date getAt()
	{
		return this.at;
	}

	public void setAt(Date at)
	{
		this.at = at;
	}

	@Override
	public String toString()
	{
		return "Scheduled: " + getAt();
	}
}
