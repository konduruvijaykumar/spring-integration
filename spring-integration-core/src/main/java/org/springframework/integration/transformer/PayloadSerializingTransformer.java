/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.transformer;

import org.springframework.commons.serializer.Serializer;
import org.springframework.commons.serializer.SerializingConverter;

/**
 * Transformer that serializes the inbound payload into a byte array by delegating to a 
 * Converter&lt;Object, byte[]&gt;. Default delegate is a {@link SerializingConverter} using
 * Java serialization.
 * 
 * <p>The payload instance must be Serializable if the default converter is used.
 * 
 * @author Mark Fisher
 * @author Gary Russell
 * @since 1.0.1
 */
public class PayloadSerializingTransformer extends PayloadTypeConvertingTransformer<Object, byte[]> {

	public void setSerializer(Serializer<Object> serializer) {
		this.setConverter(new SerializingConverter(serializer));
	}

	@Override
	protected byte[] transformPayload(Object payload) throws Exception {
		if (this.converter == null) {
			this.setConverter(new SerializingConverter());
		}
		return this.converter.convert(payload);
	}

}
