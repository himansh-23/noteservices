package com.user.notesapi.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.user.notesapi.exception.NoteException;


public class TokenVerify {

	private static String TOKEN_SECRET="gh2we43jue";
	public static long tokenVerifing(String token) throws NoteException
	{
		long userid=0;
		try {
			Verification verification=JWT.require(Algorithm.HMAC256(TokenVerify.TOKEN_SECRET));
			JWTVerifier jwtverifier=verification.build();
			DecodedJWT decodedjwt=jwtverifier.verify(token);
			Claim claim=decodedjwt.getClaim("ID");
			userid=claim.asLong();	
			
		}
		catch(Exception exception)
		{
			throw new NoteException(123,"Invalid Token");
		}
			return userid;
	}
	
	
}
