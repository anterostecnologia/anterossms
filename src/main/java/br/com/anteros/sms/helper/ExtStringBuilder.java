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

/**
 * Buffer around StringBuilder for some convinience methods.
 * 
 * @author Sebastian Just
 */
public class ExtStringBuilder
{
	final StringBuilder sb;

	public ExtStringBuilder()
	{
		this.sb = new StringBuilder();
	}

	public ExtStringBuilder(String str)
	{
		this.sb = new StringBuilder(str);
	}

	public ExtStringBuilder(StringBuilder mySb)
	{
		this.sb = mySb;
	}

	public void replaceAll(String search, int replace)
	{
		replaceAll(search, String.valueOf(replace));
	}

	public void replaceAll(String search, String replace)
	{
		int length = search.length();
		int start = this.sb.indexOf(search);
		while (start != -1)
		{
			this.sb.replace(start, start + length, replace);
			start = this.sb.indexOf(search);
		}
	}

	@Override
	public String toString()
	{
		return this.sb.toString();
	}
}
