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

import br.com.anteros.sms.ajwcc.pduUtils.gsm3040.PduUtils;
import br.com.anteros.sms.modem.ModemGateway;

/**
 * AT Handler for Huawei E160 modems.
 */
public class ATHandler_Huawei_E160 extends ATHandler_Huawei
{
	public ATHandler_Huawei_E160(ModemGateway myGateway)
	{
		super(myGateway);
		setStorageLocations("SMME");
	}

	/**
	 * Hauwei E160 requires a DCS identifier with the CUSD command. Only a value
	 * of 15 (7-bit alphabet, language unspecified) seems to work with these
	 * modems. Also requires that the command be PDU encoded.
	 */
	@Override
	protected String formatUSSDCommand(String presentation, String ussdCommand, String dcs)
	{
		byte[] textSeptets = PduUtils.stringToUnencodedSeptets(ussdCommand);
		byte[] alphaNumBytes = PduUtils.unencodedSeptetsToEncodedSeptets(textSeptets);
		String ussdCommandEncoded = PduUtils.bytesToPdu(alphaNumBytes);
		return super.formatUSSDCommand(presentation, ussdCommandEncoded, "15");
	}

	/**
	 * Also need to pdu decode USSD reponses for this modem
	 */
	@Override
	public String formatUSSDResponse(String ussdResponse)
	{
		byte[] responseEncodedSeptets = PduUtils.pduToBytes(ussdResponse);
		byte[] responseUnencodedSeptets = PduUtils.encodedSeptetsToUnencodedSeptets(responseEncodedSeptets);
		return PduUtils.unencodedSeptetsToString(responseUnencodedSeptets);
	}
}
