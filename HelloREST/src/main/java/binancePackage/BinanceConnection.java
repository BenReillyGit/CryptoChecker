package binancePackage;

import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import models.CryptoLayout;

public class BinanceConnection {
	
	public List<CryptoLayout> getPrices() {
		String symbol = getPricesJSON();
		Gson gson = new Gson();
		List<CryptoLayout> cryptos = gson.fromJson(symbol, new TypeToken<ArrayList<CryptoLayout>>(){}.getType());
		return cryptos;
	}
	
	 private String getPricesJSON() {
		HttpGet request = new HttpGet("https://api.binance.com/api/v3/ticker/24hr");
        System.setProperty("https.protocols", "TLSv1.2");
        return executeGetRequest(request);
	}

	private String executeGetRequest(HttpGet request) {
		HttpClient client = HttpClientBuilder.create().build();
		try {
			HttpResponse response = client.execute(request);
			return EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception e) {
			return e.toString();
		}
	}
}
