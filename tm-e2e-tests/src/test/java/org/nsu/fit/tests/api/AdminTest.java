package org.nsu.fit.tests.api;

import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;

public abstract class AdminTest {
    private final AccountTokenPojo adminToken = new RestClient().authenticate("admin", "setup");

    protected AccountTokenPojo getAdminToken(){
        return adminToken;
    }
}
