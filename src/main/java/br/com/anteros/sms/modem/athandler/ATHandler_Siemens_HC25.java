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

/**
 * A custom AT handler for the Siemens/Cinterion HC25 circumventing the problem
 * of the modem losing its SMSC address after issuing 'ATZ' or 'AT&F' by
 * reloading the address from the SIM card.
 */
public class ATHandler_Siemens_HC25 extends ATHandler
{
	/**
	 * Construct a HC25 handler
	 * 
	 * @param myGateway
	 *            the gateway to use
	 */
	public ATHandler_Siemens_HC25(ModemGateway myGateway)
	{
		super(myGateway);
	}

	@Override
	public void sync() throws IOException, InterruptedException
	{
		getModemDriver().write("ATZ\r");
		Thread.sleep(Service.getInstance().getSettings().AT_WAIT);
		// AT+CSCA? will reload the SMSC from SIM
		getModemDriver().write("AT+CSCA?\r");
		Thread.sleep(Service.getInstance().getSettings().AT_WAIT);
	}

	@Override
	public void reset() throws TimeoutException, GatewayException, IOException, InterruptedException
	{
		getModemDriver().write("\u001b");
		Thread.sleep(Service.getInstance().getSettings().AT_WAIT);
		getModemDriver().write("+++");
		Thread.sleep(Service.getInstance().getSettings().AT_WAIT);
		getModemDriver().write("ATZ");
		Thread.sleep(Service.getInstance().getSettings().AT_WAIT);
		getModemDriver().write("AT+CSCA?\r");
		Thread.sleep(Service.getInstance().getSettings().AT_WAIT);
		getModemDriver().clearBuffer();
	}
}
