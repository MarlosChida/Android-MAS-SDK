/*
 * Copyright (c) 2016 CA. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 *
 */
package com.ca.mas.storage;

import com.ca.mas.core.security.ScreenLockEncryptionProvider;
import com.ca.mas.foundation.MASConstants;

import static junit.framework.Assert.assertEquals;

public class MASSecureStorageUserScreenLockTest extends MASSecureLocalStorageUserTest {

    @Override
    int getMode() {
        return MASConstants.MAS_USER;
    }

    @Override
    MASStorage getMASStorage() {
        return new MASSecureLocalStorage(new ScreenLockEncryptionProvider(getContext()));
    }

}
