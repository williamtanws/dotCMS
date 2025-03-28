/*
*
* Copyright (c) 2025 dotCMS LLC
* Use of this software is governed by the Business Source License included
* in the LICENSE file found at in the root directory of software.
* SPDX-License-Identifier: BUSL-1.1
*
*/

package com.dotcms.enterprise.license.bouncycastle.crypto;

/**
 * the foundation class for the hard exceptions thrown by the crypto packages.
 */
public class CryptoException 
    extends Exception
{
    /**
     * base constructor.
     */
    public CryptoException()
    {
    }

    /**
     * create a CryptoException with the given message.
     *
     * @param message the message to be carried with the exception.
     */
    public CryptoException(
        String  message)
    {
        super(message);
    }
}
