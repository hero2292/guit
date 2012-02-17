/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.guit.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.rpc.client.impl.ClientWriterFactory;
import com.google.gwt.rpc.client.impl.CommandToStringWriter;
import com.google.gwt.rpc.client.impl.RemoteException;
import com.google.gwt.rpc.client.impl.RpcCallbackAdapter;
import com.google.gwt.rpc.client.impl.TypeOverrides;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.user.client.rpc.impl.RpcStatsContext;

public abstract class XsRpcServiceProxy extends XsRemoteServiceProxy {

  private final TypeOverrides typeOverrides;

  protected XsRpcServiceProxy(String moduleBaseURL, String remoteServiceRelativePath,
      TypeOverrides typeOverrides) {
    super(moduleBaseURL, remoteServiceRelativePath, GWT.getPermutationStrongName(), null);
    this.typeOverrides = typeOverrides;
  }

  @Override
  public SerializationStreamReader createStreamReader(String encoded)
      throws SerializationException, RemoteException {
    return ClientWriterFactory.createReader(encoded);
  }

  @Override
  public SerializationStreamWriter createStreamWriter() {
    return new CommandToStringWriter(typeOverrides);
  }

  @Override
  protected <T> RequestCallback doCreateRequestCallback(ResponseReader responseReader,
      String methodName, RpcStatsContext statsContext, AsyncCallback<T> callback) {
    return new RpcCallbackAdapter<T>(this, methodName, statsContext, callback);
  }
}