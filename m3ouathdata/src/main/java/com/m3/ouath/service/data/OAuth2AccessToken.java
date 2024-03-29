package com.m3.ouath.service.data;

import java.io.Serializable;

import com.m3.oauth.common.AccessToken;

public class OAuth2AccessToken implements AccessToken, Serializable { // no need for comparable
    private static final long serialVersionUID = 1L;

    private final String _tokenid;
    private final String _clientid;
    private final String _tokenvalue;
    private final Long _tokenexpiry;

    public OAuth2AccessToken(String tokid, String tokvalue, String clientvalue, Long expirymillis) {
        _tokenid = tokid;
        _tokenvalue = tokvalue;
        _clientid = clientvalue;
        _tokenexpiry = expirymillis;
    }

    public final String tokenId() { return _tokenid; }
    public final String clientId() { return _clientid; }
    public final String tokenValue() { return _tokenvalue; }
    public final Long tokenExpiresAfterMs() { return _tokenexpiry; }

    public void setScopes(String scopes) {
		// TODO Auto-generated method stub
		
    }
}
