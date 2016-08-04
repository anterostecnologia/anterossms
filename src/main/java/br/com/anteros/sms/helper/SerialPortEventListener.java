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

package br.com.anteros.sms.helper;

import java.util.EventListener;

/**
 * Propagates serial port events.
 * <p>
 * <b>Please note: </b>This is a wrapper around
 * <code>javax.comm.SerialPortEventListener</code> (and so
 * <code>gnu.io.SerialPortEventListener</code>). The API definition is taken
 * from Sun. So honor them!
 * </p>
 * 
 * @author Jagane Sundar
 */
public interface SerialPortEventListener extends EventListener
{
	/**
	 * Propagates a <code>SerialPortEvent</code> event.
	 * 
	 * @param ev
	 */
	public void serialEvent(SerialPortEvent ev);
}
