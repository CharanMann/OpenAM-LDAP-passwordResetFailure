/*
 * Copyright © 2017 ForgeRock, AS.
 *
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Portions Copyrighted 2017 Charan Mann
 *
 * openam-ldap-passwordResetFailure: Created by Charan Mann on 10/26/17 , 1:44 PM.
 */

/**
 * Plugin which installs the {@link com.sun.identity.authentication.modules.ldap.LDAPExt} implementation and the amAuthLDAPExt.xml service schema when AM is started
 * for the first time after adding this plugin.
 */
package com.sun.identity.authentication.modules.ldap;

import com.google.inject.Inject;
import org.forgerock.openam.plugins.AmPlugin;
import org.forgerock.openam.plugins.PluginException;
import org.forgerock.openam.plugins.PluginTools;

public class LDAPExtPlugin implements AmPlugin {
    private PluginTools pluginTools;

    @Inject
    public LDAPExtPlugin(PluginTools pluginTools) {
        this.pluginTools = pluginTools;
    }

    @Override
    public String getPluginVersion() {
        return "1.0.0";
    }

    @Override
    public void onInstall() throws PluginException {
        pluginTools.addAuthModule(LDAPExt.class,
                getClass().getClassLoader().getResourceAsStream("amAuthLDAPExt.xml"));
    }
}
