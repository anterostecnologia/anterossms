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
import br.com.anteros.sms.Service;
import br.com.anteros.sms.GatewayException;
import br.com.anteros.sms.TimeoutException;
import br.com.anteros.sms.modem.ModemGateway;

/**
 * AT Handler for Huawei modems.
 */
public class ATHandler_Huawei extends ATHandler
{
	public ATHandler_Huawei(ModemGateway myGateway)
	{
		super(myGateway);
	}

	@Override
	public void init() throws TimeoutException, GatewayException, IOException, InterruptedException
	{
		getModemDriver().write("AT+CFUN=1\r");
		Thread.sleep(Service.getInstance().getSettings().AT_WAIT_AFTER_RESET);
		getModemDriver().clearBuffer();
		getModemDriver().write("AT^CURC=0\r");
		getModemDriver().getResponse();
		getModemDriver().write("AT+CLIP=1\r");
		getModemDriver().getResponse();
	}

	@Override
	public boolean setIndications() throws TimeoutException, GatewayException, IOException, InterruptedException
	{
		// Force CNMI Emulator activation as HUAWEIs send indications on another port...
		return false;
	}
}
