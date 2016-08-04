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

package br.com.anteros.sms.modem.athandler;

import java.io.IOException;
import br.com.anteros.sms.GatewayException;
import br.com.anteros.sms.Service;
import br.com.anteros.sms.TimeoutException;
import br.com.anteros.sms.modem.ModemGateway;

public class ATHandler_SonyEricsson_GC89 extends ATHandler_SonyEricsson
{
	public ATHandler_SonyEricsson_GC89(ModemGateway myGateway)
	{
		super(myGateway);
	}

	/**
	 * Many SonyEricssons return different responses to CMGF command. Instead of
	 * a plain OK, they return "+CMGF=0\rOK\r", independently of the ECHO
	 * setting. This code bypasses the standard SMSLib checking routines and
	 * performs the check itself.
	 */
	@Override
	public boolean setPduProtocol() throws TimeoutException, GatewayException, IOException, InterruptedException
	{
		getModemDriver().write("AT+CMGF=0\r");
		return (getModemDriver().getResponse().indexOf("OK") >= 0);
	}

	@Override
	public void reset() throws TimeoutException, GatewayException, IOException, InterruptedException
	{
		super.reset();
		getModemDriver().write("AT+CFUN=1\r");
		Thread.sleep(Service.getInstance().getSettings().AT_WAIT_AFTER_RESET);
		getModemDriver().clearBuffer();
	}
}
