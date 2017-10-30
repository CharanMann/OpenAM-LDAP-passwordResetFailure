# OpenAM-LDAP-passwordResetFailure

* AM LDAP Extensions - AM Custom Auth Module for returning specific error message for password reset scenario in ROPC grant <br />
* By default AM returns "Internal Server Error", related RFE: *[OPENAM-11517](https://bugster.forgerock.org/jira/browse/OPENAM-11517)*. 
 
Disclaimer of Liability :
=========================
This is an unsupported code made available by ForgeRock for community development subject to the license for specific
language governing permission and limitations under the license.

The code is provided on an "as is" basis, without warranty of any kind, to the fullest extent permitted by law. 

ForgeRock does not warrant or guarantee the individual success developers may have in implementing the code on their 
development platforms or in production configurations.

ForgeRock does not warrant, guarantee or make any representations regarding the use, results of use, accuracy, timeliness 
or completeness of any data or information relating to the alpha release of unsupported code. ForgeRock disclaims all 
warranties, expressed or implied, and in particular, disclaims all warranties of merchantability, and warranties related 
to the code, or any service or software related thereto.

ForgeRock shall not be liable for any direct, indirect or consequential damages or costs of any type arising out of any 
action taken by you or others related to the code.
    
Pre-requisites :
================
* Versions used for this project: AM 5.5.1, DJ 5 
1. AM has been installed and configured.
2. Maven has been installed and configured.

AM Configuration:
=====================
1. Build the custom auth module by using maven. 
2. Copy the custom authentication module openam-ldap-passwordResetFailure-1.0.0.jar.jar file to WEB-INF/lib/ where AM is deployed. Refer instructions: *[Building and Installing Custom Authentication Modules](https://backstage.forgerock.com/docs/am/5.5/authentication-guide/#build-config-sample-auth-module)*
3. Restart AM. 
4. Configure LDAPExt module with required parameters. Refer instructions: *[Configuring and Testing Custom Authentication Modules](https://backstage.forgerock.com/docs/am/5.5/authentication-guide/#configuring-testing-sample-auth-module)*
5. Create a chain(ldapExt) with (LDAPExt:Required).
6. Enable OAuth service in AM, Enable "auth module messages for Password Credentials Grant" under OAuth2 Provider advanced tab.
7. Enable "force-change-on-reset" feature in DJ as explained here: *[Forcing users to reset password on next login](http://tumy-tech.com/2015/08/23/openam-forcing-user-to-reset-password-on-next-login-2/)*
  
Testing:
======== 
* ROPC testing for ldapExt:
1. Change user's password via AM admin console. 
2. Test ROPC grant flow for ldapExt chain
```
curl -X POST \
  http://openam551.example.com:8989/openam/oauth2/employees/access_token \
  -H 'authorization: BASIC bXlPQXV0aDJDbGllbnQ6cGFzc3dvcmQ=' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'grant_type=password&username=testuser1&password=password&scope=openid%20profile%20email&auth_chain=ldapExt'

{
    "error": "invalid_grant",
    "error_description": "Password must be reset. Please navigate to <Password Reset URL>."
}
```


* * *

Copyright © 2017 ForgeRock, AS.

The contents of this file are subject to the terms of the Common Development and
Distribution License (the License). You may not use this file except in compliance with the
License.

You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
specific language governing permission and limitations under the License.

When distributing Covered Software, include this CDDL Header Notice in each file and include
the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
Header, with the fields enclosed by brackets [] replaced by your own identifying
information: "Portions copyright [year] [name of copyright owner]".

Portions Copyrighted 2017 Charan Mann