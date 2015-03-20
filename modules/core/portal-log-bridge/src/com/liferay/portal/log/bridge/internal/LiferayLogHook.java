/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.log.bridge.internal;

import org.eclipse.equinox.log.ExtendedLogReaderService;
import org.eclipse.osgi.internal.hookregistry.ActivatorHookFactory;
import org.eclipse.osgi.internal.hookregistry.HookConfigurator;
import org.eclipse.osgi.internal.hookregistry.HookRegistry;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * @author Raymond Augé
 * @author Kamesh Sampath
 */
public class LiferayLogHook
	implements ActivatorHookFactory, BundleActivator, HookConfigurator {

	public LiferayLogHook() {
		_portalLogListenerImpl = new PortalLogListenerImpl();
	}

	@Override
	public void addHooks(HookRegistry hookRegistry) {
		hookRegistry.addActivatorHookFactory(this);
	}

	@Override
	public BundleActivator createActivator() {
		return this;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		ServiceReference<ExtendedLogReaderService> extendedLogServiceReference =
			bundleContext.getServiceReference(ExtendedLogReaderService.class);

		if(extendedLogServiceReference!=null){
			ExtendedLogReaderService extendedLogReaderService =
					bundleContext.getService(extendedLogServiceReference);

			extendedLogReaderService.addLogListener(_portalLogListenerImpl);
		}
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		ServiceReference<ExtendedLogReaderService> serviceReference =
			bundleContext.getServiceReference(ExtendedLogReaderService.class);

		ExtendedLogReaderService extendedLogReaderService =
			bundleContext.getService(serviceReference);

		extendedLogReaderService.removeLogListener(_portalLogListenerImpl);
	}

	private PortalLogListenerImpl _portalLogListenerImpl;

}