package com.kkrishna.nflportal.NFLPortal;

import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.YahooApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class OauthRequestProvider {

	private static final String PROTECTED_RESOURCE_URL = "http://fantasysports.yahooapis.com/fantasy/v2/game/314/players;start=50";

	public static void main(String[] args) throws HttpException, IOException {
		
		OAuthService service = new ServiceBuilder()
				.provider(YahooApi.class)
				.apiKey("dj0yJmk9Vlg5a3dHYWRHSE5QJmQ9WVdrOWIyaDZSREJsTnpJbWNHbzlNVGsxTlRVek1UUTJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD0zYg--")
				.apiSecret("2b413599c7e4fe29e81943854b048d0ee6a9d60d").build();
		Scanner in = new Scanner(System.in);

		System.out.println("=== Yahoo's OAuth Workflow ===");
		System.out.println();

		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		Token requestToken = service.getRequestToken();
		System.out.println("Got the Request Token!");
		System.out.println();

		System.out.println("Now go and authorize Scribe here:");
		System.out.println(service.getAuthorizationUrl(requestToken));
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		System.out.println();

		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		Token accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: "
				+ accessToken + " )");
		System.out.println();

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET,
				PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getCode());
		System.out.println(response.getBody());

		System.out.println();
		System.out
				.println("Thats it man! Go and build something awesome with Scribe! :)");
		//TestYahooRest.testYahooAPI();

	}

}
