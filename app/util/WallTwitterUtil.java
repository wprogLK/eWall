package util;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class WallTwitterUtil 
{
	public static Twitter setupTwitter(String oAuthAccessToken, String oAuthAccessTokenSecret)
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("rBGWjVZhbuonK1nEB9vZw");
		cb.setOAuthConsumerSecret("ub5fl7Sf0WzgRkfW5xgO4GmG0XtR4kQZSKGwRjoRweQ");
		cb.setOAuthAccessToken("220058348-KPaT8hkUK2AEJu7uzhQQBGzSPJ8Avk9eN6LoW1Hd");//oAuthAccessToken);
		cb .setOAuthAccessTokenSecret("O2tizT5EcXUH5zId5J8SPNPpqwKcYp7Jufhuy4puLoQ");//oAuthAccessTokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		return tf.getInstance();
	}
}
