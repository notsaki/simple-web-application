package me.github.notsaki.userapplication.e2e;

import me.github.notsaki.userapplication.domain.entity.response.JwtToken;
import org.junit.Before;

public abstract class E2eAuthBaseClass extends E2eBaseClass {

	protected JwtToken jwt;

	@Before
	public void before_login() throws Exception {
		this.jwt = this.login();
	}
}
