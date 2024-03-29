package com.m3.oauth.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.m3.common.core.HttpHelper;
import com.m3.common.oauth2.api.OAuth2;

public interface AuthorizationService {
    AuthorizationResponse handleAuthorizationCode(String clientid, String redirecturi, String state, String challenge, String algorithm, String[] scopes);
    TokenResponse handlePassword(String clientid, String username, String pwmd5);
    TokenResponse handleClientCredential(String clientid, String clientsecret, String realm, String redirecturi, String audience, Set<String> scopes, String requestpath);
    TokenResponse handleToken(OAuth2.GrantType granttype, String clientid, String redirecturi, String clientsecret, String challenge, String code);

    public class AuthorizationResponse extends BaseResponse  {
        private static final String CODE = "code";

        private String _code = null;
        private String _state = null;

        protected AuthorizationResponse() {
            super(SuccessResponseType.REDIRECT);
        }

        public void setCode(String value) { _code = value; }
        public void setState(String value) { _state = value; }

        public String successAsUrlEncode(String url) {
            Map<String, String> params = new HashMap<String, String>();
            params.put(CODE, _code);
            params.put(OAuth2.STATE, _state);
            return HttpHelper.buildUrlEncodedParameterUrl(url, params);
        }

        public static final AuthorizationResponse errorResponse() {
            return new AuthorizationResponse();
        }

        public static final AuthorizationResponse errorResponse(M3OAuthError err) {
            AuthorizationResponse resp = new AuthorizationResponse();
            resp.addError(err);
            return resp;
        }
    }

    public class TokenResponse extends BaseResponse {
        private static final String TOKEN = "access_token";
        private static final String EXPIRES = "expires_in";

        private String _token = null;
        private long _expires = -1L;

        public TokenResponse() {
            super(SuccessResponseType.OK);
        }

        public void setToken(String value) { _token = value; }
        public void setExpires(long value) { _expires = value; }

    	public String successAsJson() {
            StringBuilder sb = new StringBuilder("{ ");
            sb.append("\"");
            sb.append(TOKEN);
            sb.append("\": \"");
            sb.append(_token);
            sb.append("\", \"");
            sb.append(EXPIRES);
            sb.append("\": ");
            sb.append(_expires);
            sb.append(" }");
            return sb.toString();
    	}

        public static final TokenResponse errorResponse() {
            return new TokenResponse();
        }

        public static final TokenResponse errorResponse(M3OAuthError err) {
            TokenResponse resp = new TokenResponse();
            resp.addError(err);
            return resp;
        }
    }
}
