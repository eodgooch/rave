/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.rave.provider.opensocial.web.renderer;

import org.apache.rave.exception.NotSupportedException;
import org.apache.rave.portal.model.RegionWidget;
import org.apache.rave.portal.web.renderer.RegionWidgetRenderer;
import org.apache.rave.provider.opensocial.Constants;
import org.apache.rave.provider.opensocial.service.OpenSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Creates the appropriate markup to represent a W3C widget
 * <p/>
 * //TODO: Create infrastructure for rendering inline gadgets via Caja
 */
@Component
public class OpenSocialWidgetRenderer implements RegionWidgetRenderer {
    private OpenSocialService openSocialService;

    @Autowired
    public OpenSocialWidgetRenderer(OpenSocialService openSocialService) {
        this.openSocialService = openSocialService;
    }

    //Note the widgets.push() call.  This defines the widget objects, which are
    //added to the widgets[] array in home.jsp.   
    private static final String IFRAME_MARKUP =
            "widgets.push({type: '%1$s'," +
            " regionWidgetId: %2$s," +
            " widgetUrl: '%3$s', " +
            " metadata: %4$s," +
            " userPrefs: {}});";

    @Override
    public String getSupportedContext() {
        return Constants.WIDGET_TYPE;
    }

    /**
     * Renders a {@link org.apache.rave.portal.model.RegionWidget} as HTML markup
     *
     * @param item RegionWidget to render
     * @return valid HTML markup
     */
    @Override
    public String render(RegionWidget item) {
        String type = item.getWidget().getType();
        if (!Constants.WIDGET_TYPE.equals(type)) {
            throw new NotSupportedException("Invalid widget type passed to renderer: " + type);
        }

        return String.format(IFRAME_MARKUP, Constants.WIDGET_TYPE, item.getId(), item.getWidget().getUrl(),
                openSocialService.getGadgetMetadata(item.getWidget().getUrl()));
    }
}