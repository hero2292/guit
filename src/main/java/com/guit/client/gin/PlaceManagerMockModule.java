/*
 * Copyright 2010 Gal Dolber.
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
package com.guit.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import com.guit.client.place.PlaceManager;
import com.guit.client.place.PlaceManagerMock;

/**
 * Base module.
 */
public class PlaceManagerMockModule extends AbstractGinModule {

  @Override
  protected void configure() {
    bind(PlaceManager.class).to(PlaceManagerMock.class).in(Singleton.class);
  }
}