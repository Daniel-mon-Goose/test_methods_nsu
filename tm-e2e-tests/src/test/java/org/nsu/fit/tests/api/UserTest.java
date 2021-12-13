package org.nsu.fit.tests.api;

import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;

public abstract class UserTest {
    private final AccountTokenPojo userToken = new RestClient().authenticate("user@us.er", "defaultpass");

    protected AccountTokenPojo getUserToken(){
        return userToken;
    }
}
