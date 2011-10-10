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

package com.liferay.portalweb.portlet.bookmarks.folder.addfoldernamenull;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class AddFolderNameNullTest extends BaseTestCase {
	public void testAddFolderNameNull() throws Exception {
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isElementPresent("link=Bookmarks Test Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Bookmarks Test Page",
			RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		selenium.clickAt("//div[2]/ul/li[2]/a", RuntimeVariables.replace(""));
		selenium.waitForPageToLoad("30000");
		selenium.type("_28_name", RuntimeVariables.replace(""));
		selenium.type("_28_description",
			RuntimeVariables.replace("This is a null name folder."));
		assertFalse(selenium.isTextPresent("This field is required."));
		selenium.clickAt("//input[@value='Save']", RuntimeVariables.replace(""));
		assertEquals(RuntimeVariables.replace("This field is required."),
			selenium.getText(
				"//div[@class='aui-form-validator-message required']"));
	}
}