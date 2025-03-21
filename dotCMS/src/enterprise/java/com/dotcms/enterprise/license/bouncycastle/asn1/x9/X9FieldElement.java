/*
*
* Copyright (c) 2025 dotCMS LLC
* Use of this software is governed by the Business Source License included
* in the LICENSE file found at in the root directory of software.
* SPDX-License-Identifier: BUSL-1.1
*
*/

package com.dotcms.enterprise.license.bouncycastle.asn1.x9;

import java.math.BigInteger;

import com.dotcms.enterprise.license.bouncycastle.asn1.ASN1Encodable;
import com.dotcms.enterprise.license.bouncycastle.asn1.ASN1OctetString;
import com.dotcms.enterprise.license.bouncycastle.asn1.DERObject;
import com.dotcms.enterprise.license.bouncycastle.asn1.DEROctetString;
import com.dotcms.enterprise.license.bouncycastle.math.ec.ECFieldElement;

/**
 * class for processing an FieldElement as a DER object.
 */
public class X9FieldElement
    extends ASN1Encodable
{
    protected ECFieldElement  f;
    
    private static X9IntegerConverter converter = new X9IntegerConverter();

    public X9FieldElement(ECFieldElement f)
    {
        this.f = f;
    }
    
    public X9FieldElement(BigInteger p, ASN1OctetString s)
    {
        this(new ECFieldElement.Fp(p, new BigInteger(1, s.getOctets())));
    }
    
    public X9FieldElement(int m, int k1, int k2, int k3, ASN1OctetString s)
    {
        this(new ECFieldElement.F2m(m, k1, k2, k3, new BigInteger(1, s.getOctets())));
    }
    
    public ECFieldElement getValue()
    {
        return f;
    }
    
    /**
     * Produce an object suitable for an ASN1OutputStream.
     * <pre>
     *  FieldElement ::= OCTET STRING
     * </pre>
     * <p>
     * <ol>
     * <li> if <i>q</i> is an odd prime then the field element is
     * processed as an Integer and converted to an octet string
     * according to x 9.62 4.3.1.</li>
     * <li> if <i>q</i> is 2<sup>m</sup> then the bit string
     * contained in the field element is converted into an octet
     * string with the same ordering padded at the front if necessary.
     * </li>
     * </ol>
     */
    public DERObject toASN1Object()
    {
        int byteCount = converter.getByteLength(f);
        byte[] paddedBigInteger = converter.integerToBytes(f.toBigInteger(), byteCount);

        return new DEROctetString(paddedBigInteger);
    }
}
