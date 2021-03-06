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
 * AT Handler for Wavecom modems. Tested with Fastrack Xtend FXT009
 * 
 * @author Marcos Truchado
 */
public class ATHandler_Wavecom_FXT009 extends ATHandler
{
	public ATHandler_Wavecom_FXT009(ModemGateway myGateway)
	{
		super(myGateway);
		setStorageLocations("SMSR");
	}

	@Override
	public void done() throws TimeoutException, GatewayException, IOException, InterruptedException
	{
		getModemDriver().write("AT+WATH=31\r");
		Thread.sleep(Service.getInstance().getSettings().AT_WAIT);
	}
}
