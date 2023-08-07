package com.sunx.mysprmdbapp1.reqdto;

public class JwtAuthResponseDto {
	private String accessToken;
    private String tokenType = "Bearer";

    public String getAccessToken() {
		return this.accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return this.tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}