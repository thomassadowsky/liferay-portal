/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portalweb.socialofficehome.microblogs.microblogsentry.sofrdeleterepostmicroblogscontent;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class SOFr_ViewDeleteRepostMicroblogsContentTest extends BaseTestCase {
	public void testSOFr_ViewDeleteRepostMicroblogsContent()
		throws Exception {
		selenium.open("/user/socialofficefriendsn/home");

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("//div/div/div/div[1]/ul/li[1]/a")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("//div/div/div/div[1]/ul/li[1]/a",
			RuntimeVariables.replace("Home"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("What's happening?"),
			selenium.getText("//div[1]/h1/span"));
		assertTrue(selenium.isElementPresent("//textarea"));
		assertEquals(RuntimeVariables.replace("You have no microblogs entry."),
			selenium.getText("xPath=(//div[@class='portlet-msg-info'])[1]"));
		selenium.clickAt("//div/div/div/div[1]/ul/li[3]/a",
			RuntimeVariables.replace("Microblogs"));
		selenium.waitForPageToLoad("30000");
		assertEquals(RuntimeVariables.replace("Microblogs"),
			selenium.getText("//div[2]/div/div/div/section/header/h1/span[2]"));
		assertTrue(selenium.isVisible("//div[@class='entry-bubble ']"));
		assertTrue(selenium.isVisible("//div/span/a/img"));
		assertEquals(RuntimeVariables.replace("Joe Bloggs (joebloggs)"),
			selenium.getText("//div[@class='user-name']"));
		assertEquals(RuntimeVariables.replace("Microblogs Content"),
			selenium.getText("//div[@class='content']"));
		assertFalse(selenium.isTextPresent(
				"socialofficefriendfn socialofficefriendmn socialofficefriendln (socialofficefriendsn) Reposted From Joe Bloggs"));
	}
}