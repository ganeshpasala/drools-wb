/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.dtablexls.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import org.drools.workbench.screens.dtablexls.client.resources.css.StylesCss;
import org.drools.workbench.screens.dtablexls.client.resources.images.DecisionTableXLSImageResources;

public interface DecisionTableXLSResources
        extends
        ClientBundle {

    public static final DecisionTableXLSResources INSTANCE = GWT.create( DecisionTableXLSResources.class );

    @Source("css/Styles.css")
    StylesCss CSS();

    DecisionTableXLSImageResources images();

}
