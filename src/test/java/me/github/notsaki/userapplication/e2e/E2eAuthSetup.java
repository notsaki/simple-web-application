package me.github.notsaki.userapplication.e2e;

import me.github.notsaki.userapplication.infrastructure.data.response.JwtTokenEntity;

public abstract class E2eAuthSetup extends E2eSetup {

	protected JwtTokenEntity jwt;
	protected String invalidToken = "eyJ01XAiOiJKV1QiLCJhbGciOiJIUzI2NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6Ii9sb2dpbiIsImV4cCI6MTY1NjM1NzMwMX0.ywhkbp_2BQM-kch1f5yNDb5-PF3DFKT7lXdPtlecJ8g";
	protected String expiredToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6Ii9sb2dpbiIsImV4cCI6MTY1NjM1NzMwMX0.ywhkbp_2BQM-kch1f5yNDb0-PF3DFKT7lXdPtlEcJ8g";
}
