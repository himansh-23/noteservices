package com.user.notesapi.util;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Verification;
import com.user.notesapi.exception.NoteException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenVerify {

	private static final String TOKEN_SECRET="gh2we43jue";

	public static long tokenVerifing(String token) throws NoteException
	{
		try {
			Verification verification = JWT.require(Algorithm.HMAC256(TokenVerify.TOKEN_SECRET));
			return verification.build().verify(token).getClaim("ID").asLong();
			
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
			throw new NoteException(123, "Invalid Token");
		}
	}
}
