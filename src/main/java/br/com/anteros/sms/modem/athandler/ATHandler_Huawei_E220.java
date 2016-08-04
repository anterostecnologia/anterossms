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
import br.com.anteros.sms.TimeoutException;
import br.com.anteros.sms.modem.ModemGateway;

/**
 * AT Handler for Huawei E220 modems.
 */
public class ATHandler_Huawei_E220 extends ATHandler_Huawei
{
	public ATHandler_Huawei_E220(ModemGateway myGateway)
	{
		super(myGateway);
	}

	@Override
	public void init() throws TimeoutException, GatewayException, IOException, InterruptedException
	{
		super.init();
		getModemDriver().write("AT+CSCA?\r");
		String smsc = getModemDriver().getResponse();
		getModemDriver().write("AT+CSCA=" + smsc + "\r");
		getModemDriver().getResponse();
	}
}
