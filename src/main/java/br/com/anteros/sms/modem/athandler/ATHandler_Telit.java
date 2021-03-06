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
import br.com.anteros.sms.helper.Logger;
import br.com.anteros.sms.modem.ModemGateway;

public class ATHandler_Telit extends ATHandler
{
	public ATHandler_Telit(ModemGateway myGateway)
	{
		super(myGateway);
		Logger.getInstance().logInfo("ATHandler_Telit constructor.", null, getGateway().getGatewayId());
		Service.getInstance().getSettings().DISABLE_CMTI = true;
		Service.getInstance().getSettings().SERIAL_POLLING = true;
	}

	@Override
	public boolean setIndications() throws TimeoutException, GatewayException, IOException, InterruptedException
	{
		try
		{
			//disable New Message Indications To Terminal Equipment
			getModemDriver().write("AT+CNMI=0,0,0,0,0\r");
			getModemDriver().getResponse();
		}
		catch (Exception e)
		{
			Logger.getInstance().logWarn("Error disabling messageindication", null, getGateway().getGatewayId());
		}
		Logger.getInstance().logInfo("ATHandler_Telit: CNMI detectiondisabled.", null, getGateway().getGatewayId());
		return false;
	}
}
