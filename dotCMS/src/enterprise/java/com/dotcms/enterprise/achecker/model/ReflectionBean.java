/*
*
* Copyright (c) 2025 dotCMS LLC
* Use of this software is governed by the Business Source License included
* in the LICENSE file found at in the root directory of software.
* SPDX-License-Identifier: BUSL-1.1
*
*/

package com.dotcms.enterprise.achecker.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.util.Map;

import com.dotcms.enterprise.achecker.utility.Utility;

public class ReflectionBean {
	
	public ReflectionBean() {}
	
	public ReflectionBean(Map<String, Object> init) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if ( init == null )
			return;
		BeanInfo info = Introspector.getBeanInfo(this.getClass());
		for ( PropertyDescriptor property : info.getPropertyDescriptors()) {
			Object value = init.get(property.getName());
			if ( value != null ) {
				Method write = property.getWriteMethod();
				if ( write != null ) {
					if ( value instanceof Clob ) {
						String content = Utility.getClobContent((Clob) value);
						write.invoke(this, content);					}
					else {
						if ( value instanceof String ) {
							String content = (String) value;
							write.invoke(this, content);
						}
						else {
							write.invoke(this, value);
						}
					}
				}
			}
		}
	}

}
