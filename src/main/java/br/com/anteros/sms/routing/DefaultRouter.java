/**
 * 
 */

package br.com.anteros.sms.routing;

import java.util.Collection;
import br.com.anteros.sms.AGateway;
import br.com.anteros.sms.OutboundMessage;

/**
 * Default Router implementation which actually doesn't perform any custom
 * routing, instead relies on basic routing of ARouter.
 * 
 * @author Bassam Al-Sarori
 */
public class DefaultRouter extends ARouter
{
	/* (non-Javadoc)
	 * @see br.com.anteros.sms.routing.ARouter#route(br.com.anteros.sms.OutboundMessage, java.util.Collection)
	 */
	@Override
	public Collection<AGateway> customRoute(OutboundMessage msg, Collection<AGateway> gateways)
	{
		return gateways;
	}
}
