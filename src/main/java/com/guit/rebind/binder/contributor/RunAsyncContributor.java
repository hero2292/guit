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
package com.guit.rebind.binder.contributor;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.user.rebind.StringSourceWriter;

import com.guit.client.async.AbstractRunAsyncCallback;
import com.guit.rebind.binder.BinderContext;
import com.guit.rebind.binder.BinderContributor;

public class RunAsyncContributor implements BinderContributor {

  @Override
  public void contribute(BinderContext binderContext, TreeLogger logger, GeneratorContext context)
      throws UnableToCompleteException {

    StringSourceWriter before = new StringSourceWriter();
    before
        .println("GWT.runAsync(new " + AbstractRunAsyncCallback.class.getCanonicalName() + "() {");
    before.indent();
    before.println("@Override");
    before.println("public void success() {");
    before.indent();

    StringSourceWriter after = new StringSourceWriter();
    after.outdent();
    after.println("}");
    after.outdent();
    after.println("});");

    binderContext.addWrapper(before, after);
  }

  @Override
  public String name() {
    return "RunAsync";
  }
}
