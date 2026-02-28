package com.buystuff.buystuff_api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class RazorpayConfig {
	@Bean
	public RazorpayClient razorpayClient(
		@Value("${razorpay.api.key-id}") String keyId,
		@Value("${razorpay.api.secret}") String secret
	) throws RazorpayException {
		return new RazorpayClient(keyId, secret);
	}
}
